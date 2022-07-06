package dungeonmania.staticEntities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.util.Position;

public class Portal {
    private static List<Portal> portalList = new ArrayList<>();
    private Position pos;
    private String colour;

    public Portal(int x, int y, String colour) {
        this.pos = new Position(x, y);
        this.colour = colour;
        portalList.add(this);
    }

    public Position getPos() {
        return pos;
    }

    public String getColour() {
        return colour;
    }
    
    //TODO:teleport to the other portal
}
