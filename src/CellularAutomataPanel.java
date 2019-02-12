import javax.swing.*;
import java.awt.*;

public class CellularAutomataPanel extends JPanel {
    private Crystal crystal;
    private Field field;

    public CellularAutomataPanel() {
        super();
        this.field = new Field(50, 50, 4);
        this.crystal = new Crystal(465);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        field.drawField(g);
    }

    public void nextStep() {
        field.applyState(crystal.nextStep(field));
        this.repaint();
    }
}
