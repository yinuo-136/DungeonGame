package dungeonmania;

import java.io.ObjectInputFilter.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.collectableEntity.CollectableEntity;
import dungeonmania.collectableEntity.Key;
import dungeonmania.inventoryItem.Bomb;
import dungeonmania.inventoryItem.InvItem;
import dungeonmania.inventoryItem.Sword;
import dungeonmania.inventoryItem.Potion.InvincibilityPotion;
import dungeonmania.inventoryItem.Potion.InvisibilityPotion;
import dungeonmania.movingEntity.Mercenary;
import dungeonmania.movingEntity.Moving;
import dungeonmania.movingEntity.Spider;
import dungeonmania.movingEntity.ZombieToast;
import dungeonmania.player.Player;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.staticEntities.Boulder;
import dungeonmania.staticEntities.Door;
import dungeonmania.staticEntities.Exit;
import dungeonmania.staticEntities.FloorSwitch;
import dungeonmania.staticEntities.PlacedBomb;
import dungeonmania.staticEntities.Portal;
import dungeonmania.staticEntities.Wall;
import dungeonmania.staticEntities.ZombieToastSpawner;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class DungeonInfo {
    private HashMap<String, Entity> entityMap = new HashMap<>(); // the entity map
    private HashMap<String, Integer> configMap = new HashMap<>(); // the config file map
    private List<InvItem> itemList = new ArrayList<>(); // the item list
    private int entityCounter = 0;

    //store all entities into map
    public void storeEntitiesInMap(JSONArray arr){
        for (int i = 0; i < arr.length(); i++){
            JSONObject json = (JSONObject) arr.get(i);
            //get an id for that entity
            this.entityCounter = this.entityCounter + 1;
            String id = Integer.toString(this.entityCounter);
            entityMap.put(id, DungeonInfo.createEntity(json, id, this));
        }
    }

    //helper methods

    //create an entity class in terms of the type.
    public static Entity createEntity(JSONObject json, String id, DungeonInfo info){
        Entity newEntity;
        int x = (int) json.get("x");
        int y = (int) json.get("y");
        //check the type of the entity
        switch((String) json.get("type")){
            case "player":
               newEntity = new Player(new Position(x, y), id);
               newEntity.setDungeonInfo(info);
               newEntity.setConfig();
               break;
               
            case "spider":
               newEntity = new Spider(new Position(x, y), id);
               newEntity.setDungeonInfo(info);
               newEntity.setConfig();
               break;

            case "mercenary":
                newEntity = new Mercenary(new Position(x, y), id);
                newEntity.setDungeonInfo(info);
                newEntity.setConfig();
                break;

            case "boulder":
                newEntity = new Boulder(new Position(x, y), id);
                break;

            case "door":
                newEntity = new Door(new Position(x, y), (int) json.get("key"), id);
                break;
             
            case "exit":
                newEntity = new Exit(new Position(x, y), id);
                break;
            
            case "switch":
                newEntity = new FloorSwitch(new Position(x, y), id);
                break;

            case "portal":
                newEntity = new Portal(new Position(x, y), (String) json.get("colour"), id);
                break;

            case "wall":
                newEntity = new Wall(new Position(x, y), id);
                break;
            
            case "zombie_toast_spawner":
                newEntity = new ZombieToastSpawner(new Position(x, y), id);
                newEntity.setDungeonInfo(info);
                newEntity.setConfig();
                break;

            case "zombie_toast":
                newEntity = new ZombieToast(new Position(x, y), id);
                newEntity.setDungeonInfo(info);
                newEntity.setConfig();
                break;
            
            case "key":
                newEntity = new Key(id, (String) json.get("type"), new Position(x, y), (int) json.get("key"));
                break;
            // if default, it will be collectableEntities
            default:
                newEntity = new CollectableEntity(id, (String) json.get("type"), new Position(x, y));
        }
        newEntity.setDungeonInfo(info);
        return newEntity;
    }

    // get a list of entityResponse for controller to use
    public List<EntityResponse> getListEntityResponse() {
        List<EntityResponse> list = new ArrayList<>();
        for (Entity e: entityMap.values()) {
            list.add(e.getEntityResponse());
        }

        return list;
    } 

    // set config
    public void setConfigs(JSONObject config){
        for (String keyString : config.keySet()){
            int configValue = config.getInt(keyString);
            configMap.put(keyString, configValue);
        }
    }

    public int getSpecificConfig(String name){
        return configMap.get(name);
    }

    public List<String> getEntitiesStringByPosition(Position pos) {
        List<String> entities = new ArrayList<String>();
        for (Entity entity : entityMap.values()) {
            if (entity.getPos().equals(pos)) {
                entities.add(entity.getType());
            }
        }
        return entities;
    }

    public List<Entity> getEntitiesByPosition(Position pos) {
        List<Entity> entities = new ArrayList<>();
        for (Entity entity : entityMap.values()) {
            if (entity.getPos().equals(pos)) {
                entities.add(entity);
            }
        }
        return entities;
    }

    //trigger player move
    public void movePLayer(Direction d){
        //find player
        Player p = this.getPlayer();
        p.move(d);
    }
    
    //find the player
    public Player getPlayer(){
        for (Entity e : entityMap.values()){
            if (e.getType() == "player"){
                Player p = (Player) e;
                return p;
            }
        }
        return null;
    }

    public HashMap<String, Entity> getEntityMap() {
        return entityMap;
    }

    public HashMap<String, Integer> getConfigMap() {
        return configMap;
    }

    public List<InvItem> getItemList() {
        return itemList;
    }

    // get a list of itemResponse for controller to use
    public List<ItemResponse> getListItemResponse() {
        List<ItemResponse> list = new ArrayList<>();
        for (InvItem i: itemList) {
            list.add(i.getItemResponse());
        }

        return list;
    }

    public void moveAllMovingEntity(){
        for (Moving e : getAllMovingEntity()){
            e.move();
        }
    }

    public List<Moving> getAllMovingEntity(){
        List<Moving> list = new ArrayList<>();
        for (Entity e : entityMap.values()){
            if (e.getType() == "spider" || e.getType() == "mercenary" || e.getType() == "zombie_toast"){
                list.add((Moving) e);
            }
        }
        return list;
    }

    public void initSpawnConfig(){
        //in this stage, only spider needs to be init
        Spider.setClassHealth(getSpecificConfig("spider_health"));
        Spider.setDamage(getSpecificConfig("spider_attack"));
        Spider.setSpawnRate(getSpecificConfig("spider_spawn_rate"));
        Spider.setTimeToSpawn(getSpecificConfig("spider_spawn_rate"));
    }

    public boolean isItemInList(String type){
        List<ItemResponse> list = getListItemResponse();
        for (ItemResponse i : list){
            if(i.getType().equals(type)){
                return true;
            }
        }

        return false;
    }

    public void Spawn(){
        // spawn zombie
        List<ZombieToastSpawner> zl = new ArrayList<>();
        for (Entity e : entityMap.values()){
            if (e.getType() == "zombie_toast_spawner") {
                ZombieToastSpawner z = (ZombieToastSpawner) e;
                zl.add(z);
            }
        }
        for (ZombieToastSpawner z : zl){
            z.spawn();
        }

        //spawn spider
        if (Spider.spawn() == true){
            spiderSpawn();
        }
    }

    public void zombieSpawn(Position p){
        this.entityCounter = this.entityCounter + 1;
        String id = Integer.toString(this.entityCounter);
        ZombieToast z = new ZombieToast(p, id);
        z.setHealth(configMap.get("zombie_health"));
        z.setDamage(configMap.get("zombie_attack"));
        z.setDungeonInfo(this);
        entityMap.put(id, z);
    }

    public void spiderSpawn() {
        this.entityCounter = this.entityCounter + 1;
        String id = Integer.toString(this.entityCounter);
        Position p = Spider.generateSpawnPos(getPlayer().getPos());
        Spider s = new Spider(p, id);
        s.setDungeonInfo(this);
        entityMap.put(id, s);
    }
    
}
