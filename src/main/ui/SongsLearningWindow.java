package ui;

import model.Song;
import model.SongsLearning;
import model.SongsToLearn;
import model.SongsLearned;

import javax.swing.*;
import java.awt.*;

// Represents a SongsLearningWindow that is a GUI for the SongsLearningWindow
// when that button is clicked on the main menu. Has functionalities to view songs
// in SongsToLearn, remove songs and mark songs as finished learning.
public class SongsLearningWindow extends JFrame {
    private JTextArea songsTextArea;
    private SongsLearning songsLearning;
    private SongsLearned songsLearned;

    public SongsLearningWindow(SongsLearning songsLearning, SongsLearned songsLearned) {
            this.songsLearning = songsLearning;
            this.songsLearned = songsLearned;
    
            setTitle("Songs to Learn");
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
            JButton addButton = new JButton("Add Song");
            JButton removeButton = new JButton("Remove Song");
            JButton chooseButton = new JButton("Choose Song to Learn");
            buttonPanel.add(addButton);
            buttonPanel.add(removeButton);
            buttonPanel.add(chooseButton);
    
            add(buttonPanel, BorderLayout.SOUTH);

            // Action listener for the Add button
            addButton.addActionListener(e -> addSong());

            // Action listener for the Remove button
            removeButton.addActionListener(e -> removeSong());
    
            chooseButton.addActionListener(e -> chooseSong());
    
            setVisible(true);
    
        }
}
