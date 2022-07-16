package dungeonmania.staticEntities;

import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class FloorSwitch extends staticEntity {
    private String id;
    private Position pos;
    private boolean isTriggered;
    private String type = "switch";

    public FloorSwitch(Position p, String id) {
        this.id = id;
        this.pos = p;
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

    public String getType() {
        return type;
    }

    @Override
    public EntityResponse getEntityResponse() {
        EntityResponse response = new EntityResponse(id, type, pos, false);
        return response;
    }

    @Override
    public void setConfig() {
        
    }

    @Override
    public Position playerMoveIn(Position p, Direction d) {
        return this.pos;
    }

    @Override
    public Position boulderMoveIn(Position p) {
        return this.pos;
    } 
}
