package ic.util.runtime;

import ic.util.Timer.TimerType;
import ic.util.log.Log;

/**
 * A class used to perform the static runtime calculations.
 * 
 * @author Rwilmes
 * 
 */
public class Runtimes {

	private static Runtime runtimeIORead = new Runtime("io_read");
	private static Runtime runtimeIOWrite = new Runtime("io_write");
	private static Runtime runtimeProcessingResize = new Runtime("proc_resize");
	private static Runtime runtimeProcessingResizeGrayscale = new Runtime(
			"proc_resize_gray");
	private static Runtime runtimeProcessingRotate = new Runtime("proc_rotate");
	private static Runtime runtimeProcessingColoring = new Runtime(
			"proc_coloring");
	private static Runtime runtimeUnknown = new Runtime("unknown");

	// add all runtimes to this array
	private static Runtime[] allRuntimes = new Runtime[] { runtimeIORead,
			runtimeIOWrite, runtimeProcessingColoring, runtimeProcessingResize,
			runtimeProcessingResizeGrayscale, runtimeProcessingRotate,
			runtimeUnknown };

	public static void addProcessingTimeResize(long millis) {
		runtimeProcessingResize.add(millis);
	}

	public static void addProcessingTimeResizeGrayscale(long millis) {
		runtimeProcessingResizeGrayscale.add(millis);
	}

	public static void addProcessingTimeRotate(long millis) {
		runtimeProcessingRotate.add(millis);
	}

	public static void addProcessingTimeColoring(long millis) {
		runtimeProcessingColoring.add(millis);
	}

	public static void addIOTimeRead(long millis) {
		runtimeIORead.add(millis);
	}

	public static void addIOTimeWrite(long millis) {
		runtimeIOWrite.add(millis);
	}

	public static void addUnknownTime(long millis) {
		runtimeUnknown.add(millis);
	}

	public static long getProcessingTime() {
		return runtimeProcessingResize.getRuntime();
	}

	public static long getIOTime() {
		return runtimeIOWrite.getRuntime();
	}

	public static long getRuntime(String name) {
		for (Runtime rt : allRuntimes) {
			if (rt.getName().equals(name))
				return rt.getRuntime();
		}

		Log.error("runtime '" + name + "' not found!");
		return 0;
	}

	public static long getTotalTime() {
		long total = 0;
		for (Runtime rt : allRuntimes)
			total += rt.getRuntime();
		return total;
	}

	public static long getTotalOperations() {
		long total = 0;
		for (Runtime rt : allRuntimes)
			total += rt.getOperations();

		return total;
	}

	/** Accounts runtime according to the given timer-type. **/
	public static void accountTime(TimerType type, long millis) {
		switch (type) {
		case TIMER_IO_READ:
			addIOTimeRead(millis);
			break;
		case TIMER_IO_WRITE:
			addIOTimeWrite(millis);
			break;
		case TIMER_PROCESSING_COLORING:
			addProcessingTimeColoring(millis);
			break;
		case TIMER_PROCESSING_RESIZE:
			addProcessingTimeResize(millis);
			break;
		case TIMER_PROCESSING_RESIZE_GRAYSCALE:
			addProcessingTimeResizeGrayscale(millis);
			break;
		case TIMER_PROCESSING_ROTATE:
			addProcessingTimeRotate(millis);
			break;
		default:
			addUnknownTime(millis);
			break;
		}
	}

	/** Prints a report on the runtimes. **/
	public static void printReport() {
		long total = getTotalTime();
		long totalOperations = getTotalOperations();

		Log.sep();
		Log.log("PORTION" + "\t" + "TOTAL (ms)" + "\t" + "#" + "\t" + "AVG"
				+ "\t" + "NAME");
		for (Runtime rt : allRuntimes) {
			long runtime = rt.getRuntime();
			if (runtime != 0)
				Log.log(Math.floor((10000.0 * runtime / total)) / 10000.0
						+ "\t" + runtime + "\t\t" + rt.getOperations() + "\t"
						+ Math.floor(10000.0 * rt.getAverageRuntime())
						/ 10000.0 + "\t" + rt.getName());
		}
		Log.log("1.0000" + "\t" + total + "\t\t" + totalOperations + "\t"
				+ Math.floor(10000.0 * total / totalOperations) / 10000.0
				+ "\t" + "total");
		Log.sep();
	}

}
