package dungeonmania.staticEntities;

import dungeonmania.util.Position;

public class Exit {
    private Position pos;

    public Exit(int x, int y) {
        this.pos = new Position(x, y);
    }

    public Position getPos() {
        return pos;
    }

    
}
