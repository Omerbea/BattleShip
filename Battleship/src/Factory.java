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

    public Player[] CreatePlayers() throws Exception {
        //Validation pahse :
        try {
            GameDataValidator.ValidateGameBoards(GameData.getBoards().getBoard());
        }
        catch (Exception e) {
            throw e ;
        }

        Player[] PlayersArray  = new Player[2];
        GameTool[][] board= InitPlayerBoard(GameData.getBoards().getBoard().get(0).getShip());
        PlayersArray[0] = new Player("Player1" , GameData.getBoardSize() , board,1);

        return PlayersArray;
    }

    private GameTool[][] InitPlayerBoard(List<Ship> ships) {
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

        if(shipDirection.equals("ROW")) {
           for(; column <= bship.getShipSize() ; column++) {
               board[row][column] = bship;
           }
        }

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
