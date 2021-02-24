package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AtmTest {

    @Test
    void getBalanceTest() {
        ATM atmOne = new ATM();
        
        //Savings account only
        BankAccount bankAccount = new BankAccount("a@b.com");
        savings accOne = new savings("09100002212345678",200);
        bankAccount.addAcc(accOne);
        System.out.println(bankAccount.getSaving().getBalance());
        assertEquals(200, atmOne.checkBalance(bankAccount.getSaving())); //Correct Balance displays 
        
        //Savings account only
        BankAccount bankAccountTwo = new BankAccount("a@b.com");  //Balance with a Double
        savings accTwo = new savings("09100002212345678",10.55);
        bankAccountTwo.addAcc(accTwo);
        assertEquals(10.55, atmOne.checkBalance(bankAccountTwo.getSaving()));
        
       
        //Checkings account only
        BankAccount bankAccountFour = new BankAccount("a@b.com");
        checkings accOneC = new checkings("09100002212345678",20);
        bankAccountFour.addAcc(accOneC);
        assertEquals(20, atmOne.checkBalance(bankAccountFour.getChecking())); 

        //Checkings account only
        BankAccount bankAccountFive = new BankAccount("a@b.com");
        checkings accTwoC = new checkings("09100002212345678",20.55);
        bankAccountFive.addAcc(accTwoC);
        assertEquals(20.55, atmOne.checkBalance(bankAccountFive.getChecking())); 

        //Checkings and savings account 
        BankAccount bankAccountSix = new BankAccount("a@b.com");
        savings accThree = new savings("09100002212345678",10.55);
        checkings accThreeC = new checkings("09100002212345678",20.55);
        bankAccountSix.addAcc(accThreeC);
        bankAccountSix.addAcc(accThree);
        assertEquals(20.55, atmOne.checkBalance(bankAccountSix.getChecking())); 
        assertEquals(10.55, atmOne.checkBalance(bankAccountSix.getSaving()));

        

    }
    @Test
    void depositTest() throws IllegalArgumentException{
        ATM atmOne = new ATM();
        BankAccount bankAccount = new BankAccount("a@b.com");
        savings accOne = new savings("09100002212345678",200);
        bankAccount.addAcc(accOne);
        //test postive number
        atmOne.deposit(bankAccount.getSaving(), 100);
        assertEquals(300, atmOne.checkBalance(bankAccount.getSaving())); 
        //test negative number
        assertThrows(IllegalArgumentException.class, ()->atmOne.deposit(bankAccount.getSaving(), 100.111));
        assertThrows(IllegalArgumentException.class, ()->atmOne.deposit(bankAccount.getSaving(), -1));

    }

     @Test
    void withdrawTest() throws IllegalArgumentException, InsufficientFundsException {
        ATM atmOne = new ATM();
        BankAccount bankAccount = new BankAccount("a@b.com");
        savings accOne = new savings("09100002212345678",200);
        bankAccount.addAcc(accOne);
        atmOne.withdraw(bankAccount.getSaving(), 100);
        assertEquals(100, bankAccount.getSaving().getBalance());
        assertThrows(IllegalArgumentException.class, () -> atmOne.withdraw(bankAccount.getSaving(), -1));
        assertThrows(IllegalArgumentException.class, ()->atmOne.withdraw(bankAccount.getSaving(), 100.111));
        assertThrows(IllegalArgumentException.class, () -> atmOne.withdraw(bankAccount.getSaving(), 50.555));
        assertThrows(InsufficientFundsException.class, () -> atmOne.withdraw(bankAccount.getSaving(), 200));
        atmOne.withdraw(bankAccount.getSaving(), 0);
        assertEquals(100, bankAccount.getSaving().getBalance());
        atmOne.withdraw(bankAccount.getSaving(), .50);
        assertEquals(99.50, bankAccount.getSaving().getBalance());
        
        
        checkings accTwo = new checkings("09100002212345678",200);
        bankAccount.addAcc(accTwo);
        atmOne.withdraw(bankAccount.getChecking(), 100);
        assertEquals(100, bankAccount.getChecking().getBalance());
        assertThrows(IllegalArgumentException.class, () -> atmOne.withdraw(bankAccount.getChecking(), -1));
        assertThrows(IllegalArgumentException.class, ()->atmOne.withdraw(bankAccount.getChecking(), 100.111));
        assertThrows(IllegalArgumentException.class, () -> atmOne.withdraw(bankAccount.getChecking(), 50.555));
        assertThrows(InsufficientFundsException.class, () -> atmOne.withdraw(bankAccount.getChecking(), 200));
        atmOne.withdraw(bankAccount.getChecking(), 0);
        assertEquals(100, bankAccount.getChecking().getBalance());
        atmOne.withdraw(bankAccount.getChecking(), .50);
        assertEquals(99.50, bankAccount.getChecking().getBalance());
    }
}
