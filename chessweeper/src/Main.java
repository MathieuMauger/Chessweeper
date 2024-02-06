
import Models.*;
import View.*;

public class Main {
    public static void main(String[] args) {
        Cli.startMenu();
        String[][] board = Models.InitializeBoard(10,11);
        Models.placePlayers(board, 2);
        View.ShowBoard(board);
    }


}