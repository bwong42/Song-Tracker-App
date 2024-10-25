package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents an list of songs called songsToLearn
public class SongsToLearn {
    private ArrayList<Song> songsToLearn; // An array list of song

    /**
     * EFFECTS: SongsToLearn is initially set to empty.
     */
    public SongsToLearn() {
        songsToLearn = new ArrayList<>();

    }

    // EFFECTS: Adds song to SongsToLearn
    public void addSongToSongsToLearn(Song song) {
        this.songsToLearn.add(song);
    }

    // REQUIRES: songsToLearn is non-empty
    // EFFECTS: Removes song from SongsToLearn
    public void removeSongToSongsToLearn(Song song) {
        this.songsToLearn.remove(song);
    }

    public Song getSong(int index) {
        return songsToLearn.get(index);
    }

    public ArrayList<Song> getSongs() {
        return this.songsToLearn;
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: creates a new JSONObject and puts songs in it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("songs", songsToJson());
        return json;
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns songs in SongsToLearn as a JSON array
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song song : songsToLearn) {
            jsonArray.put(song.toJson());
        }

        return jsonArray;
    }

}
