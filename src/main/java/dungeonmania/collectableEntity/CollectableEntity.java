package dungeonmania.collectableEntity;
import java.util.List;

import dungeonmania.DungeonInfo;
import dungeonmania.Entity;
import dungeonmania.inventoryItem.Arrow;
import dungeonmania.inventoryItem.Bomb;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.inventoryItem.ItemKey;
import dungeonmania.inventoryItem.Sword;
import dungeonmania.inventoryItem.Treasure;
import dungeonmania.inventoryItem.Wood;
import dungeonmania.inventoryItem.Potion.InvincibilityPotion;
import dungeonmania.inventoryItem.Potion.InvisibilityPotion;
import dungeonmania.player.Player;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class CollectableEntity extends Entity{

    protected String id;
    protected String type;
    protected Position position;


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

    public void pickup() {
        //add this to item list
        List<InvItem> items = dungeonInfo.getItemList();
        InvItem newItem = null;
        //create a new item
        switch(this.type){
            case "invincibility_potion":
                newItem = new InvincibilityPotion(dungeonInfo.getConfigMap().get("invincibility_potion_duration"), this.id);
                newItem.setDungeonInfo(dungeonInfo);
                break;
            case "invisibility_potion":
                newItem = new InvisibilityPotion(dungeonInfo.getConfigMap().get("invisibility_potion_duration"), id);
                newItem.setDungeonInfo(dungeonInfo);
                break;
            case "arrow":
                newItem = new Arrow(id);
                break;
            case "bomb":
                newItem = new Bomb(id, dungeonInfo.getConfigMap().get("bomb_radius"));
                break;
            case "sword":
                newItem = new Sword(id, dungeonInfo.getConfigMap().get("sword_attack"), dungeonInfo.getConfigMap().get("sword_durability"));
                break;
            case "treasure":
                newItem = new Treasure(id);
                break;
            case "wood":
                newItem = new Wood(id);
                break;
        }
        items.add(newItem);

        //then delete the entity
        dungeonInfo.getEntityMap().remove(this.id);
    }
    
    public EntityResponse getEntityResponse(){
        EntityResponse response = new EntityResponse(id, type, position, false);
        return response;
    }

    @Override
    public void setConfig() {
    }
}
