package ic.io;

import ic.gui.MainFrame;
import ic.gui.search.SearchPanel;
import ic.image.Image;
import ic.util.IO;
import ic.util.log.Log;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * A FilesystemCrawler crawls a directory and, if specified, all subdirectories
 * for images. It implements runnable and can therefore be started in its own
 * thread.
 * 
 * @author Rwilmes
 * 
 */
public class FilesystemCrawler implements Runnable {

	private Thread t;

	private boolean paused;
	private boolean stopped;

	private String dir;
	private boolean recursive;

	private ArrayList<Component> registeredComponents;

	public FilesystemCrawler(String dir, boolean recursive) {
		this.dir = dir;
		this.recursive = recursive;
		this.paused = false;
		this.stopped = false;
		this.registeredComponents = new ArrayList<Component>();
	}

	/** Registers the given component to retrieve image objects. **/
	public void register(Component c) {
		this.registeredComponents.add(c);
	}

	/** Un-registers the given component. **/
	public void deregister(Component c) {
		this.registeredComponents.remove(c);
	}

	@Override
	public void run() {
		File baseDir = new File(dir);

		if (!baseDir.exists() || !baseDir.isDirectory()) {
			Log.error("base dir '" + dir
					+ "' does not exist or is not a directory!");
			return;
		}

		broadcastProgress("gathering file list", 0.0);

		ArrayList<File> fileList = new ArrayList<File>();
		IO.collectFiles(baseDir, fileList, recursive);

		int total = fileList.size();

		for (int i = 0; i < fileList.size(); i++) {
			try {
				// check if file format is supported
				Image img = new Image(fileList.get(i).getPath());
				broadcastImage(img);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				synchronized (this) {
					while (this.paused) {
						broadcastProgress("paused", 1.0 * i / total);
						wait();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			broadcastProgress("processing images", 1.0 * i / total);

			synchronized (this) {
				if (this.stopped)
					break;
			}
		}

		broadcastProgress("done!", 1.0);
		broadcastDone();
	}

	/** Broadcasts the image to all registered components. **/
	private void broadcastImage(Image img) {
		for (Component c : this.registeredComponents) {
			if (c != null) {
				if (c instanceof SearchPanel) {
					((SearchPanel) c).addEntry(img);
				}
			}
		}
	}

	/** Broadcasts to all components that it is done. **/
	private void broadcastDone() {
		for (Component c : this.registeredComponents) {
			if (c != null) {
				if (c instanceof SearchPanel) {
					((SearchPanel) c).setDone();
				}
			}
		}
	}

	/** Broadcasts the progress to all registered components. **/
	private void broadcastProgress(String msg, double progress) {
		for (Component c : this.registeredComponents) {
			if (c != null) {
				if (c instanceof MainFrame) {
					((MainFrame) c).updateProgress(msg, progress);
				}
				if (c instanceof SearchPanel) {
					((SearchPanel) c).updateProgress(msg, progress);
				}
			}
		}
	}

	/** starts the filecrawler **/
	public void start() {
		if (this.t == null) {
			Random random = new Random();
			this.t = new Thread(this, "FilesystemCrawler-Thread"
					+ random.nextFloat());
			this.t.start();
		}
	}

	/** Pauses the filecrawler **/
	public synchronized void pause() {
		this.paused = true;
	}

	/** Unpauses the filecrawler **/
	public synchronized void unpause() {
		this.paused = false;
		notify();
	}

	/** Stops the filecrawler **/
	public void stop() {
		this.stopped = true;
		this.t = null;
	}

	/** Returns if the filecrawler is paused or not **/
	public boolean isPaused() {
		return this.paused;
	}

}
