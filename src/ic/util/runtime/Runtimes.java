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

	private static Runtime runtimeIO = new Runtime("io");
	private static Runtime runtimeProcessing = new Runtime("processing");

	// add all runtimes to this array
	private static Runtime[] allRuntimes = new Runtime[] { runtimeIO,
			runtimeProcessing };

	public static void addProcessingTime(long millis) {
		runtimeProcessing.add(millis);
	}

	public static void addIOTime(long millis) {
		runtimeIO.add(millis);
	}

	public static long getProcessingTime() {
		return runtimeProcessing.getRuntime();
	}

	public static long getIOTime() {
		return runtimeIO.getRuntime();
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

	/** Accounts runtime according to the given timer-type. **/
	public static void accountTime(TimerType type, long millis) {
		switch (type) {
		case TIMER_IO:
			addIOTime(millis);
			break;
		case TIMER_PROCESSING:
			addProcessingTime(millis);
			break;
		}
	}

	/** Prints a report on the runtimes. **/
	public static void printReport() {
		long total = getTotalTime();

		Log.sep();
		Log.log("PORTION" + "\t" + "TOTAL (ms)" + "\t" + "NAME");
		for (Runtime rt : allRuntimes) {
			Log.log(Math.floor((10000.0 * rt.getRuntime() / total)) / 10000.0
					+ "\t" + rt.getRuntime() + "\t\t" + rt.getName());
		}
		Log.log("1.0000" + "\t" + total + "\t\t" + "total");
		Log.sep();
	}

}
