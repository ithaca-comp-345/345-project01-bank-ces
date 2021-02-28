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
        assertEquals(200, atmOne.checkBalance(bankAccount, bankAccount.getSaving())); //Correct Balance displays 
        
        //Savings account only
        BankAccount bankAccountTwo = new BankAccount("a@b.com");  //Balance with a Double
        savings accTwo = new savings("09100002212345678",10.55);
        bankAccountTwo.addAcc(accTwo);
        assertEquals(10.55, atmOne.checkBalance(bankAccountTwo, bankAccountTwo.getSaving()));
        
       
        //Checkings account only
        BankAccount bankAccountFour = new BankAccount("a@b.com");
        checkings accOneC = new checkings("09100002212345678",20);
        bankAccountFour.addAcc(accOneC);
        assertEquals(20, atmOne.checkBalance(bankAccountFour, bankAccountFour.getChecking())); 

        //Checkings account only
        BankAccount bankAccountFive = new BankAccount("a@b.com");
        checkings accTwoC = new checkings("09100002212345678",20.55);
        bankAccountFive.addAcc(accTwoC);
        assertEquals(20.55, atmOne.checkBalance(bankAccountFive, bankAccountFive.getChecking())); 

        //Checkings and savings account 
        BankAccount bankAccountSix = new BankAccount("a@b.com");
        savings accThree = new savings("09100002212345678",10.55);
        checkings accThreeC = new checkings("09100002212345678",20.55);
        bankAccountSix.addAcc(accThreeC);
        bankAccountSix.addAcc(accThree);
        assertEquals(20.55, atmOne.checkBalance(bankAccountSix, bankAccountSix.getChecking())); 
        assertEquals(10.55, atmOne.checkBalance(bankAccountSix, bankAccountSix.getSaving()));

        

    }
    
    @Test
    void depositTest() throws IllegalArgumentException{
        ATM atmOne = new ATM();
        BankAccount bankAccount = new BankAccount("a@b.com");
        savings accOne = new savings("09100002212345678",200);
        bankAccount.addAcc(accOne);
        //test postive number
        atmOne.deposit(bankAccount,bankAccount.getSaving(), 100);
        assertEquals(300, atmOne.checkBalance(bankAccount, bankAccount.getSaving())); 
        //test negative number
        assertThrows(IllegalArgumentException.class, ()->atmOne.deposit(bankAccount, bankAccount.getSaving(), 100.111));
        assertThrows(IllegalArgumentException.class, ()->atmOne.deposit(bankAccount, bankAccount.getSaving(), -1));

    }

    @Test
    void withdrawTest() throws IllegalArgumentException, InsufficientFundsException {
        ATM atmOne = new ATM();
        BankAccount bankAccount = new BankAccount("a@b.com");
        savings accOne = new savings("09100002212345678",200);
        bankAccount.addAcc(accOne);
        atmOne.withdraw(bankAccount, bankAccount.getSaving(), 100);
        assertEquals(100, bankAccount.getSaving().getBalance());
        assertThrows(IllegalArgumentException.class, () -> atmOne.withdraw(bankAccount, bankAccount.getSaving(), -1));
        assertThrows(IllegalArgumentException.class, ()->atmOne.withdraw(bankAccount, bankAccount.getSaving(), 100.111));
        assertThrows(IllegalArgumentException.class, () -> atmOne.withdraw(bankAccount, bankAccount.getSaving(), 50.555));
        assertThrows(InsufficientFundsException.class, () -> atmOne.withdraw(bankAccount, bankAccount.getSaving(), 200));
        atmOne.withdraw(bankAccount, bankAccount.getSaving(), 0);
        assertEquals(100, bankAccount.getSaving().getBalance());
        atmOne.withdraw(bankAccount, bankAccount.getSaving(), .50);
        assertEquals(99.50, bankAccount.getSaving().getBalance());
        
        
        checkings accTwo = new checkings("09100002212345678",200);
        bankAccount.addAcc(accTwo);
        atmOne.withdraw(bankAccount, bankAccount.getChecking(), 100);
        assertEquals(100, bankAccount.getChecking().getBalance());
        assertThrows(IllegalArgumentException.class, () -> atmOne.withdraw(bankAccount, bankAccount.getChecking(), -1));
        assertThrows(IllegalArgumentException.class, ()->atmOne.withdraw(bankAccount, bankAccount.getChecking(), 100.111));
        assertThrows(IllegalArgumentException.class, () -> atmOne.withdraw(bankAccount, bankAccount.getChecking(), 50.555));
        assertThrows(InsufficientFundsException.class, () -> atmOne.withdraw(bankAccount, bankAccount.getChecking(), 200));
        atmOne.withdraw(bankAccount, bankAccount.getChecking(), 0);
        assertEquals(100, bankAccount.getChecking().getBalance());
        atmOne.withdraw(bankAccount, bankAccount.getChecking(), .50);
        assertEquals(99.50, bankAccount.getChecking().getBalance());
    }
    @Test
    void transferTest() throws InsufficientFundsException { 
        ATM atmOne = new ATM();
        //transfer (amount, accountFrom, accountTo)
        
        BankAccount bankAccount = new BankAccount("a@b.com");
        savings accOne = new savings("09100002212345678",200);
        checkings accTwo = new checkings("09100002212345678",200);
        bankAccount.addAcc(accOne);
        bankAccount.addAcc(accTwo);
        
        BankAccount bankAccountTwo = new BankAccount("seanb@gmai.com");
        savings accThree = new savings("09100002212345678",200);
        checkings accFour = new checkings("09100002212345678",200);
        bankAccountTwo.addAcc(accThree);
        bankAccountTwo.addAcc(accFour);

        //Base case 
        atmOne.transfer(bankAccount, bankAccount, 50, bankAccount.getChecking(),bankAccount.getSaving()); 
        //equivalence class
        assertEquals(150, bankAccount.getChecking().getBalance()); //Tranfer int amount to second account
        assertEquals(250, bankAccount.getSaving().getBalance()); //Remaining balance of first account
        //equivalence class
        assertThrows(InsufficientFundsException.class, () -> atmOne.transfer(bankAccount, bankAccount, 200, bankAccount.getChecking(),bankAccount.getSaving())); //Not enough money to transfer
        //border case
        assertThrows(IllegalArgumentException.class, () -> atmOne.transfer(bankAccount, bankAccount, 15.555, bankAccount.getChecking(),bankAccount.getSaving())); //too many decimals
        //border case
        assertThrows(IllegalArgumentException.class, () -> atmOne.transfer(bankAccount, bankAccount, -5, bankAccount.getChecking(),bankAccount.getSaving())); //Can't transfer negative amount
        
        //equivalence class tranfer between two bank
        atmOne.transfer(bankAccount, bankAccountTwo, 50, bankAccount.getChecking(), bankAccountTwo.getChecking()); 
        assertEquals(250, bankAccountTwo.getChecking().getBalance()); 
        assertEquals(100, bankAccount.getChecking().getBalance());
    }
    @Test
    void transActionHistoryTest() throws IllegalArgumentException, InsufficientFundsException {
        ATM atmOne = new ATM();
        BankAccount bankAccount = new BankAccount("a@b.com");
        savings accOne = new savings("09100002212345678",200);
        checkings accTwo = new checkings("09100002212345678",200);
        bankAccount.addAcc(accOne);
        bankAccount.addAcc(accTwo);
        atmOne.checkBalance(bankAccount, accOne);
        atmOne.withdraw(bankAccount, bankAccount.getSaving(), 100);
        assertEquals(2, bankAccount.getTransActHistory().size());
        atmOne.withdraw(bankAccount, bankAccount.getChecking(), 100);
        assertEquals(3, bankAccount.getTransActHistory().size());
    }


    @Test
    void ATMIntegrationTest() throws IllegalArgumentException, InsufficientFundsException{
        ATM atm = new ATM();
        BankAccount bankAccount = new BankAccount("seanblackford1@gmail.com");
        savings accOne = new savings("09100002212345678",100);
        checkings accTwo = new checkings("09100002212348754",500);
        bankAccount.addAcc(accOne);
        bankAccount.addAcc(accTwo);
        assertEquals(100, atm.checkBalance(bankAccount, accOne));
        assertEquals(500,  atm.checkBalance(bankAccount, accTwo));
       
        atm.withdraw(bankAccount, bankAccount.getSaving(), 100);
        assertEquals(0, atm.checkBalance(bankAccount, accOne));
        assertThrows(InsufficientFundsException.class, () -> atm.withdraw(bankAccount, bankAccount.getSaving(), 1000));
        atm.transfer(bankAccount, bankAccount, 50, bankAccount.getChecking(), bankAccount.getSaving()); 
        assertEquals(50, atm.checkBalance(bankAccount, accOne));
    }

    @Test
    void ATMSystemTest() throws IllegalArgumentException, InsufficientFundsException{
        ATM atm = new ATM();
        CentralBank chase = new CentralBank();
        BankAccount bankAccount = new BankAccount("seanblackford1@gmail.com");
        BankAccount bankAccountTwo = new BankAccount("sblackford@gmail.com");

        savings accOne = new savings("09100002212345678",100);
        checkings accTwo = new checkings("09100002212348754",500);
        bankAccount.addAcc(accOne);
        bankAccount.addAcc(accTwo);
        chase.addAccount(bankAccount);
        chase.addAccount(bankAccountTwo);

        assertEquals(100, atm.checkBalance(bankAccount, accOne));
        assertEquals(500,  atm.checkBalance(bankAccount, accTwo));
        atm.withdraw(bankAccount, bankAccount.getSaving(), 100);
        atm.deposit(bankAccount, bankAccount.getChecking(), 200);
        assertEquals(null, chase.findAccount("sblackford@ithaca.edu"));
        BankAccount acc = chase.findAccount("seanblackford1@gmail.com");
        assertEquals(4, acc.getTransActHistory().size());

    }

    @Test
    void UseCaseTest() throws IllegalArgumentException, InsufficientFundsException{
        ATM atm = new ATM();
        BankAccount bankAccount = new BankAccount("seanblackford1@gmail.com");
        savings accOne = new savings("09100002212345678",100);
        checkings accTwo = new checkings("09100002212348754",500);
        bankAccount.addAcc(accOne);
        bankAccount.addAcc(accTwo);
        atm.checkBalance(bankAccount, bankAccount.getSaving());
        atm.checkBalance(bankAccount, bankAccount.getChecking());
        atm.withdraw(bankAccount, bankAccount.getSaving(), 100);
        atm.deposit(bankAccount, bankAccount.getChecking(), 200);
        atm.checkBalance(bankAccount, bankAccount.getSaving());
        atm.checkBalance(bankAccount, bankAccount.getChecking());
        atm.transfer(bankAccount, bankAccount, 300, bankAccount.getChecking(), bankAccount.getSaving());
        atm.checkTransActionHistory(bankAccount);
    }
}
