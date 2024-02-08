package Controller;

import Models.*;
import View.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Initialisation {
    static Scanner scanner = new Scanner(System.in);

    // Method to ask player for name and replace spaces with underscores
    public static String askName(String number){
        System.out.print("Player " + number + ", please input your name:\n>"); // Prompting user for input
        String name = scanner.nextLine(); // Getting user input
        if ((name.length() < 2) || (name.length() > 10)){ // Checking if name length is valid
            System.out.println("Chosen name is either too long or too short, please try again."); // Prompting user to try again
            return askName(number); // Calling the method recursively until valid name is provided
        } else {
            return name.replace(" ", "_"); // Replacing spaces with underscores and returning the name
        }
    }

    // Method to create a player and place them on the board
    public static Player createPlayer(String[][] board, int[] position, String number, int score){
        board[position[0]][position[1]] = number; // Placing player on the board
        return new Player(askName(number), new int[]{position[0],position[1]}, number, score ); // Instantiating Player object with name, position, and number
    }

    // Method to place players on the board based on player count
    public static Player[] placePlayers(String[][] board, int playerCount){
        if(playerCount == 2){ // If there are two players
            Player p1 = createPlayer(board, new int[]{6,6},"1", 0); // Create player 1
            Player p2 = createPlayer(board, new int[]{5,6},"2", 0); // Create player 2
            System.out.println(p1.getName()); // Print player 1's name
            System.out.println(p2.getName()); // Print player 2's name
            return new Player[]{p1,p2}; // Return an array of players
        }
        else if(playerCount == 3){ // If there are three players
            Player p1 = createPlayer(board, new int[]{6,6},"1", 0); // Create player 1
            Player p2 = createPlayer(board, new int[]{5,5},"2", 0); // Create player 2
            Player p3 = createPlayer(board, new int[]{5,7},"3", 0); // Create player 3
            return new Player[]{p1,p2,p3}; // Return an array of players
        }
        else if(playerCount == 4){ // If there are four players
            Player p1 = createPlayer(board, new int[]{5,5},"1", 0); // Create player 1
            Player p2 = createPlayer(board, new int[]{6,5},"2", 0); // Create player 2
            Player p3 = createPlayer(board, new int[]{5,7},"3", 0); // Create player 3
            Player p4 = createPlayer(board, new int[]{6,7},"4", 0); // Create player 4
            return new Player[]{p1,p2,p3,p4}; // Return an array of players
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




}
