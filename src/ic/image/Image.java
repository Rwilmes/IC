package ic.image;

import ic.metrics.name.DHash;
import ic.metrics.name.PHash;
import ic.util.Config;
import ic.util.IO;
import ic.util.Processing;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * An Image object represents an image(-file). It does not contain the actual
 * image file but rather points to it by the specified path.
 * 
 * @author Rwilmes
 * 
 */
public class Image {

	protected String path;

	protected String filename;
	protected String dir;

	protected DHash dHash;
	protected PHash pHash;

	protected BufferedImage thumbnail;

	public Image(String dir, String filename, DHash dHash, PHash pHash) {
		this.dir = dir;
		this.filename = filename;
		this.path = dir + filename;
		this.dHash = dHash;
		this.pHash = pHash;
	}

	public Image(String path) throws IOException {
		this.path = path;
		BufferedImage img = IO.readImage(path);
		this.thumbnail = Processing.resize(img,
				(int) Math.floor(Config.GUI_THUMBNAIL_SIZE.getHeight()),
				(int) Math.floor(Config.GUI_THUMBNAIL_SIZE.getHeight()));

		this.dHash = DHash.computeHash(img);
		this.pHash = PHash.computeHash(img);

		String[] splits = path.split("\\\\");
		if (splits.length > 1) {
			this.filename = splits[splits.length - 1];
			this.dir = "";
			for (int i = 0; i < splits.length - 1; i++) {
				this.dir += splits[i] + "\\";
			}
		} else {
			this.dir = "";
			this.filename = splits[0];
		}
	}

	public Image(BufferedImage img, String path, DHash dHash, PHash pHash) {
		this.path = path;
		this.thumbnail = Processing.resize(img,
				(int) Math.floor(Config.GUI_THUMBNAIL_SIZE.getHeight()),
				(int) Math.floor(Config.GUI_THUMBNAIL_SIZE.getHeight()));
		this.dHash = dHash;
		this.pHash = pHash;

		String[] splits = path.split("\\\\");
		if (splits.length > 1) {
			this.filename = splits[splits.length - 1];
			this.dir = "";
			for (int i = 0; i < splits.length - 1; i++) {
				this.dir += splits[i] + "\\";
			}
		} else {
			this.dir = "";
			this.filename = splits[0];
		}
	}

	public BufferedImage getThumbnail() {
		return thumbnail;
	}

	public String getPath() {
		return path;
	}

	public String getDir() {
		return dir;
	}

	public String getFilename() {
		return filename;
	}

	public DHash getDHash() {
		return dHash;
	}

	public PHash getPHash() {
		return pHash;
	}
}
