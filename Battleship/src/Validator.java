import GameParser.BattleShipGame;
import GameParser.BattleShipGame.Boards;
import GameParser.BattleShipGame.Boards.Board;
import GameParser.BattleShipGame.Boards.Board.Ship;
import GameParser.BattleShipGame.ShipTypes.ShipType;
import com.sun.deploy.util.StringUtils;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Validator {


    private static final int ROW = 0 ;
    private static final int COLUMN = 1;
    private final int boardsize ;

    Validator(int boardSize) throws Exception {
        ValidateBoardSize(boardSize);
        boardsize = boardSize ;
    }

    private void ValidateBoardSize(int size) throws Exception {
        //Board size validation

        if(size < 5 ||
                size > 20) {
            throw new Exception("Board size MUST be <= 20 or >= 0");
        }
    }

    public void ValidateGameBoards(List<Board> gameBoards) throws Exception {
        // Ship type validation
        for(Board GameBoard : gameBoards) {
            for(Ship ship : GameBoard.getShip()) {
                if(!IsShipTypeSupported(ship.getShipTypeId())) {
                    // Ship type not supported
                    throw new Exception(String.format("Ship Type %s is not supported ! " , ship.getShipTypeId()));
                }
            }
        }
    }

    private boolean IsShipTypeSupported(String shipTypeId) {

        if(shipTypeId.equals("shipTypeA") ||
                shipTypeId.equals("shipTypeB")) {
            return true ;
        }
        return false ;
    }

    public int[] ValidateUserMove(String move) throws Exception {

        String []splittedMove = move.split("[ ]+");
        int []cordinate = new int[2];
        int index = 0 ;

        // check if first element is ""
        if(splittedMove[0].equals("")) {
            index = 1;
        }

        try {
            cordinate[ROW] = Integer.parseInt(splittedMove[index]);
            cordinate[COLUMN] = Integer.parseInt(splittedMove[++index]);

        } catch (Exception e) {
            throw new Exception("Row and column must be number .");
        }

        if(!IsCordinateOutOfRange(cordinate[ROW]) ||
                !IsCordinateOutOfRange(cordinate[COLUMN])) {
            throw new Exception("Cordinate supplyed is out of range . ");

        }

        return cordinate;
    }

    private boolean IsCordinateOutOfRange(int cordinate) {

        if(cordinate > boardsize ||
                cordinate < boardsize) {
            return false ;
        }
        return true ;
    }

    public boolean canShipCoordinateBePlaced(int tempRow, int tempCol) {
        // implement
        return true;
    }
}
