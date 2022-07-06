package dungeonmania.staticEntities;

import dungeonmania.util.Position;

public class FloorSwitch {
    private Position pos;
    private boolean isTriggered;

    public FloorSwitch(int x, int y) {
        this.pos = new Position(x, y);
        //TODO: init the isTrigger.
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

     
}
