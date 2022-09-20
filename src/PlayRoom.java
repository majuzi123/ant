import static java.lang.StrictMath.pow;

public class PlayRoom {
    private static int maxTime=0;
    private  static int minTime=Integer.MAX_VALUE;
    private static int antCount=5;
    public static void main(String[] args) {
        startGame();
        //output!
        System.out.println("max time is: " + maxTime);
        System.out.println("min time is: " + minTime);
    }
    public static void startGame(){
        for (int i = 0; i < pow(2, antCount); i++) {
            int time;
            CreepingGame cg = new CreepingGame(i);
            time = cg.playGame();
            updateRecord(time);
        }
    }
    public static void updateRecord(int currentTime){
        if(currentTime>minTime && currentTime<=maxTime) return;
        else{
            if(currentTime<minTime) minTime=currentTime;
            else maxTime=currentTime;
        }
    }
}
