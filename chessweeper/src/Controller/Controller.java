package Controller;
import Models.*;
import View.*;

import java.util.Random;
import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);


    //
    public static void shufflePlayers(Player[] playerList){
        for(Player player : playerList){
            Random rand = new Random();

            for (int i = 0; i < playerList.length; i++) {
                int randomIndexToSwap = rand.nextInt(playerList.length);
                Player temp = playerList[randomIndexToSwap];
                playerList[randomIndexToSwap] = playerList[i];
                playerList[i] = temp;
            }
        }
    }

    //While more than 1 player is alive, keep game loop going
    public static void gameLoop(String[][] board, Player[] pList){
        View.ShowBoard(board);
        for(Player p : pList){
            gameTurn(p, board);

        }

        if(nbPlayersAlive(pList) > 1){
            gameLoop(board, pList);
        }
    }

    public static void gameTurn(Player p, String[][] board){

        View.ShowBoard(board);

        String pInput;

        System.out.println(p.getName() + " (" + View.showColoredSquares(p) + ") : It's your turn ! ");
        System.out.print("Input Z, Q, S OR D to move\n>");
        movePlayer(board, p);

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
    public static void movePlayer(String[][] board, Player p){
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
                break;
            case "q":
                if(board[p.getPosition()[0]][p.getPosition()[1]-1].equals("V")){
                    board[p.getPosition()[0]][p.getPosition()[1]] = "V";
                    p.setPosition(new int[]{p.getPosition()[0]-1, p.getPosition()[1]});
                    board[p.getPosition()[0]][p.getPosition()[1]] = p.getNumber();
                }
                break;
            case "s":
                if(board[p.getPosition()[0]+1][p.getPosition()[1]].equals("V")){
                    board[p.getPosition()[0]][p.getPosition()[1]] = "V";
                    p.setPosition(new int[]{p.getPosition()[0]+1, p.getPosition()[1]});
                    board[p.getPosition()[0]][p.getPosition()[1]] = p.getNumber();
                }
                break;
            case "d":
                if(board[p.getPosition()[0]][p.getPosition()[1]+1].equals("V")){
                    board[p.getPosition()[0]][p.getPosition()[1]] = "V";
                    p.setPosition(new int[]{p.getPosition()[0]+1, p.getPosition()[1]});
                    board[p.getPosition()[0]][p.getPosition()[1]] = p.getNumber();
                }
                break;

            default:
                System.out.println("Invalid choice, please input something else.");
                movePlayer(board, p);
                break;
        }
    }

}
