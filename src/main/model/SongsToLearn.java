package model;

import java.util.ArrayList;

public class SongsToLearn {
    private ArrayList<Song> songsLearning;

    /** 
     * EFFECTS: SongsToLearn is initially set to empty.
     */
    public SongsToLearn(){
        songsLearning = new ArrayList<>();

        }

    // EFFECTS: Adds song to SongsToLearn
    public void addSongToSongsToLearn(Song song){
        this.songsLearning.add(song);
    }

    // REQUIRES: songsLearning is non-empty
    // EFFECTS: Removes song from SongsToLearn
    public void removeSongToSongsToLearn(Song song){
        this.songsLearning.remove(song);
    }

    public Song getSong(int index){
        return songsLearning.get(index);
    }

    public ArrayList<Song> getSongs(){
        return this.songsLearning;
    }

    }


