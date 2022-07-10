package dungeonmania.staticEntities;

import dungeonmania.Entity;
import dungeonmania.util.Position;

public class Wall extends Entity {
    String id;
    private Position pos;

    public Wall(Position p, String id) {
        this.pos = p;
        this.id = id;
    }

    public Position getPos() {
        return pos;
    }

    public String getId() {
        return id;
    }

    
    
}
