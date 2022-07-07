package dungeonmania.player;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player {
    private static final double DEFAULT_HEALTH = 10;
    private static final int DEFAULT_ATTACK = 5;

    Position position;
    int attack;
    double health;

    /**
     * Creates a Player Object at a sepcificied location with default health and attack values.
     *  
     * @param x     - Horizontal Position
     * @param y     - Vertical Position
     * 
     */
    public Player(int x, int y){
        position = new Position(x, y);
        attack = DEFAULT_ATTACK;
        health = DEFAULT_HEALTH;

        return;
    }

    /**
     * Creates a Player Object at a sepcificied location with a custom attack value and default health value.
     *  
     * @param x     - Horizontal Position
     * @param y     - Vertical Position
     * @param attack    - Attack of the player
     * 
     */
    public Player(int x, int y, int attack){
        position = new Position(x, y);
        this.attack = attack;
        health = DEFAULT_HEALTH; 
         
        return;         
    }

    /**
     * Creates a Player Object at a sepcificied location with a custom health value and default attack value.
     *  
     * @param x     - Horizontal Position
     * @param y     - Vertical Position
     * @param health    - Health of the player
     * 
     */
    public Player(int x, int y, double health){
        position = new Position(x, y);
        attack = DEFAULT_ATTACK;
        this.health = health; 
        
        return; 
    }

    /**
     * Creates a Player Object at a sepcificied location with custom health and attack values.
     *  
     * @param x    - Horizontal Position
     * @param y     - Vertical Position
     * @param attack   - Attack of the player
     * @param health   - Health of the player
     * 
     */
    public Player(int x, int y, int attack, double health){
        position = new Position(x, y);
        this.attack = attack;
        this.health = health; 

        return; 
    }

    /**
     * Gets the current Attack value of the player.
     * 
     * @return Attack - Integer
     */
    public int getAttack(){
        return attack; 
    }

    /**
     * Sets the Attack value of the player.
     * 
     * @param attack
     */
    public void setAttack(int attack){
        this.attack = attack;
        
        return;
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
}
