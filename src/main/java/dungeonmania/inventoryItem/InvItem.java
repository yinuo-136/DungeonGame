package dungeonmania.inventoryItem;

import dungeonmania.response.models.ItemResponse;

public interface InvItem {
    public void use();
    public ItemResponse getItemResponse();
}
