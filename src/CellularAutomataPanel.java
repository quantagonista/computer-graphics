import javax.swing.*;
import java.awt.*;

public class CellularAutomataPanel extends JPanel {
    public Field field;

    public CellularAutomataPanel() {
        super();
        this.field = new Field(20, 20, 2);
        Drawer crystal = new Crystal(465, 10, 10);
        this.field.setDrawer(crystal);
        this.field.drawer.setOnField(this.field);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        field.drawField(g);
    }

    public void nextStep() {
        field.drawer.nextStep(field.field);
        this.repaint();
    }
}
