
public abstract class GameTool {
    String species;

    public  GameTool (String i_species){
        species = i_species;
    }


    public String getSpecies (){
        return  species;
    }

    abstract public boolean updateHitMe();
    abstract public char getMySing ();


}
