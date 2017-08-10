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

    UserInterface userInterface = new UserInterface();
    public GameManager(){
        setMainMenu();
    }
    public static void main(String [] args) throws Exception {
        Parser p = new Parser();
        BattleShipGame b = p.GetParsedGame();

        Factory f = new Factory();
        try {
            f.CreatePlayers();
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
                case '3':
                    break;
                case '4':
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

    private  boolean gameStart(){
        userInterface.printBaordsAndMenu(players[whoPlay].getMyBoardForPrint(),players[whoPlay].getRivalBoard(), this.mainMenu );
    }

    private boolean loadGame (){
        if (isGameRun){
            //TODO: present error to the ui and back to the loop
        }
        try{
            factory = new Factory();
            this.players = factory.CreatePlayers();
            return true;
        }
        catch (Exception e){
            //TODO: handle with the exceptions
        }
    }


}
