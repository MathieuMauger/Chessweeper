package Controller;

import Models.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Saving {

    // Method to input scores and save them to a file
    public static void inputScores(Player[] pList){
        StringBuilder scores = new StringBuilder();

        File file = new File("scoreboard.txt"); // Create a new file object for the scoreboard

        try{
            for( Player p : pList){
                if (p.isAlive()){
                    p.setScore(5); // Set score for alive players
                }
                else {
                    p.setScore(-2); // Set score for dead players
                }
            }
            if (file.createNewFile()){
                // If no scoreboard exists locally

                // Create string list from current scores
                for (Player p : pList){
                    scores.append(p.getName());
                    scores.append(",");
                    scores.append(p.getScore());
                    scores.append(";");
                }

                // Output scores to the file
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write(scores.toString());
                out.close();
            }else {
                // If scoreboard exists locally

                String[][] parts2 = txtToMatrix(file); // Read existing scoreboard into a matrix

                if(parts2.length != 0){
                    BufferedWriter out = new BufferedWriter(new FileWriter(file));

                    // Write updated scores to the file
                    out.write(matrixToTxt(parts2, pList));
                    out.close();
                }
            }
        }
        catch(IOException e){
            System.out.println("SAVED SCORES INTO SCOREBOARD\n" + e); // Print error message if an exception occurs
        }
    }

    // Method to convert text file to matrix
    public static  String[][] txtToMatrix(File file) throws IOException {
        StringBuilder scores = new StringBuilder();

        Scanner fileScanner = new Scanner(file);

        // Read the text file and convert it into a string
        while (fileScanner.hasNextLine()) {
            scores.append(fileScanner.nextLine());
        }

        // Split text and create matrix from it
        String[] parts = (scores.toString()).split(";");
        String[][] parts2 = new String[parts.length][];
        int i = 0;
        for(String  s : parts){
            parts2[i] = s.split(",");
            i++;
        }
        return parts2;
    }

    // Method to convert matrix to text
    public static String matrixToTxt(String[][] scoreboardMatrix, Player[] pList){
        // Append new scores
        StringBuilder updatedScoreBoard = new StringBuilder();

        for(Player p : pList){
            boolean isInMatrix = false;
            for(String[] matrix : scoreboardMatrix){
                if(p.getName().equals(matrix[0])){
                    isInMatrix = true;
                    matrix[1] = Integer.toString(p.getScore() + Integer.parseInt(matrix[1])); // Update score
                }
            }
            if (!isInMatrix){
                updatedScoreBoard.append(p.getName());
                updatedScoreBoard.append(",");
                updatedScoreBoard.append(p.getScore());
                updatedScoreBoard.append(";");
            }
        }

        // Append existing scores
        for(String[] matrix : scoreboardMatrix) {
            updatedScoreBoard.append(matrix[0]);
            updatedScoreBoard.append(",");
            updatedScoreBoard.append(matrix[1]);
            updatedScoreBoard.append(";");
        }
        return (updatedScoreBoard).toString();
    }
}
