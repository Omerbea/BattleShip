//import GameParser.BattleShipGame;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.org.apache.xpath.internal.operations.String;

import java.util.List;

public class Player {

    GameTool[][] myBoard ;
    int[][] rivalBoard ;
    String Name ;
    int numOfShip;
    private  boolean isAlive;
    /* name - player name
    size - size board
    newPlayreBoard -  is must be a valid before we call to the constractor*/
    public Player(String i_name, int i_size,  GameTool [][] i_newPlayerBoard, int i_numOfship) {
        Name = i_name;
        myBoard = i_newPlayerBoard;
        rivalBoard = new int[i_size][i_size];
        numOfShip = i_numOfship;
        isAlive = true;
    }

    /* return "non" if no hit
    *  otherwise return the name of the GameTool : "mine" or "BattelShip"*/
    public java.lang.String howFindThere(int row, int column){
        if (myBoard[row][column] == null){
            return "non";
        }
        java.lang.String species = myBoard[row][column].getSpecies();

        if (species.equals("BattleShip")) {
            return "BattleShip";
        }
        if (species.equals("Mine")){
            return "Mine";
        }
        else{
            System.out.print(("warnnig: isHit function insert to else condition..."));
            return  "non";
        }
    }

    public void updateHitMe (int row, int column){
        updateStatistics (row, column, true);
        boolean distroyShip =  myBoard[row][column].updateHitMe();
        if (distroyShip){
            numOfShip--;
            if (numOfShip == 0){
                isAlive = false;
            }
        }
        myBoard[row][column] = null;
    }

    private  void updateStatisticsHitMe (int row, int column){
        //TODO: implement
    }

    private  void updateStatisticIHit ( int row, int column){
        //TODO: implement
    }

    private  void updateStatistics (int row, int clolumn, boolean iHit){
        if (iHit){
            updateStatisticIHit(row, clolumn);
        }
        else{
            updateStatisticsHitMe(row,clolumn);
        }
    }

    public  boolean getIsAlive (){
        return  isAlive;
    }

    public void updateIHit (int row, int column){
        updateStatistics(row, column , true);
    }
    
    /* just for testing
    public void boardtest() {
        board[0][0] = new BattleShip();
        board[0][1] = new Mine();
        board[0][2] = null ;
        board[0][3] = null ;
    }
    */

}
