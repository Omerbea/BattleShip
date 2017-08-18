final public class BattleShip extends GameTool {

    private int score ;
    private String direction ;

    public BattleShip(String i_species, String i_type, int i_size, char i_sign, int i_score, String i_shipDirection){
        super(i_species , i_type , i_sign , i_size);
        score = i_score ;
        direction = i_shipDirection;
    }



    /*return true if the ship distroyed*/
    public boolean updateHitMe() {
        int size = this.getSize();
        if (size == 0 ){
            System.out.print("ERROR: BattleShip.updateHitMe() -  size = 0 ");
            //TODO: THROW EXCEPTION
        }
        size--;
        if (size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getShipDirection() {
        return direction;
    }
}
