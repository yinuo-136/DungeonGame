package dungeonmania.buildableEntity;

import dungeonmania.DungeonInfo;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.response.models.ItemResponse;

public class MidnightArmour implements InvItem, Buildable{
    private String id;
    private static String type = "midnight_armour";

    private int defenseBonus;
    private int AttackBonus;
    private DungeonInfo dungeonInfo;

    public MidnightArmour() {
    }

    public MidnightArmour(String id, int defenseBonus, int AttackBonus) {
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDungeonInfo(DungeonInfo dungeonInfo) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getAttackBonus() {
        return 0;
    }

    public int getDefenseBonus() {
        return 0;
    }
    
}
