package Models;

import java.util.ArrayList;
import java.util.Scanner;

public class Models {
    static Scanner scanner = new Scanner(System.in);

    //asks player for name over and over until given valid name, then fills space characters with underscore.
    public static String askName(String number, ArrayList<String> nameList){
        System.out.print("Player " + number + ", please input your name:\n>");
        String name = scanner.nextLine();
        if ((name.length() < 2) || (name.length() > 10)){
            System.out.println("Chosen name is either too long or too short, please try again.");
            return askName(number, nameList);
        }
        //If name is already chosen, ask again
        else if(nameList.contains(name)){
            System.out.println("Chosen name has already been chosen, please chose something else.");
            return askName(number, nameList);
        }
        //if name is correct, add to list for future checking of validity.
        else{
            nameList.add(name);
            return name.replace(" ", "_");
        }

    }

    //Instantiates a player and places him on the board at inputted positions
    public static Player createPlayer(String[][] board, int[] position, String number, ArrayList<String> nameList){
        board[position[0]][position[1]] = number;
        return new Player(askName(number, nameList), new int[]{position[0],position[1]}, number);
    }

}
