
public abstract class GameTool {
    private String species;
    private String type;


    public  GameTool (String i_species , String i_type){
        species = i_species;
        type = i_type ;

    }


    public String getSpecies (){
        return  species;
    }

    abstract public boolean updateHitMe();
    abstract public char getMySing ();


}
