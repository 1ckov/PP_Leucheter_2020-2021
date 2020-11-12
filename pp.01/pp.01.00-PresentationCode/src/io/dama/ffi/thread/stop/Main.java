package io.dama.ffi.thread.stop;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		TaskRunnable task = new TaskRunnable();
		Thread thread1 = new Thread(task);
		thread1.start();
		Thread.sleep(4000);
		task.stopRequest();
		thread1.join();
		
		TaskThread thread2 = new TaskThread();
		thread2.start();
		Thread.sleep(4000);
		thread2.stopRequest();
		thread2.join();
	}

}
