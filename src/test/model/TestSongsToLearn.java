package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSongsToLearn {
    private SongsToLearn testSongsToLearn;
    private Song s1;
    private Song s2;

    @BeforeEach
    void runBefore() {
        testSongsToLearn = new SongsToLearn();
        s1 = new Song("Test", "Me", "Guitar");
        s2 = new Song("Okay", "You", "Piano");

    }

    @Test
    void testAddOneSong() {
        testSongsToLearn.addSongToSongsToLearn(s1);
        ArrayList<Song> songs = testSongsToLearn.getSongs();
        assertEquals(s1, songs.get(0));
        assertEquals(1, songs.size());

    }

    @Test
    void testAddTwoSongs() {
        testSongsToLearn.addSongToSongsToLearn(s1);
        testSongsToLearn.addSongToSongsToLearn(s2);
        ArrayList<Song> songs = testSongsToLearn.getSongs();
        assertEquals(s1, songs.get(0));
        assertEquals(s2, songs.get(1));
        assertEquals(2, songs.size());

    }

    @Test
    void testGetSong() {
        testSongsToLearn.addSongToSongsToLearn(s1);
        testSongsToLearn.addSongToSongsToLearn(s2);
        assertEquals(s1, testSongsToLearn.getSong(0));
        assertEquals(s2, testSongsToLearn.getSong(1));
    }

    @Test
    void removeSong() {
        testSongsToLearn.addSongToSongsToLearn(s1);
        testSongsToLearn.addSongToSongsToLearn(s2);
        testSongsToLearn.removeSongToSongsToLearn(s1);
        ArrayList<Song> songs = testSongsToLearn.getSongs();
        assertEquals(s2, songs.get(0));
        assertEquals(1, songs.size());

    }
}
