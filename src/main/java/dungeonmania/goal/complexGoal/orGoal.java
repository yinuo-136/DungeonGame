package dungeonmania.goal.complexGoal;

import dungeonmania.goal.Goal;

public class orGoal implements Goal{
    Goal firstGoal;
    Goal secGoal;

    public orGoal(Goal firstGoal, Goal secGoal) {
        this.firstGoal = firstGoal;
        this.secGoal = secGoal;
    }

    @Override
    public String evalGoal() {
        
        return firstGoal.evalGoal() + secGoal.evalGoal();
    }
    
}
