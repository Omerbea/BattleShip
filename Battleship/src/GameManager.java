import GameParser.BattleShipGame;

import java.util.List;

public class GameManager {
    /*private List<String> mainMenu ;
    private  boolean isGameRun = false;
    private  Factory factory = new Factory();
    UserInterface userInterface = new UserInterface();
    public GameManager() throws Exception {
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
        mainMenu.add("read file");  //1
        mainMenu.add("start game"); //2
        mainMenu.add("state game"); //3
        mainMenu.add("play your turn"); //4
        mainMenu.add("statistics"); //5
        mainMenu.add("restart"); //6
        mainMenu.add("add mine"); //7
        mainMenu.add("quit game"); //8
    }
    private void start(){

        this.userInterface.printMenu(mainMenu,"middle", 1);
        char input = this.userInterface.waitForInput();
        while (true){
            switch (input){
                case '1':
                    if (isGameRun){
                        //TODO: present error to the ui and back to the loop
                    }
                    try{
                         //factory.
                    }
                    catch (Exception e){

                    }
                    break;
                case '2':
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
    }*/
    public static void main(String [] args) throws Exception {
        Factory f = new Factory();
        f.createPlayers();

    }
}
