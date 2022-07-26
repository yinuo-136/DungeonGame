package dungeonmania.buildableEntity;

import dungeonmania.DungeonInfo;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.inventoryItem.InvItem;

import java.util.List;

public class BuildableFactory {

    public void build(String buildable, DungeonInfo info) throws IllegalArgumentException, InvalidActionException {
        Buildable buildableItem;

        switch (buildable) {
            case "bow":
                String bowId = idGenerator("bow", info);

                buildableItem = new Bow(bowId, info.getSpecificConfig("bow_durability"));
                break;

            case "shield":
                String shieldId = idGenerator("shield", info);

                buildableItem = new Shield(shieldId, info.getSpecificConfig("shield_defence"), info.getSpecificConfig("shield_durability"));
                break;
        
            default:
                throw new IllegalArgumentException("Invalid Build Request!");
        }

        buildableItem.setDungeonInfo(info);
        buildableItem.craft();
        info.addInvItem((InvItem) buildableItem);
    }

    private String idGenerator(String string, DungeonInfo info){
        int idCounter = 0;
        List<String> idList = info.getInvItemIdsListByType(string);
        String id = string + idCounter;

        while(idList.contains(id)) {
            idCounter++;
            id = string + idCounter;
        }

        return id;
    }

}
