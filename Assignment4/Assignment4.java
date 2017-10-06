import java.util.*;
public class Assignment4 {
	
	/*1. Now you are given a string S, which represents a software license key which we would like to format.
	The string is composed of alphanumeric characters and dashes. The dashes split the alphanumeric characters within the string into groups.
	The dashes in the given string are possibly misplaced.
	We want each group of characters to be of length K (except for possibly the first group, which could be shorter, but still must contain at least one character). 
	To satisfy this requirement, we will reinsert dashes. Additionally, all the lower case letters in the string must be converted to upper case.
	So, you are given a non-empty string S, representing a license key to format, and an integer K. 
	And you need to return the license key formatted according to the description above. (Score 2)
	 */
	public static String convert(String S, int K) {
		ArrayList charlist = new ArrayList();
		int count = 0;
		for(int i = S.length() - 1; i >= 0; i --) {
			char str = S.charAt(i);
			if(str >= 'A' && str <= 'Z' || str >= '0' && str <= '9') {
				charlist.add(0, str);
				count += 1;
			}
			else if(str >= 'a' && str <= 'z') {
				str -= 32;
				charlist.add(0, str);
				count += 1;
			}
			else {
				continue;
			}
			if(count == K && i > 0) {
				charlist.add(0, '-');
				count = 0;
			}
		}
		String converted_S = "";
		for(int i = 0; i < charlist.size(); i++)
			converted_S += charlist.get(i);
		return converted_S;
	}
	
	/*5. Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to3999. (Score 2)
	 */
	public static String intToRoman(int num) {
		String thousand[] = {"", "M", "MM", "MMM"};
	    String hundred[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
	    String ten[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
	    String unit[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
	    return thousand[num/1000] + hundred[(num%1000)/100]+ ten[(num%100)/10] + unit[num%10];
	}
	
	/*Extra credit: There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays. (Score 2)
	 */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int l1 = nums1.length, l2 = nums2.length;
		if(l1 > l2)
			return findMedianSortedArrays(nums2, nums1);
		int k = (l1 + l2 - 1) / 2;
		int left = 0, right = Math.min(k, l1);
		while(left < right) {
			int mid1 = (left + right) / 2;
			int mid2 = k - mid1;
			if(nums1[mid1] < nums2[mid2])
				left = mid1 + 1;
			else
				right = mid1;
		}
		double median, median1, median2;
		if(left - 1 >= 0 && k - left >= 0)
			median1 = Math.max(nums1[left - 1], nums2[k - left]);
		else if(left - 1 >= 0)
			median1 = nums1[left - 1];
		else
			median1 = nums2[k - left];
		
		if((l1 + l2) % 2 == 1)
			median = median1;
		else {
			if(left < l1 && k - left + 1 < l2)
				median2 = Math.min(nums1[left], nums2[k - left + 1]);
			else if(left < l1)
				median2 = nums1[left];
			else
				median2 = nums2[k - left + 1];
			median = (median1 + median2) / 2;
		}
		return median;
	}
	
	public static void main(String[] args) {
		test.main(args); //test all
	}
}

/*2. Implement a class called Tool. It should have an int field called strength and a char field called type.
The Tool class should also contain the function void setStrength(int), which sets the strength for the Tool.
Create 3 more classes called Rock, Paper, and Scissors, which inherit from Tool. 
 */
class Tool {
	char type;
	int strength;
	void setStrength(int n) {
		this.strength = n;
	}
}

class Scissors extends Tool {
	Scissors(int n) {
		this.strength = n;
		this.type = 's';
	}
	public boolean fight(Tool tool) {
		switch(tool.type) {
			default:
				return true;
			case 's':
				return this.strength > tool.strength;
			case 'p':
				return this.strength * 2 > tool.strength;
			case 'r':
				return this.strength > tool.strength * 2;
		}
	}
}

class Paper extends Tool {
	Paper(int n) {
		this.strength = n;
		this.type = 'p';
	}
	public boolean fight(Tool tool) {
		switch(tool.type) {
			default:
				return true;
			case 'p':
				return this.strength > tool.strength;
			case 'r':
				return this.strength * 2 > tool.strength;
			case 's':
				return this.strength > tool.strength * 2;
		}
	}
}

class Rock extends Tool {
	Rock(int n) {
		this.strength = n;
		this.type = 'r';
	}
	public boolean fight(Tool tool) {
		switch(tool.type) {
			default:
				return true;
			case 'r':
				return this.strength > tool.strength;
			case 's':
				return this.strength * 2 > tool.strength;
			case 'p':
				return this.strength > tool.strength * 2;
		}
	}
}

class RockPaperScissorsGame {
	public static void main(String args[]) {
		Scissors s = new Scissors(5);
		Paper p = new Paper(7);
		Rock r = new Rock(15);
		System.out.println(s.fight(p) + " , "+ p.fight(s) );
		System.out.println(p.fight(r) + " , "+ r.fight(p) );
		System.out.println(r.fight(s) + " , "+ s.fight(r) );
	}
}

/*3. Every computer on the Internet has a unique identifying number, called an Internet protocol (IP)address. 
 To contact a computer on the Internet, you send a message to the computer¡¯s IP address.
 */
class IpAddress {
	String dottedDecimal;
	int firstOctet, secondOctet, thirdOctet, fourthOctet;
	IpAddress(String IP) {
		this.dottedDecimal = IP;
		String[] Octets = IP.split("\\.");
		this.firstOctet = Integer.parseInt(Octets[0]);
		this.secondOctet = Integer.parseInt(Octets[1]);
		this.thirdOctet = Integer.parseInt(Octets[2]);
		this.fourthOctet = Integer.parseInt(Octets[3]);
	}
	public String getDottedDecimal() {
		return this.dottedDecimal;
	}
	public int getOctet(int n) {
		switch(n) {
			default:
				return this.firstOctet;
			case 1:
				return this.firstOctet;
			case 2:
				return this.secondOctet;
			case 3:
				return this.thirdOctet;
			case 4:
				return this.fourthOctet;
		}
	}
	
	public static void main(String args[]) { //run in test class
		IpAddress ip = new IpAddress("216.27.6.136");
		System.out.println(ip.getDottedDecimal());
		System.out.println(ip.getOctet(4));
		System.out.println(ip.getOctet(1));
		System.out.println(ip.getOctet(3));
		System.out.println(ip.getOctet(2));
	}
}

/*4. Design a simple registration system that allows Student to register in a course using 2 classes: 
class Student & class Course. Implement the scenarios in class Test¡¯s main method.
 */
class Student {
	String name, id;
	Student(String name, String id) {
		this.name = name;
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public String getId() {
		return this.id;
	}
}

class Course {
	String name;
	private int numberOfStudent;
	private Student[] students;
	Course(String name) {
		this.name = name;
		this.numberOfStudent = 0;
		this.students = new Student[10];
	}
	public Student[] getStudents() {
		return this.students;
	}
	public boolean isFull() {
		if(this.numberOfStudent < 10)
			return false;
		else
			return true;
	}
	public String getTitle() {
		return this.name;
	}
	public int getNumberOfStudent() {
		return this.numberOfStudent;
	}
	public void registerStudent(Student student) {
		if(this.isFull()) {
			System.out.println("This course is already full");
			return;
		}
		this.numberOfStudent += 1;
		this.students[numberOfStudent - 1] = student;
	}
}
