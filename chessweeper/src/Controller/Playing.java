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

    public static void gameLoop(String[][] board, Player[] pList){
        checkEveryoneIsAlive(pList, board);
        if(nbPlayersAlive(pList) == 1){
            View.ShowBoard(board);
            System.out.println("WE HAVE A WINNER!!!!");
        } else {
            for(Player p : pList){
                gameTurn(p, board, pList);
            }
            gameLoop(board, pList);// Continue the game loop
        }
    }

    public static void gameTurn(Player p, String[][] board, Player[] pList){
        View.ShowBoard(board);
        System.out.println(p.getName() + " (" + View.showColoredSquares(p) + ") : It's your turn ! ");

        if(p.isAlive()){
            movePlayer(board, p);
            checkEveryoneIsAlive(pList, board);
            View.ShowBoard(board);

            destroySquare(board);
            checkEveryoneIsAlive(pList, board);
        }
    }


    //calculates and returns number of alive players to see if game needs to stop or keep going
    public static int nbPlayersAlive(Player[] pList){
        int nb = 0;
        for(Player p : pList){
            if(p.isAlive()){
                nb++;
            }
        }
        return nb;
    }


    //check if adjacent squares are anything else than valid (V), and if 4 squares are invalid (NOT V), kills player.
    public static void checkIfAlive(Player p, String[][] board){
        int count = 0;
        //check up, down, left, right in any order(by checking Player instance position which is stocked as an array
        // which contains as first position his vertical position and as second position his horizontal position)
        if (!board[p.getPosition()[0] + 1][p.getPosition()[1]].equals("V")) count++;
        if (!board[p.getPosition()[0] - 1][p.getPosition()[1]].equals("V")) count++;
        if (!board[p.getPosition()[0]][p.getPosition()[1] + 1].equals("V")) count++;
        if (!board[p.getPosition()[0]][p.getPosition()[1] - 1].equals("V")) count++;
        //if all squares are anything else than valid, kill player. (by making him NOT ALIVE ;))):D:D//D fuck you
        if (count == 4){
            p.setAlive(false);
        }
    }

    public static void checkEveryoneIsAlive(Player[] pList, String[][] board){
        for(Player p : pList){
            checkIfAlive(p, board);
        }
    }

    public static void movePlayer(String[][] board, Player p){
        System.out.print("Input Z for up, Q for left, S for down or D for right to move\n>");
        String pInput = (scanner.nextLine().toLowerCase());

        switch (pInput) {
            case "z":
                // check if square above player is valid, if so set board square to V square, then set Player square to
                // new position and change board for new player position
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

    public static int[] destroySquare(String[][] board) {
        System.out.print("Type the coordinates of the square you want to destroy in this format : 'A 1'\n>");
        String pInput = scanner.nextLine().toLowerCase();
        //try to split input into two parts with splitting character being space
        int y = 0;
        int x = 0;
        String[] parts = pInput.split(" ");
        if (pInput.isEmpty() || parts.length !=2 || parts[0].isEmpty() || parts[1].isEmpty()){
            System.out.println("Invalid input, please input something");
            return destroySquare(board);
        } else {
            y = convertToNumber(parts[0]);
            try {
                x = Integer.parseInt(parts[1]);
            } catch (NumberFormatException error) {
                System.out.println("Invalid input, please try again.");
                return destroySquare(board);
            }
            if (x >= 1 && x <= board.length - 2 && y >= 1 && y <= board[0].length - 2 && board[x][y].equals("V")) {
                board[x][y] = "I";
                return null;
            }
            else{
                System.out.println("Invalid input, please input something");
                return destroySquare(board);
            }
        }
    }

    //converts first character of string into number
    public static int convertToNumber(String input){
        char inputToChar = (input.toLowerCase()).charAt(0);
        return inputToChar - 'a' + 1;
    }
}
