package Models;

import java.util.ArrayList;
import java.util.Scanner;

public class Models {
    static Scanner scanner = new Scanner(System.in);

    //asks player for name over and over until given valid name, then fills space characters with underscore.
    public static String askName(String number){
        System.out.print("Player " + number + ", please input your name:\n>");
        String name = scanner.nextLine();
        if ((name.length() < 2) || (name.length() > 10)){
            System.out.println("Chosen name is either too long or too short, please try again.");
            return askName(number);

        }else{

            return name.replace(" ", "_");
        }

    }

    //Instantiates a player and places him on the board at inputed positions
    public static Player createPlayer(String[][] board, int[] position, String number){
        board[position[0]][position[1]] = number;
        return new Player(askName(number), new int[]{position[0],position[1]}, number);
    }


    // Checks player count and calls appropriate functions
    public static Player[] placePlayers(String[][] board, int playerCount){
        if(playerCount == 2){
            Player p1 = createPlayer(board, new int[]{6,6},"1");
            Player p2 = createPlayer(board, new int[]{5,6},"2");
            System.out.println(p1.getName());
            System.out.println(p2.getName());

            return new Player[]{p1,p2};

        }
        else if(playerCount == 3){
            Player p1 = createPlayer(board, new int[]{6,6},"1");
            Player p2 = createPlayer(board, new int[]{5,5},"2");
            Player p3 = createPlayer(board, new int[]{5,7},"3");

            return new Player[]{p1,p2,p3};

        }
        else if(playerCount == 4){
            Player p1 = createPlayer(board, new int[]{5,5},"1");
            Player p2 = createPlayer(board, new int[]{6,5},"2");
            Player p3 = createPlayer(board, new int[]{5,7},"3");
            Player p4 = createPlayer(board, new int[]{6,7},"4");

            return new Player[]{p1,p2,p3,p4};
        }
        return null;
    }

    //Creates board and fills playable squares with V for "Valid square"
    public static String[][] InitializeBoard(int x,int y){
        x=x+2;
        y=y+2;
        String[][] board = new String[x][y];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if((i==0) || (j==0) || (i==x-1) || (j==y-1)){
                    board[i][j]="I";
                }
                else{
                    board[i][j] = "V";

                }
            }
        }
        return board;
    }

}
