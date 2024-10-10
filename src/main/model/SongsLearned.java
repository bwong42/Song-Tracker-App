package model;

import java.util.ArrayList;

public class SongsLearned {
    private ArrayList<Song> songsLearned;

    /** 
     * EFFECTS: SongsLearned is initially set to empty.
     */
    public SongsLearned(){
        songsLearned = new ArrayList<>();

        }

    // EFFECTS: Adds song to songsLearned
    public void addSongToSongsLearned(Song song){
        this.songsLearned.add(song);
    }

    // REQUIRES: songsLearned must be non-empty
    // EFFECTS: Removes song to songsLearned
    public void removeSongToSongsLearned(Song song){
        this.songsLearned.remove(song);
    }

    public ArrayList<Song> getSongs(){
        return this.songsLearned;
    }


    }


