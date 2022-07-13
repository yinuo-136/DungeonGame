package dungeonmania.inventoryItem;

import dungeonmania.response.models.ItemResponse;

public class Wood implements InvItem {
    private String id;
    private String type = "wood";

    public Wood(String id){
        this.id = id;
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
