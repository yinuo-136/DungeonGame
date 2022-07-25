package dungeonmania;

import java.io.ObjectInputFilter.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import dungeonmania.buildableEntity.Bow;
import dungeonmania.buildableEntity.Buildable;
import dungeonmania.buildableEntity.Shield;
import dungeonmania.collectableEntity.CollectableEntity;
import dungeonmania.collectableEntity.Key;
import dungeonmania.goal.Goal;
import dungeonmania.goal.basicGoal.bouldersGoal;
import dungeonmania.goal.basicGoal.enemiesGoal;
import dungeonmania.goal.basicGoal.exitGoal;
import dungeonmania.goal.basicGoal.treasureGoal;
import dungeonmania.goal.complexGoal.andGoal;
import dungeonmania.goal.complexGoal.orGoal;
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
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.staticEntities.Boulder;
import dungeonmania.staticEntities.Door;
import dungeonmania.staticEntities.Exit;
import dungeonmania.staticEntities.FloorSwitch;
import dungeonmania.staticEntities.PlacedBomb;
import dungeonmania.staticEntities.Portal;
import dungeonmania.staticEntities.SwampTile;
import dungeonmania.staticEntities.Wall;
import dungeonmania.staticEntities.ZombieToastSpawner;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class DungeonInfo {
    private HashMap<String, Entity> entityMap = new HashMap<>(); // the entity map
    private HashMap<String, Integer> configMap = new HashMap<>(); // the config file map
    private List<InvItem> itemList = new ArrayList<>(); // the item list
    private Goal dungeonGoal = null;
    private List<BattleResponse> battleList = new ArrayList<>(); // battle response list
    private List<Tick> tickList = new ArrayList<>(); // tickable entities list
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

            case "swamp_tile":
                newEntity = new SwampTile(new Position(x, y), id, (int) json.get("movement_factor"));
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
        for (Moving m : getAllMovingEntity()){
            m.move();
            for(Entity e : getEntitiesByPosition(m.getPos())){
                if (e instanceof Player) {
                    Player player = (Player) e;
                    Battle battle = new Battle(player, m, this);
                    BattleResponse response = battle.start();
                    if (response != null)
                        addBattleResponse(response);
                    break;
                }
            }
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
    public List<Mercenary> getAllMencenary(){
        List<Mercenary> list = new ArrayList<>();
        for (Entity e : entityMap.values()){
            if (e.getType() == "mercenary"){
                list.add((Mercenary) e);
            }
        }
        return list;
    }
    public List<ZombieToast> getAllZombie(){
        List<ZombieToast> list = new ArrayList<>();
        for (Entity e : entityMap.values()){
            if (e.getType() == "zombie_toast"){
                list.add((ZombieToast) e);
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

    public boolean isItemInList(String id){
        List<ItemResponse> list = getListItemResponse();
        for (ItemResponse i : list){
            if(i.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public InvItem getItemById(String id) {
        for (InvItem i : itemList){
            if(i.getId().equals(id)){
                return i;
            }
        }
        return null;
    }

    public boolean isItemAllowed(String id, List<String> allowedList){
        List<ItemResponse> list = getListItemResponse();
        for (ItemResponse i : list){
            if(i.getId().equals(id) && allowedList.contains(i.getType())){
                return true;
            }
        }
        return false;
    }

    /*
     * Returns the list of item id's by a specified item type
     */
    public List<String> getInvItemIdsListByType(String type){
        List<ItemResponse> list = getListItemResponse();
        return list.stream().filter(obj -> obj.getType().equals(type)).map(ItemResponse::getId).collect(Collectors.toList());
    }
    
    /*
     * Remove a specified item from inventory by it's id
     */
    public void removeInvItemById(String id){
        for (InvItem i: itemList) {
            i.getItemResponse().getId().equals(id);
            itemList.remove(i);
            break;
        }

    }

    /*
     * Returns number of inventory items of a specific type
     */
    public int getNumInvItemType(String type){
        return getInvItemIdsListByType(type).size();
    }

    /*
     * Adds a InvItem to the Item List
     */
    public void addInvItem(InvItem item){
        itemList.add(item);
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

    public void storeGoals(JSONObject jsonGoals) {
        this.dungeonGoal = getGoalsFromJson(jsonGoals);
    }
    
    //recursive method to initalize the goal
    public Goal getGoalsFromJson(JSONObject jsonGoals) {
        //read the json
        String superGoal = jsonGoals.getString("goal");
        //base case
        if (superGoal != "AND" && superGoal != "OR") {
            switch (superGoal){
                case "exit":
                    return new exitGoal(this);
                case "enemies":
                    return new enemiesGoal(this, getSpecificConfig("enemy_goal"));
                case "boulders":
                    return new bouldersGoal(this);
                case "treasure":
                    return new treasureGoal(this, getSpecificConfig("treasure_goal"));
            }
        //get the subgoal
        JSONArray subGoals = jsonGoals.getJSONArray("subgoals");
        //case and
        if (superGoal.equals("AND")) {
            return new andGoal(getGoalsFromJson((JSONObject) subGoals.get(0)), getGoalsFromJson((JSONObject) subGoals.get(1)));
        } else {
            return new orGoal(getGoalsFromJson((JSONObject) subGoals.get(0)), getGoalsFromJson((JSONObject) subGoals.get(1)));
        }
        
        }
        //we will never get to this.
        return null;
    }

    public String getGoalString() {
        return dungeonGoal.evalGoal();
    }
    public void addBattleResponse(BattleResponse response) {
        battleList.add(response);
    }

    public List<BattleResponse> getBattleResponses() {
        return battleList;
    }

    public void addTick(Tick tickableEntity) {
        tickList.add(tickableEntity);
    }

    public List<Tick> getTickList() {
        return tickList;
    }

    public void runTicks() {
        for (Tick tickableEntity : tickList)
            tickableEntity.tick();
    }

    /*
     * Returns list of currently craftable items
     */
    public List<String> getCurrentBuildables() {
        List<String> buildables = new ArrayList<String>();

        Buildable bow = new Bow();
        bow.setDungeonInfo(this);
        Buildable shield = new Shield();
        shield.setDungeonInfo(this);

        if (bow.checkCraftable()) {
            buildables.add("bow");
        }

        if (shield.checkCraftable()) {
            buildables.add("shield");
        }

        return buildables;
    }

    public void setDungeonGoal(Goal dungeonGoal) {
        this.dungeonGoal = dungeonGoal;
    }

    

}
