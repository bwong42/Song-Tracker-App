package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
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

    @Test
    void testToJson() {
        SongsLearned songsLearned = new SongsLearned();
        songsLearned.addSongToSongsLearned(new Song("Test Song 1", "Artist 1", "Guitar"));
        songsLearned.addSongToSongsLearned(new Song("Test Song 2", "Artist 2", "Piano"));

        JSONObject json = songsLearned.toJson();

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
