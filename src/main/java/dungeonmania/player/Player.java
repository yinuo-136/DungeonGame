package dungeonmania.player;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player {
    //TODO: Finish STUB

    /**
     * Creates a Player Object at a sepcificied location with default health and attack values.
     *  
     * @param x     - Horizontal Position
     * @param y     - Vertical Position
     * 
     */
    public Player(Position position){
        //TODO 
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
    public Player(Position position, int attack){
        //TODO 
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
    public Player(Position position, double health){
        //TODO 
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
    public Player(Position position, int attack, double health){
        //TODO 
        return; 
    }

    /**
     * Gets the current Attack value of the player.
     * 
     * @return Attack - Integer
     */
    public int getAttack(){
        //TODO 
        return 0; 
    }

    /**
     * Sets the Attack value of the player.
     * 
     * @param attack
     */
    public void setAttack(int attack){
        //TODO 
        return;
    }

    /**
     * Gets the Health value of the player.
     * 
     * @return Health - Integer
     */
    public double getHealth(){
        //TODO 
        return 0;
    }
    
    /**
     * Sets the Health to a specified value.
     * 
     * @param Health
     */
    public void setHealth(double Health){
        //TODO 
        return;
    }
    
    /**
     * Reduces the Health by a specified value.
     * 
     * @param Health
     */
    public void reduceHealth(double Health){
        //TODO 
        return;
    }

    /**
     * Gets the current position of the player.
     * 
     * @return Position - Position
     */
    public Position getPos(){
        //TODO 
        return new Position(0,0);
    }

    /**
     * Sets the Position to a specified value.
     * 
     * @param x - Horizontal Position 
     * @param y - Vertical Position 
     */
    public void setPos(int x, int y){
        //TODO 
        return;
    }

    /**
     * Sets the Position to a specified value.
     * 
     * @param position - Position (x and y)  
     */
    public void setPos(Position position){
        //TODO 
        return;
    }
    
    /**
     * Moves the Player in a specified direction.
     * 
     * @param direction
     */
    public void move(Direction direction){
        //TODO 
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
        //TODO 
        return false;
    }
}
