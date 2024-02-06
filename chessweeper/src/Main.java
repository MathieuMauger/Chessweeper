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
    public static void ShowBoard(String[][] board){
        String toString = "";
        for(int i=1;i<= board.length-1;i++){
            for(int j=1;i<=board[i].length-1;j++){
                if(board[i][j]==""){

                }
            }
        }
    }

    public static void main(String[] args) {
    }
}