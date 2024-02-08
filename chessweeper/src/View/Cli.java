package View;

import Controller.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cli {


    // Method to display the game rules
    public static void startRules(){

        System.out.print("\033[H\033[2J");
        System.out.flush();
        // Display the game rules
        System.out.println(
                "During his turn a player can move his pawn one space (vertically or\n" +
                        "horizontally), then he destroys a square on the board.\n" +
                        "The last player who can still move wins.\n\n" +
                        "Constraints:\n" +
                        "- A player cannot destroy an occupied square.\n" +
                        "- A player cannot occupy a destroyed square or a square already occupied.\n" +
                        "- A player blocked during a round is declared a loser.\n"
        );

        // Display options for the user
        System.out.println("1 - Menu                    2 - Leave");

        // Get user input
        Scanner scanner = new Scanner(System.in);
        try {
            int response = scanner.nextInt();
            switch (response) {
                case 1:
                    Menues.startMenu();
                    break;
                case 2:
                    System.out.println("See you soon..");
                    break;
                default:
                    System.out.println("Wrong Answer");
                    startRules();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Answer");
            startRules();
        }
    }

    // Method to display the menu for selecting number of players
    public static void setupGame(){
        // Clears the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Display the menu options for number of players
        System.out.println("╔════════════════════════╗");
        System.out.println("║    Number of players   ║");
        System.out.println("╠════════════════════════╣");
        System.out.println("║ 1. 2 players           ║");
        System.out.println("║ 2. 3 players           ║");
        System.out.println("║ 3. 4 players           ║");
        System.out.println("╚════════════════════════╝");

        // Get user input
        Scanner scanner = new Scanner(System.in);
        try {
            int response = scanner.nextInt();
            switch (response) {
                case 1:
                    initialization.startGame(2);
                    break;
                case 2:
                    initialization.startGame(3);
                    break;
                case 3:
                    initialization.startGame(4);
                    break;
                default:
                    System.out.println("Wrong Answer");
                    setupGame();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Answer");
            setupGame();
        }
    }
}
