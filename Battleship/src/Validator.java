import GameParser.BattleShipGame;
import GameParser.BattleShipGame.Boards;
import GameParser.BattleShipGame.Boards.Board;
import GameParser.BattleShipGame.Boards.Board.Ship;
import GameParser.BattleShipGame.ShipTypes.ShipType;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Validator {

    public void ValidateBoardSize(int boardSize) throws Exception {
        //Board size validation
        if(boardSize < 0 ||
                boardSize > 20) {
            throw new Exception("Board size MUST be <= 20 or >= 0");
        }
    }

    public void ValidateGameBoards(List<Board> GameBoards) throws Exception {
        // Ship type validation
        for(Board GameBoard : GameBoards) {
            for(Ship ship : GameBoard.getShip()) {
                if(!isShipTypeSupported(ship.getShipTypeId())) {
                    // Ship type not supported
                    throw new Exception(String.format("Ship Type %s is not supported ! " , ship.getShipTypeId()));
                }
            }
        }
    }

    private boolean isShipTypeSupported(String shipTypeId) {

        if(shipTypeId.equals("shipTypeA") ||
                shipTypeId.equals("shipTypeB")) {
            return true ;
        }
        return false ;
    }
}
