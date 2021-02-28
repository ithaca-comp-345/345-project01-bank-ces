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
        savings accOne = new savings("1234",2000);
        
        accOne.compounded(5, 8);
        
        assertEquals(2979, accOne.getBalance());
        assertThrows(IllegalArgumentException.class, ()->  accOne.compounded(-5,3));
        assertThrows(IllegalArgumentException.class, ()->  accOne.compounded(5,-3));
        assertThrows(IllegalArgumentException.class, ()->  accOne.compounded(0,3));
        savings accFour = new savings("1234",0);
        assertEquals(0, accFour.getBalance());
    }
}
