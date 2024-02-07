package View;
import Models.Player;
public class View {

    //Prints out current board in cli
    public static void ShowBoard(String[][] board){
        StringBuilder toString = new StringBuilder();
        System.out.println("  ╔════════════════════════════════════╗");
        // Going across the board with nested for loop
        for(int i=1;i< board.length-1;i++){
            // Check if number is more than 10 so we put less space for text
            if(i<10){
                toString.append(i);
                toString.append(" ");
            }
            else {
                toString.append(i);
            }
            toString.append("║ ");
            //enter second for
            for(int j=1;j<board[i].length-1;j++){
                //if board j i empty add empty space very useful comment
                if(board[i][j].equals("I")){
                    toString.append("⬛ ");
                }
                //if valid square add white square
                else if(board[i][j].equals("V")){
                    toString.append("⬜ ");
                }
                //if square is player 1 add blue square
                else if(board[i][j].equals("1")){
                    toString.append("\uD83D\uDFE6 ");

                }
                //if square is player 2 add red square
                else if(board[i][j].equals("2")){
                    toString.append("\uD83D\uDFE5 ");
                }
                else if(board[i][j].equals("3")){
                    toString.append("\uD83D\uDFE9 ");
                } 
                else if(board[i][j].equals("4")){
                    toString.append("\uD83D\uDFE8 ");
                }
            }
            toString.append("║");
            toString.append("\n");
        }
        toString.append("  ╚════════════════════════════════════╝\n");
        toString.append("    A  B  C   D  E  F   G  H  I   J  K\n");
        System.out.println(toString);


    }

    public static void displayPlayer(Player player) {
        if (player.getNumber().equals("1")) {
            System.out.println("\uD83D\uDFE6 : " + player.getName() + " ");
        }
        if (player.getNumber().equals("2")) {
            System.out.println("\uD83D\uDFE5 : " + player.getName() + " ");
        }
        if (player.getNumber().equals("3")) {
            System.out.println("\uD83D\uDFE9 : " + player.getName() + " ");
        }
        if (player.getNumber().equals("4")) {
            System.out.println("\uD83D\uDFE8 : " + player.getName() + " ");
        }
    }
}
