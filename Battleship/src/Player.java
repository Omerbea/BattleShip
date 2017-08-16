//import GameParser.BattleShipGame;
import java.util.ArrayList;
import java.util.List;

public class Player {

    class PlayerStatistics {
        private int scure = 0;
        private int missNum = 0;
        private long avargeTimeTurn = 0;

        public int getMissNum() {
            return missNum;
        }

        public int getScore() {
            return scure;
        }

        public long getAverageTimeTurn() {
            return avargeTimeTurn;
        }

        public void incMissNum() {
            missNum = missNum++;
        }

        public void incScore(int inc) {
            this.scure += inc;
        }
    }

    GameTool[][] myBoard ;
    char[][] rivalBoard ;
    String Name ;
    int numOfShip;
    private  boolean isAlive;
    private int size;
    private int score = 0;
    private PlayerStatistics playerStatistics = new PlayerStatistics();

    public String getName() {
        return Name;
    }

    public int getScore() {
        return score;
    }

    public int getMissNum(){
        return  playerStatistics.getMissNum();
    }

    public long getAvargeTime (){
        return  playerStatistics.avargeTimeTurn;
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
    public ArrayList<String> whoFindThere(int row, int column){
        ArrayList<String> result = new ArrayList<String>();
        if (myBoard[row][column] == null){
            result.add("non");
        }

        result.add(myBoard[row][column].getSpecies());
        result.add((myBoard[row][column].getType()));
        return  result;
    }


    private  void updateStatisticsMyTurn (int row, int clolumn, boolean iHit, String typeGameTool){

        if (iHit){
            //TODO:
        }
        else{
            //TODO:
        }
    }

    public boolean updateIMissMyTurn (int row, int column ){
        updateStatisticsMyTurn(row, column, false, null);
        updateRivalBoard(row, column, false);
        return  true;
    }

    public  boolean getIsAlive (){
        return  isAlive;
    }

    public void updateIHitMyTurn (int row, int column, String typeGameTool){
        updateStatisticsMyTurn(row, column , true, typeGameTool);
        updateRivalBoard (row,column, true);
    }

    // פונקציה לעדכון


    private boolean updateRivalBoard (int row, int column, boolean iHit){
        if (rivalBoard[row][column] != 0){
            System.out.print("warnnig: updateRivalBoard - rivalBoard[row][column] not empty cell");
            return  false;
        }
        if (iHit) {
            rivalBoard[row][column] = 'X';
        }
        else {
            rivalBoard[row][column] = '-';
        }
        return true;
    }

    public char [][] getMyBoardForPrint() {
        char [][] boardForPrint = new char[this.size][this.size];
        for (int row =0 ; row< size ; row++){
            for (int column =0 ; column < size ; column++){
                if (this.myBoard[row][column] != null) {
                    boardForPrint[row][column] = this.myBoard[row][column].getMySing();
                }
                else{
                    boardForPrint[row][column] = ' ';
                }
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
