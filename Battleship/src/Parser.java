import GameParser.BattleShipGame;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.spi.FileTypeDetector;

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

            String xmlPath = "resources\\battleShip_5_basic.xml";

            File file = new File(xmlPath);

            if(file.exists() && !file.isDirectory()) {

                int dotIndex = file.getName().lastIndexOf('.');
                if(dotIndex == -1) {
                    throw new Exception("File extension is not recognize.");
                } else {
                    if(!file.getName().substring(dotIndex + 1).equals("xml")) {
                        throw new Exception("File must be of format xml .");
                    }
                }
                JAXBContext jaxbContext = JAXBContext.newInstance(BattleShipGame.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                ParsedGame = (BattleShipGame) jaxbUnmarshaller.unmarshal(file);
            } else {
                throw new Exception("File " + xmlPath + " is not exsist ." );
            }


        } catch (JAXBException e) {
            throw new Exception("File Cannot be Parsed by JAXB");
        }
    }

    public String getFileExtension(String fullName) throws Exception {
        if(fullName == null); {
            throw new Exception("Path is null");
        }
    }
}
