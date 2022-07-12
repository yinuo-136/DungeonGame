package dungeonmania.staticEntities;

import dungeonmania.Entity;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class ZombieToastSpawner extends Entity{
    private String id;
    private Position pos;
    private static int Spawntime;
    private int RemainingTimeToSpawn;
    private String type = "zombie_toast_spawner";

    public ZombieToastSpawner(Position p, String id) {
        this.id = id;
        this.pos = p;  
        RemainingTimeToSpawn = Spawntime;
    }

    public Position getPos() {
        return pos;
    }

    public static int getSpawntime() {
        return Spawntime;
    }

    public static void setSpawntime(int spawntime) {
        Spawntime = spawntime;
    }

    public void setRemainingTimeToSpawn(int remainingTimeToSpawn) {
        RemainingTimeToSpawn = remainingTimeToSpawn;
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

    //TODO: Spawn a zombie toast.
    
}
