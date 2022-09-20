import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayRoomJFrame{

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Ant Creeping");

        jFrame.setSize(800, 500);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.setLayout(new BorderLayout());

        JButton button1 = new JButton("Start");
        button1.setSize(50, 30);
        JButton button2 = new JButton("Reset");
        button2.setSize(50, 30);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground((new Color(0, 127, 127, 255)));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        jFrame.add(buttonPanel, BorderLayout.NORTH);

        CreepingGame cg = new CreepingGame(4);


        CreepPanel creepPanel = new CreepPanel(cg);
        jFrame.add(creepPanel, BorderLayout.CENTER);

        jFrame.setVisible(true);

        button1.addActionListener(event -> {
            Thread t = new Thread(creepPanel);
            t.start();
        });
    }

}

class CreepPanel extends JPanel implements Runnable {

    private CreepingGame cg;
    private int y = 225;
    private List<Color> antColor = new ArrayList<>();
    private int[] r = {240, 0, 250, 0, 0};
    private int[] g = {0, 240, 230, 240, 0};
    private int[] b = {0, 240, 0, 0, 240};

    private void init(){
        for (int i = 0; i < 5; i++) {
            antColor.add(new Color(r[i],g[i],b[i]));
        }
    }
    public CreepPanel(CreepingGame cg) {
        super();
        this.cg = cg;
        this.init();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(0, 0, 0));
        g.fillRect(250,255,320,5);
        List<Ant> ants = cg.getAnts();
        for (int i = 0; i < ants.size(); i++) {
            if (ants.get(i).getPosition() > 0 && ants.get(i).getPosition() <300) {
                g.setColor(antColor.get(i));
                g.fillOval(ants.get(i).getPosition() + 250, y, 20, 20);
            }
        }
    }

    @Override
    public void run() {
        JLabel textTimeLabel = new JLabel();
        this.add(textTimeLabel);
        while (!cg.isFinished()) {
            cg.drivingGame();
            try {
                Thread.sleep(300);
            } catch (Exception e) {
            }
            repaint();
            textTimeLabel.setText("Current time: " + cg.getCurrentTime());
        }
    }
}