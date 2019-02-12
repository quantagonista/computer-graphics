import javax.swing.*;
import java.awt.event.ActionEvent;

public class LabyrinthAutomata {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Field");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CellularAutomataPanel panel = new CellularAutomataPanel();
        frame.setContentPane(panel);
        frame.setVisible(true);
        startTimer(panel);
    }

    private static void startTimer(CellularAutomataPanel panel) {
        Timer timer = new Timer(200, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panel.nextStep();
            }
        });
        timer.start();
    }
}
