package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AtmTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        ATM atmOne = new ATM();
        //savings accOne = new savings("1234",200);
       //assertEquals(200, atmOne.checkBalance(bankAccount.getChecking())); //Correct Balance displays 
        assertEquals(200, atmOne.checkBalance(bankAccount)); //Correct Balance displays 
        BankAccount bankAccountTwo = new BankAccount("a@b.com", 10.50);  //Balance with a Double
        assertEquals(10.50, atmOne.checkBalance(bankAccountTwo));
        BankAccount bankAccountThree = new BankAccount("a@b.com", -1);
        assertEquals(0, atmOne.checkBalance(bankAccountThree)); //Balance should be 0

    }
    @Test
    void depositTest() throws IllegalArgumentException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        ATM atmOne = new ATM();
        //test postive number
        atmOne.deposit(bankAccount, 100);
        assertEquals(300, atmOne.checkBalance(bankAccount)); 
        //test negative number
        assertThrows(IllegalArgumentException.class, ()->atmOne.deposit(bankAccount, 100.111));
        assertThrows(IllegalArgumentException.class, ()->atmOne.deposit(bankAccount, -1));

    }

     @Test
    void withdrawTest() throws IllegalArgumentException, InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        ATM atmOne = new ATM();
        atmOne.withdraw(bankAccount, 100);
        assertEquals(100, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, () -> atmOne.withdraw(bankAccount, -1));
        assertThrows(IllegalArgumentException.class, ()->atmOne.withdraw(bankAccount, 100.111));
        assertThrows(IllegalArgumentException.class, () -> atmOne.withdraw(bankAccount, 50.555));
        assertThrows(InsufficientFundsException.class, () -> atmOne.withdraw(bankAccount, 200));
        atmOne.withdraw(bankAccount, 0);
        assertEquals(100, bankAccount.getBalance());
        atmOne.withdraw(bankAccount, .50);
        assertEquals(99.50, bankAccount.getBalance());
    }
}
