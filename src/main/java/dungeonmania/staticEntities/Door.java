package dungeonmania.staticEntities;

import dungeonmania.util.Position;

public class Door {
    private Position pos;
    private boolean isOpen;
    private int key;

    public Door(int x, int y, int key) {
        this.pos = new Position(x, y);
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

    
}
