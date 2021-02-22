package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class savingsTest {
    
    @Test
    void creatCheckingsTest() {
        savings accOne = new savings("1234",300);
        assertEquals(300, accOne.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> new savings("1234",300.555));
        assertThrows(IllegalArgumentException.class, ()->  new savings("1234",-30));
        savings accFour = new savings("1234",0);
        assertEquals(0, accFour.getBalance());
    }
    @Test
    void compoundedTest(){
        savings accOne = new savings("1234",300);
        accOne.compounded(.02, 5);
        assertEquals(6647, accOne.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> accOne.compounded(.0333,5));
        assertThrows(IllegalArgumentException.class, ()->  accOne.compounded(-0.03,5));
        assertThrows(IllegalArgumentException.class, ()->  accOne.compounded(0.03,0));
        savings accFour = new savings("1234",0);
        assertEquals(0, accFour.getBalance());
    }
}
