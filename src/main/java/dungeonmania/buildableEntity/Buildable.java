package dungeonmania.buildableEntity;

import dungeonmania.DungeonInfo;
import dungeonmania.exceptions.InvalidActionException;

public interface Buildable {
    public void craft(DungeonInfo info) throws InvalidActionException;
}
