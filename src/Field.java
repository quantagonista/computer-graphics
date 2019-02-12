import java.awt.*;

public class Field {
    public int[][] field;
    private int width;
    private int height;
    private int cellSize;

    public Field(int width, int height, int cellSize) {
        this.field = new int[width][height];
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        setCellValue(width/2, height/2, 1);
    }

    public void setCellValue(int x, int y, int value) {
        this.field[x % width][y % height] = value;
    }

    public void applyState(int[][] ints) {
        field = ints;
    }

    public void drawField(Graphics g) {
        for (int y = 0; y < this.width; y++) {
            for (int x = 0; x < this.height; x++) {
                int colorNumber = this.field[y][x];
                Color color = colorWithNumber(colorNumber);
                g.setColor(color);
                g.fillRect(
                    y * cellSize, x * cellSize, cellSize, cellSize
                );
            }
        }
    }

    private Color colorWithNumber(int number) {
        Color[] colors = new Color[]{
            Color.BLACK,
            Color.WHITE,
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.GRAY,
            Color.YELLOW,
            Color.ORANGE,
            Color.PINK,
            Color.CYAN,
            Color.MAGENTA,
            new Color(52, 99, 255),
            new Color(85, 159, 255),
            new Color(36, 130, 100),
            new Color(130, 0, 255),
            new Color(196, 255, 101),
            new Color(255, 116, 153),
        };
        return colors[number % 16];
    }
}
