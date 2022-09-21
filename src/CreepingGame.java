import java.util.ArrayList;
import java.util.List;

public class CreepingGame {
    private int currentTime;
    private int directionMark;
    private Stick stick;
    private List<Ant> ants = new ArrayList<>();
    private int antCount=5;
    private int[] initialPosition = {30, 80, 110, 160, 250};

    public void init(){
        for (int i = 0; i < antCount; i++) {
            ants.add(new Ant(5, (directionMark >> i) & 1, initialPosition[i]));
        }
        stick = new Stick(300);
        currentTime = 0;
    }
    public CreepingGame(int Mark){
        this.directionMark=Mark;
    }
    public int playGame(){
        init();
        while(!isFinished()){
            drivingGame();
        }
        System.out.println(currentTime);
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
        for(int i=0; i < antCount-1; i++){
            Ant p1 = ants.get(i);
            Ant p2 = ants.get(i + 1);
            if(p1.judgeCollision(p2)){
                p1.changeDir();
                p2.changeDir();
            }
        }
        updateCurrentTime();
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public List<Ant> getAnts() {
        return ants;
    }
    private void updateCurrentTime(){
        currentTime+=1;
    }
}
