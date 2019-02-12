import java.util.HashMap;

public class Crystal extends Drawer {
    private HashMap<String, Integer> rules;

    public Crystal(int seed) {
        this.rules = getRules(seed);
    }

    private HashMap<String, Integer> getRules(int seed) {
        return Rules.getCrystal(seed);
    }

    @Override
    public int[][] nextStep(Field field) {
        return calculateState(field.field);
    }

    private int[][] calculateState(int[][] field) {
        int height = field.length;
        int width = field[0].length;
        int[][] nextState = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int currentState = field[y][x];
                int neighbours = getNeighbours(y, x, field);
                int nextCellState = findRule(currentState, neighbours);
                nextState[y][x] = nextCellState;
            }
        }
        return nextState;
    }

    private int getNeighbours(int y, int x, int[][] field) {
        int height = field.length;
        int width = field[0].length;

        int topY = y - 1;
        int bottomY = y + 1;
        int leftX = x - 1;
        int rightX = x + 1;

        int top = (topY >= 0) ? field[topY][x] : 0;
        int bottom = (bottomY < height) ? field[bottomY][x] : 0;
        int left = (leftX >= 0) ? field[y][leftX] : 0;
        int right = (rightX < width) ? field[y][rightX] : 0;

        return top + bottom + left + right;
    }

    private int findRule(int current, int count) {
        String key = String.valueOf(current) + String.valueOf(count);
        return this.rules.get(key);
    }
}
