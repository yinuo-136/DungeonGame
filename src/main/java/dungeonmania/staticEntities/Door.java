package dungeonmania.staticEntities;

import java.util.List;

import dungeonmania.inventoryItem.InvItem;
import dungeonmania.inventoryItem.ItemKey;
import dungeonmania.inventoryItem.Sunstone;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Door extends staticEntity{
    private String id;
    private Position pos;
    private boolean isOpen;
    private int key;
    private String type = "door";

    public Door(Position position, int key, String id) {
        this.id = id;
        this.pos = position;
        this.isOpen = false;
        this.key = key;
    }

    public Position getPos() {
        return pos;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void Open() {
        this.isOpen = true;
    }

    public int getKey() {
        return key;
    }

    public String getId() {
        return id;
    }
 
    public String getType() {
        return type;
    }

    @Override
    public EntityResponse getEntityResponse() {
        EntityResponse response = new EntityResponse(id, type, pos, false);
        return response;
    }

    @Override
    public void setConfig() {
        
    }

    @Override
    public Position playerMoveIn(Position p, Direction d) {
        //check if player can open the door
        List<InvItem> items = dungeonInfo.getItemList();
        for (InvItem i : items){
            if (i instanceof ItemKey) {
                ItemKey k = (ItemKey) i;
                if (k.getKey() == this.key){
                    this.Open(); 
                    items.remove(i);
                    break;   
                }
            } else if (i instanceof Sunstone) {
                this.Open();
                break;
            }
        }
        
        if (isOpen()){
            return this.pos;
        } else {
            return p;
        }
    }

    @Override
    public Position boulderMoveIn(Position p) {
        if (isOpen()){
            return this.pos;
        } else {
            return p;
        }
    }
    
    
}
