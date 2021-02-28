package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class bankTellerTest {

    @Test
    void createAccountTest() {
    BankTeller tellerOne = new BankTeller();
    //Test Checking
    checkings accOne = tellerOne.createChecking("09100002212345678",0);
    assertEquals(0, accOne.getBalance());
    //Test illegal throws
    assertThrows(IllegalArgumentException.class, ()-> tellerOne.createChecking("09100002212345678",-30));
    assertThrows(IllegalArgumentException.class, ()-> tellerOne.createChecking("09100002212345678", 300.001));
    //Test amount
    checkings accTwo = tellerOne.createChecking("09100002212345678",300);
    assertEquals(300, accTwo.getBalance());
    //Test savings
    savings accThree = tellerOne.createSavings("09100002212345678",0);
    assertEquals(0, accThree.getBalance());
    savings accFour = tellerOne.createSavings("09100002212345678",300);
    assertEquals(300, accFour.getBalance());
}

    @Test
    void closeAccountTest(){
        BankTeller tellerOne = new BankTeller();
        BankAccount accOne = new BankAccount("a@b.com");
        accOne.addAcc(tellerOne.createSavings("09100002212345678",0));
        accOne.addAcc(tellerOne.createChecking("09100002212345678",0));
        tellerOne.closeAccount("checkings", accOne);
        assertThrows(IllegalArgumentException.class, ()-> tellerOne.closeAccount("a", accOne));
        assertEquals(null,accOne.getChecking());
    }
}


