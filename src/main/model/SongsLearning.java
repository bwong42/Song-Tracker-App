package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents an list of songs called songsLearning
public class SongsLearning {
    private ArrayList<Song> songsLearning; // An array list of song

    /**
     * EFFECTS: SongsLearning is initially set to empty.
     */
    public SongsLearning() {
        songsLearning = new ArrayList<>();

    }

    // EFFECTS: Adds song to SongsLearning
    public void addSongToSongsLearning(Song song) {
        this.songsLearning.add(song);
        EventLog.getInstance().logEvent(new Event("Started learning song:" + song.getTitle()));
    }

    // REQUIRES: SongsLearning must be non-empty
    // EFFECTS: Removes song from SongsLearning
    public void removeSongToSongsLearning(Song song) {
        this.songsLearning.remove(song);
        EventLog.getInstance().logEvent(new Event("Stopped learning song:" + song.getTitle()));
    }

    public Song getSong(int index) {
        return songsLearning.get(index);
    }

    public ArrayList<Song> getSongs() {
        return this.songsLearning;
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
    // EFFECTS: returns songs in songsLearning as a JSON array
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song song : songsLearning) {
            jsonArray.put(song.toJson());
        }

        return jsonArray;
    }
}
