final public class BattleShip extends GameTool {

    private int size;
    private char sign;
    public BattleShip (String i_species, String i_type, int i_size){
        super(i_species , i_type);
        size = i_size;
    }

    public int getShipSize() {
        return size;
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

    @Override
    public char getMySing() {
        return  this.sign;
    }
}
