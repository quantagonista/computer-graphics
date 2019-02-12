import java.util.*;

public class Rules {
    private static String Labyrinth = "" +
            "A 0 1 -1 A\nA 1 2 -1 A\nA 2 3 -1 A\nA 3 4 -1 A\n" +
            "A 4 5 -1 A\nA 5 6 1 B\nB 0 1 1 A\nB 5 6 -1 B\n" +
            "B 6 7 -1 B\nB 7 8 -1 B\nB 8 9 -1 B\nB 9 10 -1 B\n" +
            "B 10 11 -1 B\nB 11 12 -1 B\nB 12 13 -1 B\nB 13 14 -1 B\n" +
            "B 14 15 -1 B\nB 15 0 -1 B";

    private static String Island = "" +
            "A 0 1 -1 B\nA 1 2 -1 B\nA 2 3 -1 B\nA 3 4 -1 B\n" +
            "A 4 5 1 B\nA 5 6 1 B\nA 6 7 1 B\nA 7 8 1 B\n" +
            "A 8 9 -1 B\nA 9 10 -1 B\nA 10 11 -1 B\nA 11 12 -1 B\n" +
            "A 12 13 1 B\nA 13 14 1 B\nA 14 15 1 B\nA 15 0 1 A\n" +
            "B 0 1 1 B\nB 1 2 1 A\nB 2 3 1 A\nB 3 4 1 A\n" +
            "B 4 5 -1 A\nB 5 6 -1 A\nB 6 7 -1 A\nB 7 8 -1 A\n" +
            "B 8 9 1 A\nB 9 10 1 A\nB 10 11 1 A\nB 11 12 1 A\n" +
            "B 12 13 -1 A\nB 13 14 -1 A\nB 14 15 -1 A\nB 15 0 -1 A";


    public static HashMap<String, Integer> getCrystal(int seed) {
        if (seed > 1023) throw new RuntimeException("Seed must be less than 1024");

        HashMap<String, Integer> rules = new HashMap<>();
        List<String> binarySeed = binarifySeed(seed);

        int index = 0;
        for (int state = 0; state <= 1; state++) {
            for (int neighbours = 0; neighbours <= 4; neighbours++) {
                String condition = state + "" + neighbours;
                int newValue = Integer.parseInt(binarySeed.get(index++));
                rules.put(condition, newValue);
            }
        }
        return rules;
    }

    private static List<String> binarifySeed(int seed) {
        String string = Integer.toBinaryString(seed);
        List<String> list = new ArrayList<>();
        Collections.addAll(list, string.split(""));

        while (list.size() < 10) {
            list.add("0");
        }
        return list;
    }

    private static void inverse(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i);
            if (x == 0) x = 1;
            else x = 0;
            list.set(i, x);
        }
    }


    public static HashMap getLabyrinth() {
        return getRulesFromString(Rules.Labyrinth);
    }

    public static HashMap getIsland() {
        return getRulesFromString(Rules.Island);
    }


    private static HashMap<String, Rule> getRulesFromString(String string) {
        HashMap<String, Rule> rules = new HashMap<>();
        for (String line : string.split("\n")) {
            List<String> items = Arrays.asList(line.split(" "));
            String key = items.get(0) + items.get(1);

            int newtColor = Integer.parseInt(items.get(2));
            int newTurn = Integer.parseInt(items.get(3));
            String newState = items.get(4);

            Rule rule = new Rule(newtColor, newTurn, newState);
            rules.put(key, rule);
        }
        return rules;
    }
}
