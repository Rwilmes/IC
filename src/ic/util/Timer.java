package ic.util;

import ic.util.log.Log;
import ic.util.runtime.Runtimes;

/**
 * A Timer object is used to measure runtimes.
 * 
 * @author Rwilmes
 * 
 */
public class Timer {

	public enum TimerType {
		TIMER_PROCESSING_RESIZE, TIMER_PROCESSING_ROTATE, TIMER_PROCESSING_RESIZE_GRAYSCALE, TIMER_PROCESSING_COLORING, TIMER_IO_READ, TIMER_IO_WRITE, TIMER_HASHING_DHASH, TIMER_HASHING_PHASH
	}

	private long start;
	private long stop;

	private boolean finished;
	private TimerType type;

	public Timer(TimerType type) {
		this.start = System.currentTimeMillis();
		this.type = type;
		this.finished = false;
	}

	public long stop() {
		if (finished) {
			Log.error("calling stop on an already finished Timer!");
			return 0;
		} else {
			this.stop = System.currentTimeMillis();
			this.finished = true;
			this.accountTime();
			return this.stop - this.start;
		}
	}

	public long getTotal() {
		if (!finished) {
			Log.error("calling getTotal on a still running Timer!");
			return 0;
		} else {
			return this.stop - this.start;
		}
	}

	public String getTotalAsString() {
		if (!finished) {
			Log.error("calling getTotal on a still running Timer!");
			return "0";
		} else {
			return (this.stop - this.start) + " ms";
		}
	}

	private void accountTime() {
		Runtimes.accountTime(this.type, getTotal());
	}

}
