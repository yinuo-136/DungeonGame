package dungeonmania.staticEntities;

import dungeonmania.Entity;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class Boulder extends Entity {
    private String id;
    private Position position;
    private String type = "boulder";

    public Boulder(Position position, String id) {
        this.position = position;
        this.id = id;
    }

    public Position getPos() {
        return position;
    }

    public void setPos(Position p){
        //TODO: Floor switch check before and after set
        this.position = p;
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
