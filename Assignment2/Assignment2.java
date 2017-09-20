public class Assignment2 {
	/*1. Write a java function to calculate the salary of an employee based on the following rules.
		i. function takes input of number of hours an employee worked and returns the salary.
		ii.  The first 36 hours worked are paid at a rate of 15.0, then the next 5 hours are paid at a rate of 15 * 1.5. Hours after that up to a max of 48 are paid at a rate of 15 * 2.
	 */
	public static double employeeSalary(double hours) {
		double salary = 0;
		if(hours > 48) {
			System.out.println("The maximum value of hours is 48");
			return employeeSalary(48);
		}
		else if(hours > 41) {
			salary = 15*36 + 15*1.5*5;
			hours -= 41;
			salary += 15*2*hours;
		}
		else if(hours > 36) {
			salary = 15*36;
			hours -= 36;
			salary += 15*1.5*hours;
		}
		else if(hours < 0)
			System.out.println("The minimum value of hours is 0");
		else
			salary = 15 * hours;
		
		return salary;	
	}
	
	/* 2. Write a java function that adds all the digits of an integer until it is single digit.
		i. function takes an integer as input and returns its sum of digits.
		ii. for example input = 37, sum = 3+7 = 10, sum = 1+0 = 1. result = 1.
	 */
	public static int addDigits(int input) {
		int sum = 0;
		while(input > 0) {
			sum += input % 10;
			input /= 10;
		}
		if(sum < 10)
			return sum;
		else
			return addDigits(sum);
	}
	
	/*3. Write a java function to print all perfect number between 1 and n.
		i. Perfect number is a positive integer which is equal to the sum of its proper positive divisors.
		ii. For example: 6 is the first perfect number, Proper divisors of 6 are 1, 2, 3. Sum of its proper divisors = 1 + 2 + 3 = 6.
	 */
	public static void printPerfectNumbers(int n) {
		if(n < 6)
			return;
		int divisorSum, number, divisor;
		for(number = 6; number <= n; number++) {
			divisorSum = 1;
			for(divisor = 2; divisor <= Math.sqrt(number); divisor++) {
				if(number % divisor == 0) {
					divisorSum += divisor;
					if(number/divisor != divisor)
						divisorSum += number/divisor;
				}
			}
			if(divisorSum == number)
				System.out.print(number + " ");
		}
		System.out.print("\n");
	}
	
	/*6. Write a Java program that generates an isosceles right angled triangle made of asterisks. 
		i. function should take input of one equal side as integer. Other than the edges the inner part of the triangle should be empty.
		ii. For example input is 6. the function should print¡ª
	   *
	   **
	   * *
	   *  *
	   *   *
	   ******
	 */
	public static void printIsoscelesTriangle(int n) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <= i; j++) {
				if(j == 0 || j == i || i == n-1)
					System.out.print("*");
				else
					System.out.print(" ");
			}
				System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		System.out.println("1. " + employeeSalary(42)); //test 1
		System.out.println("2. " + addDigits(37)); //test 2
		System.out.print("3. "); printPerfectNumbers(10000); //test 3
		System.out.println("4. "); pizza A = new pizza(), B = new pizza(123), C = new pizza("BigBig", "Maigre"), D = new pizza("BigBig", 9.9); //test 4
		System.out.println("5. "); customer.main(args); //test 5
		System.out.println("6."); printIsoscelesTriangle(6); //test 6
		
	}
}




