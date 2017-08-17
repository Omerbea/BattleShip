
public abstract class GameTool {
    String species;
    String type;
    char sign ;
    int score ;
    private boolean isAlive =true;

    public  GameTool (String i_species , String i_type , char i_sign, int i_score){
        species = i_species;
        type = i_type ;
        sign = i_sign;
        score = i_score;

    }

    public char getSign() {
        return sign;
    }

    public boolean getIsAlive(){
        return this.isAlive;
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


}
