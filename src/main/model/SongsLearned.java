package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents an list of songs called songsLearned
public class SongsLearned {
    private ArrayList<Song> songsLearned; // An array list of song

    /**
     * EFFECTS: SongsLearned is initially set to empty.
     */
    public SongsLearned() {
        songsLearned = new ArrayList<>();

    }

    // EFFECTS: Adds song to songsLearned
    public void addSongToSongsLearned(Song song) {
        this.songsLearned.add(song);
    }

    // REQUIRES: songsLearned must be non-empty
    // EFFECTS: Removes song to songsLearned
    public void removeSongToSongsLearned(Song song) {
        this.songsLearned.remove(song);
    }

    public Song getSong(int index) {
        return songsLearned.get(index);
    }

    public ArrayList<Song> getSongs() {
        return this.songsLearned;
    }

    // EFFECTS: creates a new JSONObject and puts songs in it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("songs", songsToJson());
        return json;
    }

    // EFFECTS: returns songs in songsLearned as a JSON array
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song song : songsLearned) {
            jsonArray.put(song.toJson());
        }

        return jsonArray;
    }

}
