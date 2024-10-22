package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSongsLearned {
    private SongsLearned testSongsLearned;
    private Song s1;
    private Song s2;

    @BeforeEach
    void runBefore() {
        testSongsLearned = new SongsLearned();
        s1 = new Song("Test", "Me", "Guitar");
        s2 = new Song("Okay", "You", "Piano");

    }

    @Test
    void testAddOneSong() {
        testSongsLearned.addSongToSongsLearned(s1);
        ArrayList<Song> songs = testSongsLearned.getSongs();
        assertEquals(s1, songs.get(0));
        assertEquals(1, songs.size());

    }

    @Test
    void testAddTwoSongs() {
        testSongsLearned.addSongToSongsLearned(s1);
        testSongsLearned.addSongToSongsLearned(s2);
        ArrayList<Song> songs = testSongsLearned.getSongs();
        assertEquals(s1, songs.get(0));
        assertEquals(s2, songs.get(1));
        assertEquals(2, songs.size());

    }

    @Test
    void testGetSong() {
        testSongsLearned.addSongToSongsLearned(s1);
        testSongsLearned.addSongToSongsLearned(s2);
        assertEquals(s1, testSongsLearned.getSong(0));
        assertEquals(s2, testSongsLearned.getSong(1));
    }

    @Test
    void removeSong() {
        testSongsLearned.addSongToSongsLearned(s1);
        testSongsLearned.addSongToSongsLearned(s2);
        testSongsLearned.removeSongToSongsLearned(s1);
        ArrayList<Song> songs = testSongsLearned.getSongs();
        assertEquals(s2, songs.get(0));
        assertEquals(1, songs.size());

    }
}
