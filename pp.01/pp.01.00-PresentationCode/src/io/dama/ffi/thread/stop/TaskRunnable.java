package io.dama.ffi.thread.stop;

public class TaskRunnable implements Runnable {
	private volatile boolean stopped = false;
	private volatile Thread self;

	public boolean isStopped() {
		return this.stopped;
	}

	public void stopRequest() {
		this.stopped = true;
		if (this.self != null) {
			this.self.interrupt();
		}
	}

	@Override
	public void run() {
		this.self = Thread.currentThread();
		// Initialisierungen etc.
		while (!isStopped()) {
			// ... arbeiten ...
		}
		// aufräumen etc.
	}
}
