final public class BattleShip extends GameTool {

    private String type;
    private int size;
    public BattleShip (String i_species, String i_type, int i_size){
        super(i_species);
        type = i_type;
        size = i_size;
    }

    /*return true if the ship distroyed*/
    public boolean updateHitMe() {
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
}
