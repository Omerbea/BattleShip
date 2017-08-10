import GameParser.BattleShipGame;
import GameParser.BattleShipGame.Boards.Board.Ship;
import GameParser.BattleShipGame.ShipTypes.ShipType;

import java.util.ArrayList;
import java.util.List;

public class Factory {
    BattleShipGame GameData;
    Validator GameDataValidator ;

    Factory() throws Exception {
        Parser parsedGame = new Parser();
        GameData = parsedGame.GetParsedGame();
        GameDataValidator = new Validator(parsedGame.GetParsedGame().getBoardSize());
    }

    public Player[] createPlayers() throws Exception {
        //Validation pahse :
        try {
            GameDataValidator.ValidateGameBoards(GameData.getBoards().getBoard());
        }
        catch (Exception e) {
            throw e ;
        }

        Player[] PlayersArray  = new Player[2];
        GameTool[][] board= initPlayerBoard(GameData.getBoards().getBoard().get(0).getShip());
        PlayersArray[0] = new Player("Player1" , GameData.getBoardSize() , board,1);

        return PlayersArray;
    }

    private GameTool[][] initPlayerBoard(List<Ship> ships) {
        int size = GameData.getBoardSize();
        int column = -1 , row = -1 ;
        GameTool[][] board = new GameTool[size][size];

        for(Ship ship : ships) {
            column = ship.getPosition().getX();
            row = ship.getPosition().getY();
            setBoard(column , row ,board , ship.getDirection() , ship.getShipTypeId());
        }

        for(column = 0; column < size ; column++) {
            for(row = 0 ; row < size ; row++ ) {

            }
        }
        return board;
    }

    private void setBoard(int column, int row, GameTool[][] board, String shipDirection, String shipTypeId) {
        BattleShip bship = new BattleShip("Ship" ,shipTypeId ,getShipSizeByType(shipTypeId));
        int numberOfIterations = 0;
        int tempCol = column ;
        int tempRow = row ;

        if(shipDirection.equals("ROW")) {
           for(numberOfIterations = 0; numberOfIterations < bship.getShipSize() ; numberOfIterations++ , tempCol++) {
               board[tempRow][tempCol] = bship;
           }
        }

        tempCol = column ;
        tempRow = row ;

        if(shipDirection.equals("COLUMN")) {
            for(numberOfIterations = 0 ; numberOfIterations < bship.getShipSize() ; numberOfIterations++ , tempRow--) {
                board[tempRow][tempCol] = bship;
            }
        }

        // test board
        printb(board);

    }

    private void printb(GameTool[][] board) {
        for(int i = 0 ; i < 5 ; i++) {
            for (int j = 0 ; j < 5 ; j++) {
                if(board[i][j] != null) {
                System.out.printf("*");
                } else {
                    System.out.printf("0");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private int getShipSizeByType(String id) {
        List<ShipType> types = GameData.getShipTypes().getShipType();
        int length = 0 ;
        for(ShipType type : types) {
            if(id.equals(type.getId())) {
                length = type.getLength();
            }
        }
        return length;

    }
}
