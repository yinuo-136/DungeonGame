package dungeonmania.staticEntities;

import dungeonmania.Entity;
import dungeonmania.util.Position;

public class Boulder extends Entity {
    private String id;
    private Position position;

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

    
}
