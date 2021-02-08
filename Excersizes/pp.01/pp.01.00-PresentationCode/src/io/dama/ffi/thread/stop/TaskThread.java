package io.dama.ffi.thread.stop;

public class TaskThread extends Thread {

	private volatile boolean stopped = false;

	public boolean isStopped() {
		return this.stopped;
	}

	public void stopRequest() {
		this.stopped = true;
		this.interrupt();
	}

	@Override
	public void run() {
		// Initialisierungen etc.
		while (!isStopped()) {
			// ... arbeiten ...
		}
		// aufräumen etc.
	}
}

