import GameParser.BattleShipGame;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.binding.BooleanExpression;
import sun.applet.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
//comment1--
public class GameManager {
    class GameStatistic {
        int howManyTurn =0 ;
        long startTime = System.nanoTime();

        public int getHowManyTurn() {
            return howManyTurn;
        }

        public  String getGameTime () {
            long timeNano= ((System.nanoTime() - startTime));
            String hms = String.format("%02d:%02d",
                     TimeUnit.NANOSECONDS.toMinutes(timeNano) - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(timeNano)),
                    TimeUnit.NANOSECONDS.toSeconds(timeNano) - TimeUnit.NANOSECONDS.toSeconds(TimeUnit.NANOSECONDS.toMinutes(timeNano)));
            return hms;
        }
        public  void incrementTurn (){
            howManyTurn += 1;
        }
    }

    private  GameStatistic gameStatistic ;
    private ArrayList<String> mainMenu = new ArrayList<>();
    private  boolean isGameRun = false;
    private  boolean isGameLoaded = false;
    private boolean isGameOver = false;
    private  Factory factory ;
    private Player []players;
    private int whoPlay =0;
    private Validator validator ;
    private UserInterface userInterface = new UserInterface();
    public GameManager(){
        setMainMenu();
        gameStatistic = new GameStatistic();
    }

    public static void main(String [] args) throws Exception{
        GameManager gameManager = new GameManager();
        gameManager.start();
    }

    private void  setMainMenu(){
        this.mainMenu.add("read file");  //1
        this.mainMenu.add("start game"); //2
        this.mainMenu.add("show game state"); //3
        this.mainMenu.add("play your turn"); //4
        this.mainMenu.add("statistics"); //5
        this.mainMenu.add("restart"); //6
        this.mainMenu.add("add mine"); //7
        this.mainMenu.add("quit game"); //8
    }

    private void start() {
        this.userInterface.printMenu(mainMenu,"middle");
        int input = -1;

        while (true) {
            try {
                input = this.userInterface.getMenuOption();
            } catch (Exception e) {
                //send it to the console
                this.backToMainMenu("Please enter number");
                input = -1;
            }
            switch (input) {
                case 1:
                    if (this.isGameRun){
                        backToMainMenu("the game already run");
                        continue;
                    }
                    userInterface.printMassage("Please Enter full path for xml file :");
                    String xmlpath = new Scanner(System.in).nextLine();
                    if (this.loadGame(xmlpath)) {
                        backToMainMenu("your file is loaded...");

                    }
                    else{
                    }
                    break;
                case 2:
                    this.gameStart();
                    break;
                case 3:
                    this.showStatusGame();
                    break;
                case 4:
                    this.executeMove();
                    break;
                case 5:
                    this.showStatistic();
                    break;
                case 6:
                    this.restartGame();
                    break;
                case 7:
                    this.addMine();
                    break;
                case 8:
                    this.quiteGame();
                    return;
                default:
                    // send it to the console
                    System.out.println("Please choose number 1-7");
            }
        }
    }

    private boolean restartGame (){
        if (! this.isGameRun){
            backToMainMenu("cannot do restart because no game run..");
        }
        userInterface.printMassage("restarting...");
        this.whoPlay=0;
        this.players = null;
        this.gameStatistic = null;
        this.isGameRun =false;
        this.isGameLoaded= false;
        userInterface.printMassage(("restart successfully!"));
        this.userInterface.printMenu(mainMenu,"middle");

        return true;
    }

    private  boolean addMine (){
        if (!this.isGameRun){
            backToMainMenu("cannot add mine when no game run...");
            return  false;
        }
        if (! players[whoPlay].iHaveMoreMine()){
            backToMainMenu("You don't have anymore mines to set");
            return false;
        }
        while (true) {
            userInterface.printMassage("please insert coordinates ");
            Mine mine = new Mine("Mine");
            try {   
                ArrayList<Integer> coordinates = userInterface.waitForCoordinates();
                //Fixing user row to start from 0
                coordinates.set(0 , coordinates.get(0) - 1);
                if ( !validator.isCordinateInRange(coordinates.get(0)) || ! validator.isCordinateInRange((coordinates.get(1)))){
                    userInterface.printMassage("the coordinates not in the range, please try anther coordinates in range...");
                    continue;
                }

                mine.setCoordinates(coordinates.get(0), coordinates.get(1));
                // check if mine can be set there

                if (validator.canGameToolBePlaced(mine, players[whoPlay].myBoard)) {
                    if (players[whoPlay].setMine(coordinates.get(0), coordinates.get(1))) {
                        backToMainMenu("set mine! ");
                        break;
                    }
                    userInterface.printMassage(" canot set Mine in these place. please insert new coordinates... ");
                }
            }
            catch (Exception e){
                userInterface.printMassage(e.getMessage());
            }
        }
        return true;
    }
    private  boolean quiteGame(){
        userInterface.printMassage("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        userInterface.printMassage("Hope you enjoyed:)");
        userInterface.printMassage("Thank you for playing... see you next time ;)");
        userInterface.printMassage("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        userInterface.printMassage("Quit!");
        return true;
    }
    private boolean showStatistic () {
        if (!this.isGameRun) {
            this.backToMainMenu("no game run...");
            return  false;
        }
        userInterface.printMassage(" ");
        userInterface.printMassage("Statistics: ");
        userInterface.printMassage("Number of Turns: " + gameStatistic.howManyTurn);
        userInterface.printMassage("Game Time: " + gameStatistic.getGameTime());
        showStatisticOnePlayer(whoPlay);
        showStatisticOnePlayer(1-whoPlay);
        backToMainMenu(" ");
        return  true;
    }

    private void showStatisticOnePlayer (int player){
        //scure
        userInterface.printMassage( players[player].getName() +  " -- Score: " +  players[player].getScore());
        //miss
        userInterface.printMassage( players[player].getName() +  " -- Miss:  "+  players[player].getMissNum());
        //Average time
        userInterface.printMassage( players[player].getName() + " -- Average time: " +  players[player].getAvargeTime());
    }
    private void backToMainMenu (String msg){
                userInterface.printMassage(msg);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                }
                catch (InterruptedException e){

                }
                userInterface.printMenu(this.mainMenu, "middel");
    }

    private  boolean executeMove() {
        if (!this.isGameRun) {
            this.backToMainMenu("no game run...");
            return false;
        }
        userInterface.printMassage( players[whoPlay].getName() + " Please insert coordinates. Should be from the form : ");
        userInterface.printMassage("Row(number) space column(capital letter)");
        userInterface.printMassage("for example : 4 B");
        long finishTime = 0;
        long startTime =0;
        ArrayList<Integer> coordinates = null;
        while (true) {
            startTime = System.nanoTime();
            coordinates = userInterface.waitForCoordinates();
            //Fixing user row to start from 0
            coordinates.set(0 , coordinates.get(0) - 1);
            if (! validator.isCordinateInRange(coordinates.get(0)) || ! validator.isCordinateInRange((coordinates.get(1))))
            {
                userInterface.printMassage(("your coordinates not in the range , please try again with cooredinates that are in range..."));
                continue;
            }


            finishTime = System.nanoTime();
            if (this.checkIfgGuessed (coordinates) == false){
                break;
            }
            userInterface.printMassage("You guessed already this cooredinates. try again..");
        }
        //Validator
        long deltaTime = finishTime - startTime ;
        players[whoPlay].setAvargeTimeTurn(deltaTime);
        gameStatistic.incrementTurn();

        ArrayList <String> gameToolType = players[1- whoPlay].whoFindThere(coordinates.get(0), coordinates.get(1));
        executeByTypeTool (gameToolType , coordinates , whoPlay, false);

        return true;
    }

    private  boolean checkIfgGuessed(ArrayList<Integer> coordinates){
         return players[whoPlay].rivalBoard[coordinates.get(0)][ coordinates.get(1)] != 0;
    }
    private boolean executeByTypeTool (ArrayList<String> gameToolType , ArrayList<Integer> coordinates , int player, boolean mine) {
        switch (gameToolType.get(0)) {
            case "non":
                players[player].updateIMissMyTurn(coordinates.get(0), coordinates.get(1));
                this.changePlayer();
                //userInterface.printMassage(("You miss :( "));
                if (mine){
                    backToMainMenu("player" + (player+1) + " your mine explode and miss");
                }
                else {
                    backToMainMenu("You miss :( ");
                }
                return true;
            case "Ship":
                if (mine){
                    userInterface.printMassage("player " + (player +1) + " you mine hit in a ship! wall done!");
                }
                players[player].updateIHitMyTurn(coordinates.get(0), coordinates.get(1), gameToolType.get(1), factory.getScoreByShipTypeId(gameToolType.get(1)));
                String msg = players[1-player].updateHitMe(coordinates , false);
                if (msg == "Game Over"){
                    this.finishTheGame();
                }
                backToMainMenu(msg);
                return  true;
            case "Mine":

                userInterface.printMassage( players[whoPlay].getName() +" You hit in Mine :/");
                this.executeMine(coordinates, player);
                backToMainMenu( "");
                return true ;
             default: return false;
        }
    }

    private void  finishTheGame(){
        this.isGameOver = true;
        this.isGameLoaded =false;
        this.isGameRun = false;
        this.userInterface.printMassage("Well Done! you won");
    }

    private  boolean executeMine(ArrayList<Integer> coordinates, int player){

        ArrayList <String> gameToolType = players[whoPlay].whoFindThere(coordinates.get(0), coordinates.get(1));
        if (gameToolType.get(0) == "Mine"){
            //players[whoPlay].rivalBoard[coordinates.get(0)][coordinates.get(1)] = '-';
            //players[1 - whoPlay].rivalBoard[coordinates.get(0)][coordinates.get(1)] = '-';
            players[whoPlay].updateIMissMyTurn(coordinates.get(0), coordinates.get(1));
            players[ 1- whoPlay].updateIMissMyTurn(coordinates.get(0), coordinates.get(1));
            players[whoPlay].updateHitMe(coordinates, true);
            players[1- whoPlay].updateHitMe((coordinates), true);
            userInterface.printMassage(players[ 1 - whoPlay].getName() + " your mine Hit at your mine arrival :/ ");
            return true;
        }
        players[whoPlay].updateIHitMyTurn(coordinates.get(0), coordinates.get(1), gameToolType.get(0), factory.getScoreByShipTypeId(gameToolType.get(0)));
        players[1-whoPlay].updateHitMe(coordinates, true);
        this.executeByTypeTool(gameToolType, coordinates ,1- whoPlay , true);

        return true;
    }
    private  void changePlayer (){
        whoPlay = 1- whoPlay;
    }



    private  boolean gameStart(){
        if (! this.isGameLoaded){
            //ERROR: the game not loaded.
            //TODO: implement return relevant massage
            backToMainMenu("game not loaded...");
            return true;
        }
        if (this.isGameRun){
            backToMainMenu("game already run.");
            return true;
        }
        this.isGameRun = true;
        this.showStatusGame();
        //TODO: embuse of the function its not realy error
        //handleWithErrorNoGame("");
        return  true;
    }

     private boolean showStatusGame (){
         if (! this.isGameRun){
             backToMainMenu("no game run...");
                return false;
        }
        try {

            userInterface.printBaordsAndMenu(players[whoPlay].getName() ,players[whoPlay].getMyBoardForPrint(),players[whoPlay].getRivalBoard(), players[whoPlay].getScore(), this.mainMenu );
        }
        catch (Exception e){
            return  false;
        }
        return  true;
    }

    private boolean loadGame (String xmlPath){
        if ( this.isGameRun){
            //TODO: present error to the ui and back to the loop
            backToMainMenu("Game is already run... ");
            return  false;
        }
        try{
            this.factory = new Factory(xmlPath);
            this.players = factory.createPlayers();
            this.validator = factory.getGameDataValidator();
            this.userInterface.setBoardSize(factory.GameData.getBoardSize());
            this.isGameLoaded = true;
            return true;
        }
        catch (Exception e){
            backToMainMenu(e.getMessage());
        }


        return false;
    }


}

