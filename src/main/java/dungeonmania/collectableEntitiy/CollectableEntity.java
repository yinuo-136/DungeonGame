package dungeonmania.collectableEntitiy;
import dungeonmania.player.Player;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Position;

public class CollectableEntity {
    private Position position;
    private String entity;
    public CollectableEntity(String entity) {
        this.entity = entity;
    }
    public void pickup(Player player) {
        // TODO remove collectable from entity list and add item to player inventory (could maybe make an inventory class)
    }
    public ItemResponse getItemResponse(){
        ItemResponse item_response = null;
        return item_response;
    }
    public CollectableEntity(Position position){
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public String getEntity() {
        return entity;
    }
}
