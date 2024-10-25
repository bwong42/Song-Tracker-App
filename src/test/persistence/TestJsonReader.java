package persistence;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import model.Song;
import model.SongsLearned;
import model.SongsLearning;
import model.SongsToLearn;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class TestJsonReader extends TestJson {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            JSONObject jsonObject = reader.read(); // Read entire JSON object
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySongTracker.json");
        try {
            JSONObject jsonObject = reader.read();
            assertTrue(jsonObject.isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSongTracker.json");
        try {
            SongsToLearn songsToLearn = reader.readSongsToLearn();
            SongsLearning songsLearning = reader.readSongsLearning();
            SongsLearned songsLearned = reader.readSongsLearned();
            ArrayList<Song> toLearn = songsToLearn.getSongs();
            ArrayList<Song> learning = songsLearning.getSongs();
            ArrayList<Song> learned = songsLearned.getSongs();
            assertEquals(1, toLearn.size());
            checkSong("Test", "You", "Piano", toLearn.get(0));
            assertEquals(2, learning.size());
            checkSong("a", "a", "a", learning.get(1));
            checkSong("z", "z", "z", learning.get(0));
            assertEquals(1, learned.size());
            checkSong("Star", "Ben", "car", learned.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}