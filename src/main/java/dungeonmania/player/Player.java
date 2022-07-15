package dungeonmania.player;

import java.util.List;

import dungeonmania.Entity;
import dungeonmania.collectableEntity.CollectableEntity;
import dungeonmania.collectableEntity.Key;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.staticEntities.staticEntity;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends Entity {
    private String id;
    private String type = "player";

    protected int attack;
    protected Position position;
    protected double health;

    private PlayerState playerState = new NormalState(this);

    /**
     * Creates a Player Object at a sepcificied location with default health and attack values.
     *  
     * @param x     - Horizontal Position
     * @param y     - Vertical Position
     * 
     */
    public Player(Position position, String id){
        this.position = position;
        this.id = id;

        return;
    }

    public void setConfig(){
        this.health = dungeonInfo.getSpecificConfig("player_health");
        this.attack = dungeonInfo.getSpecificConfig("player_attack");
    }

    /**
     * Gets the current Attack value of the player.
     * 
     * @return Attack - Integer
     */
    public int getAttack(){
        return playerState.getAttack(); 
    }

    /**
     * Gets the Health value of the player.
     * 
     * @return Health - Integer
     */
    public double getHealth(){ 
        return playerState.getHealth();
    }
    
    /**
     * Sets the Health to a specified value.
     * 
     * @param health
     */
    public void setHealth(double health){
        this.health = health;

        return;
    }
    
    /**
     * Reduces the Health by a specified value.
     * 
     * @param health
     */
    public void reduceHealth(double health){
        this.health -= health;

        return;
    }

    /**
     * Gets the current position of the player.
     * 
     * @return Position - Position
     */
    public Position getPos(){
        return playerState.getPosition();
    }

    /**
     * Sets the Position to a specified value.
     * 
     * @param x - Horizontal Position 
     * @param y - Vertical Position 
     */
    public void setPos(int x, int y){
        position = new Position(x, y);
         
        return;
    }

    /**
     * Sets the Position to a specified value.
     * 
     * @param position - Position (x and y)  
     */
    public void setPos(Position position){
        this.position = position;

        return;
    }
    
    /**
     * Moves the Player in a specified direction.
     * 
     * @param direction
     */
    public void move(Direction direction){
        //check the static entities before move into the cell
        Position p = null;
        Position checkPosition = position.translateBy(direction);
        List<Entity> checkEntity = dungeonInfo.getEntitiesByPosition(checkPosition);
        for (Entity e : checkEntity){
            if (e instanceof staticEntity){
                staticEntity se = (staticEntity) e;
                p = se.playerMoveIn(this.position, direction);
            }

            if (this.position.equals(p)){
                break;
            }
        }
        if (p == null){
            this.position = checkPosition;
        } else {
            this.position = p;
        }

        //After move to the cell, collect all collectables
        checkEntity = dungeonInfo.getEntitiesByPosition(this.position);
        for(Entity e : checkEntity){
            if (e instanceof CollectableEntity) {
                if (e instanceof Key){
                    Key k = (Key) e;
                    k.pickup();
                } else {
                    CollectableEntity ce = (CollectableEntity) e;
                    ce.pickup();
                }

            }
        }
        
    }
    
    /**
     * Checks if the player is currently alive or dead.
     * 
     * @return 
     *      <code>true</code> if health is 
     *      greater than 0;
     *      <code>false</code> otherwise.
     */
    public Boolean isAlive() {
        if (health > 0) { return true; }
        else { return false; }
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

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }
    
}
