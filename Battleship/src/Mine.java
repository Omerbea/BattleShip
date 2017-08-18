public class Mine extends GameTool {
    public Mine (String i_species){
        super(i_species, "Mine" , '*');
    }
    @Override
    public boolean updateHitMe() {
        return false;
    }

    @Override
    public String getShipDirection() {
        return null;
    }

    public  Mine (String i_species , String i_type , char i_sign){
        super( i_species, i_type , i_sign);
    }
}
