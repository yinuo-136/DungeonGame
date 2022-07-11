package dungeonmania;

import dungeonmania.response.models.EntityResponse;

public abstract class Entity {
    protected DungeonInfo dungeonInfo;
    public abstract String getId();
    public abstract String getType();
    public abstract EntityResponse getEntityResponse();
    
    public void setDungeonInfo(DungeonInfo dungeonInfo) {
        this.dungeonInfo = dungeonInfo;
    }

    
}
