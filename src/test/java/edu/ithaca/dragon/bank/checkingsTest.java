package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class checkingsTest {
    @Test
    void creatCheckingsTest() {
        checkings accOne = new checkings("1234",300);
        assertEquals(300, accOne.getBalance()); //equivalence class
        assertThrows(IllegalArgumentException.class, ()-> new checkings("1234",300.555)); //equivalence class
        assertThrows(IllegalArgumentException.class, ()->  new checkings("1234",-30)); //equivalence class
        checkings accFour = new checkings("1234",0); 
        assertEquals(0, accFour.getBalance()); //equivalence class
    }
}
