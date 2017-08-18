import java.util.ArrayList;

public abstract class GameTool {
    String species;
    String type;
    char sign ;
    int score ;
    ArrayList<Position> positionsHited = new ArrayList<>();
    private boolean isAlive =true;

    public  GameTool (String i_species , String i_type , char i_sign, int i_score){
        species = i_species;
        type = i_type ;
        sign = i_sign;
        score = i_score;

    }

    public void setAlive(boolean alive) {
        isAlive = alive;
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

    abstract public boolean updateHitMe(Position position);

    public char getMySing (){
        return  sign;
    }

    public boolean isHitMyThere (Position i_position){
        boolean flag = false;
        for (Position position : this.positionsHited){
            if (i_position.row == position.row && i_position.column == position.column){
                flag = true;
            }
        }
        return  flag;
    }


}
