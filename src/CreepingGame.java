import java.util.ArrayList;
import java.util.List;

public class CreepingGame {
    private int currentTime;
    private int directionMark;
    private Stick stick;
    private List<Ant> ants = new ArrayList<>();
    private int antCount=5;
    private int[] antPosition = {30, 80, 110, 160, 250};

    private void init(){
        for (int i = 0; i < antCount; i++) {
            ants.add(new Ant(5, (directionMark >> i) & 1, antPosition[i]));
        }
        stick = new Stick(300);
        currentTime = 0;
    }
    public CreepingGame(int Mark){
        this.directionMark=Mark;
        this.init();
    }
    public int playGame(){
        while(!isFinished()){
            drivingGame();
        }
        return currentTime;
    }

    public boolean isFinished(){
        for (int i = 0; i < antCount; i++) {
            if (ants.get(i).isOnStick(stick))
                return false;
        }
        return true;
    }

    public void drivingGame(){
        for(int i = 0; i < antCount; i++){
            ants.get(i).creeping();
        }
        judgeCollision();
        currentTime+=1;
    }

    public boolean judgeCollision(){
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < antCount - 1; i++) {
                Ant p1 = ants.get(i);
                Ant p2 = ants.get(i + 1);
                if (p1.getDirection() == 1 && p2.getDirection() == -1 && p2.getPosition() == p1.getPosition()) {
                    p1.changeDir();
                    p2.changeDir();
                    flag = true;
                }
            }
        }
        return flag;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public List<Ant> getAnts() {
        return ants;
    }
}
