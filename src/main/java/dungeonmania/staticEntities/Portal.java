package dungeonmania.staticEntities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Entity;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class Portal extends Entity {
    private String id;
    private static List<Portal> portalList = new ArrayList<>();
    private Position pos;
    private String colour;
    private String type = "portal";

    public Portal(Position p, String colour, String id) {
        this.id = id;
        this.pos = p;
        this.colour = colour;
        portalList.add(this);
    }

    public Position getPos() {
        return pos;
    }

    public String getColour() {
        return colour;
    }

    public String getId() {
        return id;
    }
    
    //TODO:teleport to the other portal
    public String getType() {
        return type;
    }

    @Override
    public EntityResponse getEntityResponse() {
        EntityResponse response = new EntityResponse(id, type, pos, false);
        return response;
    }
}
