import java.awt.AWTException;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Atm {
	double availableAmountInMachine, transactionFee;
	private ArrayList<User> userData;
	private User currentUser;
	public Atm(double Amount, double Fee) {
		this.availableAmountInMachine = Amount;
		this.transactionFee = Fee;
		this.userData = new ArrayList<User>();
	}
	public ArrayList<User> getUserData() {
		return this.userData;
	}
	public void start() {
		this.clearScreen();
		System.out.println("        Automated Teller Machine        \n----------------------------------------");
		System.out.println("Please input number to choose:\n1. NEW USER\n2. CURRENT USER");
		Scanner in = new Scanner(System.in);
		int choose = in.nextInt();
		switch(choose) {
		case 1:
			this.register();
			break;
		case 2:
			this.login();
			break;
		default:
			this.start();
			break;
		}
	}
	public void register() {
		this.clearScreen();
		System.out.println("        Automated Teller Machine        \n----------------------------------------");
		System.out.println("            **User Register**           ");
		Scanner in = new Scanner(System.in);
		System.out.println("Please input your name:");
		String name = in.nextLine();
		System.out.println("Please input your age:");
		String age = in.nextLine();
		System.out.println("Please input your address:");
		String address = in.nextLine();
		System.out.println("Please input your phone number:");
		String phoneNumber = in.nextLine();
		String bankAccount;
		String tmp;
		for(int i = 0; i < this.userData.size(); i ++) {
			if(this.userData.get(i).getPhoneNumber().equals(phoneNumber)) {
				System.out.println("Register failed, this phone number has been registered.");
				System.out.println("Input anything to continue");
				String input = in.next();
				if (input != null)
					this.start();
				return;
			}
		}
		if(this.userData.size() == 0)
			bankAccount = "00000000";
		else {
			tmp = String.valueOf(Integer.parseInt(this.userData.get(this.userData.size() - 1).getBankAccountNumber()) + 1);
			bankAccount = "";
			for(int i = 0; i < 8 - tmp.length(); i ++)
				bankAccount += "0";
			bankAccount += tmp;
		}
		String password = new String(bankAccount);
		this.userData.add(new User(name, age, address, phoneNumber, bankAccount, password));
		this.clearScreen();
		System.out.println("        Automated Teller Machine        \n----------------------------------------");
		System.out.println("            **User Register**           ");
		System.out.println("Successfully registered. Your information is listed below:");
		System.out.println(this.userData.get(this.userData.size() - 1));
		System.out.println("Input anything to continue");
		String input = in.next();
		if (input != null)
			this.start();
		
	}
	public void login() {
		this.clearScreen();
		System.out.println("        Automated Teller Machine        \n----------------------------------------");
		System.out.println("             **User Login**             ");
		Scanner in = new Scanner(System.in);
		System.out.println("Please input your account number:");
		String account = in.nextLine();
		System.out.println("Please input your password (If you forget it, input 0):");
		String password = in.nextLine();
		if(password.equals("0")) {
			this.resetPassword();
			return;
		}
		boolean isSuccess = false;
		for(int i = 0; i < this.userData.size(); i++) {
			if(this.userData.get(i).getBankAccountNumber().equals(account) && this.userData.get(i).getPassword().equals(password)) {
				isSuccess = true;
				this.currentUser = this.userData.get(i);
				break;
			}
		}
		if(isSuccess)
			this.mainMenu();
		else {
			System.out.println("Login failed, wrong account number or password. Input anything to continue.");
			String input = in.next();
			if (input != null)
				this.start();
		}	
	}
	public void mainMenu() {
		this.clearScreen();
		System.out.println("        Automated Teller Machine        \n----------------------------------------");
		System.out.println("              **Main Menu**             ");
		System.out.println("Please input number to choose:\n1. Available Balance\n2. Withdrawal\n3. Deposit\n4. Recent Transactions\n5. Change Password\n6. Exit");
		Scanner in = new Scanner(System.in);
		int choose = in.nextInt();
		switch(choose) {
		case 1:
			this.availableBalance();
			break;
		case 2:
			this.withdrawal();
			break;
		case 3:
			this.deposit();
			break;
		case 4:
			this.recentTransactions();
			break;
		case 5:
			this.changePassword();
			break;
		case 6:
			this.exit();
			break;
		default:
			this.mainMenu();
			break;
		}
	}
	public void availableBalance() {
		this.clearScreen();
		System.out.println("        Automated Teller Machine        \n----------------------------------------");
		System.out.println("          **Available Balance**         ");
		System.out.println("Your Available Balance is: " + this.currentUser.getBalance());
		System.out.println("Input anything to continue");
		Scanner in = new Scanner(System.in);
		String input = in.next();
		if (input != null)
			this.mainMenu();
	}
	public void withdrawal() {
		this.clearScreen();
		System.out.println("        Automated Teller Machine        \n----------------------------------------");
		System.out.println("              **Withdrawal**            ");
		Scanner in = new Scanner(System.in);
		System.out.println("Input money value you want to withdrawal: ");
		double value = in.nextDouble();
		if(value > this.currentUser.getBalance() - this.transactionFee || value > this.availableAmountInMachine) {
			System.out.println("Failed, amount exceeded");
		}
		else {
			this.currentUser.setBalance(this.currentUser.getBalance() - value - this.transactionFee);
			this.currentUser.addTransction( - value - this.transactionFee);
			this.availableAmountInMachine -= value;
			System.out.println("Withdrawal successed");
		}
		System.out.println("Input anything to continue");
		String input = in.next();
		if (input != null)
			this.mainMenu();	
	}
	public void deposit() {
		this.clearScreen();
		System.out.println("        Automated Teller Machine        \n----------------------------------------");
		System.out.println("               **Deposit**              ");
		Scanner in = new Scanner(System.in);
		System.out.println("Input money value you want to deposit: ");
		double value = in.nextDouble();
		if(value <= this.transactionFee) {
			System.out.println("Failed, need to deposit more money");
		}
		else {
			this.currentUser.setBalance(this.currentUser.getBalance() + value - this.transactionFee);
			this.currentUser.addTransction(value - this.transactionFee);
			this.availableAmountInMachine += value;
			System.out.println("Deposit successed");
		}
		System.out.println("Input anything to continue");
		String input = in.next();
		if (input != null)
			this.mainMenu();
	}
	public void recentTransactions() {
		this.clearScreen();
		System.out.println("        Automated Teller Machine        \n----------------------------------------");
		System.out.println("         **Recent Transctions**         ");
		System.out.println(this.currentUser.getTransction());
		Scanner in = new Scanner(System.in);
		System.out.println("Input anything to continue");
		String input = in.next();
		if (input != null)
			this.mainMenu();
	}
	public void changePassword() {
		this.clearScreen();
		System.out.println("        Automated Teller Machine        \n----------------------------------------");
		System.out.println("          **Change Password**           ");
		Scanner in = new Scanner(System.in);
		System.out.println("Please input your old password:");
		String oldPassword = in.nextLine();
		System.out.println("Please input your new password:");
		String newPassword = in.nextLine();
		if(this.currentUser.getPassword().equals(oldPassword)) {
			this.currentUser.setPassword(newPassword);
			System.out.println("Password changed");
		}
		else
			System.out.println("Failed, the original password you input is wrong");
		System.out.println("Input anything to continue");
		String input = in.next();
		if (input != null)
			this.mainMenu();
	}
	public void exit() {
		this.currentUser = null;
		this.start();
	}
	
	public void resetPassword() {
		this.clearScreen();
		System.out.println("        Automated Teller Machine        \n----------------------------------------");
		System.out.println("           **Reset Password**           ");
		Scanner in = new Scanner(System.in);
		System.out.println("Please input your name:");
		String name = in.nextLine();
		System.out.println("Please input your age:");
		String age = in.nextLine();
		System.out.println("Please input your phone number:");
		String phoneNumber = in.nextLine();
		System.out.println("Please input your new password:");
		String password = in.nextLine();
		boolean isSuccess = false;
		for(int i = 0; i < this.userData.size(); i++) {
			if(this.userData.get(i).getName().equals(name) && this.userData.get(i).getAge().equals(age) && this.userData.get(i).getPhoneNumber().equals(phoneNumber)) {
				this.userData.get(i).setPassword(password);
				isSuccess = true;
				break;
			}
		}
		if(isSuccess)
			System.out.println("Password Reseted");
		else
			System.out.println("Failed, cannot find your information");
		System.out.println("Input anything to continue");
		String input = in.next();
		if (input != null)
			this.login();
	}
	
	public static void clearScreen() {
		//Clear screen, which is useful in eclipse console. 
		Robot r;
		try {
			r = new Robot();
			r.mousePress(InputEvent.BUTTON3_MASK);
	        r.mouseRelease(InputEvent.BUTTON3_MASK);
	        r.keyPress(KeyEvent.VK_CONTROL); 
	        r.keyPress(KeyEvent.VK_R); 
	        r.keyRelease(KeyEvent.VK_R);
	        r.keyRelease(KeyEvent.VK_CONTROL); 
	        r.delay(100);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		Atm ATM = new Atm(1000000, 0.1);
		ATM.start();
	}
}
