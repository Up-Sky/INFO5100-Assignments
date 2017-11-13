package assignment;

import java.util.Random;

public class MaxValue extends Thread{
	private int arr[];
	private int lo, hi;
	private int maxValue = Integer.MIN_VALUE;
	
	public MaxValue(int arr[], int lo, int hi) {
		this.arr = arr;
		this.lo = lo;
		this.hi = hi;
	}
	
	@Override
	public void run() {
		for(int i = lo; i < hi; i ++) {
			if(this.maxValue < this.arr[i])
				this.maxValue = this.arr[i];
		}
	}
	
	public static int getMaxValue(int[] arr) throws InterruptedException {
		int len = arr.length;
		int maxValue = Integer.MIN_VALUE;
		MaxValue[] ts = new MaxValue[4];
		for(int i = 0; i < 4; i++) {
            ts[i] = new MaxValue(arr, (i * len) / 4, ((i + 1) * len / 4));
            ts[i].start();
        }
		Thread.sleep(3);
		for(int i = 0; i < 4; i ++) {
			if(ts[i].maxValue > maxValue)
				maxValue = ts[i].maxValue;
		}
		return maxValue;
	}

	public static void main(String[] args) throws InterruptedException{
		 int[] arr = new int[100];
		 Random random = new Random();
		 for(int i = 0; i < arr.length; i++)
			 arr[i] = random.nextInt(1000);
		 int maxValue = getMaxValue(arr);
	        System.out.println("Max: " + maxValue);

	}

}
