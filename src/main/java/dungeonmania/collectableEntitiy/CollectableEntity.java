package dungeonmania.collectableEntitiy;
import dungeonmania.util.Position;

public abstract class CollectableEntity {
    private Position position;
    
    public abstract void pickup();
    public abstract void use();
    public CollectableEntity(Position position){
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
}
