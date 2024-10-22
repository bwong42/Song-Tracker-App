package model;

import java.util.ArrayList;

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

}
