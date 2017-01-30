package ic.util.runtime;

import ic.util.log.Log;

/**
 * A runtime object holds a name and a long value, representing the number of
 * millis measured by the runtime.
 * 
 * @author Rwilmes
 * 
 */
public class Runtime {

	private String name;
	private long runtime;
	private long operations;

	public Runtime(String name) {
		this.name = name;
		this.runtime = 0;
		this.operations = 0;
	}

	public void add(long millis) {
		if (millis < 0)
			Log.error("negative time added to runtime '" + getName() + "'");
		runtime += millis;
		operations += 1;
	}

	public String getName() {
		return name;
	}

	public long getRuntime() {
		return runtime;
	}

	public long getOperations() {
		return operations;
	}

	public double getAverageRuntime() {
		return 1.0 * runtime / operations;
	}
}
