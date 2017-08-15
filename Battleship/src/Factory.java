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

    public Validator getGameDataValidator() {
        return GameDataValidator;
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

        for(int player = 0 ; player < 2 ; player++) {
            GameTool[][] board = initPlayerBoard(GameData.getBoards().getBoard().get(player).getShip());
            PlayersArray[player] = new Player("Player" + (player + 1), GameData.getBoardSize(), board, GameData.getBoards().getBoard().get(player).getShip().size());

        }
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
        return board;
    }

    private void setBoard(int column, int row, GameTool[][] board, String shipDirection, String shipTypeId) {
        BattleShip bship = new BattleShip("Ship" ,shipTypeId ,getShipSizeByType(shipTypeId) , '#');
        int numberOfIterations = 0;
        int tempCol = column ;
        int tempRow = row ;

        if(shipDirection.equals("ROW")) {
           for(; numberOfIterations < bship.getShipSize() ; numberOfIterations++ , tempCol++) {
               board[tempRow][tempCol] = bship;
           }
        }

         if(shipDirection.equals("COLUMN")) {
            for( ; numberOfIterations < bship.getShipSize() ; numberOfIterations++ , tempRow++) {
                board[tempRow][tempCol] = bship;
            }
        }
    }

    private void printb(GameTool[][] board) {
        for(int i = 0 ; i < GameData.getBoardSize() ; i++) {
            for (int j = 0 ; j < GameData.getBoardSize(); j++) {
                if(board[i][j] == null) {
                System.out.printf("0");
                } else {
                    System.out.printf("*");
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
