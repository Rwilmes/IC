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

	public Runtime(String name) {
		this(name, 0);
	}

	public Runtime(String name, long runtime) {
		this.name = name;
		this.runtime = runtime;
	}

	public void add(long millis) {
		if (millis < 1)
			Log.error("negative time added to runtime '" + getName() + "'");
		runtime += millis;
	}

	public String getName() {
		return name;
	}

	public long getRuntime() {
		return runtime;
	}
}
