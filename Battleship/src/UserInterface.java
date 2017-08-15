import javax.swing.*;
import java.util.*;

public class UserInterface {

    private int boardSize = -1;


        /* PARAMS
     * optins: list of all the lines that should to present
     * style: where and how to menu should to present. for right now we support only 'middel' style
     * RETURN
     * true if success , false otherwise.
     * */

    public boolean printMenu(List<String> options, String style) {

        int optionNumber = 1;

        System.out.println("Menu :");
        for (String option : options) {
            System.out.printf("%d. %s \n\r", optionNumber, option);
            optionNumber++;
        }
        System.out.println();
        System.out.println("Please Enter your choice : ");
        return true;
    }

    public boolean printMassage (String msg){

        System.out.println(msg);
        return  true;
    }

    public  ArrayList <Integer> waitForCoordinates (){

        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        column -= 'A';

        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(row);
        coordinates.add(column);

        return coordinates;
    }

    /* PARAMS
     * optins: list of all the lines that should to present
     * style: where and how to menu should to present. for right now we support only 'middel' style
     * startFrom : the defult the first options start from 0. if you want something else, like to start form 1 set startFrom to 1 shoul
     * RETURN
     * true if success , false otherwise.
     * */
    public boolean printMenu(List<String> options, String Style, int startFrom) {

        return false;
    }

    //listing to keybaord and return the input
    public int getMenuOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void printBaordsAndMenu(String name, char[][] boardOne, char[][] boardTwo, int score, List<String> menu) {


        int row = 0;
        int column = 0;
        boardSize = boardOne[0].length;


        printMenu(menu , "");

        System.out.println("Player Board :");
        printTopRaw();
        for (; row < boardSize; row++) {

            System.out.printf(Integer.toString(row + 1));
            for (column = 0; column < boardSize; column++) {
                System.out.printf(" %c", boardOne[row][column]);
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");

        System.out.println("Rival Board :");
        printTopRaw();
        for (row = 0; row < boardSize; row++) {

            System.out.printf(Integer.toString(row + 1));
            for (column = 0; column < boardSize; column++) {
                System.out.printf(" %c", boardTwo[row][column]);
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");

        System.out.printf("Current Score : %d \n" , score);
    }


    private void printTopRaw() {

        String row = new String("  ");
        int rowEnd = boardSize + 65;
        char letter = 0;

        for(int i = 65 ; i < rowEnd ; i++ ) {
            letter = (char)i ;
            row = row.concat(Character.toString(letter) + " ");
        }

        System.out.println(row);
    }

}
