package dungeonmania.player;

import java.util.List;

import dungeonmania.inventoryItem.Potion.Potion;
import dungeonmania.movingEntity.BribedStrategy;
import dungeonmania.movingEntity.Mercenary;
import dungeonmania.movingEntity.Moving;
import dungeonmania.movingEntity.NotBribedStrategy;
import dungeonmania.movingEntity.RandomStrategy;
import dungeonmania.util.Position;

public class InvisibleState implements PlayerState {
    
    private int potionTime;
    private Player player;
    private Position position;

    public InvisibleState(Player player, int potionTime) {
        this.player = player;
        this.potionTime = potionTime;

        List<Mercenary> allMencenary = player.getDungeonInfo().getAllMencenary();
        for (Mercenary mencenary : allMencenary) {
            mencenary.setStrategy(new RandomStrategy());
        }
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
        return player.position;
    }

    public int getPotionTime() {
        return potionTime;
    }

    // Reduce the potion duration time by 1. then pull the potion from the queue and activate it or return to normal state if no potion in the queue.
    public void tickPotionTime() {
        potionTime--;
        if (potionTime < 0) {
            List<Mercenary> allMencenary = player.getDungeonInfo().getAllMencenary();
            // return all mencenary to its original state
            for (Mercenary mencenary : allMencenary) {
                if (mencenary.getBribed()) {
                    mencenary.setStrategy(new BribedStrategy());
                } else {
                    mencenary.setStrategy(new NotBribedStrategy());
                }
            }
            Potion potion = player.pullPotion();
            if (potion != null) {
                potion.takeAction();
                return;
            }
            player.setPlayerState(new NormalState(player));
        }
    }
    
}
