package ui;

import model.Song;
import model.SongsLearning;
import model.SongsLearned;

import javax.swing.*;
import java.awt.*;

// Represents a SongsLearningWindow that is a GUI for the SongsLearningWindow
// when that button is clicked on the main menu. Has functionalities to view songs
// in SongsLearning, remove songs and mark songs as finished learning.
public class SongsLearningWindow extends JFrame {
    private JTextArea songsTextArea;
    private SongsLearning songsLearning;
    private SongsLearned songsLearned;

    // MODIFIES: this
    // EFFECTS: Creates a SongsLearningWindow that initializes all of the layouts,
    // title and size for all of the buttons & songs in SongsLearningWindow
    public SongsLearningWindow(SongsLearning songsLearning, SongsLearned songsLearned) {
        this.songsLearning = songsLearning;
        this.songsLearned = songsLearned;

        setTitle("Songs Learning");
        setSize(600, 600);
        setLocationRelativeTo(null); // Centers the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only closes this window

        setLayout(new BorderLayout());

        // Create a text area to display the list of songs
        songsTextArea = new JTextArea();
        songsTextArea.setEditable(false); // Make the text area read-only

        refreshSongsList();

        // Add the text area to a scroll pane in case the song list is long
        JScrollPane scrollPane = new JScrollPane(songsTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton removeButton = new JButton("Remove Song");
        JButton finishButton = new JButton("Mark Songs as Finished Learning");
        buttonPanel.add(finishButton);
        buttonPanel.add(removeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for the Remove button
        removeButton.addActionListener(e -> removeSong());

        // // Action listener for the Finish button
        finishButton.addActionListener(e -> finishSong());

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Refreshes the text area with the current list of songs
    private void refreshSongsList() {
        if (songsLearning.getSongs().isEmpty()) {
            songsTextArea.setText("You have no songs currently learning.");
        } else {
            StringBuilder songsList = new StringBuilder();
            for (Song song : songsLearning.getSongs()) {
                songsList.append("Title: ").append(song.getTitle())
                        .append("\nArtist: ").append(song.getArtist())
                        .append("\nInstrument: ").append(song.getInstrument())
                        .append("\n\n");
            }
            songsTextArea.setText(songsList.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: Method to remove a song from the list
    private void removeSong() {
        String titleToRemove = JOptionPane.showInputDialog(this, "Enter the title of the song to remove:");
        if (titleToRemove != null && !titleToRemove.isEmpty()) {
            Song songToRemove = null;
            for (Song song : songsLearning.getSongs()) {
                if (song.getTitle().equalsIgnoreCase(titleToRemove)) {
                    songToRemove = song;
                    break;
                }
            }
            if (songToRemove != null) {
                songsLearning.removeSongToSongsLearning(songToRemove);
                refreshSongsList(); // Refresh the list after removing
            } else {
                JOptionPane.showMessageDialog(this, "Song not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Method to remove a song from the list
    private void finishSong() {
        if (songsLearning.getSongs().isEmpty()) {
            JOptionPane.showMessageDialog(this, "There are no songs to choose.", "No Songs Available",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get list of song titles
        String[] songTitles = songsLearning.getSongs().stream().map(Song::getTitle).toArray(String[]::new);
        String chosenTitle = (String) JOptionPane.showInputDialog(
                this,
                "Choose a song to finish learning:",
                "Choose Song to Finish",
                JOptionPane.PLAIN_MESSAGE,
                null,
                songTitles,
                songTitles[0]);

        if (chosenTitle != null) {
            Song chosenSong = null;
            for (Song song : songsLearning.getSongs()) {
                if (song.getTitle().equalsIgnoreCase(chosenTitle)) {
                    chosenSong = song;
                    break;
                }
            }

            if (chosenSong != null) {
                songsLearning.removeSongToSongsLearning(chosenSong);
                songsLearned.addSongToSongsLearned(chosenSong);
                refreshSongsList();
                JOptionPane.showMessageDialog(this, "The song \"" + chosenTitle + "\" has been marked as learning.",
                        "Song Chosen", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
