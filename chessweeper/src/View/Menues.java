package View;

import Controller.Saving;
import Models.Player;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Menues {

    static  Scanner scanner = new Scanner(System.in);

    public static void startMenu() {
        // Clears the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        String[][] scoreboard = new String[][]{};
        try {
            scoreboard = Saving.txtToMatrix(new File("scoreboard.txt"));
        } catch (IOException error) {
            System.out.println("You done fucked up");
        }


        // Display the menu options
        System.out.println("╔════════════════════════╗");
        System.out.println("║         Menu           ║");
        System.out.println("╠════════════════════════╣");
        System.out.println("║ 1. Start the Game      ║");
        System.out.println("║ 2. Rules               ║");
        System.out.println("║ 3. Scores              ║");
        System.out.println("║ 4. Leave               ║");
        System.out.println("╚════════════════════════╝");

        // Get user input
        Scanner scanner = new Scanner(System.in);
        try {
            int response = scanner.nextInt();
            switch (response) {
                case 1:
                    Cli.setupGame();
                    break;
                case 2:
                    Cli.startRules();
                    break;
                case 3:
                    View.showScoreBoard(scoreboard);
                    break;
                case 4:
                    System.out.println("See you soon..");
                    break;
                default:
                    System.out.println("Wrong Answer");
                    startMenu();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Answer");
            startMenu();
        }
    }

    public static void endGameMenu(Player[] pList) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        String[][] scoreboard = new String[][]{};
        try {
            scoreboard = Saving.txtToMatrix(new File("scoreboard.txt"));
        } catch (IOException error) {
            System.out.println("You done fucked up");
        }

        Saving.inputScores(pList);

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
                    System.out.println("debug1");
                    View.showScoreBoard(scoreboard);
                    break;
                case 3:
                    startMenu();
                    break;
                default:
                    System.out.println("Wrong Answer");
                    endGameMenu(pList);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Answer");
            endGameMenu(pList);
        }

    }
}
