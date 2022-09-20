public class Ant {
    private static int speed=5;
    private int direction;
    private int position;

    public Ant(int speed, int direction, int position) {
        this.speed = speed;
        this.direction = direction == 1 ? 1 : -1;
        this.position = position;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isOnStick(Stick stick){
        if(position<=0 || position>=stick.getLength()) return false;
        else return true;
    }
    public void changeDir() {
        direction = -direction;
    }
    public void creeping() {
        position = position + direction * speed;
    }
}
