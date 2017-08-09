import GameParser.BattleShipGame;

public class GameManager {

    public static void main(String [] args) throws Exception {
//        Parser p = new Parser();
//        BattleShipGame b = p.GetParsedGame();
//        Factory f = new Factory();
//        try {
//            f.CreatePlayers();
//        } catch (Exception e) {
//            System.out.printf(e.getMessage());
//        }
//
//        System.out.println();

        Validator v = new Validator();
        v.ValidateUserMove("     A111 222   333                 4");

    }
}
