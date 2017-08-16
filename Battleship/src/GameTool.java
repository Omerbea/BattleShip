
public abstract class GameTool {
    private String species;
    private String type;
    private char sign ;



    public  GameTool (String i_species , String i_type , char i_sign){
        species = i_species;
        type = i_type ;
        sign = i_sign;

    }


    public String getSpecies (){
        return  species;
    }

    public String getType() {
        return type;
    }

    abstract public boolean updateHitMe();
    public char getMySing (){
        return  sign;
    }


}
