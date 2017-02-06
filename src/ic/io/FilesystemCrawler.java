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

	private String dir;
	private boolean recursive;

	private ArrayList<Component> registeredComponents;

	public FilesystemCrawler(String dir, boolean recursive) {
		this.dir = dir;
		this.recursive = recursive;
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

		Log.log("crawling dir: '" + dir + "'");
		broadcastProgress("crawling filesystem", 0.0);

		ArrayList<File> fileList = new ArrayList<File>();

		IO.collectFiles(baseDir, fileList, recursive);

		int total = fileList.size();

		for (int i = 0; i < fileList.size(); i++) {
			try {
				Image img = new Image(fileList.get(i).getPath());
				broadcastImage(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
			broadcastProgress("processing images", 1.0 * i / total);
		}

		broadcastProgress("done!", 1.0);
		// broadcastProgress("processing images", 0.5);

	}

	/** Broadcasts the image to all registered components. **/
	private void broadcastImage(Image img) {
		for (Component c : this.registeredComponents) {
			if (c instanceof SearchPanel) {
				((SearchPanel) c).addEntry(img);
			}
		}
	}

	private void broadcastProgress(String msg, double progress) {
		for (Component c : this.registeredComponents) {
			if (c instanceof MainFrame) {
				((MainFrame) c).updateProgress(msg, progress);
			}
		}
	}

	/** starts the batchhandler **/
	public void start() {
		if (this.t == null) {
			Random random = new Random();
			this.t = new Thread(this, "FilesystemCrawler-Thread"
					+ random.nextFloat());
			Log.log("Starting FilesystemCrawler in new thread: " + t);
			this.t.start();
		}
	}

}
