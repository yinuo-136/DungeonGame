package dungeonmania.staticEntities;

import dungeonmania.Entity;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class Door extends Entity{
    private String id;
    private Position pos;
    private boolean isOpen;
    private int key;
    private String type = "door";

    public Door(Position position, int key, String id) {
        this.id = id;
        this.pos = position;
        this.isOpen = false;
        this.key = key;
    }

    public Position getPos() {
        return pos;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void Open(int key) {
        if (key == this.key){
            this.isOpen = true; 
        }
    }

    public int getKey() {
        return key;
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
