package dungeonmania.player;

import dungeonmania.util.Position;

public class NormalState implements PlayerState {

    private Player player;
    public NormalState(Player player) {
        this.player = player;
    }

    public String getStateName() {
        return "Normal";
    }

    public int getAttack() {
        return player.attack;
    }

    public double getHealth() {
        return player.health;
    }

    public Position getPosition() {
        return player.position;
    }

    public int getPotionTime() {
        return -1;
    }

    public void tickPotionTime() {
        return;
    }
    
}
