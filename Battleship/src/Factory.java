import GameParser.BattleShipGame;

import java.util.ArrayList;

public class Factory {
    BattleShipGame GameData;
    Validator GameDataValidator = new Validator();

    Factory() {
        Parser parsedGame = new Parser();
        GameData = parsedGame.GetParsedGame();
    }

    public ArrayList<Player> CreatePlayers() throws Exception {
        //Validation pahse :
        try {
            GameDataValidator.ValidateBoadSize(GameData.getBoardSize());
            GameDataValidator.ValidateShipsType(GameData.getShipTypes().getShipType());
        }
        catch (Exception e) {
            throw e ;
        }

        ArrayList<Player> PlayersList = new ArrayList<Player>(2);

        return PlayersList;
    }
}
