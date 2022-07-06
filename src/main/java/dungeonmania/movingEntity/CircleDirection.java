package dungeonmania.movingEntity;

public class CircleDirection implements SpiderMovingState{
    private Spider spider;

    public CircleDirection(Spider spider) {
        this.spider = spider;
    }


    @Override
    public void onClockwise(Spider spider) {
        spider.setCurrentState(new CircleDirection(spider));
    }

    @Override
    public void onCounterClockwise(Spider spider) {
        spider.setCurrentState(new ReverseDirection(spider));
    }


    @Override
    public void move() {
        // TODO Auto-generated method stub
        
    }
    
    
}
