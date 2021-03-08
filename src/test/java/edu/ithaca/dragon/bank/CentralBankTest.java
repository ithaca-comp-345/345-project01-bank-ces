package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class CentralBankTest {
    
    @Test
    void findAccount(){
        CentralBank chase = new CentralBank();
        BankAccount bankAccount = new BankAccount("a@b.com");
        BankAccount bankAccountOne = new BankAccount("sean@b.com");
        BankAccount bankAccountTwo = new BankAccount("twsdsdsd@b.com");
        BankAccount bankAccountThree = new BankAccount("hjyjdfc@b.com");
        chase.addAccount(bankAccount);
        chase.addAccount(bankAccountOne);
        chase.addAccount(bankAccountTwo);
        chase.addAccount(bankAccountThree);
        assertEquals("a@b.com", chase.findAccount("a@b.com").getEmail()); //equivalence class
    }
    @Test
    void getAllAccounts(){
        CentralBank chase = new CentralBank();
        BankAccount bankAccount = new BankAccount("a@b.com");
        BankAccount bankAccountOne = new BankAccount("sean@b.com");
        BankAccount bankAccountTwo = new BankAccount("twsdsdsd@b.com");
        BankAccount bankAccountThree = new BankAccount("hjyjdfc@b.com");
        chase.addAccount(bankAccount);
        chase.addAccount(bankAccountOne);
        chase.addAccount(bankAccountTwo);
        chase.addAccount(bankAccountThree);
        ArrayList<BankAccount> bankAccounts = chase.getAllAccounts();
        assertEquals(4, bankAccounts.size()); //equivalence class
    }
}
