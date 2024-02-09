package View;

import Controller.Saving;
import Controller.Sorting;
import Models.Player;
import Save.*;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menues {

    static Scanner scanner = new Scanner(System.in);

    // Method to display the main menu
    public static void startMenu() {
        // Clears the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        String[][] scoreboard = new String[][]{};
        try {
            // Load the scoreboard from the file
            scoreboard = Saving.txtToMatrix(new File("scoreboard.txt"));
        } catch (IOException error) {
            System.out.println("Error loading the scoreboard");
        }

        // Display the menu options
        System.out.println("╔════════════════════════╗");
        System.out.println("║         Menu           ║");
        System.out.println("╠════════════════════════╣");
        System.out.println("║ 1. Start New Game      ║");
        System.out.println("║ 2. Load Game           ║");
        System.out.println("║ 3. Rules               ║");
        System.out.println("║ 4. Scores              ║");
        System.out.println("║ 5. Leave               ║");
        System.out.println("╚════════════════════════╝");

        // Get user input
        Scanner scanner = new Scanner(System.in);
        try {
            int response = scanner.nextInt();
            switch (response) {
                case 1:
                    Cli.setupGame(); // Start the game
                    break;
                case 2:
                    Load.loadGame();
                case 3:
                    Cli.startRules(); // Display the game rules
                    break;
                case 4:
                    Sorting.askForOrderScoreboard(scoreboard); // Display the scoreboard
                    break;
                case 5:
                    System.out.println("See you soon.."); // Exit the application
                    break;
                default:
                    System.out.println("Wrong Answer");
                    startMenu(); // Prompt again if the input is invalid
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Answer");
            startMenu(); // Prompt again if the input is invalid
        }
    }

    // Method to display the end game menu
    public static void endGameMenu(Player[] pList) {
        // Clears the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        String[][] scoreboard = new String[][]{};
        try {
            // Load the scoreboard from the file
            scoreboard = Saving.txtToMatrix(new File("scoreboard.txt"));
        } catch (IOException error) {
            System.out.println("Error loading the scoreboard");
        }

        // Update the scoreboard with the current game scores
        Saving.inputScores(pList);

        // Display the end game menu options
        System.out.println("╔════════════════════════╗");
        System.out.println("║       What next ?      ║");
        System.out.println("╠════════════════════════╣");
        System.out.println("║ 1. Restart             ║");
        System.out.println("║ 2. Score               ║");
        System.out.println("║ 3. Main Menu           ║");
        System.out.println("╚════════════════════════╝");

        // Get user input
        try {
            int response = scanner.nextInt();
            switch (response) {
                case 1:
                    Cli.setupGame(); // Restart the game
                    break;
                case 2:
                    Sorting.askForOrderScoreboard(scoreboard); // Display the scoreboard
                    break;
                case 3:
                    startMenu(); // Return to the main menu
                    break;
                default:
                    System.out.println("Wrong Answer");
                    endGameMenu(pList); // Prompt again if the input is invalid
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Answer");
            endGameMenu(pList); // Prompt again if the input is invalid
        }
    }
}
