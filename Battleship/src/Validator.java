import GameParser.BattleShipGame.Boards.Board;
import GameParser.BattleShipGame.Boards.Board.Ship;

import java.util.LinkedList;
import java.util.List;

public class Validator {

    class Postion {
        private int row ;
        private int column;

        Postion(int i_row , int i_column) {
            row = i_row;
            column = i_column ;
        }

        public int getRow() {return row ;}
        public int getColumn() {return column;}
    }
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


    public boolean isCordinateInRange(int cordinate) {

        if(cordinate < boardsize &&
                cordinate >= 0) {
            return true ;
        }
        return false ;
    }

    public boolean canGameToolBePlaced(GameTool bship, GameTool[][] board) throws Exception {

        LinkedList<Postion> listOfGameToolCoordinates = new LinkedList<>();
        listOfGameToolCoordinates = createListOfCoordinates(bship);

        for(Postion p : listOfGameToolCoordinates) {
            if(!isCordinateInRange(p.column)
                    || !isCordinateInRange(p.getRow())) {
                throw new Exception("Supplyed coordinates" + "(" + p.getRow() +","+p.getColumn() +")" +" are out of range ! ");

            }
        }

        for(Postion p : listOfGameToolCoordinates) {
            if(board[p.getRow()][p.getColumn()] != null) {
              throw new Exception("There is already a game tool at " + "(" + p.getRow() + "," + p.getColumn() +")");
            }
        }


        //check if gametool can be placed
        for(Postion p : listOfGameToolCoordinates) {
            if(isGameToolArroundCoordinate(p.getRow(), p.getColumn(), board , listOfGameToolCoordinates)) {
                throw new Exception("Should be one cell space from other gametools !");
            }
        }
        return true;
    }

    private boolean isGameToolArroundCoordinate(int i_row, int i_column, GameTool[][] board, LinkedList<Postion> listOfGameToolCoordinates) {

        int runner = 0;

        int column = (i_column - 1) ;
        int row = ( i_row - 1);

        for(int j = 0 ; j < 3 ; j++) {

            for (int index = 0; index < 3; index++, runner++) {

                if(isCordinateInRange(row) && isCordinateInRange(column + runner)) {
                    if (!isMyCoordinate(row, column + runner, listOfGameToolCoordinates)
                            && board[row][column + runner] != null) {
                        return true;
                    }
                }
            }
            runner = 0 ;
            row = row + 1;
        }
    return false ;
    }

    private boolean isMyCoordinate(int row, int column, LinkedList<Postion> listOfGameToolCoordinates) {
        for(Validator.Postion p : listOfGameToolCoordinates) {
            if (p.getColumn() == column &&
                    p.getRow() == row) {
                return true;
            }
        }

        return false ;
    }

    private LinkedList<Postion> createListOfCoordinates(GameTool gameTool) {

        LinkedList<Postion> postionList = new LinkedList<>();

        if(gameTool.getShipDirection().equals("Mine")) {
            postionList.add(new Postion(gameTool.getRow() , gameTool.getColumn()));
        }
        else {
            for (int i = 0; i < gameTool.getSize(); i++) {
                if (gameTool.getShipDirection().equals("ROW")) {
                    Postion p = new Postion(gameTool.getRow(), (gameTool.getColumn() + i));
                    postionList.add(p);

                } else if (gameTool.getShipDirection().equals("COLUMN")) {
                    Postion p = new Postion((gameTool.getRow() + i), gameTool.getColumn());
                    postionList.add(p);
                }
            }
        }

        return postionList;

    }
}
