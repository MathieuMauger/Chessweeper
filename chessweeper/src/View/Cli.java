package View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cli {

    // design du menu
    public static void startMenu() {
        /** Improves game visibility */


        System.out.print("\033[H\033[2J");
        System.out.flush();

        /**
         Method for displaying the menu
         Returns nothing but displays the menu line by line
         */
        System.out.println("╔════════════════════════╗");
        System.out.println("║         Menu           ║");
        System.out.println("╠════════════════════════╣");
        System.out.println("║ 1. Start the Game      ║");
        System.out.println("║ 2. Rules               ║");
        System.out.println("║ 3. Leave               ║");
        System.out.println("╚════════════════════════╝");


        Scanner scanner = new Scanner(System.in);
        try {

            int response = scanner.nextInt();
            switch (response) {
                case 1:
                    startGame();
                    break;
                case 2:
                    startRules();
                    break;
                case 3:
                    System.out.println("See you soon..");
                    break;
                default:
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Answer");
            startMenu();
        }
    }

    public static void startGame(){
        System.out.println("Start");
    }

    public static void startRules(){
        System.out.println(
                "During his turn a player can move his pawn one space (vertically or\n" +
                "horizontally), then he destroys a square on the board.\n" +
                "The last player who can still move wins.\n\n" +
                "Constraints:\n" +
                "- A player cannot destroy an occupied square.\n" +
                "- A player cannot occupy a destroyed square or a square already occupied.\n" +
                "- A player blocked during a round is declared a loser.\n"
        );

        System.out.println("1 - Menu                    2 - Leave");

        Scanner scanner = new Scanner(System.in);
        try {

            int response = scanner.nextInt();
            switch (response) {
                case 1:
                    startMenu();
                    break;
                case 2:
                    System.out.println("See you soon..");
                    break;
            }
        }catch (InputMismatchException e) {
            System.out.println("Wrong Answer");
            startRules();
        }
    }
}