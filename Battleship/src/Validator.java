import GameParser.BattleShipGame;
import GameParser.BattleShipGame.ShipTypes.ShipType;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Validator {
    public void ValidateBoadSize(int boardSize) throws Exception {

        if(boardSize < 0 ||
                boardSize > 20) {
            throw new Exception("Board size MUST be <= 20 or >= 0");
        }
    }

    public void ValidateShipsType(List<ShipType> shipType) {

    }
}
