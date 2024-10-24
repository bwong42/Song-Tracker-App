package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSong {
    private Song testSong;
    
    @BeforeEach
    void runBefore() {
        testSong = new Song("Test", "Me", "Guitar");
    }

    @Test
    void testConstructor(){
        assertEquals("Test", testSong.getTitle());
        assertEquals("Me", testSong.getArtist());
        assertEquals("Guitar", testSong.getInstrument());
        assertFalse(testSong.isFavourite());
    }

    @Test
    void testGetTitle() {
        assertEquals("Test", testSong.getTitle());
    }

    @Test
    void testGetArtist() {
        assertEquals("Me", testSong.getArtist());
    }

    @Test
    void testGetInstrument() {
        assertEquals("Guitar", testSong.getInstrument());
    }

    @Test
    void testGetFavourite() {
        assertFalse(testSong.isFavourite());
    }

    @Test
    void testMakeFavourite() {
        assertTrue(testSong.makeFavourite());
    }
}
