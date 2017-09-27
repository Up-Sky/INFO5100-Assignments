import java.util.*;
/*1. Find the error in the following code and explain in few lines why it is wrong. (Score 1)
public class Book{
	int size;
	int price;
	String name;
	public Book(int size) {
	this.size = size;
	}
	public Book(int size, int price, String name){
		super();
		this.size = size;
		this.price = price;
		this.name = name;
	}
	public Book(int price){
		this.price = price;
	}
	public setName(String name){
		return name;
	}
}

error1: The construction functions Book(int size) and Book(int price) take same type of parameters, so they are conflicting to each other;
error2: The function setName has a return value but doesn't have a type declaration, should be public String setName;
error3: The function setName actually has no use, it cannot set the name of Book class;
 */

/*2. Find the error in the following code and explain in few lines why it is wrong. (Score 1)
class Clock{
	String time;
	void getTime(){
		return time;
	}
	void setTime(String t){
		time = t ;
	}
}

error1: The function getTime has a return value but its type declaration is void, should be String;
 */

public class Assignment3 {
	
	/*3. Write a Java function to remove vowels in a string. (Score 2)
		i. The function should take a string as input.
		ii. Should return the input string after omitting the vowels.
	 */
	public static String removeVowelsFromString(String input) {
		String output = ""; 
		char[] vowels = {'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'};
		boolean isVowel = false;
		for(int i = 0; i < input.length(); i++) {
			isVowel = false;
			for(int j = 0; j < vowels.length; j++) {
				if(input.charAt(i) == vowels[j]) {
					isVowel = true;
					break;
				}
			}
			if(!isVowel)
				output += input.charAt(i);
		}
		input = output;	
		return output;
	}
	
	/*4. Write a java function to check if two strings are Anagrams or not. (Score 2)
		i. The function should take two input strings.
		ii. Should return a boolean °Ætrue°Ø if the inputs are Anagrams else return °Æfalse°Ø.
	 */
	public static boolean checkIfTwoStringsAreAnagrams(String s1, String s2) {
		if(s1 == s2 || s1.length() != s2.length())
			return false;
		char[] chars1 = s1.toCharArray(), chars2 = s2.toCharArray();
		Arrays.sort(chars1);
		Arrays.sort(chars2);
		for(int i = 0; i < chars1.length; i ++) {
			if(chars1[i] != chars2[i])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("3. " + removeVowelsFromString("aeiou12345")); //test 3
		System.out.println("4. " + checkIfTwoStringsAreAnagrams("ABC", "abc")); //test 4
		System.out.println("5. "); calculator.mainmenu(); //test 5

	}

}

/*5. Create a calculator that can perform the following features. (Total Score 4)
i. The calculator should be able to perform Addition, subtraction, multiplication, division. (Score 2)
ii. Should be able to perform squareRoot, square, cube. (Score 1)
iii. Should be able to convert °ÆFahrenheit-Celsius°Ø , °ÆFeet-Inches°Ø. (Score 1)
Extra Credit(Score 2)
The calculator should be able to solve a quadratic equation and return the solution as array.
i. This function should take three arguments.
ii. For example, if quadratic equation is Ax 2 + Bx + C. The function should take A,B,C as arguments
and return a solution as array.
*/
class calculator {
	public static void mainmenu() {
		while(true) {
			Scanner in=new Scanner(System.in);
			System.out.println("Choose Function: (input choice number)\n0. quit    1. + - * /    2. squareRoot, square, cube    3. convert units    4. solve a quadratic equation");
			int choose = in.nextInt();
			switch(choose) {
				default:
					System.out.println("please choose between 0~4");
					break;
				case 0:
					return;
				case 1:
					basic();
					break;
				case 2:
					exponent();
					break;
				case 3:
					convert();
					break;
				case 4:
					solve();
					break;
			}
		}
	}//Output a menu to let user choose which function to use
	
	public static void basic() {
		Scanner in=new Scanner(System.in);
		System.out.println("1. + - * /  input: number1(space)operator(space)number2 (exp: 1.5 + 2)");
		String tmp = in.nextLine();
		String arr[] = tmp.split(" ");
		switch(arr[1]) {
			default:
				System.out.println("wrong input");
				break;
			case "+":
				System.out.println(arr[0] + " + " + arr[2] + " = " + (Double.valueOf(arr[0])+Double.valueOf(arr[2])));
				break;
			case "-":
				System.out.println(arr[0] + " - " + arr[2] + " = " + (Double.valueOf(arr[0])-Double.valueOf(arr[2])));
				break;
			case "*":
				System.out.println(arr[0] + " * " + arr[2] + " = " + (Double.valueOf(arr[0])*Double.valueOf(arr[2])));
				break;
			case "/":
				System.out.println(arr[0] + " / " + arr[2] + " = " + (Double.valueOf(arr[0])/Double.valueOf(arr[2])));
				break;
		}
		System.out.print("\n");
	}
	
	public static void exponent() {
		Scanner in=new Scanner(System.in);
		System.out.println("2. squareRoot, square, cube  input: number(space)numberOfPower (exp: 2 0.5)");
		String tmp = in.nextLine();
		String arr[] = tmp.split(" ");
		switch(arr[1]) {
			default:
				System.out.println(arr[0] + "^" + arr[1] + " = " + Math.pow(Double.valueOf(arr[0]), Double.valueOf(arr[1])));
				break;
			case "0.5":
				System.out.println("the squareRoot of " + arr[0] + " is " + Math.pow(Double.valueOf(arr[0]), 0.5));
				break;
			case "2":
				System.out.println("the square of " + arr[0] + " is " + Math.pow(Double.valueOf(arr[0]), 2));
				break;
			case "3":
				System.out.println("the cube of " + arr[0] + " is " + Math.pow(Double.valueOf(arr[0]), 3));
				break;
		}
		System.out.print("\n");
	}
	
	public static void convert() {
		Scanner in=new Scanner(System.in);
		System.out.println("3. convert °„F<->°„C  Feet<->Inch   input: number(space)type (exp: 100 F; 100 C; 10 Feet; 10 Inch)");
		String tmp = in.nextLine();
		String arr[] = tmp.split(" ");
		switch(arr[1]) {
			default:
				System.out.println("wrong input");
				break;
			case "F":
				System.out.println(arr[0] + "°„F" + " = " + (Double.valueOf(arr[0])-32)/1.8 + "°„C");
				break;
			case "C":
				System.out.println(arr[0] + "°„C" + " = " + (Double.valueOf(arr[0])*1.8+32) + "°„F");
				break;
			case "Feet":
				System.out.println(arr[0] + " Feet" + " = " + (Double.valueOf(arr[0])*12) + " Inch");
				break;
			case "Inch":
				System.out.println(arr[0] + " Inch" + " = " + (Double.valueOf(arr[0])/12) + " Feet");
				break;
		}
		System.out.print("\n");
	}
	
	public static double[] solve() {
		Scanner in=new Scanner(System.in);
		System.out.println("4. solve quadratic equation   input: number1(space)number2(space)number3 (exp: 1 2 1, which means 1x^2 + 2x + 1 = 0)");
		String tmp = in.nextLine();
		String arr[] = tmp.split(" ");
		if(arr.length < 3) {
			System.out.println("should input 3 numbers, even if they are equal to 0");
			return null;
		}
		double a = Double.valueOf(arr[0]), b = Double.valueOf(arr[1]), c = Double.valueOf(arr[2]);
		double[] solution = new double[2];
		double value = b*b - 4*a*c;
		if(value < 0) {
			System.out.println("(" + a + "x^2) + (" + b + "x) + (" + c + ") = 0  does not have solution");
			System.out.print("\n");
			return null;
		}
		else if(value == 0) {
			System.out.println("(" + a + "x^2) + (" + b + "x) + (" + c + ") = 0  has one solution: x = " + (-b/2)/a);
			solution[0] = (-b/2)/a;
		}
		else {
			solution[0] = (-b + Math.sqrt(value))/(2*a);
			solution[1] = (-b - Math.sqrt(value))/(2*a);
			System.out.println("(" + a + "x^2) + (" + b + "x) + (" + c + ") = 0  has two solutions: x1 = " + solution[0] + ", x2 = " + solution[1]);
		}
		System.out.print("\n");
		return solution;
	}
}


