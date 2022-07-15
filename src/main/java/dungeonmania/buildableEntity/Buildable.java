package dungeonmania.buildableEntity;

import dungeonmania.exceptions.InvalidActionException;

public interface Buildable {
    public void craft() throws InvalidActionException;
}
