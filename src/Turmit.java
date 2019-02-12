import java.util.HashMap;

public class Turmit extends Drawer {
    public String state = "A";
    public Direction direction = Direction.UP;
    public int x;
    public int y;
    private HashMap rules;

    public Turmit(int x, int y, HashMap rules) {
        this.x = x;
        this.y = y;
        this.setRules(rules);
    }


    public void setRules(HashMap rules) {
        this.rules = rules;
    }

    public Rule findRule(String state, int color) {
        return (Rule) rules.get(state + color);
    }

    public int[][] nextStep(Field field) {
        int width = field.field.length;
        int height = field.field[0].length;

        int[][] newState = new int[width][height];

        int X = x % width;
        int Y = y % height;

        int color = field.field[X][Y];
        Rule rule = findRule(state, color);

        newState[X][Y] = rule.nextColor;
        state = rule.nextState;

        findDirection(rule.turn);
        move();
        return newState;
    }

    private void move() {
        switch (direction) {
            case UP:
                y -= 1;
                break;
            case DOWN:
                y += 1;
                break;
            case LEFT:
                x -= 1;
                break;
            case RIGHT:
                x += 1;
                break;
        }
    }

    private void findDirection(int turn) {
        if (turn == -1) {
            if (direction == Direction.UP) {
                direction = Direction.LEFT;
            } else if (direction == Direction.LEFT) {
                direction = Direction.DOWN;
            } else if (direction == Direction.DOWN) {
                direction = Direction.RIGHT;
            } else if (direction == Direction.RIGHT) {
                direction = Direction.UP;
            }
        } else if (turn == 1) {
            if (direction == Direction.UP) {
                direction = Direction.RIGHT;
            } else if (direction == Direction.LEFT) {
                direction = Direction.UP;
            } else if (direction == Direction.DOWN) {
                direction = Direction.LEFT;
            } else if (direction == Direction.RIGHT) {
                direction = Direction.DOWN;
            }
        }
    }

}
