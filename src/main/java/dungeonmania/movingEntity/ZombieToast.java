package dungeonmania.movingEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dungeonmania.Entity;
import dungeonmania.player.Player;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToast extends Entity implements Moving {
    private String id;
    private double health;
    private int damage;
    private Position position;
    //private boolean isAlive = true;
    private List<Direction> directions = Arrays.asList(Direction.DOWN, Direction.UP, Direction.LEFT, Direction.RIGHT);
    private String type = "zombie_toast";

    public ZombieToast(Position position, String id) {
        this.id = id;
        this.position = position;
    }

    public void move() {
        Random rand = new Random();
        Direction randDirection = directions.get(rand.nextInt(directions.size()));
        
        position = position.translateBy(randDirection);
    }

    @Override
    public void attack(Player player) {
        while(player.isAlive() || this.isAlive()) {
            player.setHealth(player.getHealth() - this.damage);
            this.setHealth(this.getHealth() - player.getAttack());
        }
        // then remove the player or the enemy depend on who died first.
    }

    @Override
    public Position getPosition() {
        
        return position;
    }

    @Override
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

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public EntityResponse getEntityResponse() {
        EntityResponse response = new EntityResponse(id, type, position, false);
        return response;
    }
    
    
}
