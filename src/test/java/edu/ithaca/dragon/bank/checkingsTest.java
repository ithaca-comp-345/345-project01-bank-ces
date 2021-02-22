package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class checkingsTest {
    @Test
    void creatCheckingsTest() {
        checkings accOne = new checkings("1234",300);
        assertEquals(300, accOne.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> new checkings("1234",300.555));
        assertThrows(IllegalArgumentException.class, ()->  new checkings("1234",-30));
        checkings accFour = new checkings("1234",0);
        assertEquals(0, accFour.getBalance());
    }
}
