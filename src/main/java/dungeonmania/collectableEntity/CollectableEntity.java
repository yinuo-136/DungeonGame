package dungeonmania.collectableEntity;
import dungeonmania.Entity;
import dungeonmania.player.Player;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class CollectableEntity extends Entity{

    private String id;
    private String type;
    private Position position;

    public CollectableEntity(String id, String type, Position position) {
        this.id = id;
        this.type = type;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Position getPos() {
        return position;
    }

    public void pickup(Player player) {
        // TODO remove collectable from entity list and add item to player inventory (could maybe make an inventory class)
    }
    
    public EntityResponse getEntityResponse(){
        EntityResponse response = new EntityResponse(id, type, position, false);
        return response;
    }

    @Override
    public void setConfig() {
        // TODO Auto-generated method stub
        
    }
}
