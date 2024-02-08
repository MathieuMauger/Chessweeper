package Controller;
import Models.Player;

public class View {

    // Prints out current board in CLI
    public static void ShowBoard(String[][] board){
        StringBuilder toString = new StringBuilder();
        System.out.println("  ╔════════════════════════════════════╗");
        // Going across the board with nested for loop
        for(int i=1;i< board.length-1;i++){
            // Check if number is more than 10, so we put less space for text
            if(i<10){
                toString.append(i);
                toString.append(" ");
            }
            else {
                toString.append(i);
            }
            toString.append("║ ");
            // enter second for
            for(int j=1;j<board[i].length-1;j++){
                // if board j i empty add empty space very useful comment
                if(board[i][j].equals("I")){
                    toString.append("⬛ "); // Border square
                }
                // if valid square add white square
                else if(board[i][j].equals("V")){
                    toString.append("⬜ "); // Valid square
                }
                // if square is player 1 add blue square
                else if(board[i][j].equals("1")){
                    toString.append("\uD83D\uDFE6 "); // Player 1
                }
                // if square is player 2 add red square
                else if(board[i][j].equals("2")){
                    toString.append("\uD83D\uDFE5 "); // Player 2
                }
                else if(board[i][j].equals("3")){
                    toString.append("\uD83D\uDFE9 "); // Player 3
                }
                else if(board[i][j].equals("4")){
                    toString.append("\uD83D\uDFE8 "); // Player 4
                }
            }
            toString.append("║");
            toString.append("\n");
        }
        toString.append("  ╚════════════════════════════════════╝\n");
        toString.append("    A  B  C   D  E  F   G  H  I   J  K\n");
        System.out.println(toString);
    }

    // Method to display colored squares for players
    public static String showColoredSquares(Player player) {
        if (player.getNumber().equals("1")) {
            return ("\uD83D\uDFE6"); // Player 1 color
        }
        if (player.getNumber().equals("2")) {
            return ("\uD83D\uDFE5"); // Player 2 color
        }
        if (player.getNumber().equals("3")) {
            return ("\uD83D\uDFE9"); // Player 3 color
        }
        if (player.getNumber().equals("4")) {
            return ("\uD83D\uDFE8"); // Player 4 color
        }
        return null;
    }

    // Method to present players with their colored squares
    public static void presentPlayers(Player[] playerlist){
        for(Player player : playerlist){ // Iterate over each player in the player list
            System.out.println(showColoredSquares(player) + " : " + player.getName()); // Print colored square and player name
        }
    }
}
