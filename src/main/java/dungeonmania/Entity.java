package dungeonmania;

import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public abstract class Entity {
    public abstract String getId();
    public abstract String getType();
    public abstract EntityResponse getEntityResponse();
}
