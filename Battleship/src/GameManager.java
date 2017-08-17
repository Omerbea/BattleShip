import GameParser.BattleShipGame;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.binding.BooleanExpression;
import sun.applet.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


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
    private  Factory factory ;
    private Player []players;
    private int whoPlay =0;
    private Validator validator ;
    private UserInterface userInterface = new UserInterface();
    public GameManager(){
        setMainMenu();
        gameStatistic = new GameStatistic();
    }

    public static void main(String [] args) throws Exception {
        Factory f = new Factory();
        try {
            f.createPlayers();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }

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
                System.out.println("Please enter number");
            }

            switch (input) {
                case 1:
                    if (this.loadGame()) {
                        this.userInterface.printMassage("your file loaded...");
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                        }
                        this.userInterface.printMenu(mainMenu, "middel");
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
                default:
                    // send it to the console
                    System.out.println("Please choose number 1-7");


            }
        }
    }
    private boolean restartGame (){
        return true;
    }
    private  boolean addMine (){
        return true;
    }
    private  boolean quiteGame(){
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
                    TimeUnit.SECONDS.sleep(1);
                }
                catch (InterruptedException e){

                }
                userInterface.printMenu(this.mainMenu, "middel");
    }


    private  boolean executeMove() {
        if (!this.isGameRun) {
            this.backToMainMenu("no game run...");
        }

        userInterface.printMassage( players[whoPlay].getName() + " please insert coordinates. first row space and then colunm");

        long startTime = System.nanoTime();
        ArrayList<Integer> coordinates = userInterface.waitForCoordinates();
        long finishTime = System.nanoTime();
        //Validator
        long deltaTime = finishTime - startTime ;
        players[whoPlay].setAvargeTimeTurn(deltaTime);
        gameStatistic.incrementTurn();
        ArrayList <String> gameToolType = players[1- whoPlay].whoFindThere(coordinates.get(0), coordinates.get(1));
        switch (gameToolType.get(0)){
            case "non":
                players[whoPlay].updateIMissMyTurn(coordinates.get(0), coordinates.get(1));
                this.changePlayer();
                //userInterface.printMassage(("You miss :( "));
                backToMainMenu("You miss :( ");
                return true;
            case "Ship":
                players[whoPlay].updateIHitMyTurn(coordinates.get(0), coordinates.get(1), gameToolType.get(1), factory.getScoreByShipTypeId(gameToolType.get(1)));
                backToMainMenu("You hit! your turn again...");

                break;
            case  "Mine":
                players[whoPlay].updateIHitMyTurn(coordinates.get(0), coordinates.get(1), gameToolType.get(1) ,0 );
                break;

        }




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

    private boolean loadGame (){
        if ( this.isGameRun){
            //TODO: present error to the ui and back to the loop
            backToMainMenu("Game is already run... ");
            return  false;
        }
        try{
            if (this.factory == null) {
                this.factory = new Factory();
            }
            this.players = factory.createPlayers();
            this.validator = factory.getGameDataValidator();
            this.isGameLoaded = true;
            return true;
        }
        catch (Exception e){
            //TODO: handle with the exceptions
        }


        return false;
    }


}

