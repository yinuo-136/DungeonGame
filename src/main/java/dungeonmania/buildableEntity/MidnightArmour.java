package dungeonmania.buildableEntity;

import dungeonmania.DungeonInfo;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.response.models.ItemResponse;

public class MidnightArmour implements InvItem, Buildable{
    private String id;
    private static String type = "midnight_armour";

    private int defenseBonus;
    private int attackBonus;
    private DungeonInfo dungeonInfo;

    public MidnightArmour() {
    }

    public MidnightArmour(String id, int defenseBonus, int attackBonus) {
        this.id = id;
        this.defenseBonus = defenseBonus;
        this.attackBonus = attackBonus;
    }

    @Override
    public void craft() throws InvalidActionException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Boolean checkCraftable() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ItemResponse getItemResponse() {
        return new ItemResponse(id, type);
    }

    @Override
    public void setDungeonInfo(DungeonInfo dungeonInfo) {
        this.dungeonInfo = dungeonInfo;
        
    }

    @Override
    public String getId() {
        return id;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }
    
}
