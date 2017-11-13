package assignment;

import java.util.Stack;


public class ReverseHello extends Thread {
	private int number;
	public ReverseHello(int num) {
		this.number = num;
	}
	@Override
	public void run() {
		if(number < 50) {
			ReverseHello newThread = new ReverseHello(number + 1);
			newThread.start();
			try {
				newThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Hello from Thread " + this.number + "!");
	}

	public static void main(String[] args) throws InterruptedException {
		ReverseHello newThread = new ReverseHello(1);
		newThread.start();

	}

}
