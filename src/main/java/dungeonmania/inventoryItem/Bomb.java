package dungeonmania.inventoryItem;

public class Bomb implements InvItem {

    private String id;
    private String type = "bomb";

    private static int radius;

    public Bomb(String id) {
        this.id = id;
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

    public static void setRadius(int radius) {
        Bomb.radius = radius;
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
