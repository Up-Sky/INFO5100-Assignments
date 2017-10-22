import java.util.ArrayList;

public class User {
	private String name, age, address, phoneNumber, bankAccountNumber, password;
	private double balance;
	private ArrayList<Double> Transction;
	public User() {
	}
	public User(String name, String age, String address, String phoneNumber, String bankAccountNumber, String password) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.bankAccountNumber = bankAccountNumber;
		this.password = password;
		this.balance = 0;
		this.Transction = new ArrayList<Double>();
	}
	String getName() {
		return this.name;
	}
	String getAge() {
		return this.age;
	}
	String getAddress() {
		return this.address;
	}
	String getPhoneNumber() {
		return this.phoneNumber;
	}
	String getBankAccountNumber() {
		return this.bankAccountNumber;
	}
	String getPassword() {
		return this.password;
	}
	String getTransction() {
		String transctionList = "";
		int i = Math.max(0, this.Transction.size() - 10);
		for(; i < this.Transction.size(); i ++) {
			double amount = this.Transction.get(i);
			if(amount > 0)
				transctionList += ("Deposit    -- " + String.valueOf(amount) + "\n");
			else
				transctionList += ("Withdrawal -- " + String.valueOf(-amount) + "\n");
		}
		return transctionList;
	}
	double getBalance() {
		return this.balance;
	}
	void setPassword(String password) {
		this.password = password;
	}
	void setBalance(double balance) {
		this.balance = balance;
	}
	void addTransction(double amount) {
		this.Transction.add(amount);
	}
	public String toString() {
		return "Account Number: " + this.bankAccountNumber + "\nPassword: " + this.password + "\nName: "
				+ this.name + "\nAge: " + this.age + "\nAddress: " + this.address + "\nPhone Number:" + this.phoneNumber;
	}
}

