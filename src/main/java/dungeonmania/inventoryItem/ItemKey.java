package dungeonmania.inventoryItem;

import dungeonmania.response.models.ItemResponse;

public class ItemKey implements InvItem {

    private String id;
    private int key;
    private String type = "key";

    public ItemKey(String id, int key) {
        this.id = id;
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ItemResponse getItemResponse() {
        return new ItemResponse(id, type);
        
    }
    
}
