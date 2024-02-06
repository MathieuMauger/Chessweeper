package Models;

public class Models {

    // Checks player count and places players appropriately
    public static void placePlayers(String[][] board, int playerCount){
        if(playerCount == 2){
            board[6][5] = "1";
            board[6][6] = "2";
        }
        else if(playerCount == 3){
            board[6][6] = "1";
            board[5][5] = "2";
            board[5][7] = "3";
        }
        else if(playerCount == 4){
            board[5][5] = "1";
            board[6][5] = "2";
            board[5][7] = "3";
            board[6][7] = "4";
        }
    }

    //Creates board and fills playable squares with V for "Valid square"
    public static String[][] InitializeBoard(int x,int y){
        x=x+2;
        y=y+2;
        String[][] board = new String[x][y];
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                if((i==0) || (i==y-1) || (j==0) || (j==x-1)){
                    board[j][i]="";
                }
                board[j][i] = "V";
            }
        }
        return board;
    }



}
