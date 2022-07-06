package dungeonmania.staticEntities;

import dungeonmania.util.Position;

public class Wall {
    private Position pos;

    public Wall(int x, int y) {
        this.pos = new Position(x, y);
    }

    public Position getPos() {
        return pos;
    }

    
}
