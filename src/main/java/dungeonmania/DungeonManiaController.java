package dungeonmania;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;

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
            e.printStackTrace();
        }

        //convert json into JSONObject
        JSONObject configContent = new JSONObject(jsonContent);

        //set config
        info.setConfigs(configContent);

        //read json from dungeonName

        try {
            jsonContent = FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json");
        } catch (IOException e) {
            e.printStackTrace();
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
        return null;
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        DungeonInfo info = infoMap.get(this.dungeonId);
        //trigger player movement
        info.movePLayer(movementDirection);
        info.moveAllMovingEntity();
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
        return null;
    }
}
