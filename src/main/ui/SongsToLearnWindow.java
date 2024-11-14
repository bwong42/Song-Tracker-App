package ui;

import model.Song;
import model.SongsToLearn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a SongsToLearnWindow that is a GUI for the SongsToLearnWindow
// when that button is clicked on the main menu. Has functionalities to view songs
// in SongsToLearn, add songs and remove songs.
public class SongsToLearnWindow extends JFrame {
    private SongsToLearn songsToLearn;
    private JTextArea songsTextArea;

    // MODIFIES: this
    // EFFECTS: Creates a SongsToLearnWindow that initializes all of the layouts,
    // title
    // and size for all of the buttons & songs in SongsToLearnWindow
    public SongsToLearnWindow(SongsToLearn songsToLearn) {
        this.songsToLearn = songsToLearn;

        setTitle("Songs to Learn");
        setSize(600, 400);
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
        JButton addButton = new JButton("Add Song");
        JButton removeButton = new JButton("Remove Song");
        JButton chooseButton = new JButton("Choose Song to Learn");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(chooseButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for the Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSong();
            }
        });

        // Action listener for the Remove button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSong();
            }
        });

        // Action listener for the Choose button
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseSong();
            }
        });

        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: Refreshes the text area with the current list of songs
    private void refreshSongsList() {
        if (songsToLearn.getSongs().isEmpty()) {
            songsTextArea.setText("You have no songs on your learn list.");
        } else {
            StringBuilder songsList = new StringBuilder();
            for (Song song : songsToLearn.getSongs()) {
                songsList.append("Title: ").append(song.getTitle())
                        .append("\nArtist: ").append(song.getArtist())
                        .append("\nInstrument: ").append(song.getInstrument())
                        .append("\n\n");
            }
            songsTextArea.setText(songsList.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a new song to the list
    private void addSong() {
        JTextField titleField = new JTextField();
        JTextField artistField = new JTextField();
        JTextField instrumentField = new JTextField();

        Object[] message = {
                "Title:", titleField,
                "Artist:", artistField,
                "Instrument:", instrumentField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add New Song", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String artist = artistField.getText();
            String instrument = instrumentField.getText();

            if (!title.isEmpty() && !artist.isEmpty() && !instrument.isEmpty()) {
                Song newSong = new Song(title, artist, instrument);
                songsToLearn.addSongToSongsToLearn(newSong);
                refreshSongsList(); // Refresh the list after adding
            } else {
                JOptionPane.showMessageDialog(this, "All fields must be filled in.", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Method to remove a song from the list
    private void removeSong() {
        String titleToRemove = JOptionPane.showInputDialog(this, "Enter the title of the song to remove:");
        if (titleToRemove != null && !titleToRemove.isEmpty()) {
            Song songToRemove = null;
            for (Song song : songsToLearn.getSongs()) {
                if (song.getTitle().equalsIgnoreCase(titleToRemove)) {
                    songToRemove = song;
                    break;
                }
            }

            if (songToRemove != null) {
                songsToLearn.removeSongToSongsToLearn(songToRemove);
                refreshSongsList(); // Refresh the list after removing
            } else {
                JOptionPane.showMessageDialog(this, "Song not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Method to remove a song from the list
    private void chooseSong() {
        if (songsToLearn.getSongs().isEmpty()) {
            JOptionPane.showMessageDialog(this, "There are no songs to choose.", "No Songs Available",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get list of song titles
        String[] songTitles = songsToLearn.getSongs().stream().map(Song::getTitle).toArray(String[]::new);
        String chosenTitle = (String) JOptionPane.showInputDialog(
                this,
                "Select a song to mark as learning:",
                "Choose Song to Learn",
                JOptionPane.PLAIN_MESSAGE,
                null,
                songTitles,
                songTitles[0]);

        if (chosenTitle != null) {
            Song chosenSong = null;
            for (Song song : songsToLearn.getSongs()) {
                if (song.getTitle().equalsIgnoreCase(chosenTitle)) {
                    chosenSong = song;
                    break;
                }
            }

            if (chosenSong != null) {
                songsToLearn.removeSongToSongsToLearn(chosenSong);
                refreshSongsList(); // Refresh the list after marking as learning
                JOptionPane.showMessageDialog(this, "The song \"" + chosenTitle + "\" has been marked as learning.",
                        "Song Chosen", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
