package ic.util.log;

/**
 * Class performing all the logging. Each log-level can be set separately in
 * order to enable / disable its output. The PRINT_LOG flag acts as the master
 * flag and can be set false to disable all log outputs.
 * 
 * @author Rwilmes
 * 
 */
public class Log {

	// master-flag
	public static boolean PRINT_LOG = true;

	// print general logs
	public static boolean PRINT_GENERAL = true;

	// print io operation logs
	public static boolean PRINT_IO = false;

	// print image processing logs
	public static boolean PRINT_PROCESSING = false;

	// print error logs
	public static boolean PRINT_ERROR = true;

	// static strings
	public static final String prefix = "~ ";
	public static final String separator = "~ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

	/*
	 * LOG METHODS
	 */
	public static void log(String message) {
		if (PRINT_LOG && PRINT_GENERAL)
			System.out.println(prefix + "GN : " + message);

	}

	public static void io(String message) {
		if (PRINT_LOG && PRINT_IO)
			System.out.println(prefix + "IO : " + message);
	}

	public static void proc(String message) {
		if (PRINT_LOG && PRINT_PROCESSING)
			System.out.println(prefix + "PC : " + message);
	}

	public static void error(String message) {
		if (PRINT_LOG && PRINT_ERROR)
			System.err.println(prefix + "ER : " + message);

	}

	public static void sep() {
		if (PRINT_LOG)
			System.out.println(separator);
	}

	/*
	 * CONFIGURATION METHODS
	 */
	public static void setDebugMode() {
		PRINT_LOG = true;
		PRINT_GENERAL = true;
		PRINT_IO = true;
		PRINT_PROCESSING = true;
		PRINT_ERROR = true;
	}

	public static void setNormalMode() {
		PRINT_LOG = true;
		PRINT_GENERAL = true;
		PRINT_IO = false;
		PRINT_PROCESSING = false;
		PRINT_ERROR = true;
	}

	public static void setQuiet() {
		PRINT_LOG = false;
	}

}
