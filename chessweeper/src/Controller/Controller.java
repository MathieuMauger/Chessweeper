package Controller;
import Models.*;
import View.View;

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
        String pInput;

        System.out.println(p.getName() + "(" + View.showColoredSquares(p) + ") : It's your turn !");
        System.out.print("Input Z Q S OR D to move\n>");
        pInput = scanner.nextLine();
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

}
