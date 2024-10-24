package model;

import org.json.JSONObject;

// Represents a song that has title, instrument, learned, favourite.
public class Song {
    private String title; // title of song
    private String artist; // artist of song
    private String instrument; // instrument of song to learn
    private Boolean favourite; // is song favourited?

    /*
     * REQUIRES: title has non-zero length, artist has non-zero length,
     * instrument has non-zero length,
     * EFFECTS: title of song set to title, artist of song set to artist,
     * instrument of song set to instrument, learning initially set to false,
     * learned initially set to false, favourite initially set to false.
     */
    public Song(String title, String artist, String instrument) {
        this.title = title;
        this.artist = artist;
        this.instrument = instrument;
        this.favourite = false;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getInstrument() {
        return instrument;
    }

    public Boolean isFavourite() {
        return favourite;
    }

    public Boolean makeFavourite() {
        return favourite = true;
    }

    // MODIFIES: this
    // REQUIRES: 
    // EFFECTS: Puts title, artist and instrument of song to JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("artist", artist);
        json.put("instrument", instrument);
        return json;
    }

}
