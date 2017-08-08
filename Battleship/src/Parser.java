import GameParser.BattleShipGame;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Parser {
    public void ParseXML() {
        try {

            File file = new File("resources\\battleShip_5_basic.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(BattleShipGame.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            BattleShipGame battleShipGame = (BattleShipGame) jaxbUnmarshaller.unmarshal(file);


        } catch (JAXBException e) {
            //e.printStackTrace();
        }
    }
}
