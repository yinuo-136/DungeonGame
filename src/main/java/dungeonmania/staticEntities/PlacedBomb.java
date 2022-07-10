package dungeonmania.staticEntities;

import dungeonmania.util.Position;

public class PlacedBomb {
    
    private Position pos;

    public PlacedBomb(int x, int y) {
        this.pos = new Position(x, y);
    }

    public Position getPos() {
        return pos;
    }
}
