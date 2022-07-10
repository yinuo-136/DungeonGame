package dungeonmania;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class DungeonInfo {
    private HashMap<String, Entity> entityMap = new HashMap<>(); 
    private int entityCounter = 0;

    //store all entities into map
    public void storeEntitiesInMap(JSONArray arr){
        for (int i = 0; i < arr.length(); i++){
            JSONObject json = (JSONObject) arr.get(i);
            DungeonInfo.createEntity((String) json.get("type"));
        }
    }

    //helper methods
    public static Entity createEntity(String type){
        Entity newEntity;
        switch(type){
            case "player":
                
            default:
                newEntity = null;
        }
        return newEntity;
    }
}
