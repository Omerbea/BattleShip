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

        public  long getGameTime () {
            return (System.nanoTime() - startTime);
        }

        public  void incrementTurn (){
            howManyTurn = howManyTurn++;
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
                    this.excecuteMove();
                    break;
                case 5:
                    this.showStatistic();
                    break;
                case 6:
                    System.out.println("option 6");
                    break;
                case 7:
                    break;
                default:
                    // send it to the console
                    System.out.println("Please choose number 1-7");


            }
        }
    }

    private boolean showStatistic () {
        if (!this.isGameRun) {
            this.handleWithErrorNoGame("no game run...");
            return  false;
        }
        userInterface.printMassage("Statistics: ");
        userInterface.printMassage("Number of Turns: " + gameStatistic.howManyTurn);
        showStatisticOnePlayer(whoPlay);
        showStatisticOnePlayer(1-whoPlay);
        return  true;
    }

    private void showStatisticOnePlayer (int player){
        //scure
        userInterface.printMassage( "Scure: " + players[player].getName() + ": "+  players[player].getScore());
        //miss
        userInterface.printMassage( "Miss: " + players[player].getName() + ": "+  players[player].getMissNum());
        //Average time
        userInterface.printMassage( "Average time: " + players[player].getName() + ": "+  players[player].getAvargeTime());
    }
    private void handleWithErrorNoGame (String msg){
                userInterface.printMassage(msg);
                try {
                    TimeUnit.SECONDS.sleep(1);
                }
                catch (InterruptedException e){

                }
                userInterface.printMenu(this.mainMenu, "middel");
    }


    private  boolean excecuteMove() {
        if (!this.isGameRun) {
            this.handleWithErrorNoGame("no game run...");
        }

        gameStatistic.incrementTurn();
        userInterface.printMassage( players[whoPlay].getName() + " please insert coordinates");
        ArrayList<Integer> coordinates = userInterface.waitForCoordinates();
        ArrayList <String> gameToolType = players[1- whoPlay].whoFindThere(coordinates.get(0), coordinates.get(1));
        switch (gameToolType.get(0)){
            case "non":
                players[whoPlay].updateIMissMyTurn(coordinates.get(0), coordinates.get(1));
                this.changePlayer();
                userInterface.printMassage(("You miss :( "));
                return true;
            case "BattleShip":
                players[whoPlay].updateIHitMyTurn(coordinates.get(0), coordinates.get(1), gameToolType.get(1));
                userInterface.printMassage("You hit! your turn...");

                break;
            case  "Mine":
                players[whoPlay].updateIHitMyTurn(coordinates.get(0), coordinates.get(1), gameToolType.get(1));
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
            handleWithErrorNoGame("game not loaded...");
        }
        this.isGameRun = true;
        this.showStatusGame();
        //TODO: embuse of the function its not realy error
        //handleWithErrorNoGame("");
        return  true;
    }

        private boolean showStatusGame (){
         if (! this.isGameRun){
                handleWithErrorNoGame("no game run...");
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
            handleWithErrorNoGame("Game is already run... ");
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

