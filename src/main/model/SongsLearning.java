package model;

import java.util.ArrayList;

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
    }

    // REQUIRES: SongsLearning must be non-empty
    // EFFECTS: Removes song from SongsLearning
    public void removeSongToSongsLearning(Song song) {
        this.songsLearning.remove(song);
    }

    public Song getSong(int index) {
        return songsLearning.get(index);
    }

    public ArrayList<Song> getSongs() {
        return this.songsLearning;
    }
}
