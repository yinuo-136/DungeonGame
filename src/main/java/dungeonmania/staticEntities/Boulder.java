package dungeonmania.staticEntities;

import dungeonmania.util.Position;

public class Boulder {
    private Position pos;

    public Boulder(int x, int y) {
        this.pos = new Position(x, y);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position p){
        //TODO: Floor switch check before and after set
        this.pos = p;
    }

    
}
