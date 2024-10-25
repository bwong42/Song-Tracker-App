package persistence;

import org.junit.jupiter.api.Test;

import model.SongsToLearn;
import model.SongsLearning;
import model.Song;
import model.SongsLearned;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestJsonWriter extends TestJson {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            SongsToLearn songsToLearn = new SongsToLearn();
            JsonWriter writer = new JsonWriter("./data/my\\0illegal:/fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            SongsToLearn songsToLearn = new SongsToLearn();
            SongsLearning songsLearning = new SongsLearning();
            SongsLearned songsLearned = new SongsLearned();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySongTracker.json");
            writer.open();
            writer.write(songsToLearn, songsLearning, songsLearned);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySongTracker.json");
            songsToLearn = reader.readSongsToLearn();
            songsLearning = reader.readSongsLearning();
            songsLearned = reader.readSongsLearned();
            ArrayList<Song> sizeToLearn = songsToLearn.getSongs();
            assertEquals(0, sizeToLearn.size());
            ArrayList<Song> sizeLearning = songsLearning.getSongs();
            assertEquals(0, sizeLearning.size());
            ArrayList<Song> sizeLearned = songsLearned.getSongs();
            assertEquals(0, sizeLearned.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            SongsToLearn toLearn = new SongsToLearn();
            SongsLearning learning = new SongsLearning();
            SongsLearned learned = new SongsLearned();
            toLearn.addSongToSongsToLearn(new Song("Happy", "Me", "Guitar"));
            toLearn.addSongToSongsToLearn(new Song("Test", "You", "Piano"));
            learning.addSongToSongsLearning(new Song("a", "b", "c"));
            learning.addSongToSongsLearning(new Song("z", "z", "z"));
            learned.addSongToSongsLearned(new Song("none", "ok", "xylophone"));
            learned.addSongToSongsLearned(new Song("Star", "Ben", "car"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSongTracker.json");
            writer.open();
            writer.write(toLearn, learning, learned);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSongTracker.json");
            toLearn = reader.readSongsToLearn();
            learning = reader.readSongsLearning();
            learned = reader.readSongsLearned();
            ArrayList<Song> songsToLearn = toLearn.getSongs();
            assertEquals(2, songsToLearn.size());
            checkSong("Happy", "Me", "Guitar", songsToLearn.get(0));
            checkSong("Test", "You", "Piano", songsToLearn.get(1));
            ArrayList<Song> songsLearning = learning.getSongs();
            assertEquals(2, songsLearning.size());
            checkSong("a", "b", "c", songsLearning.get(0));
            checkSong("z", "z", "z", songsLearning.get(1));
            ArrayList<Song> songsLearned = learned.getSongs();
            assertEquals(2, songsLearned.size());
            checkSong("none", "ok", "xylophone", songsLearned.get(0));
            checkSong("Star", "Ben", "car", songsLearned.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}