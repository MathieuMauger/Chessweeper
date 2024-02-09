package Controller;

import View.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Sorting {

    public static void sortAscending(String[][] t, int i){
        if (i>1) {
            sortAscending(t,i-1);
            String[] last = t[i-1];

            int j = i - 2;
            while(j>=0 && Integer.parseInt(t[j][1])<Integer.parseInt(last[1])){
                t[j+1]=t[j];
                j--;
            }
            t[j+1] = last;
        }
    }
    public static void sortDescending(String[][] t, int i){
        if (i>1) {
            sortDescending(t,i-1);
            String[] last = t[i-1];

            int j = i - 2;
            while(j>=0 && Integer.parseInt(t[j][1])>Integer.parseInt(last[1])){
                t[j+1]=t[j];
                j--;
            }
            t[j+1] = last;
        }
    }

    public static void askForOrderScoreboard(String[][] board){

        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "╔═════════════════════════╗\n" +
                "║        Sort order       ║\n" +
                "╠═════════════════════════╣\n" +
                "║ 1. Winners first        ║\n" +
                "║ 2. Losers first         ║\n" +
                "╚═════════════════════════╝");


        try{
            int choice = scanner.nextInt(); //ask for user input

            if(choice == 1){ //if choice is 1
                sortAscending(board, board.length);
                View.showScoreBoard(board);
            } else if(choice == 2){
                sortDescending(board, board.length);
                View.showScoreBoard(board);
            }
            else {
                askForOrderScoreboard(board);
            }
        } catch (InputMismatchException error){
            askForOrderScoreboard(board);
        }
    }
}
