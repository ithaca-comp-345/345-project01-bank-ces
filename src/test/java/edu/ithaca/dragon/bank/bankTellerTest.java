package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTellerTest {

    @Test
    void createAccountTest() {
    //Test Checking
    checkings accOne = createAccount("09100002212345678","checking",0);
    assertEquals(300, accOne.getBalance());
    //Test illegal throws
    assertThrows(IllegalArgumentException.class, ()-> createAccount("accOne","09100002212345678","a",0));
    assertThrows(IllegalArgumentException.class, ()-> createAccount("accOne","09100002212345678","checking",-30));
    assertThrows(IllegalArgumentException.class, ()-> createAccount("accOne","09100002212345678","checking", 300.001));
    //Test amount
    checkings accTwo = createAccount("09100002212345678","checking",300);
    assertEquals(3, acctwo.getBalance());
    //Test savings
    Savings accThree = createAccount("09100002212345678","savings",0);
    assertEquals(0, accThree.getBalance());
    savings AccFour = createAccount("09100002212345678","savings",300);
    assertEquals(300, accFour.getBalance());
}

    @Test
    void closeAccountTest(){
        //createAccount("09100002212345678","checking",0);
        //closeAccount("09100002212345678");
        //assertEquals(accOne,null)
    }


