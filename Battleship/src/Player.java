import GameParser.BattleShipGame;
import java.util.List;

public class Player {

    GameTool[][] PlayerBoard = new GameTool[5][5];
    GameTool[][] RivalBoard = new GameTool[5][5];
    String PlayerName ;

    public void SetPlayerBoard(List<GameTool> gameToolsList) {
        // need this interface for the factory class .
        // gameToolsList is valid at this point .
    }

    public void SetPlayerName(String Name) {

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
