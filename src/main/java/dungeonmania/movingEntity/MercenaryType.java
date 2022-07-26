package dungeonmania.movingEntity;

import dungeonmania.player.Player;

public interface MercenaryType {
    public boolean checkBribeAmoountEnough(int bribeAmount);
    public boolean checkBribeDistance(Player player);
    public boolean bribe(int bribeAmount);
    public int getCostToBribe();
    public void setStrategy(MercenaryMovingStrategy strategy);
    public void setInvisibleStrategy(Player player);
    public void revertInvisibleStrategy();
}
