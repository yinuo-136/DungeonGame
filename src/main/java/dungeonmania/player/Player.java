package dungeonmania.player;

import dungeonmania.Entity;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends Entity {
    private static double DEFAULT_HEALTH = 10;
    private static int DEFAULT_ATTACK;
    private String id;
    private String type = "player";

    Position position;
    double health;

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
        health = DEFAULT_HEALTH;

        return;
    }

    /**
     * Gets the current Attack value of the player.
     * 
     * @return Attack - Integer
     */
    public int getAttack(){
        return DEFAULT_ATTACK; 
    }

    /**
     * Gets the Health value of the player.
     * 
     * @return Health - Integer
     */
    public double getHealth(){ 
        return health;
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
        return position;
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
        position = position.translateBy(direction);

        return;
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

    
}
