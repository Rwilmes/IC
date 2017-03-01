package ic;

import java.io.IOException;

import ic.gui.MainFrame;
import ic.metrics.BasicDistancePolicy;
import ic.metrics.ImageComparator;
import ic.metrics.hashes.perceptual.DHash;
import ic.metrics.hashes.perceptual.PHash;
import ic.util.Config;

/**
 * Main class of the ImageComparison project.
 * 
 * @author Rwilmes
 * 
 */
public class ImageComparison {

	public static void main(String[] args) throws IOException {
		// set hashes to be computed
		Config.setHashesToCompute(DHash.class, PHash.class);

		// set distance upper bound
		int distanceUpperBound = 11;
		BasicDistancePolicy policy = new BasicDistancePolicy(distanceUpperBound);
		ImageComparator comparator = new ImageComparator(policy);
		Config.setImageComparator(comparator);

		// start GUI
		MainFrame frame = new MainFrame();
	}
}
