package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
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

    @Test
    void testToJson() {
        SongsLearning songsLearning = new SongsLearning();
        songsLearning.addSongToSongsLearning(new Song("Test Song 1", "Artist 1", "Guitar"));
        songsLearning.addSongToSongsLearning(new Song("Test Song 2", "Artist 2", "Piano"));

        JSONObject json = songsLearning.toJson();

        assertTrue(json.has("songs"));
        JSONArray jsonArray = json.getJSONArray("songs");
        assertEquals(2, jsonArray.length());

        JSONObject song1Json = jsonArray.getJSONObject(0);
        assertEquals("Test Song 1", song1Json.getString("title"));
        assertEquals("Artist 1", song1Json.getString("artist"));
        assertEquals("Guitar", song1Json.getString("instrument"));
        assertFalse(song1Json.getBoolean("favourite"));

        JSONObject song2Json = jsonArray.getJSONObject(1);
        assertEquals("Test Song 2", song2Json.getString("title"));
        assertEquals("Artist 2", song2Json.getString("artist"));
        assertEquals("Piano", song2Json.getString("instrument"));
        assertFalse(song2Json.getBoolean("favourite"));
    }
}
