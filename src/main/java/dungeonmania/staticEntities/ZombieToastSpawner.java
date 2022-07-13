package dungeonmania.staticEntities;

import dungeonmania.Entity;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToastSpawner extends staticEntity {
    private String id;
    private Position pos;
    private int Spawntime;
    private int TimeToSpawn;
    private String type = "zombie_toast_spawner";

    public ZombieToastSpawner(Position p, String id) {
        this.id = id;
        this.pos = p; 
        
    }

    public void setConfig(){
        this.Spawntime = dungeonInfo.getSpecificConfig("zombie_spawn_rate"); 
        TimeToSpawn = Spawntime;
    }

    public Position getPos() {
        return pos;
    }

    public void setTimeToSpawn(int remainingTimeToSpawn) {
        TimeToSpawn = remainingTimeToSpawn;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public EntityResponse getEntityResponse() {
        EntityResponse response = new EntityResponse(id, type, pos, true);
        return response;
    }

    @Override
    public Position playerMoveIn(Position p, Direction d) {
        return p;
    }

    @Override
    public Position boulderMoveIn(Position p) {
        return p;
    }

    //TODO: Spawn a zombie toast.
    
}
