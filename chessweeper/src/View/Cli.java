package View;

import Controller.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cli {

    // Method to display the game rules
    public static void startRules(){

        System.out.print("\033[H\033[2J"); // Clears the console
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

        Menues.startMenu();
    }

    // Method to display the menu for selecting the number of players
    public static void setupGame(){
        // Clears the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Display the menu options for the number of players
        System.out.println("╔════════════════════════╗");
        System.out.println("║    Number of players   ║");
        System.out.println("╠════════════════════════╣");
        System.out.println("║ 2. players             ║");
        System.out.println("║ 3. players             ║");
        System.out.println("║ 4. players             ║");
        System.out.println("╚════════════════════════╝");

        // Get user input
        Scanner scanner = new Scanner(System.in);
        try {
            int response = scanner.nextInt();
            switch (response) {
                case 2:
                    Initialization.startGame(2); // Start the game with 2 players
                    break;
                case 3:
                    Initialization.startGame(3); // Start the game with 3 players
                    break;
                case 4:
                    Initialization.startGame(4); // Start the game with 4 players
                    break;
                default:
                    System.out.println("Wrong Answer");
                    setupGame(); // Prompt again if the input is invalid
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Answer");
            setupGame(); // Prompt again if the input is invalid
        }
    }
}
