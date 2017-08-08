import GameParser.BattleShipGame;

public class Player {

    GameTool[][] board = new GameTool[5][5];

    public void boardtest() {
        board[0][0] = new BattleShip();
        board[0][1] = new Mine();
        board[0][2] = null ;
    }

}
