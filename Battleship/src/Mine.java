public class Mine extends GameTool {
    public Mine (String i_species){
        super(i_species, "Mine" , 'M', 0);
    }
    @Override
    public boolean updateHitMe() {
        return false;
    }


}
