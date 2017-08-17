//import GameParser.BattleShipGame;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Player {


    class PlayerStatistics {
        private int score = 0;
        private int missNum = 0;
        private long avargeTimeTurn = 0;
        private long timeTurn =0;
        private int turns =0 ;

        public int getTurns() {
            return turns;
        }

        public int getMissNum() {
            return missNum;
        }

        public int getScore() {
            return score;
        }

        public String getAverageTimeTurn() {
            String hms = String.format("%02d:%02d",
                    TimeUnit.NANOSECONDS.toMinutes(this.avargeTimeTurn) - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(this.avargeTimeTurn)),
                    TimeUnit.NANOSECONDS.toSeconds(this.avargeTimeTurn) - TimeUnit.NANOSECONDS.toSeconds(TimeUnit.NANOSECONDS.toMinutes(this.avargeTimeTurn)));
            return hms;
        }

        public void setAvarageTimeTurn(long time) {
            if (this.avargeTimeTurn == 0) {
                this.avargeTimeTurn = time;
                this.timeTurn = time;
                this.turns = 1;
            }
            else{
                this.turns++;
                this.timeTurn += time;
                long test = (this.timeTurn)/(this.turns);
                this.avargeTimeTurn = test;
            }
        }

        public void incMissNum() {
            this.missNum += 1;      }

        public void incScore(int inc) {
            this.score += inc;
        }
    }

    GameTool[][] myBoard ;
    char[][] rivalBoard ;
    String Name ;
    int numOfShip;
    private  boolean isAlive;
    private int size;
    private PlayerStatistics playerStatistics = new PlayerStatistics();
    private int shipTypeBScore = 5;
    private int shipTypeAScore = 10;
    public String getName() {
        return Name;
    }

    public int getScore(){
        return  this.playerStatistics.getScore();
    }


    public int getMissNum(){
        return  playerStatistics.getMissNum();
    }

    public String getAvargeTime (){
        return  playerStatistics.getAverageTimeTurn();
    }

    public void setAvargeTimeTurn(long time) {
        playerStatistics.setAvarageTimeTurn(time);
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
    public ArrayList<String> whoFindThere(int row, int column) {
        ArrayList<String> result = new ArrayList<String>();
        if (myBoard[row][column] == null) {
            result.add("non");
            return result;
        }

        if (myBoard[row][column].getIsAlive()) {
            result.add(myBoard[row][column].getSpecies());
            result.add((myBoard[row][column].getType()));
        }
        else{
            result.add("beenThere");
        }
        return result;
    }


    private  void updateStatisticsMyTurn (int row, int column, boolean iHit, String typeGameTool , int score){
        if (iHit){
            if (typeGameTool == "Mine"){

            }
            else {
                playerStatistics.incScore(score);
            }
        }
        else{
            playerStatistics.incMissNum();
        }
    }

    public boolean updateIMissMyTurn (int row, int column ){
        updateStatisticsMyTurn(row, column, false, null , 0);
        updateRivalBoard(row, column, false);
        return  true;
    }

    public boolean setMine (int row, int column){
        if (this.myBoard[row][column] != null){
            return  false;
        }

        this.myBoard[row][column] = new Mine("Mine");
        return  true;
    }

    public  boolean getIsAlive (){
        return  isAlive;
    }

    public void updateIHitMyTurn (int row, int column, String typeGameTool, int score){
        updateStatisticsMyTurn(row, column , true, typeGameTool, score);
        updateRivalBoard (row,column, true);
    }

    private boolean updateRivalBoard (int row, int column, boolean iHit){
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


}
