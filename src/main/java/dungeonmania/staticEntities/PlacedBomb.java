package dungeonmania.staticEntities;

import dungeonmania.Entity;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class PlacedBomb extends staticEntity {
    
    private Position pos;
    private String type = "bomb";
    private String id;
    private int radius;

    public PlacedBomb(Position position, String id) {
        this.pos = position;
        this.id = id;      
    }

    public void setConfig(){
        radius = dungeonInfo.getSpecificConfig("bomb_radius");
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

    public int getRadius() {
        return radius;
    }

    @Override
    public Position playerMoveIn(Position p, Direction d) {
        return p;
    }

    @Override
    public Position boulderMoveIn(Position p) {
        return p;
    }


    
}
