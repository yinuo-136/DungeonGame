package dungeonmania;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class DungeonManiaController {
    private String dungeonId;
    private String dungeonName;
    private int DungeonCounter = 0;
    private HashMap<String, DungeonInfo> infoMap = new HashMap<>();

    public String getSkin() {
        return "default";
    }

    public String getLocalisation() {
        return "en_US";
    }

    /**
     * /dungeons
     */
    public static List<String> dungeons() {
        return FileLoader.listFileNamesInResourceDirectory("dungeons");
    }

    /**
     * /configs
     */
    public static List<String> configs() {
        return FileLoader.listFileNamesInResourceDirectory("configs");
    }

    /**
     * /game/new
     */
    public DungeonResponse newGame(String dungeonName, String configName) throws IllegalArgumentException{
        //Create a new dungeon with unique id.
        this.dungeonName = dungeonName;
        DungeonCounter = DungeonCounter + 1;
        String dungeonId = Integer.toString(DungeonCounter);
        this.dungeonId = dungeonId;
        DungeonInfo info = new DungeonInfo();
        infoMap.put(dungeonId, info);

        String jsonContent = null;
        //read and set config file
        try {
            jsonContent = FileLoader.loadResourceFile("/configs/" + configName + ".json");
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        //convert json into JSONObject
        JSONObject configContent = new JSONObject(jsonContent);

        //set config
        info.setConfigs(configContent);

        //read json from dungeonName

        try {
            jsonContent = FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json");
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        //convert json into JSONObject
        JSONObject dungeonContent = new JSONObject(jsonContent);

        //store entities from JSONOject into dungeonInfo
        JSONArray arrEntities = dungeonContent.getJSONArray("entities");
        info.storeEntitiesInMap(arrEntities);

        //get the list of entity response
        List<EntityResponse> entityResponses = info.getListEntityResponse();

        //TODO: read and store the goals
        JSONObject jsonGoals = dungeonContent.getJSONObject("goal-condition");

        
        //init spawner status
        info.initSpawnConfig();
        DungeonResponse response = new DungeonResponse(dungeonId, dungeonName, entityResponses, new ArrayList<ItemResponse>(), new ArrayList<BattleResponse>(), new ArrayList<String>(), ":exit");

        return response;
    }
    
    /**
     * /game/dungeonResponseModel
     */
    public DungeonResponse getDungeonResponseModel() {
        DungeonInfo info = infoMap.get(this.dungeonId);
        List<EntityResponse> entityResponses = info.getListEntityResponse();
        List<ItemResponse> itemResponses = info.getListItemResponse();
        return new DungeonResponse(dungeonId, dungeonName, entityResponses, itemResponses, new ArrayList<BattleResponse>(), new ArrayList<String>(), ":exit");
    }

    /**
     * /game/tick/item
     */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {
        //check exceptions
        if (itemUsedId != "bomb" && itemUsedId != "invincibility_potion" && itemUsedId != "invisibility_potion"){
            throw new IllegalArgumentException();
        }

        DungeonInfo info = infoMap.get(this.dungeonId);
        if (info.isItemInList(itemUsedId) == false){
            throw new InvalidActionException("not in the player's inventory");
        }

        return this.getDungeonResponseModel();
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        DungeonInfo info = infoMap.get(this.dungeonId);
        //trigger player movement
        info.movePLayer(movementDirection);
        info.moveAllMovingEntity();
        info.Spawn();
        return this.getDungeonResponseModel();
    }

    /**
     * /game/build
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        DungeonInfo info = infoMap.get(this.dungeonId);
        HashMap<String, Entity> entityMap = info.getEntityMap();
        Entity e = entityMap.get(entityId);
        if (e == null) {
            throw new IllegalArgumentException("null");
        }
        if (e.getType() != "zombie_toast_spawner" && e.getType() != "mercenary") {
            throw new IllegalArgumentException();
        }

        //case zombie spawner
        if (e.getType() == "zombie_toast_spawner") {
            //check player position
            if (isNearSpawner(e.getPos()) == false) {
                throw new InvalidActionException(" player is not cardinally adjacent to the spawner");
            }
            //check has weapon
            if (hasWeapon() == false) {
                throw new InvalidActionException("Player dose not have a weapon");
            }

        }
        info.getEntityMap().remove(entityId);
        System.out.println("1");
        return this.getDungeonResponseModel();
    }

    public boolean isNearSpawner(Position Spawner){
        DungeonInfo info = infoMap.get(this.dungeonId);
        Position p = info.getPlayer().getPos();
        Position up = Spawner.translateBy(Direction.UP);
        Position down = Spawner.translateBy(Direction.DOWN);
        Position left = Spawner.translateBy(Direction.LEFT);
        Position right = Spawner.translateBy(Direction.RIGHT);
        if (up.equals(p) || down.equals(p) 
        || left.equals(p) || right.equals(p)){
            return true;
        }

        return false;
    }

    public boolean hasWeapon(){
        DungeonInfo info = infoMap.get(this.dungeonId);
        List<InvItem> itemList = info.getItemList();
        for (InvItem i : itemList){
            if (i.getItemResponse().getType() == "sword") {
                return true;
            }
        }
        return false;
    }
}
