public class Stick {
    private static int length;

    public Stick(int length) {
        this.length=length;
    }
    public static int getLength() {
        return length;
    }

    public static void setLength(int length) {
        Stick.length = length;
    }
}
