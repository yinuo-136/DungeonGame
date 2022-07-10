package dungeonmania.staticEntities;

import dungeonmania.Entity;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class Exit extends Entity {
    private Position pos;
    private String id;
    private String type = "exit";

    public Exit(Position position, String id) {
        this.pos = position;
        this.id = id;
    }

    public Position getPos() {
        return pos;
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
}
