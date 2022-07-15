package dungeonmania.player;

import dungeonmania.util.Position;

public class InvincibleState implements PlayerState {

    private int attack = Integer.MAX_VALUE;
    private double health = Double.MAX_VALUE;
    private int potionTime;
    private Player player;

    public InvincibleState(Player player) {
        this.player = player;
        //this.potionTime = ;
    }

    public String getStateName() {
        return "Invincible";
    }

    public int getAttack() {
        return attack;
    }

    public double getHealth() {
        return health;
    }

    public Position getPosition() {
        return player.position;
    }

    public int getPotionTime() {
        return potionTime;
    }

    public void tickPotionTime() {
        potionTime--;
        if (potionTime <= 0) {
            player.setPlayerState(new NormalState(player));
        }
    }

}

