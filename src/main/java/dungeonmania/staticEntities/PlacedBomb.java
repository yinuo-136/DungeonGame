package dungeonmania.staticEntities;

import dungeonmania.Entity;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class PlacedBomb extends Entity{
    
    private Position pos;
    private String type = "bomb";
    private String id;

    public PlacedBomb(Position position, String id) {
        this.pos = position;
        this.id = id;
    }

    public Position getPos() {
        return pos;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    @Override
    public EntityResponse getEntityResponse() {
        EntityResponse response = new EntityResponse(id, type, pos, false);
        return response;
    }

    
}
