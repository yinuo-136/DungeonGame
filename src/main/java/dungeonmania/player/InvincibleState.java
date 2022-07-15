package dungeonmania.player;

import dungeonmania.inventoryItem.Potion.Potion;
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

    // Reduce the potion duration time by 1. then pull the potion from the queue and activate it or return to normal state if no potion in the queue.
    public void tickPotionTime() {
        potionTime--;
        if (potionTime <= 0) {
            Potion potion = player.pullPotion();
            if (potion != null) {
                potion.takeAction();
                return;
            }
            player.setPlayerState(new NormalState(player));
        }
    }

}

