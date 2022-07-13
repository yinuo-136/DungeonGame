package dungeonmania;

import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public abstract class Entity {
    protected DungeonInfo dungeonInfo;
    public abstract String getId();
    public abstract String getType();
    public abstract Position getPos();
    public abstract EntityResponse getEntityResponse();
    public abstract void setConfig();
    
    public void setDungeonInfo(DungeonInfo dungeonInfo) {
        this.dungeonInfo = dungeonInfo;
    }

    
}
