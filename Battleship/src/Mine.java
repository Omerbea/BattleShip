public abstract class Mine extends GameTool {
    public Mine (String i_species){
        super(i_species, "Mine");
    }
    @Override
    public boolean updateHitMe() {
        return false;
    }
}
