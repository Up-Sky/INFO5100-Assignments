public class test {	
	public static void main(String[] args) {
		System.out.println("1. " + Assignment4.convert("12345abcdefgHIJKLMN", 6)); //test 1
		System.out.print("\n");
		
		Scissors s = new Scissors(5); //test 2
		Paper p = new Paper(7);
		Rock r = new Rock(15);
		System.out.println("2. "); 
		System.out.println(s.fight(p) + " , "+ p.fight(s) );
		System.out.println(p.fight(r) + " , "+ r.fight(p) );
		System.out.println(r.fight(s) + " , "+ s.fight(r) );
		System.out.print("\n");
		
		IpAddress ip = new IpAddress("216.27.6.136"); //test 3
		System.out.println("3. "); 
		System.out.println(ip.getDottedDecimal());
		System.out.println(ip.getOctet(4));
		System.out.println(ip.getOctet(1));
		System.out.println(ip.getOctet(3));
		System.out.println(ip.getOctet(2));
		System.out.print("\n");
		
		Course Java = new Course("Java"); //test 4
		Student Jack = new Student("Jack", "000");
		System.out.println("4. ");
		System.out.println(Jack.getName() + " " + Jack.getId());
		Java.registerStudent(Jack);
		for(int i = 0; i < 10; i++)
			Java.registerStudent(new Student("Student" + Integer.toString(i + 1), "00" + Integer.toString(i + 1)));
		System.out.println(Java.getTitle() + " " + Java.getNumberOfStudent() + " " + Java.getStudents()[9].name);
		System.out.print("\n");
		
		System.out.println("5. " + Assignment4.intToRoman(256)); //test 5
		System.out.print("\n");
		
		System.out.println("Extra. " + Assignment4.findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{2, 3})); //test extra

	}

}
