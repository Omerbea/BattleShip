import GameParser.BattleShipGame;
import sun.applet.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class GameManager {
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
        this.mainMenu.add("play your turn"); //3
        this.mainMenu.add("statistics"); //4
        this.mainMenu.add("restart"); //5
        this.mainMenu.add("add mine"); //6
        this.mainMenu.add("quit game"); //7
    }
    private void start() {
        this.userInterface.printMenu(mainMenu,"middle");
        int input = -1;

        while (true) {

            try {
                input = this.userInterface.getUserInput();
            } catch (Exception e) {
                //send it to the console
                System.out.println("Please enter number");
            }

            switch (input) {
                case 1:
                    this.loadGame();
                    this.userInterface.printMenu(mainMenu, "middel");
                    this.userInterface.printMassage("your file loaded...");
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


    private  boolean excecuteMove() {
        if (!this.isGameRun) {
            //ERROR:
            //TODO: implement - returrn current massage
            return false;
        }
        userInterface.printMassage("player: " + players[whoPlay].getName() + " please insert coordinates");
        ArrayList<Integer> coordinates = userInterface.waitForCoordinates();
        String gameToolType = players[1- whoPlay].whoFindThere(coordinates.get(0), coordinates.get(1));
        switch (gameToolType){
            case "non":
                players[whoPlay].updateIMissMyTurn(coordinates.get(0), coordinates.get(1));
                this.changePlayer();
                return true;
            case "BattleShip":
                players[whoPlay].updateIHitMyTurn(coordinates.get(0), coordinates.get(1), gameToolType);
                userInterface.printMassage("You hit! your turn...");

                break;
            case  "Mine":
                players[whoPlay].updateIHitMyTurn(coordinates.get(0), coordinates.get(1), gameToolType);
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
            this.userInterface.printMassage("no file loaded, please try option 1 befre you try to start game..");
        }
        this.showStatusGame();
        this.isGameRun = true;
        return  true;
    }

    private boolean showStatusGame (){
/*        if (! this.isGameRun){
            //TODO: return relevent massage
            return false;
        }*/
        try {
            userInterface.printBaordsAndMenu(players[whoPlay].getName() ,players[whoPlay].getMyBoardForPrint(),players[whoPlay].getRivalBoard(), players[whoPlay].getScore(), this.mainMenu );
        }
        catch (Exception e){
            return  false;
        }
        return  true;

    }

    private boolean loadGame (){
        if (this.isGameRun){
            //TODO: present error to the ui and back to the loop
        }
        try{
            factory = new Factory();
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

