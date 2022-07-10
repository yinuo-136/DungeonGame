package dungeonmania.movingEntity;

import dungeonmania.player.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Mercenary implements Moving {
    private Position position;
    //private boolean isAlive = true;
    private double health;
    private int damage;
    private MercenaryMovingStrategy currentState = new NotBribedStrategy();
    private int costToBribe;

    public Mercenary(Position position, double health, int damage, int costToBribe) {
        this.position = position;
        this.health = health;
        this.damage = damage;
        this.costToBribe = costToBribe;
    }
    
    public void move(Player player) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void attack(Player player) {
        while(player.isAlive() || this.isAlive()) {
            player.setHealth(player.getHealth() - this.damage);
            this.setHealth(this.getHealth() - player.getAttack());
        }
    }

    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        return position;
    }

    public boolean bribe(int bribeAmount) {
        if (bribeAmount >= costToBribe) {
            currentState = new BribedStrategy();
            return true;
        }
        return false;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isAlive() {
        if (this.getHealth() > 0){
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Mercenary";	
    }
    
}
