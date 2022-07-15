package dungeonmania.buildableEntity;

import dungeonmania.DungeonInfo;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.inventoryItem.InvItem;

import java.util.List;

public class BuildableFactory {

    public void build(String buildable, DungeonInfo info) throws IllegalArgumentException, InvalidActionException {
        Buildable buildableItem;
        int idCounter = 0;

        switch (buildable) {
            case "bow":
                List<String> bowIdList = info.getInvItemIdsListByType("bow");
                String bowId = "bow" + idCounter;

                while(bowIdList.contains(bowId)) {
                    idCounter++;
                    bowId = "bow" + idCounter;
                }

                buildableItem = new Bow(bowId, info.getSpecificConfig("bow_durability"));
                break;

            case "shield":
            List<String> shieldIdList = info.getInvItemIdsListByType("shield");
            String shieldId = "shield" + idCounter;

            while(shieldIdList.contains(shieldId)) {
                idCounter++;
                shieldId = "shield" + idCounter;
            }

                buildableItem = new Shield(shieldId, info.getSpecificConfig("shield_defence"), info.getSpecificConfig("shield_durability"));
                break;
        
            default:
                throw new IllegalArgumentException("Invalid Build Request!");
        }

        buildableItem.setDungeonInfo(info);
        buildableItem.craft();
        info.addInvItem((InvItem) buildableItem);
    }

}
