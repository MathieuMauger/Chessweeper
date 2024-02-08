package Controller;

import Models.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Saving {

    public static void inputScores(Player[] pList){
        StringBuilder scores = new StringBuilder();

        File file = new File("scoreboard.txt");

        try{
            for( Player p : pList){
                if (p.isAlive()){
                    p.setScore(5);

                }
                else {
                    p.setScore(-2);
                }
            }
            if (file.createNewFile()){

                //IF NO SCOREBOARD EXISTS LOCALLY :

                //CREATES STRING LIST FROM CURRENT SCORES
                for (Player p : pList){
                    scores.append(p.getName());
                    scores.append(",");
                    scores.append(p.getScore());
                    scores.append(";");
                }

                //OUTPUTS IT
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write(scores.toString());
                out.close();
            }else {
                //IF SCOREBOARDS EXIST LOCALLY

                String[][] parts2 = txtToMatrix(file);


                if(parts2.length != 0){
                    BufferedWriter out = new BufferedWriter(new FileWriter(file));

                    out.write(matrixToTxt(parts2, pList));
                    out.close();
                }


            }

        }
        catch(IOException e){
            System.out.println("SAVED SCORES INTO SCOREBOARD\n" + e);
        }
    }

    public static  String[][] txtToMatrix(File file) throws IOException {

        StringBuilder scores = new StringBuilder();

        Scanner fileScanner = new Scanner(file);

        //READS TXT FILE AND MAKES IT INTO STRING
        while (fileScanner.hasNextLine()) {
            scores.append(fileScanner.nextLine());
        }

        //SPLITS TEXT AND CREATES MATRIX FROM IT
        String[] parts = (scores.toString()).split(";");
        String[][] parts2 = new String[parts.length][];
        int i = 0;
        for(String  s : parts){
            parts2[i] = s.split(",");
            i++;
        }
        return parts2;
    }

    public static String matrixToTxt(String[][] scoreboardMatrix, Player[] pList){
        //APPENDS NEW SCORES
        StringBuilder updatedScoreBoard = new StringBuilder();


        for(Player p : pList){
            boolean isInMatrix = false;
            for(String[] matrix : scoreboardMatrix){
                if(p.getName().equals(matrix[0])){
                    isInMatrix = true;
                    matrix[1] = Integer.toString(p.getScore() + Integer.parseInt(matrix[1]));
                }
            }
            if (!isInMatrix){
                updatedScoreBoard.append(p.getName());
                updatedScoreBoard.append(",");
                updatedScoreBoard.append(p.getScore());
                updatedScoreBoard.append(";");
            }
        }

        for(String[] matrix : scoreboardMatrix) {
            updatedScoreBoard.append(matrix[0]);
            updatedScoreBoard.append(",");
            updatedScoreBoard.append(matrix[1]);
            updatedScoreBoard.append(";");
        }
        return (updatedScoreBoard).toString();
    }
}
