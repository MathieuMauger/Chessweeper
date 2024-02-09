package Controller;

import Models.*;
import View.*;

import java.util.Random;
import java.util.Scanner;

public class Initialization {
    static Scanner scanner = new Scanner(System.in);

    // Method to ask player for name and replace spaces with underscores
    public static String askName(String number, Player[] pList){
        System.out.print("Player " + number + ", please input your name:\n>"); // Prompting user for input
        String name = scanner.nextLine(); // Getting user input

        boolean nameAlreadyInUse = false;
        for (Player p : pList) {
            if (p!=null && p.getName().equals(name)) {
                nameAlreadyInUse = true;
                break;
            }
        }

        // Checking if name length is valid and if it contains invalid characters
        if ((name.length() < 2) || (name.length()) > 10 || (name.contains(",")) || (name.contains(";")) || (name.contains("&"))){
            System.out.println("Chosen name is either too long or too short, or contains the character '&', ';' or ','. Please try again."); // Prompting user to try again
            return askName(number, pList); // Calling the method recursively until valid name is provided
        }
        else if(nameAlreadyInUse){
            System.out.println("Chosen name is already in use, please use another.");
            return askName(number, pList); // Calling the method recursively until valid name is provided
        }
        else {
            return name.replace(" ", "_"); // Replacing spaces with underscores and returning the name
        }

    }

    // Method to create a player and place them on the board
    public static Player createPlayer(String[][] board, int[] position, String number, int score, Player[] pList){
        board[position[0]][position[1]] = number; // Placing player on the board
        String name = askName(number, pList);

        return new Player(name, new int[]{position[0],position[1]}, number, score ); // Instantiating Player object with name, position, and number
    }

    // Method to place players on the board based on player count
    public static Player[] placePlayers(String[][] board, int playerCount){
        int middleH = ((board.length)/2);
        int middleV = ((board[0].length)/2);
        if(playerCount == 2){ // If there are two players
            Player[] pList = new Player[2];

            Player p1 = createPlayer(board, new int[]{middleV,middleH},"1", 0, pList); // Create player 1
            pList[0] = p1;
            Player p2 = createPlayer(board, new int[]{middleV-1,middleH},"2", 0, pList); // Create player 2
            pList[1] = p2;

            System.out.println(p1.getName()); // Print player 1's name
            System.out.println(p2.getName()); // Print player 2's name
            return pList; // Return an array of players
        }
        else if(playerCount == 3){ // If there are three players
            Player[] pList = new Player[3];
            Player p1 = createPlayer(board, new int[]{middleV-1,middleH+1},"1", 0, pList); // Create player 1
            pList[0] = p1;
            Player p2 = createPlayer(board, new int[]{middleV-1,middleH-1},"2", 0, pList); // Create player 2
            pList[1] = p2;
            Player p3 = createPlayer(board, new int[]{middleV,middleH},"3", 0, pList); // Create player 3
            pList[2] = p3;
            return pList; // Return an array of players
        }
        else if(playerCount == 4){ // If there are four players
            Player[] pList = new Player[4];
            Player p1 = createPlayer(board, new int[]{middleV-1,middleH-1},"1", 0, pList); // Create player 1
            pList[0] = p1;
            Player p2 = createPlayer(board, new int[]{middleV,middleH-1},"2", 0, pList); // Create player 2
            pList[1] = p2;
            Player p3 = createPlayer(board, new int[]{middleV-1,middleH+1},"3", 0, pList); // Create player 3
            pList[2] = p3;
            Player p4 = createPlayer(board, new int[]{middleV,middleH+1},"4", 0, pList); // Create player 4
            pList[3] = p4;
            return pList; // Return an array of players
        }
        return null; // Return null if player count is invalid
    }

    // Method to create and initialize the board
    public static String[][] InitializeBoard(int x, int y){
        x=x+2; // Adding 2 to x dimension
        y=y+2; // Adding 2 to y dimension
        String[][] board = new String[x][y]; // Creating a 2D array for the board
        for(int i=0;i<x;i++){ // Looping through rows
            for(int j=0;j<y;j++){ // Looping through columns
                if((i==0) || (j==0) || (i==x-1) || (j==y-1)){ // Checking if it's a border square
                    board[i][j]="I"; // Setting border square
                }
                else{
                    board[i][j] = "V"; // Setting playable square
                }
            }
        }
        return board; // Returning the initialized board
    }

    // Method to shuffle the order of players randomly
    public static void shufflePlayers(Player[] playerList){
        for(Player player : playerList){ // Iterate over each player in the player list
            Random rand = new Random(); // Create a new Random object for shuffling

            for (int i = 0; i < playerList.length; i++) { // Iterate over each element in the player list
                int randomIndexToSwap = rand.nextInt(playerList.length); // Generate a random index to swap
                Player temp = playerList[randomIndexToSwap]; // Store the player at the random index in a temporary variable
                playerList[randomIndexToSwap] = playerList[i]; // Swap the player at the random index with the player at index i
                playerList[i] = temp; // Place the stored player at index i
            }
        }
    }

    // Method to start the game with the given number of players
    public static void startGame(int n) {
        // Initialize the game board and players
        String[][] board = Initialization.InitializeBoard(10, 11);
        Player[] playerList = Initialization.placePlayers(board, n);
        View.presentPlayers(playerList);
        Initialization.shufflePlayers(playerList);
        // Starting the game
        Playing.gameLoop(board, playerList);
    }
}