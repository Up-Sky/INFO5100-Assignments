import java.io.*;

/*1. Create your own MyIndexOutOfBoundException Class. It should contain these parameters
lowerBound - the lowest legal index value.
upperBound - the highest legal index value.
index - the current index value.
 */
class MyIndexOutOfBoundException extends RuntimeException {
	int lowerBound, upperBound, index;
	MyIndexOutOfBoundException() {
	}
	
	MyIndexOutOfBoundException(int lower, int upper, int curr) {
		this.lowerBound = lower;
		this.upperBound = upper;
		this.index = curr;
	}

	public String toString() {
		return "Error Message: Index: " + index + ", but Lower bound: " + this.lowerBound + ", Upper bound: " + this.upperBound;
	}
}

public class Assignment6 {
	/*2. Modify the following parse() method so that it will compile: (Score 1)
	 */
	public static void parse(File file) throws IOException {
		RandomAccessFile input = null;
		String line = null;  
	    try {
	    	input = new RandomAccessFile(file, "r");
	    	while ((line = input.readLine()) != null) {
	    		System.out.println(line);
	    	}
	    	return;
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
	
	public static void main(String[] args) {
		// test 1
		int[] numbers = {1, 2, 3, 4, 5};
		System.out.println("1. ");
		for(int i = 0; i <= numbers.length + 1; i++) {
			try {
				System.out.print("index: " + i + "  ");
				if(i > numbers.length - 1)
					throw new MyIndexOutOfBoundException(0, numbers.length - 1, i);
				else
					System.out.println("value: " + numbers[i]);
			} catch(Exception e) {
				System.out.println(e);
				return;
			}
		}
	}
	
}
