package dungeonmania.staticEntities;

import dungeonmania.Entity;
import dungeonmania.util.Position;

public class FloorSwitch extends Entity {
    private String id;
    private Position pos;
    private boolean isTriggered;

    public FloorSwitch(Position p, String id) {
        this.id = id;
        this.pos = p;
        //TODO: init the isTrigger.
        this.isTriggered = false;
    }

    public Position getPos() {
        return pos;
    }

    public boolean isTriggered() {
        return isTriggered;
    }

    public void setTriggered(boolean isTriggered) {
        this.isTriggered = isTriggered;
    }

    public String getId() {
        return id;
    }

     
}
