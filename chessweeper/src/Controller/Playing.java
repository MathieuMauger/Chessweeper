package Controller;
import Models.*;

import java.util.Random;
import java.util.Scanner;

public class Playing {
    static Scanner scanner = new Scanner(System.in);

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


    // While more than 1 player is alive, keep game loop going
    public static void gameLoop(String[][] board, Player[] pList){
        View.ShowBoard(board); // Display the game board
        for(Player p : pList){ // Iterate through each player in the player list
            gameTurn(p, board); // Execute turn for each player
        }
        if(nbPlayersAlive(pList) > 1){ // If more than one player is alive
            gameLoop(board, pList); // Continue the game loop
        }
    }

    // Method to execute a turn for a player
    public static void gameTurn(Player p, String[][] board){
        View.ShowBoard(board); // Display the game board

        String pInput;

        System.out.println(p.getName() + " (" + View.showColoredSquares(p) + ") : It's your turn ! "); // Prompt the player for their turn
        System.out.print("Input Z, Q, S OR D to move\n>"); // Prompt for movement input
        movePlayer(board, p); // Move the player
    }

    // Method to calculate and return the number of alive players
    public static int nbPlayersAlive(Player[] pList){
        int nb = 0; // Initialize the count of alive players
        for(Player p : pList){ // Iterate through each player in the player list
            if(p.isAlive()){ // If the player is alive
                nb++; // Increment the count of alive players
            }
        }
        return nb; // Return the count of alive players
    }

    // Method to move a player based on user input
    public static void movePlayer(String[][] board, Player p){
        String pInput = (scanner.nextLine().toLowerCase()); // Get the player's input and convert it to lowercase

        switch (pInput) {
            case "z": // If input is "z"
                if(board[p.getPosition()[0]-1][p.getPosition()[1]].equals("V")){ // If the square above the player is valid
                    board[p.getPosition()[0]][p.getPosition()[1]] = "V"; // Set the current square of the player to a valid square
                    p.setPosition(new int[]{p.getPosition()[0]-1, p.getPosition()[1]}); // Update player's position
                    board[p.getPosition()[0]][p.getPosition()[1]] = p.getNumber(); // Update the board with the player's new position
                }
                else{
                    System.out.println("Invalid choice, please input something else."); // Prompt for an invalid input
                    movePlayer(board, p); // Recursively call the movePlayer method until a valid input is received
                }
                break;
            case "q": // If input is "q"
                if(board[p.getPosition()[0]][p.getPosition()[1]-1].equals("V")){ // If the square to the left of the player is valid
                    board[p.getPosition()[0]][p.getPosition()[1]] = "V"; // Set the current square of the player to a valid square
                    p.setPosition(new int[]{p.getPosition()[0], p.getPosition()[1]-1}); // Update player's position
                    board[p.getPosition()[0]][p.getPosition()[1]] = p.getNumber(); // Update the board with the player's new position
                }
                else{
                    System.out.println("Invalid choice, please input something else."); // Prompt for an invalid input
                    movePlayer(board, p); // Recursively call the movePlayer method until a valid input is received
                }
                break;
            case "s": // If input is "s"
                if(board[p.getPosition()[0]+1][p.getPosition()[1]].equals("V")){ // If the square below the player is valid
                    board[p.getPosition()[0]][p.getPosition()[1]] = "V"; // Set the current square of the player to a valid square
                    p.setPosition(new int[]{p.getPosition()[0]+1, p.getPosition()[1]}); // Update player's position
                    board[p.getPosition()[0]][p.getPosition()[1]] = p.getNumber(); // Update the board with the player's new position
                }
                else{
                    System.out.println("Invalid choice, please input something else."); // Prompt for an invalid input
                    movePlayer(board, p); // Recursively call the movePlayer method until a valid input is received
                }
                break;
            case "d": // If input is "d"
                if(board[p.getPosition()[0]][p.getPosition()[1]+1].equals("V")){ // If the square to the right of the player is valid
                    board[p.getPosition()[0]][p.getPosition()[1]] = "V"; // Set the current square of the player to a valid square
                    p.setPosition(new int[]{p.getPosition()[0], p.getPosition()[1]+1}); // Update player's position
                    board[p.getPosition()[0]][p.getPosition()[1]] = p.getNumber(); // Update the board with the player's new position
                }
                else{
                    System.out.println("Invalid choice, please input something else."); // Prompt for an invalid input
                    movePlayer(board, p); // Recursively call the movePlayer method until a valid input is received
                }
                break;
            default:
                System.out.println("Invalid choice, please input something else."); // Prompt for an invalid input
                movePlayer(board, p); // Recursively call the movePlayer method until a valid input is received
                break;
        }
    }
}
