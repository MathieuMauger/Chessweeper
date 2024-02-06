import java.util.Arrays;

public class Main {

    //Creates board and fills playable squares with V for "Valid square"
    public static String[][] InitializeBoard(int x,int y){
        String[][] board = new String[x][y];
        for(int i=1;i<=x-1;i++){
            for(int j=1;j<=y-1;j++){
                board[i][j] = "V";
            }
        }
        return board;
    }

    //Prints out current board in cli
    public static String ShowBoard(String[][] board){
        String toString = "";
        for(int i=1;i;i++){
            for(int j=1;j<=y-1;j++){
                board[i][j] = "V";
            }
        }
    }

    public static void main(String[] args) {
    }
}