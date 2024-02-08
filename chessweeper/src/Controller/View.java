package Controller;
import Models.Player;
import View.Cli;

import java.io.*;
import java.lang.reflect.Array;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class View {

    static Scanner scanner = new Scanner(System.in);


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
                switch (board[i][j]) {
                    case "I" -> toString.append("⬛ "); // Border square

                    // if valid square add white square
                    case "V" -> toString.append("⬜ "); // Valid square

                    // if square is player 1 add blue square
                    case "1" -> toString.append("\uD83D\uDFE6 "); // Player 1

                    // if square is player 2 add red square
                    case "2" -> toString.append("\uD83D\uDFE5 "); // Player 2
                    case "3" -> toString.append("\uD83D\uDFE9 "); // Player 3
                    case "4" -> toString.append("\uD83D\uDFE8 "); // Player 4
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


    public static void inputEndGame(Player[] pList) {
        String scoreBoard = scanner.nextLine(); // Getting user input
        try {
            inputScores(pList);
        }
        catch (IOException e){
            System.out.println("Error Alert !");
        }

        System.out.println("╔════════════════════════╗");
        System.out.println("║       What next ?      ║");
        System.out.println("╠════════════════════════╣");
        System.out.println("║ 1. Restart             ║");
        System.out.println("║ 2. Score               ║");
        System.out.println("║ 3. Main Menu           ║");
        System.out.println("╚════════════════════════╝");

        try {
            int response = scanner.nextInt();
            switch (response) {
                case 1:
                    Cli.setupGame();
                    break;
                case 2:
                    showScoreBoard(pList);
                    break;
                case 3:
                    Cli.startMenu();
                    break;
                default:
                    System.out.println("Wrong Answer");
                    inputEndGame(pList);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Answer");
            inputEndGame(pList);
        }

    }


    public static void inputScores(Player[] pList) throws IOException {


        for( Player p : pList){
            if (p.isAlive()){
                p.setScore(5);

            }
            else {
                p.setScore(-2);
            }
        }

        StringBuilder scores = new StringBuilder();
        String[] parts = (scores.toString()).split(";");
        String[][] parts2 = new String[parts.length][];

        File file = new File(
                "../Save/scoreboard.txt");
        Scanner fileScanner = new Scanner(file);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        if (file.createNewFile()){
            for (Player p : pList){
                scores.append(p.getName());
                scores.append(",");
                scores.append(p.getScore());
                scores.append(";");


            }
            StringBuilder updatedScoreBoard = new StringBuilder();
            for (String[] s : parts2){
                updatedScoreBoard.append(s[0]);
                updatedScoreBoard.append(",");
                updatedScoreBoard.append(s[1]);
                updatedScoreBoard.append(";");
            }
            out.write(updatedScoreBoard.toString());
        }else {
            while (fileScanner.hasNextLine()) {
                scores.append(fileScanner.nextLine());

            }
            int i = 0;
            for(String  s : parts){
                parts2[i] = s.split(",");
                i++;

            }
            for (Player p : pList){
                for (int j = 0; j <= i; j++){
                    if (p.getName().equals(parts2[j][0])){
                        int baseScore = Integer.parseInt(parts2[j][1]);
                        int newScore = baseScore + p.getScore();
                        parts2[j][1] += Integer.toString(newScore);

                    }
                }
            }
            StringBuilder updatedScoreBoard = new StringBuilder();
            for (String[] s : parts2){
                updatedScoreBoard.append(s[0]);
                updatedScoreBoard.append(",");
                updatedScoreBoard.append(s[1]);
                updatedScoreBoard.append(";");

            }
            out.write(updatedScoreBoard.toString());
        }

    }
    public static void showScoreBoard(Player[] pList){


    }
}
