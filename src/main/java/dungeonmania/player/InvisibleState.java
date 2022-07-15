package dungeonmania.player;

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

    public int getAttack() {
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

    public void tickPotionTime() {
        potionTime--;
        if (potionTime <= 0) {
            player.setPlayerState(new NormalState(player));
        }
    }
    
}
