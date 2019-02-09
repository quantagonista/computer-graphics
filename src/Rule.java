public class Rule {
    public String nextState;
    public int nextColor;
    public int turn;

    public Rule(int color, int turn, String state) {
        this.nextColor = color;
        this.turn = turn;
        this.nextState = state;
    }
}
