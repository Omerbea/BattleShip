import GameParser.BattleShipGame;

import java.lang.reflect.Array;
import java.util.List;

public class GameManager {
    private List<String> mainMenu ;
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
        Parser p = new Parser();
        BattleShipGame b = p.GetParsedGame();

        Factory f = new Factory();
        try {
            f.createPlayers();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }

        System.out.println();
    }

    private void  setMainMenu(){
        this.mainMenu.add("read file");  //1
        this.mainMenu.add("start game"); //2
        this.mainMenu.add("state game"); //3
        this.mainMenu.add("play your turn"); //4
        this.mainMenu.add("statistics"); //5
        this.mainMenu.add("restart"); //6
        this.mainMenu.add("add mine"); //7
        this.mainMenu.add("quit game"); //8
    }
    private void start(){
        this.userInterface.printMenu(mainMenu,"middle", 1);
        char input = this.userInterface.waitForInput();
        while (true){
            switch (input){
                case '1': this.loadGame();
                    break;
                case '2': this.gameStart();
                    break;
                case '3': this.showStatusGame();
                    break;
                case '4': excecuteMove();
                    break;
                case '5':
                    break;
                case '6':
                    break;
                case '7':
                    break;
                case '8':
                    break;

            }
        }
    }

    private  boolean excecuteMove(){
        if (!this.isGameRun){
            //ERROR:
            //TODO: implement - returrn current massage
            return false;
        }
        return true ;

    }

    private  boolean gameStart(){
        if (! this.isGameLoaded){
            //ERROR: the game not loaded.
            //TODO: implement return relevant massage
        }
        this.showStatusGame();
        this.isGameRun = true;
        return  true;
    }

    private boolean showStatusGame (){
        if (! this.isGameRun){
            //TODO: return relevent massage
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
