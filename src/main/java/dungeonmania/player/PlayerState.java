package dungeonmania.player;

import dungeonmania.util.Position;

public interface PlayerState {
    public String getStateName();
    public int getAttack();
    public double getHealth();
    public Position getPosition();
    public int getPotionTime();
    public void tickPotionTime();
}
