/*5. Write a java class called customer (Add detail as needed) : 
	i. Create Attributes: customer name and which pizzas customer has ordered. 
	ii. Think about what kind of data structure can be used to record the pizza name and number of each kind of pizza.( Give me your thought, Extra credit of 1 point)
	iii. In main method , sum up how much money the customer spent.
 */
public class customer {
	String name;
	pizza[] pizzas; 
	/*I think the best data structure to record the pizza name and number of each kind of pizza is an array of pizza-class data.
	Since I have defined pizza class when answering the last question, in this question I can just use the pizza class I defined*/ 
	customer(String name) {
		this.name = name;
		this.pizzas = new pizza[10];
	}
	
	
	public static void main(String[] args) {
		double sum = 0;
		customer Jack = new customer("Jack");
		Jack.pizzas[0] = new pizza("BigBig", 9.9);
		Jack.pizzas[1] = new pizza("Big", 6.6);
		Jack.pizzas[2] = new pizza("Special", 8.8);
		for(int i = 0; i < Jack.pizzas.length; i++) {
			if(Jack.pizzas[i] == null)
				break;
			sum += Jack.pizzas[i].price;
		}
		System.out.println("The amount of money customer spent is: " + sum);
		
	}
}
