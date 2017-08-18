import GameParser.BattleShipGame;
import GameParser.BattleShipGame.Boards.Board.Ship;
import GameParser.BattleShipGame.ShipTypes.ShipType;

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

    private GameTool[][] initPlayerBoard(List<Ship> ships) throws Exception {
        int size = GameData.getBoardSize();
        int column = -1 , row = -1 ;
        GameTool[][] board = new GameTool[size][size];


        for(Ship ship : ships) {
            column = ship.getPosition().getX();
            row = ship.getPosition().getY();
            try {
                setBoard(column, row, board, ship.getDirection(), ship.getShipTypeId());
            } catch (Exception e) {
                throw e ;
            }
        }
        return board;
    }

    private void setBoard(int column, int row, GameTool[][] board, String shipDirection, String shipTypeId) throws Exception {
        BattleShip bship = new BattleShip("Ship" ,shipTypeId ,getShipSizeByType(shipTypeId) , '#' , getScoreByShipTypeId(shipTypeId) , shipDirection);
        int numberOfIterations = 0;
        int tempCol = column ;
        int tempRow = row ;
        bship.setCoordinates(row,column);

        if(shipDirection.equals("ROW")) {
                for (; numberOfIterations < bship.getSize(); numberOfIterations++, tempCol++) {
                    board[tempRow][tempCol] = bship;
                }
        }

        if(shipDirection.equals("COLUMN")) {
                for( ; numberOfIterations < bship.getSize() ; numberOfIterations++ , tempRow++) {
                    board[tempRow][tempCol] = bship;
            }
        }

        if(!GameDataValidator.canGameToolBePlaced(bship , board)) {
            throw new Exception("Problem placing : " + bship.getType() + " in board . \n row : " + bship.getRow() + " column : " + bship.getColumn() + " \n");
        }

    }

    public int getScoreByShipTypeId(String id) {
        List<ShipType> types = GameData.getShipTypes().getShipType();
        int score =0 ;
        for(ShipType type : types) {
            if(id.equals(type.getId())) {
                score = type.getScore();
            }
        }
        return score;
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
