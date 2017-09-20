/*4. Write a java class called pizza with (Add detail as needed) : 
	i. Create at least 3 attributes :pizza type , unit price and loyalty points. More attributes are welcome to have. 
	ii. Create constructors . Extra-credit of 0.5 point if you write more than 1 right constructor to this class
 */
public class pizza {
	String name, type;
	double price, size;
	int loyaltyPoints;
	pizza() {
		System.out.println("class pizza constructed");
	}
	pizza(int number) {
		this.loyaltyPoints = number;
		System.out.println("class pizza constructed, whose loyaltyPoints is " + this.loyaltyPoints);
	}
	pizza(String name, String type) {
		this.name = name;
		this.type = type;
		System.out.println("class pizza constructed, whose name is " + this.name + " and type is " + this.type);
	}
	pizza(String name, double price) {
		this.name = name;
		this.price = price;
		System.out.println("class pizza constructed, whose name is " + this.name + " and price is " + this.price);
	}
}

