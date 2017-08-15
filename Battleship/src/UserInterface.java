import javax.swing.*;
import java.util.*;

public class UserInterface {

    private int boardSize = 5;


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
        return  true;
    }

    public  ArrayList <Integer> waitForCoordinates (){
        return  new ArrayList<Integer>();
    }

    /* PARAMS
     * optins: list of all the lines that should to presentx
     * style: where and how to menu should to present. for right now we support only 'middel' style
     * startFrom : the defult the first options start from 0. if you want something else, like to start form 1 set startFrom to 1 shoul
     * RETURN
     * true if success , false otherwise.
     * */
    public boolean printMenu(List<String> options, String Style, int startFrom) {

        return false;
    }

    //listing to keybaord and return the input
    public int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void printBaordsAndMenu(String name, char[][] boardOne, char[][] BoardTwo, int score, List<String> menu) {


        int row = 0;
        int column = 0;
        //char[][] board = new char[5][5];

        printTopRaw();


        for (; row < boardSize; row++) {

            System.out.printf(Integer.toString(row + 1));
            for (column = 0; column < boardSize; column++) {
                System.out.printf(" %c", boardOne[column][row]);
            }
        }
        System.out.printf("\n");
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
