package model;

import java.util.ArrayList;

public class SongsLearning {
    private ArrayList<Song> songsLearning;

    /** 
     * EFFECTS: SongsLearning is initially set to empty.
     */
    public SongsLearning(){
        songsLearning = new ArrayList<>();

        }

    // EFFECTS: Adds song to SongsLearning
    public void addSongToSongsLearning(Song song){
        this.songsLearning.add(song);
    }

    // REQUIRES: SongsLearning must be non-empty
    // EFFECTS: Removes song from SongsLearning
    public void removeSongToSongsLearning(Song song){
        this.songsLearning.remove(song);
    }

    public ArrayList<Song> getSongs(){
        return this.songsLearning;
    }
    }


