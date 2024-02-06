package View;

public class View {

    //Prints out current board in cli
    public static void ShowBoard(String[][] board){
        StringBuilder toString = new StringBuilder();
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
                } 
                else if(board[j][i].equals("3")){
                    toString.append("\uD83D\uDFE9 ");
                } 
                else if(board[j][i].equals("4")){
                    toString.append("\uD83D\uDFE8 ");
                }
            toString.append("║");
            toString.append("\n");
        }
        toString.append("  ╚════════════════════════════════════╝\n");
        toString.append("    A  B  C   D  E  F   G  H  I   J  K\n");
        System.out.println(toString);
    }
}
