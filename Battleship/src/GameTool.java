
public abstract class GameTool {
    private String species;
    private String type;
    private char sign;
    private int score;
    private int row;
    private int column;
    private int size;


    public GameTool(String i_species, String i_type, char i_sign) {
        species = i_species;
        type = i_type;
        sign = i_sign;

    }

    public GameTool(String i_species, String i_type, char i_sign, int i_size) {

    species =i_species;
    type =i_type;
    sign =i_sign;
    size = i_size;
}


    public String getSpecies (){
        return  species;
    }

    public String getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    abstract public boolean updateHitMe();
    public char getMySing (){
        return  sign;
    }

    public void setCoordinates(int i_row , int i_column) {
        row = i_row ;
        column = i_column;
    }

    public int getRow() {
        return row ;
    }

    public int getColumn() {
        return column;
    }

    public int getSize() {
        return size;
    }

    abstract public String getShipDirection();

    public void setSize(int i_size) {
        size = i_size ;
    }



}
