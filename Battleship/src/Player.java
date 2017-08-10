//import GameParser.BattleShipGame;
import java.util.List;

public class Player {

    GameTool[][] myBoard ;
    char[][] rivalBoard ;
    String Name ;
    int numOfShip;
    private  boolean isAlive;
    private int size;
    private int score = 0;

    public String getName() {
        return Name;
    }

    public int getScore() {
        return score;
    }

    /* name - player name
        size - size board
        newPlayreBoard -  is must be a valid before we call to the constractor*/
    public Player(String i_name, int i_size,  GameTool [][] i_newPlayerBoard, int i_numOfship) {
        Name = i_name;
        myBoard = i_newPlayerBoard;
        rivalBoard = new char[i_size][i_size];
        numOfShip = i_numOfship;
        isAlive = true;
        size = i_size;
    }

    /* return "non" if no hit
    *  otherwise return the name of the GameTool : "mine" or "BattelShip"*/
    public java.lang.String whoFindThere(int row, int column){
        if (myBoard[row][column] == null){
            return "non";
        }
        return myBoard[row][column].getSpecies();
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
        updateRivalBoard (row,column);
    }


    private boolean updateRivalBoard (int row, int column){
        if (rivalBoard[row][column] != 0){
            System.out.print("warnnig: updateRivalBoard - rivalBoard[row][column] not empty cell");
            return  false;
        }
        rivalBoard[row][column] = 'X';
        return true;
    }

    public char [][] getMyBoardForPrint() {
        char [][] boardForPrint = new char[this.size][this.size];
        for (int row =0 ; row< size ; row++){
            for (int column =0 ; column < size ; column++){
                boardForPrint[row][column] = this.myBoard[row][column].getMySing();
            }
        }
        return  boardForPrint;
    }

    public char[][] getRivalBoard() {
        return rivalBoard;
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
