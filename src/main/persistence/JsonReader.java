package persistence;

import model.Song;
import model.SongsToLearn;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SongsToLearn read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private SongsToLearn parseWorkRoom(JSONObject jsonObject) {
        SongsToLearn songsToLearn = new SongsToLearn();
        addSongsSongsToLearn(songsToLearn, jsonObject);
        return songsToLearn;
    }

    // MODIFIES: wr
    // EFFECTS: parses songs from JSON object and adds them to SongsToLearn
    private void addSongsSongsToLearn(SongsToLearn songsToLearn, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("songs");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addSongSongsToLearn(songsToLearn, nextThingy);
        }
    }

    // MODIFIES: song
    // EFFECTS: parses song from JSON object and adds it to SongsToLearn
    private void addSongSongsToLearn(SongsToLearn songsToLearn, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String artist = jsonObject.getString("artist");
        String instrument = jsonObject.getString("instrument");
        Song song = new Song(title, artist, instrument);
        songsToLearn.addSongToSongsToLearn(song);
    }
}
