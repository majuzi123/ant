import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayRoomJFrame extends JFrame{
    public static void main(String[] args) {
        new PlayRoomJFrame();
    }
    public PlayRoomJFrame(){

        JFrame jFrame = new JFrame("Ant Creeping");
        jFrame.setSize(800, 400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());

        AtomicInteger mask= new AtomicInteger();
        JLabel input=new JLabel("Input:");
        input.setSize(20,55);
        Font font = new Font("Comic Sans MS",Font.PLAIN,20);
        input.setFont(font);
        JTextField maskText=new JTextField(18);
        maskText.setPreferredSize(new Dimension(20,28));

        JButton button1 = new JButton("Start");
        button1.setSize(100, 55);
        button1.setFont(font);
        button1.setBackground((new Color(247, 238, 214, 255)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground((new Color(160, 148, 231, 255)));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(input);
        buttonPanel.add(maskText);
        buttonPanel.add(button1);

        jFrame.add(buttonPanel,BorderLayout.NORTH);
        jFrame.setVisible(true);

        maskText.addActionListener(event ->{
                    int temp=Integer.parseInt(maskText.getText());
                    mask.set(temp);
                    maskText.setText(Integer.toString(mask.get()));
                    CreepingGame cg = new CreepingGame(mask.get());
                    CreepPanel creepPanel = new CreepPanel(cg);
                    cg.init();
                    jFrame.add(creepPanel, BorderLayout.CENTER);

                    button1.addActionListener(event2 -> {
                        Thread t = new Thread(creepPanel);
                        t.start();
                    });
                }
        );

    }

}

class CreepPanel extends JPanel implements Runnable {

    private CreepingGame cg;
    private int y = 195;
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
        g.fillRect(250,225,320,5);
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
        Font font = new Font("Comic Sans MS",Font.PLAIN,20);
        textTimeLabel.setFont(font);
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