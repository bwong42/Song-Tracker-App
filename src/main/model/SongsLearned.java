package model;

import java.util.ArrayList;

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

}
