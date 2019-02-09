import java.util.HashMap;

public class Crystal extends Drawer {
    private int seed;
    private HashMap<String, Integer> rules;
    private int x;
    private int y;

    public Crystal(int seed, int x, int y) {
        this.seed = seed;
        this.rules = getRules(this.seed);
        this.x = x;
        this.y = y;
    }

    private static void printField(int[][] field) {
        int height = field.length;
        int width = field[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void setOnField(Field field) {
        field.setCellValue(x, y, 1);
    }

    private HashMap<String, Integer> getRules(int seed) {
        try {
            int[][] crystal = Rules.getCrystal(seed);
            HashMap<String, Integer> rules = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                int value = crystal[0][i];
                String key = String.valueOf(crystal[2][i]) + String.valueOf(crystal[1][i]);
                rules.put(key, value);
            }

            return rules;

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public void nextStep(int[][] field) {
        int[][] nextState = calculateState(field);
        printField(nextState);
        applyState(field, nextState);
    }

    private void applyState(int[][] field, int[][] nextState) {
        int height = field.length;
        int width = field[0].length;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                field[y][x] = nextState[y][x];
            }
        }
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

        int count = top + bottom + left + right;
        return count;
    }

    private int findRule(int current, int count) {
        String key = getKey(current, count);
        return this.rules.get(key);
    }


    private String getKey(int current, int count) {
        return String.valueOf(current) + String.valueOf(count);
    }
}
