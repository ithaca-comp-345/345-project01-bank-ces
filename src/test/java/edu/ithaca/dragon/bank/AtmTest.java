package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AtmTest {

    @Test
    void getBalanceTest() {
        ATM atmOne = new ATM();
        
        BankAccount bankAccount = new BankAccount("a@b.com");
        savings accOne = new savings("09100002212345678",200);
        bankAccount.addAcc(accOne);
        System.out.println(bankAccount.getSaving().getBalance());
        assertEquals(200, atmOne.checkBalance(bankAccount.getSaving())); //Correct Balance displays 
        
        BankAccount bankAccountTwo = new BankAccount("a@b.com");  //Balance with a Double
        savings accTwo = new savings("09100002212345678",10.55);
        bankAccountTwo.addAcc(accTwo);
        assertEquals(10.55, atmOne.checkBalance(bankAccountTwo.getSaving()));
        
        BankAccount bankAccountThree = new BankAccount("a@b.com");
        savings accThree = new savings("09100002212345678",0);
        bankAccountThree.addAcc(accThree);
        assertEquals(0, atmOne.checkBalance(bankAccountThree.getSaving())); //Balance should be 0

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

    //  @Test
    // void withdrawTest() throws IllegalArgumentException, InsufficientFundsException {
    //     BankAccount bankAccount = new BankAccount("a@b.com", 200);
    //     ATM atmOne = new ATM();
    //     atmOne.withdraw(bankAccount, 100);
    //     assertEquals(100, bankAccount.getBalance());
    //     assertThrows(IllegalArgumentException.class, () -> atmOne.withdraw(bankAccount, -1));
    //     assertThrows(IllegalArgumentException.class, ()->atmOne.withdraw(bankAccount, 100.111));
    //     assertThrows(IllegalArgumentException.class, () -> atmOne.withdraw(bankAccount, 50.555));
    //     assertThrows(InsufficientFundsException.class, () -> atmOne.withdraw(bankAccount, 200));
    //     atmOne.withdraw(bankAccount, 0);
    //     assertEquals(100, bankAccount.getBalance());
    //     atmOne.withdraw(bankAccount, .50);
    //     assertEquals(99.50, bankAccount.getBalance());
    // }
}
