import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;
/* I use scanner to accept input and all methods in Atm are void, so it's hard to use jUnit to do test.
But I do design test cases as below.

        Automated Teller Machine        
----------------------------------------
Please input number to choose:
1. NEW USER
2. CURRENT USER

1

        Automated Teller Machine        
----------------------------------------
            **User Register**           
Please input your name:
Jack
Please input your age:
22
Please input your address:
2020 E Madison St.
Please input your phone number:
2063889867

        Automated Teller Machine        
----------------------------------------
            **User Register**           
Successfully registered. Your information is listed below:
Account Number: 00000001
Password: 00000001
Name: Jack
Age: 22
Address: 2020 E Madison St.
Phone Number:2063889867
Input anything to continue

0

        Automated Teller Machine        
----------------------------------------
Please input number to choose:
1. NEW USER
2. CURRENT USER

2

        Automated Teller Machine        
----------------------------------------
             **User Login**             
Please input your account number:
00000000
Please input your password (If you forget it, input 0):
0

        Automated Teller Machine        
----------------------------------------
           **Reset Password**           
Please input your name:
Jack
Please input your age:
22
Please input your phone number:
2063889867
Please input your new password:
abc
Password Reseted
Input anything to continue

0

        Automated Teller Machine        
----------------------------------------
             **User Login**             
Please input your account number:
00000000
Please input your password (If you forget it, input 0):
abc

        Automated Teller Machine        
----------------------------------------
              **Main Menu**             
Please input number to choose:
1. Available Balance
2. Withdrawal
3. Deposit
4. Recent Transactions
5. Change Password
6. Exit

3

        Automated Teller Machine        
----------------------------------------
               **Deposit**              
Input money value you want to deposit: 
100
Deposit successed
Input anything to continue

0

        Automated Teller Machine        
----------------------------------------
              **Withdrawal**            
Input money value you want to withdrawal: 
50
Withdrawal successed
Input anything to continue

0

        Automated Teller Machine        
----------------------------------------
              **Main Menu**             
Please input number to choose:
1. Available Balance
2. Withdrawal
3. Deposit
4. Recent Transactions
5. Change Password
6. Exit

4

        Automated Teller Machine        
----------------------------------------
         **Recent Transctions**         
Deposit    -- 99.9
Withdrawal -- 50.1

Input anything to continue

0

        Automated Teller Machine        
----------------------------------------
              **Main Menu**             
Please input number to choose:
1. Available Balance
2. Withdrawal
3. Deposit
4. Recent Transactions
5. Change Password
6. Exit

6

        Automated Teller Machine        
----------------------------------------
Please input number to choose:
1. NEW USER
2. CURRENT USER
 */
public class AtmTest {
	Atm ATM = new Atm(100000, 0.1);
	
	@Test
	public void testAtm() {
		Assert.assertTrue(ATM.availableAmountInMachine == 100000);
		Assert.assertTrue(ATM.transactionFee == 0.1);
	}


	@Test
	public void testRegister() {
	}

	@Test
	public void testLogin() {
	}

	@Test
	public void testAvailableBalance() {
	}

	@Test
	public void testWithdrawal() {
	}

	@Test
	public void testDeposit() {
	}

	@Test
	public void testRecentTransactions() {
	}

	@Test
	public void testChangePassword() {
	}


	@Test
	public void testResetPassword() {
	}



}
