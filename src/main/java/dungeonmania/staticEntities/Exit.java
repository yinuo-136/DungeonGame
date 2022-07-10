package dungeonmania.staticEntities;

import dungeonmania.Entity;
import dungeonmania.util.Position;

public class Exit extends Entity {
    private Position pos;
    private String id;

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

    
}
