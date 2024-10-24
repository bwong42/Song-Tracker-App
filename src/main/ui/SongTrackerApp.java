package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;

import model.Song;
import model.SongsLearned;
import model.SongsLearning;
import model.SongsToLearn;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents a SongTrackerApp which has the fields songsToLearn,
// songsLearning, songsLearned and Scanner to track input, contains
// all of the lists and allows for them to be viewed, and add or remove
// song from each list. Also declares the jsonWriter and jsonReader fields
// to allow for the saving and loading of our application states.
public class SongTrackerApp {
    private static final String JSON_STORE = "./data/songtracker.json";
    private SongsToLearn songsToLearn;
    private SongsLearning songsLearning;
    private SongsLearned songsLearned;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: starts runSongTracker()
    public SongTrackerApp() {
        runSongTracker();
    }

    // EFFECTS: sets going to true, lets us know to keep program running.
    // sets command to null, initialized our starting classes to all be empty,
    // creates a loop that runs showDisplay() and ends when command.equals("q")
    //
    private void runSongTracker() {
        boolean going = true;
        String command = null;

        initialize();

        while (going) {
            showDisplay();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                going = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThanks for using the application!");
    }

    // MODIFIES: this
    // EFFECTS: Processes command for each specific input string
    // and then passes it on to corresponding method
    private void processCommand(String command) {
        if (command.equals("a")) {
            viewSongsToLearn();
        } else if (command.equals("b")) {
            viewSongsLearning();
        } else if (command.equals("c")) {
            viewSongsLearned();
        } else if (command.equals("s")) {
            saveSongTracker();
        } else if (command.equals("l")) {
            loadSongTracker();
        } else {
            System.out.println("Invalid Choice");
        }
    }

    // EFFECTS: Prints all of the songs in SongsToLearn
    // and gives user option to add or remove song
    private void viewSongsToLearn() {
        boolean inSongsToLearn = true;
        while (inSongsToLearn) {
            viewSongsToLearnOptions();
            if ((songsToLearn.getSongs()).isEmpty()) {
                System.out.println("\nYou have no songs on your learn list");
            } else {
                printSongsSongsToLearn();
            }
            String command = input.next();
            command = command.toLowerCase();
            if (command.equals("a")) {
                addSongToLearn();
            } else if (command.equals("b")) {
                removeSongToLearn();
            } else if (command.equals("c")) {
                markLearning();
            } else if (command.equals("q")) {
                inSongsToLearn = false;
            } else {
                System.out.println("Not valid choice");
            }
        }
    }

    // REQUIRES: songsToLearn is non-empty
    // EFFECTS: Prints all of the songs in songsToLearn with its
    // title, artist and instrument
    private void printSongsSongsToLearn() {
        for (Song song : songsToLearn.getSongs()) {
            System.out.println("\n\nTitle:" + song.getTitle()
                    + "\nArtist:" + song.getArtist()
                    + "\nInstrument:" + song.getInstrument());
        }
    }

    // EFFECTS: Prints all of the options for SongsToLearn
    private void viewSongsToLearnOptions() {
        System.out.println("\n--- Songs To Learn ---");
        System.out.println("\t a -> add a song");
        System.out.println("\t b -> remove a song");
        System.out.println("\t c -> choose song to laern");
        System.out.println("\t q -> return to main menu");
    }

    // EFFECTS: Takes user input for title, artist and instrument,
    // converts this into a song and adds song into songsToLearn
    private void addSongToLearn() {
        System.out.println("Enter a Title");
        String title = input.next();
        System.out.println("Enter an Artist");
        String artist = input.next();
        System.out.println("Enter an Instrument");
        String instrument = input.next();
        Song newSong = new Song(title, artist, instrument);
        songsToLearn.addSongToSongsToLearn(newSong);

    }

    // REQUIRES: songsToLearn must be non-empty
    // EFFECTS: Takes user input to choose the song that they want to
    // mark as currently learning. Marks song as learned, removes song
    // from songsToLearn and adds song to songsLearning
    private void markLearning() {
        System.out.println("Choose an song using the number in the order it appears in (top to bottom)");
        String index = input.next();
        int number = Integer.parseInt(index);
        Song learningSong = songsToLearn.getSong(number - 1);
        songsToLearn.removeSongToSongsToLearn(learningSong);
        songsLearning.addSongToSongsLearning(learningSong);
    }

    // REQUIRES: songsToLearn must be non-empty
    // EFFECTS: Takes user input to choose the song that they want to
    // remove. Removes song from songsToLearn.
    private void removeSongToLearn() {
        System.out.println("Choose an song using the number in the order it appears in (top to bottom)");
        String index = input.next();
        int number = Integer.parseInt(index);
        Song learntSong = songsToLearn.getSong(number - 1);
        songsToLearn.removeSongToSongsToLearn(learntSong);
    }

    // EFFECTS: Prints all of the songs in SongsLearning
    // and gives user option to add or remove song
    private void viewSongsLearning() {
        boolean inSongsLearning = true;
        while (inSongsLearning) {
            viewSongsLearningOptions();
            if ((songsLearning.getSongs()).isEmpty()) {
                System.out.println("\nYou have no songs currently learning");
            } else {
                printSongsSongsLearning();
            }
            String command = input.next();
            command = command.toLowerCase();
            if (command.equals("a")) {
                removeSongLearning();
            } else if (command.equals("b")) {
                markLearned();
            } else if (command.equals("q")) {
                inSongsLearning = false;
            } else {
                System.out.println("Not valid choice");
            }
        }
    }

    // REQUIRES: songsToLearn is non-empty
    // EFFECTS: Prints all of the songs in songsToLearn with its
    // title, artist and instrument
    private void printSongsSongsLearning() {
        for (Song song : songsLearning.getSongs()) {
            System.out.println("\n\nTitle:" + song.getTitle()
                    + "\nArtist:" + song.getArtist()
                    + "\nInstrument:" + song.getInstrument());
        }
    }

    // EFFECTS: Prints all of the options for SongsLearning
    private void viewSongsLearningOptions() {
        System.out.println("\n--- Songs Currently Learning ---");
        System.out.println("\t a -> remove a song");
        System.out.println("\t b -> mark song as finished learning");
        System.out.println("\t q -> return to main menu");
    }

    // REQUIRES: songsLearning must be non-empty
    // EFFECTS: Takes user input to choose the song that they want to
    // remove. Removes song from songsLearning.
    private void removeSongLearning() {
        System.out.println("Choose an song using the number in the order it appears in (top to bottom)");
        String index = input.next();
        int number = Integer.parseInt(index);
        Song learntSong = songsLearning.getSong(number - 1);
        songsLearning.removeSongToSongsLearning(learntSong);
    }

    // REQUIRES: songsLearning must be non-empty
    // EFFECTS: Takes user input to choose the song that they want to
    // mark as learned. Removes song from songsLearning. Mark it as learned
    // and adds songs to songsLearned
    private void markLearned() {
        System.out.println("Choose an song using the number in the order it appears in (top to bottom)");
        String index = input.next();
        int number = Integer.parseInt(index);
        Song learningSong = songsLearning.getSong(number - 1);
        songsLearning.removeSongToSongsLearning(learningSong);
        songsLearned.addSongToSongsLearned(learningSong);
    }

    // EFFECTS: Prints all of the songs in SongsLearned, allows user to favourite
    // song or quit depending on user input
    private void viewSongsLearned() {
        boolean inSongsLearned = true;
        while (inSongsLearned) {
            viewSongsLearnedOptions();
            if ((songsLearned.getSongs()).isEmpty()) {
                System.out.println("\nYou have no songs finished learning");
            } else {
                printSongsSongsLearned();
            }
            String command = input.next();
            command = command.toLowerCase();
            if (command.equals("a")) {
                markSongFavourite();
            } else if (command.equals("q")) {
                inSongsLearned = false;
            } else {
                System.out.println("Not valid choice");
            }
        }
    }

    // REQUIRES: songsLearned is non-empty
    // EFFECTS: Prints all of the songs in songsLearned
    private void printSongsSongsLearned() {
        for (Song song : songsLearned.getSongs()) {
            System.out.println("\n\nTitle:" + song.getTitle()
                    + "\nArtist:" + song.getArtist()
                    + "\nInstrument:" + song.getInstrument());
            if (song.isFavourite()) {
                System.out.println("FAVOURITED");
            }
        }
    }

    // EFFECTS: Prints all of the options for SongsLearned
    private void viewSongsLearnedOptions() {
        System.out.println("\n--- Songs Finished Learning ---");
        System.out.println("\t a -> mark song as favourite");
        System.out.println("\t q -> return to main menu");
    }

    // EFFECTS: Takes user input to choose the song that they want to
    // favourite. Takes song and changes its favourite status to true
    private void markSongFavourite() {
        System.out.println("Choose an song using the number in the order it appears in (top to bottom)");
        String index = input.next();
        int number = Integer.parseInt(index);
        Song learningSong = songsLearned.getSong(number - 1);
        learningSong.makeFavourite();
    }

    // EFFECTS: Prints the display options for the menu of the application
    private void showDisplay() {
        System.out.println("\nChoose an Option");
        System.out.println("\ta -> view songs to learn");
        System.out.println("\tb -> view songs learning");
        System.out.println("\tc -> view songs learned");
        System.out.println("\ts -> save application status to file");
        System.out.println("\tl -> load application status from file");
        System.out.println("\tq -> quit application");
    }

    // EFFECTS: Initialized all of our lists to start and be empty
    private void initialize() {
        input = new Scanner(System.in);
        songsToLearn = new SongsToLearn();
        songsLearning = new SongsLearning();
        songsLearned = new SongsLearned();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: saves the workroom to file
    private void saveSongTracker() {
        try {
            jsonWriter.open();
            jsonWriter.write(songsToLearn, songsLearning, songsLearned);
            jsonWriter.close();
            System.out.println("Saved " + "Song Tracker Status" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadSongTracker() {
        try {
            JSONObject jsonObject = jsonReader.read();
            songsToLearn = jsonReader.parseSongsToLearn(jsonObject.getJSONObject("songsToLearn"));
            songsLearning = jsonReader.parseSongsLearning(jsonObject.getJSONObject("songsLearning"));
            songsLearned = jsonReader.parseSongsLearned(jsonObject.getJSONObject("songsLearned"));
            System.out.println("Loaded " + "Song Tracker Status" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
