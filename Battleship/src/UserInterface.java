import java.util.ArrayList;
import java.util.List;

public class UserInterface {

    private int boardSize ;


        /* PARAMS
     * optins: list of all the lines that should to present
     * style: where and how to menu should to present. for right now we support only 'middel' style
     * RETURN
     * true if success , false otherwise.
     * */

    public boolean printMenu(List<String> options, String style) {

        return true ;
    }

    /* PARAMS
     * optins: list of all the lines that should to present
     * style: where and how to menu should to present. for right now we support only 'middel' style
     * startFrom : the defult the first options start from 0. if you want something else, like to start form 1 set startFrom to 1 shoul
     * RETURN
     * true if success , false otherwise.
     * */
    public boolean printMenu(List<String> options, String Style, int startFrom) {
        return false ;
    }

    //listing to keybaord and return the input
    public char waitForInput() {
        return 'c';
    }

    public void printBaordsAndMenu( String name,char[][] baordOne, char[][]BoardTwo,int score, List<String> menu){

    }

    public String waitForStringInput() { return  "sdf";}

    public ArrayList <Integer> waitForCoordinates() {
       ArrayList <Integer> coordinates = new ArrayList<Integer> ();
       coordinates.add((1));
       coordinates.add((2));
       return  coordinates;
    }
    public boolean printMassage(String msg){
        return  true;
    }
}
