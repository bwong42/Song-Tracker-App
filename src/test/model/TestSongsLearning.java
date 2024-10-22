package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSongsLearning {
    private SongsLearning testSongsLearning;
    private Song s1;
    private Song s2;

    @BeforeEach
    void runBefore() {
        testSongsLearning = new SongsLearning();
        s1 = new Song("Test", "Me", "Guitar");
        s2 = new Song("Okay", "You", "Piano");

    }

    @Test
    void testAddOneSong() {
        testSongsLearning.addSongToSongsLearning(s1);
        ArrayList<Song> songs = testSongsLearning.getSongs();
        assertEquals(s1, songs.get(0));
        assertEquals(1, songs.size());

    }

    @Test
    void testAddTwoSongs() {
        testSongsLearning.addSongToSongsLearning(s1);
        testSongsLearning.addSongToSongsLearning(s2);
        ArrayList<Song> songs = testSongsLearning.getSongs();
        assertEquals(s1, songs.get(0));
        assertEquals(s2, songs.get(1));
        assertEquals(2, songs.size());

    }

    @Test
    void testGetSong() {
        testSongsLearning.addSongToSongsLearning(s1);
        testSongsLearning.addSongToSongsLearning(s2);
        assertEquals(s1, testSongsLearning.getSong(0));
        assertEquals(s2, testSongsLearning.getSong(1));
    }

    @Test
    void removeSong() {
        testSongsLearning.addSongToSongsLearning(s1);
        testSongsLearning.addSongToSongsLearning(s2);
        testSongsLearning.removeSongToSongsLearning(s1);
        ArrayList<Song> songs = testSongsLearning.getSongs();
        assertEquals(s2, songs.get(0));
        assertEquals(1, songs.size());

    }
}
