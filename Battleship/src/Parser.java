import GameParser.BattleShipGame;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Parser {

    private BattleShipGame ParsedGame;

    public BattleShipGame GetParsedGame() {
        return ParsedGame;
    }

    Parser() throws Exception {
        ParseXML();
    }



    private void ParseXML() throws Exception {
        try {

            File file = new File("resources\\battleShip_5_basic.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(BattleShipGame.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ParsedGame = (BattleShipGame) jaxbUnmarshaller.unmarshal(file);


        } catch (JAXBException e) {
            throw new Exception("File Cannot be Parsed by JAXB");
        }
    }
}
