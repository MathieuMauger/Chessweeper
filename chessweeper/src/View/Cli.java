package View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cli {

    // design du menu
    public static void menu() {
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
                    System.out.println("Start");
                    break;
                case 2:
                    System.out.println("Read the rules");
                    break;
                case 3:
                    System.out.println("See you soon..");
                    break;
                default:
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Answer");
            menu();
        }
    }
}