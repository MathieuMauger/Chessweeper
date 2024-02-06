package Models;

public class Models {

    // Checks player count and places players appropriately
    public static void placePlayers(String[][] board, int playerCount){
        if(playerCount == 2){
            board[6][6] = "1";
            board[5][6] = "2";
        }
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
