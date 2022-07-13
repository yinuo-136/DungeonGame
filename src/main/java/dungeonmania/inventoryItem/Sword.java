package dungeonmania.inventoryItem;

import dungeonmania.response.models.ItemResponse;

public class Sword implements InvItem {

    private String id;
    private String type = "sword";

    private int attackBonus;
    private int durability;

    public Sword(String id, int attackBonus, int durability) {
        this.id = id;
        this.attackBonus = attackBonus;
        this.durability = durability;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
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
