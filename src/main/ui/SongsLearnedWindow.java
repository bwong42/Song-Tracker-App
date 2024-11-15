package ui;

import model.Song;
import model.SongsLearned;

import javax.swing.*;
import java.awt.*;

// Represents a SongsLearnedWindow that is a GUI for the SongsLearnedWindow
// when that button is clicked on the main menu. Has functionalities to view songs
// in SongsLearned, and mark songs as favourites.
public class SongsLearnedWindow extends JFrame {
    private JTextArea songsTextArea;
    private SongsLearned songsLearned;
    private JLabel starLabel;

    // MODIFIES: this
    // EFFECTS: Creates a SongsLearningWindow that initializes all of the layouts,
    // title and size for all of the buttons & songs in SongsLearningWindow
    public SongsLearnedWindow(SongsLearned songsLearned) {
        this.songsLearned = songsLearned;

        configureFrame();

        setLayout(new BorderLayout());

        // Create a text area to display the list of songs
        songsTextArea = new JTextArea();
        songsTextArea.setEditable(false); // Make the text area read-only

        refreshSongsList();

        // Add the text area to a scroll pane in case the song list is long
        JScrollPane scrollPane = new JScrollPane(songsTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton finishButton = new JButton("Mark Songs as Favourite");
        buttonPanel.add(finishButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // // Action listener for the Finish button
        finishButton.addActionListener(e -> favouriteSong());

        starLabel = new JLabel("★");
        starLabel.setFont(new Font("SansSerif", Font.BOLD, 200));
        starLabel.setForeground(Color.YELLOW);
        starLabel.setHorizontalAlignment(SwingConstants.CENTER);
        starLabel.setVisible(false);
        add(starLabel, BorderLayout.NORTH); // Add to the center of the window

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the initial frame for GUI
    private void configureFrame() {
        setTitle("Songs Finished ");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: Refreshes the text area with the current list of songs
    private void refreshSongsList() {
        if (songsLearned.getSongs().isEmpty()) {
            songsTextArea.setText("You have no songs finished learning.");
        } else {
            StringBuilder songsList = new StringBuilder();
            for (Song song : songsLearned.getSongs()) {
                if (song.isFavourite()) {
                    songsList.append("Favourited\n");
                }
                songsList.append("Title: ").append(song.getTitle())
                        .append("\nArtist: ").append(song.getArtist())
                        .append("\nInstrument: ").append(song.getInstrument());
                songsList.append("\n\n");
            }
            songsTextArea.setText(songsList.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: Method to remove a song from the list
    private void favouriteSong() {
        if (songsLearned.getSongs().isEmpty()) {
            JOptionPane.showMessageDialog(this, "There are no songs to choose.", "No Songs Available",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get list of song titles
        String[] songTitles = songsLearned.getSongs().stream().map(Song::getTitle).toArray(String[]::new);
        String chosenTitle = (String) JOptionPane.showInputDialog(
                this,
                "Choose a song to favourite:",
                "Choose Song to Finish",
                JOptionPane.PLAIN_MESSAGE,
                null,
                songTitles,
                songTitles[0]);

        chooseTitle(chosenTitle);
    }

    // MODIFIES: this
    // EFFECTS: Helper method to reduce line length. Handles choosing title input
    // and prints popup for when option has been chosen.
    private void chooseTitle(String chosenTitle) {
        if (chosenTitle != null) {
            Song chosenSong = null;
            for (Song song : songsLearned.getSongs()) {
                if (song.getTitle().equalsIgnoreCase(chosenTitle)) {
                    chosenSong = song;
                    break;
                }
            }

            if (chosenSong != null) {
                chosenSong.makeFavourite();
                refreshSongsList();
                showStarEffect();

            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays the star label for 2 seconds and then hides it
    private void showStarEffect() {
        starLabel.setVisible(true); // Make the star label visible

        Timer timer = new Timer(2000, e -> starLabel.setVisible(false)); // Hide star after 2 seconds
        timer.setRepeats(false); // Ensure it only runs once
        timer.start();
    }
}
