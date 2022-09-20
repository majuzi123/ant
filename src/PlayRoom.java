import static java.lang.StrictMath.pow;

public class PlayRoom {
    private static int maxTime=0;
    private  static int minTime=Integer.MAX_VALUE;
    public static void main(String[] args) {
        int antCount = 5;
        for (int i = 0; i < pow(2, antCount); i++) {
            int currentTime;
            CreepingGame cg = new CreepingGame(i);
            currentTime = cg.playGame();
            updateRecord(currentTime);
            System.out.println("第" + (i+1) + "种情况time: "+cg.getCurrentTime());
        }
        //output!
        System.out.println("max time is: " + maxTime);
        System.out.println("min time is: " + minTime);
    }
    public static void updateRecord(int currentTime){
        if(currentTime>minTime && currentTime<=maxTime) return;
        else{
            if(currentTime<minTime) minTime=currentTime;
            else maxTime=currentTime;
        }
    }
}
