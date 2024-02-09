package Save;

import Models.Player;
import View.Menues;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

    public static String saveToString(String[][] board, Player[] pList, int playerTurn){
        StringBuilder boardToString = new StringBuilder();
        for(String[] line : board){
            for(String value : line){
                boardToString.append(value);
            }
            boardToString.append(",");
        }
        boardToString.append("&");

        for(Player p : pList){
            boardToString.append(p.getName());
            boardToString.append(",");
            boardToString.append(p.isAlive());
            boardToString.append(",");
            boardToString.append(p.getPosition()[0]);
            boardToString.append(",");
            boardToString.append(p.getPosition()[1]);
            boardToString.append(";");
        }
        boardToString.append("&");

        boardToString.append(playerTurn);
        return boardToString.toString();
    }

    public static void writeSaveFile(String[][] board, Player[] pList, int playerTurn){
        File file = new File("save.txt");


        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(file));

            file.createNewFile();

            out.write(saveToString(board, pList, playerTurn));

            out.close();
        } catch (IOException error){
            System.out.println("ERROR");
        }
        Menues.startMenu();
    }

}
