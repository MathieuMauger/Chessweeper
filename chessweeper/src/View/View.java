package View;

import Controller.Saving;
import Models.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {

    static Scanner scanner = new Scanner(System.in);

    // Method to print out the current game board in the CLI
    public static void ShowBoard(String[][] board) {
        StringBuilder toString = new StringBuilder();
        System.out.println("  ╔════════════════════════════════════╗");
        // Iterate over each row of the board
        for (int i = 1; i < board.length - 1; i++) {
            // Check if the row number is less than 10 to adjust spacing
            if (i < 10) {
                toString.append(i).append(" ");
            } else {
                toString.append(i);
            }
            toString.append("║ ");
            // Iterate over each column of the board
            for (int j = 1; j < board[i].length - 1; j++) {
                // Check the content of each square and append corresponding symbols
                switch (board[i][j]) {
                    case "I" -> toString.append("⬛ "); // Border square
                    case "V" -> toString.append("⬜ "); // Valid square
                    case "1" -> toString.append("\uD83D\uDFE6 "); // Player 1
                    case "2" -> toString.append("\uD83D\uDFE5 "); // Player 2
                    case "3" -> toString.append("\uD83D\uDFE9 "); // Player 3
                    case "4" -> toString.append("\uD83D\uDFE8 "); // Player 4
                }
            }
            toString.append("║\n");
        }
        toString.append("  ╚════════════════════════════════════╝\n");
        toString.append("    A  B  C   D  E  F   G  H  I   J  K\n");
        System.out.println(toString);
    }

    // Method to display colored squares for players
    public static String showColoredSquares(Player player) {
        // Return the corresponding colored square based on the player's number
        switch (player.getNumber()) {
            case "1":
                return "\uD83D\uDFE6"; // Player 1 color
            case "2":
                return "\uD83D\uDFE5"; // Player 2 color
            case "3":
                return "\uD83D\uDFE9"; // Player 3 color
            case "4":
                return "\uD83D\uDFE8"; // Player 4 color
            default:
                return null;
        }
    }

    // Method to present players with their colored squares
    public static void presentPlayers(Player[] playerlist) {
        for (Player player : playerlist) {
            // Print colored square and player name for each player
            System.out.println(showColoredSquares(player) + " : " + player.getName());
        }
    }

    // Method to display the scoreboard
    public static void showScoreBoard(String[][] m) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        StringBuilder scoreboard = new StringBuilder(
                        "╔═════════════════════════╗\n" +
                        "║        Scoreboard       ║\n" +
                        "╠═════════════════════════╣\n");
        // Iterate over each entry in the scoreboard matrix
        for (String[] entry : m) {
            // Append the player name and score with proper formatting
            scoreboard.append("║ ");
            scoreboard.append(entry[0]); // Player name
            scoreboard.append(" ".repeat(23 - (entry[0].length() + entry[1].length())));
            scoreboard.append(entry[1]); // Player score
            scoreboard.append(" ║\n");
        }
        scoreboard.append("╚═════════════════════════╝\n");
        scoreboard.append("1 - Menu         2 - Leave");
        System.out.println(scoreboard);

        // Get user input for menu navigation
        try {
            int response = scanner.nextInt();
            switch (response) {
                case 1:
                    Menues.startMenu(); // Go back to the main menu
                    break;
                case 2:
                    System.out.println("See you soon.."); // Exit the application
                    break;
                default:
                    System.out.println("Wrong Answer");
                    showScoreBoard(m); // Prompt again if the input is invalid
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Answer");
            showScoreBoard(m); // Prompt again if the input is invalid
        }
    }
}
