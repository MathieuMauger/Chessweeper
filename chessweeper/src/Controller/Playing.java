package Controller;

import Models.*;
import View.*;
import Save.Save;

import java.util.Scanner;

public class Playing {
    static Scanner scanner = new Scanner(System.in);

    // Method to run the game loop
    public static void gameLoop(String[][] board, Player[] pList)  {
        checkEveryoneIsAlive(pList, board); // Check if everyone is alive
        boolean isGameOver = false;

        int playerTurn = 0;

        for(Player p : pList){ // Iterate over each player
            playerTurn++;
            if(nbPlayersAlive(pList) == 1) { // If only one player is alive, end the game
                System.out.print("\033[H\033[2J"); // Clear the console
                System.out.flush();
                View.ShowBoard(board); // Display the board
                System.out.println("GAME OVER"); // Print the winner message
                Menues.endGameMenu(pList); // Display the end game menu
                System.out.print("\033[H\033[2J"); // Clear the console
                isGameOver = true; //gameIsOver
                break;
            }
            else if (nbPlayersAlive(pList) == 0){
                System.out.print("\033[H\033[2J"); // Clear the console
                System.out.flush();
                View.ShowBoard(board); // Display the board
                System.out.println("DRAW"); // Print the winner message
                Menues.endGameMenu(pList); // Display the end game menu
                System.out.print("\033[H\033[2J"); // Clear the console
                isGameOver = true; //gameIsOver
                break;
            }
            else{
                System.out.flush();
                gameTurn(p, board, pList); // Execute the turn for each player

                Save.writeSaveFile(board, pList, playerTurn);
            }
        }
        //if game is not over
        if(!isGameOver){
            gameLoop(board, pList); // Continue the game loop
        }
    }

    // Method to execute a player's turn
    public static void gameTurn(Player p, String[][] board, Player[] pList){

        View.ShowBoard(board); // Display the board

        if(p.isAlive()){ // If the player is alive
            System.out.println(p.getName() + " (" + View.showColoredSquares(p) + ") : It's your turn ! ");
            movePlayer(board, p); // Move the player
            checkEveryoneIsAlive(pList, board); // Check if everyone is alive after the move
            View.ShowBoard(board); // Display the board after the move
            destroySquare(board); // Destroy a square on the board
            checkEveryoneIsAlive(pList, board); // Check if everyone is alive after destroying a square
        }
    }

    // Method to calculate and return the number of alive players
    public static int nbPlayersAlive(Player[] pList){
        int nb = 0;
        for(Player p : pList){
            if(p.isAlive()){
                nb++;
            }
        }
        return nb;
    }

    // Method to check if a player is alive based on adjacent squares
    public static void checkIfAlive(Player p, String[][] board){
        int count = 0;
        // Check up, down, left, right in any order
        if (!board[p.getPosition()[0] + 1][p.getPosition()[1]].equals("V")) count++;
        if (!board[p.getPosition()[0] - 1][p.getPosition()[1]].equals("V")) count++;
        if (!board[p.getPosition()[0]][p.getPosition()[1] + 1].equals("V")) count++;
        if (!board[p.getPosition()[0]][p.getPosition()[1] - 1].equals("V")) count++;
        // If all squares are anything else than valid, kill player
        if (count == 4){
            p.setAlive(false); // Set player as not alive
        }
    }

    // Method to check if everyone is alive
    public static void checkEveryoneIsAlive(Player[] pList, String[][] board){
        for(Player p : pList){
            checkIfAlive(p, board); // Check if each player is alive
        }
    }

    // Method to move the player
    public static void movePlayer(String[][] board, Player p){
        System.out.print("Input Z for up, Q for left, S for down or D for right to move\n>");
        String pInput = (scanner.nextLine().toLowerCase());

        switch(pInput) {
            case "kill": // Cheat code to kill player
                p.setAlive(false); // Set player as not alive
                break;
            case "z":
                // Check if square above player is valid, if so, move player up
                if(board[p.getPosition()[0]-1][p.getPosition()[1]].equals("V")){
                    board[p.getPosition()[0]][p.getPosition()[1]] = "V";
                    p.setPosition(new int[]{p.getPosition()[0]-1, p.getPosition()[1]});
                    board[p.getPosition()[0]][p.getPosition()[1]] = p.getNumber();
                }
                else{
                    System.out.println("Invalid choice, please input something else.");
                    movePlayer(board, p);
                }
                break;
            case "q":
                // Check if square left of player is valid, if so, move player left
                if(board[p.getPosition()[0]][p.getPosition()[1]-1].equals("V")){
                    board[p.getPosition()[0]][p.getPosition()[1]] = "V";
                    p.setPosition(new int[]{p.getPosition()[0], p.getPosition()[1]-1});
                    board[p.getPosition()[0]][p.getPosition()[1]] = p.getNumber();
                }
                else{
                    System.out.println("Invalid choice, please input something else.");
                    movePlayer(board, p);
                }
                break;
            case "s":
                // Check if square below player is valid, if so, move player down
                if(board[p.getPosition()[0]+1][p.getPosition()[1]].equals("V")){
                    board[p.getPosition()[0]][p.getPosition()[1]] = "V";
                    p.setPosition(new int[]{p.getPosition()[0]+1, p.getPosition()[1]});
                    board[p.getPosition()[0]][p.getPosition()[1]] = p.getNumber();
                }
                else{
                    System.out.println("Invalid choice, please input something else.");
                    movePlayer(board, p);
                }
                break;
            case "d":
                // Check if square right of player is valid, if so, move player right
                if(board[p.getPosition()[0]][p.getPosition()[1]+1].equals("V")){
                    board[p.getPosition()[0]][p.getPosition()[1]] = "V";
                    p.setPosition(new int[]{p.getPosition()[0], p.getPosition()[1]+1});
                    board[p.getPosition()[0]][p.getPosition()[1]] = p.getNumber();
                }
                else{
                    System.out.println("Invalid choice, please input something else.");
                    movePlayer(board, p);
                }
                break;
            default:
                System.out.println("Invalid choice, please input something else.");
                movePlayer(board, p);
                break;
        }
    }

    // Method to destroy a square on the board
    public static void destroySquare(String[][] board) {
        System.out.print("Type the coordinates of the square you want to destroy in this format : 'A 1'\n>");
        String pInput = scanner.nextLine().toLowerCase();
        int y = 0;
        int x = 0;
        String[] parts = pInput.split(" "); // Split input into parts
        if (pInput.isEmpty() || parts.length !=2 || parts[0].isEmpty() || parts[1].isEmpty()){
            System.out.println("Invalid input, please input something");
            destroySquare(board);
        } else {
            y = convertToNumber(parts[0]); // Convert letter part to number
            try {
                x = Integer.parseInt(parts[1]); // Parse integer part
            } catch (NumberFormatException error) {
                System.out.println("Invalid input, please try again.");
                destroySquare(board);
            }
            if (x >= 1 && x <= board.length - 2 && y >= 1 && y <= board[0].length - 2 && board[x][y].equals("V")) {
                board[x][y] = "I"; // Destroy the square
            }
            else{
                System.out.println("Invalid input, please input something");
                destroySquare(board);
            }
        }
    }

    // Method to convert letter to number (A -> 1, B -> 2, etc.)
    public static int convertToNumber(String input){
        char inputToChar = (input.toLowerCase()).charAt(0);
        return inputToChar - 'a' + 1;
    }
}
