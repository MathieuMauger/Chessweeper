package Save;

import View.Menues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Load {

    public static void loadGame(){

        File file = new File("save.txt");

        try {

            System.out.println("NOT IMPLEMENTED YET");

            //board = [][]

            Scanner sc = new Scanner(file);

            String[] parts = sc.nextLine().split("&");
            String[][] parts2 = new String[parts.length][];

        } catch (FileNotFoundException e) {
            System.out.println("No saved game found.");
        }
        Menues.startMenu();

    }
}
