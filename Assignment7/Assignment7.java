package assignment;

import java.util.ArrayList;
import java.util.Arrays;

public class Assignment7 {
	
	/* 4. Pascal¡¯s triangle is a triangular array of the binomial coefficients. 
	 * Write a function that takes an integer value n as input and prints first n lines of the Pascal¡¯s triangle. 
	 */
	 public static void printPascalTriangle(int n) {
		 ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		 for(int i = 0; i < n; i++)
			 list.add(new ArrayList<Integer>());
		 
		 list.get(0).add(1);
		 if(n == 1) {
			 System.out.println("1");
			 return;
		 }
		 list.get(1).add(1);
		 list.get(1).add(1);
		 for(int i = 2; i < n; i ++) {
			 list.get(i).add(1);
			 for(int j = 0; j < list.get(i - 1).size() - 1; j++) {
				 list.get(i).add(list.get(i - 1).get(j) + list.get(i - 1).get(j + 1));
			 }
			 list.get(i).add(1);
		 }
		 for(int i = 0; i < n; i ++) {
			 for(int j = 0; j < list.get(i).size(); j ++) {
				 System.out.print(list.get(i).get(j) + " ");
			 }
			 System.out.print("\n");
		 }
	  }
	 
	 /*5. Determine whether a given array can be partitioned into two subsets such that the sum of elements in both subsets is same.
	  */
	 public static boolean findPartition (int arr[]) {
		 int sum = 0;
		 for (int num : arr)
			 sum += num;
		 if ((sum % 1) == 1)
			 return false;
		 sum /= 2;
		 int n = arr.length;
		 boolean[] dp = new boolean[sum+1];
		 Arrays.fill(dp, false);
		 dp[0] = true;
		 for (int num : arr) {
			 for (int i = sum; i > 0; i--) {
				 if (i >= num)
					 dp[i] = dp[i] || dp[i-num];
			 }
		 }
		 return dp[sum];
	 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printPascalTriangle(6);
		int[] arr = {1, 5, 11, 5};
		boolean result = findPartition(arr);
		System.out.println(result);
		

	}
}
