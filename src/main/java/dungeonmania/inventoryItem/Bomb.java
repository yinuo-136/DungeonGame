package dungeonmania.inventoryItem;

public class Bomb implements InvItem {

    private String id;
    private String type = "bomb";

    private int radius;

    public Bomb(String id, int radius) {
        this.id = id;
        this.radius = radius;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getItemResponse() {
        // TODO Auto-generated method stub
        
    }
    
}
