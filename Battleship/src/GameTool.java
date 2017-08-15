
public abstract class GameTool {
    private String species;
    private String type;
    public char sign ;


    public  GameTool (String i_species , String i_type , char i_sign){
        species = i_species;
        type = i_type ;
        sign = i_sign;

    }


    public String getSpecies (){
        return  species;
    }

    abstract public boolean updateHitMe();
    abstract public char getMySing ();


}
