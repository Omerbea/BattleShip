public class Mine extends GameTool {
    public Mine (String i_species){
        super(i_species, "Mine" , 'M', 0);
    }

     public boolean updateHitMe(Position position){
        super.positionsHited.add(position);
        return  true;
     }



}
