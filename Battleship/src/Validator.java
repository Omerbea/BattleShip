import GameParser.BattleShipGame;
import GameParser.BattleShipGame.Boards;
import GameParser.BattleShipGame.Boards.Board;
import GameParser.BattleShipGame.Boards.Board.Ship;
import GameParser.BattleShipGame.ShipTypes.ShipType;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Validator {

    public void ValidateBoardSize(int boardSize) throws Exception {
        //Board size validation
        if(boardSize < 0 ||
                boardSize > 20) {
            throw new Exception("Board size MUST be <= 20 or >= 0");
        }
    }

    public void ValidateGameBoards(List<Board> gameBoards) throws Exception {
        // Ship type validation
        for(Board GameBoard : gameBoards) {
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

    public void ValidateUserMove(String move) throws Exception {

        String []splittedMove = move.split("[ ]+");
        int r , c ;
        // check if first element is ""
        if(splittedMove[0].equals("")) {
            try {
                r = Integer.parseInt(splittedMove[1]);
                c = Integer.parseInt(splittedMove[2]);
                System.out.printf("numbers are %d %d" , r ,c );

            } catch (Exception e) {

                System.out.println(e.getMessage() + "that whay");
            }
        }
    }
}
