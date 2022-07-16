package dungeonmania.player;

import dungeonmania.inventoryItem.Potion.Potion;
import dungeonmania.util.Position;

public class InvisibleState implements PlayerState {
    
    private Position position = null;
    private int potionTime;
    private Player player;

    public InvisibleState(Player player) {
        this.player = player;
        //this.potionTime = potionTime;
    }


    public String getStateName() {
        return "Invisible";
    }

    public double getAttack() {
        return player.attack;
    }

    public double getHealth() {
        return player.health;
    }

    public Position getPosition() {
        return position;
    }

    public int getPotionTime() {
        return potionTime;
    }

    // Reduce the potion duration time by 1. then pull the potion from the queue and activate it or return to normal state if no potion in the queue.
    public void tickPotionTime() {
        potionTime--;
        if (potionTime < 0) {
            Potion potion = player.pullPotion();
            if (potion != null) {
                potion.takeAction();
                return;
            }
            player.setPlayerState(new NormalState(player));
        }
    }
    
}
