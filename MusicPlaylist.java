import java.util.*;
import java.io.*;

// Evelyn Salas
// P1: Music Playlist


// This class allows users to create a music playlist that keeps track the history of queued songs
public class MusicPlaylist {
    public static void main (String[] args) {
        Stack<String> history = new Stack<String>();
        Queue<String> musicQueue = new LinkedList<String>();
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the CSE 122 Music Playlist!");
        intro(console);
        String input = console.nextLine();

        while(!input.equalsIgnoreCase("q")) {
            if (input.equalsIgnoreCase("a")) {
                addSong(console, musicQueue);
            } else if(input.equalsIgnoreCase("p")) {
                playSong(history, musicQueue);
            } else if(input.equalsIgnoreCase("pr")) {
                printHistory(history);
                System.out.println();
            } else if (input.equalsIgnoreCase("c")){
                clearHistory(history);
            } else if (input.equalsIgnoreCase("d")){
                deleteFromHistory(console, history);
            }

            System.out.println();
            intro(console);
            input = console.nextLine();
        }

    }

    //Behavior
    //  - This method genetates a menu of actions a user can pick from to do to music playlist
    //Parameter
    //  - console: user input representing the action they picked
    public static void intro(Scanner console) {
        System.out.println("(A) Add song");
        System.out.println("(P) Play song");
        System.out.println("(Pr) Print history");
        System.out.println("(C) Clear history");
        System.out.println("(D) Delete from history");
        System.out.println("(Q) Quit");
        System.out.println();
        System.out.print("Enter your choice: ");
    }

    //Behavior
    //  - This method adds songs to a music queue so they can be played later
    //Parameters
    //  - console: user input for what songs to add
    //  - musicQueue: stores songs in a queue
    public static void addSong(Scanner console, Queue<String> musicQueue) {
        System.out.print("Enter song name: ");
        String song = console.nextLine();
        musicQueue.add(song);
        System.out.println("Successfully added " + song);
        System.out.println();
    }

    //Behavior
    //  - This method plays songs from the music queue and adds them to the playlist's history
    //Parameters
    //  - history: stores the songs that have already been played
    //  - musicQueue: stores queued songs
    //Exception
    //  - if the music queue is empty, an IllegalStateException is thrown
    public static void playSong(Stack<String> history, Queue<String> musicQueue) {
        if(musicQueue.isEmpty()){
            throw new IllegalStateException();
        }
        String song = musicQueue.peek();
        history.add(song);
        song = musicQueue.remove();
        System.out.print("Playing song: " + song);
        System.out.println();
        System.out.println();
    }

    //Behavior
    //  - This method prints out the songs that have already been played
    //Parameter
    //  - history: stores played songs
    //Exception
    //  - IllegalStateException is thrown if no songs have been played yet
    public static void printHistory(Stack<String> history) {
        if(history.isEmpty()) {
            throw new IllegalStateException();
        }
        String temp = history.peek();
        history.pop();
        System.out.println("    "+ temp);
        if (!history.isEmpty()) {
            printHistory(history);
        }
        history.push(temp);
    }

    //Behavior
    //  - This method deletes the list of songs that have already been played
    //Parameter
    //  - history: stores played songs
    //Returns
    //  - empty list of played songs
    public static void clearHistory(Stack<String> history) {
        history.clear();
    }

    //Behavior
    //  - This method deletes songs from the recent history or from the begining of history
    //Parameters
    //  - console: takes in user input for the number of songs they want to delete
    //  - history: stores played songs
    //Exception
    //  - Throws IllegalArgumentException if the number of songs deleted is greater than the
    //    songs in playlist history
    //Returns
    //  - history of played songs minus the songs removed
    public static void deleteFromHistory(Scanner console, Stack<String> history) {
        System.out.println("A positive number will delete from recent history.");
        System.out.println("A negative number will delete from the beginning of history.");
        System.out.print("Enter number of songs to delete: ");
        String input = console.nextLine();
        int num = Integer.parseInt(input);

        if (Math.abs(num) > history.size()) {
            throw new IllegalArgumentException();
        }
        if (num == 0) {

        } if (num > 0) {
            if (num == history.size()) {
                clearHistory(history);
            } else {
                history.pop();
            }
        } if (num < 0) {
            if(Math.abs(num) == history.size()) {
                clearHistory(history);
            } else {
                Queue<String> storage = new LinkedList<String>();
                while (!history.isEmpty()) {
                    storage.add(history.pop());
                }
                for (int i = 0; i < Math.abs(num); i++) {
                    history.add(storage.remove());
                }
                storage.clear();
                while (!history.isEmpty()) {
                    storage.add(history.pop());
                }
                for (int i = 0; i < Math.abs(num); i++) {
                    history.add(storage.remove());
                }
            }
        }
    }
}