import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Wolfram extends Drawer {
    protected int x;
    protected int y;
    private HashMap<String, Integer> rules;

    public Wolfram(int y, int x, int seed) {
        this.x = x;
        this.y = y;
        rules = getRules(seed);
    }

    public void setOnField(Field field) {
        field.setCellValue(this.y, this.x, 1);
    }

    protected HashMap<String, Integer> getRules(int seed) {
        ArrayList<Integer> binarifiedSeed = Rules.binarifySeed(seed, 8);
        Collections.reverse(binarifiedSeed);
        HashMap<String, Integer> rules = new HashMap<>();
        for (int i = 0; i < 8; i++) rules.put(String.valueOf(i), binarifiedSeed.get(i));
        return rules;
    }

    public void nextStep(int[][] field) {
        int height = field.length;
        int width = field[0].length;
        for (int y = 1; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int neighbours = getNeighbours(y, x, field);
                int nextCellState = this.rules.get(String.valueOf(neighbours));
                field[y][x] = nextCellState;
            }
        }
    }

    protected int getNeighbours(int y, int x, int[][] field) {
        int width = field[0].length;

        int topY = y - 1;
        int leftX = x - 1;
        int rightX = x + 1;

        int top = (topY >= 0) ? field[topY][x] : 0;
        int topLeft = (leftX >= 0 && topY >= 0) ? field[topY][leftX] : 0;
        int topRight = (rightX < width && topY >= 0) ? field[topY][rightX] : 0;
        int[] neighbours = {topLeft, top, topRight};

        return decimalizeArray(neighbours);
    }

    private int decimalizeArray(int[] list) {
        int last = list.length - 1;
        int e = 0;
        int result = 0;
        for (int i = last; i == 0; i--) {
            result += list[i] * Math.pow(2, e);
            e++;
        }
        return result;
    }
}
