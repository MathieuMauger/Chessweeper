package View;

public class View {

    //Prints out current board in cli
    public static void ShowBoard(String[][] board){
        StringBuilder toString = new StringBuilder();
        for(int i=1;i<= board.length-2;i++){
            for(int j=1;j<=board[i].length-2;j++){
                if(board[j][i].isEmpty()){
                    toString.append("  ");
                } else if(board[j][i].equals("V")){
                    toString.append("⬜ ");
                } else if(board[j][i].equals("1")){
                    toString.append("\uD83D\uDFE6 ");
                } else if(board[j][i].equals("2")){
                    toString.append("\uD83D\uDFE5 ");
                } else{
                    toString.append("⬛ ");
                }
            }
            toString.append("\n");
        }
        System.out.println(toString);
    }
}
