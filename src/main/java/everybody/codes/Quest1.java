package everybody.codes;

import java.io.IOException;
import java.util.List;

public class Quest1 {
    public static void main(String[] args) throws IOException {
        Quest1 quest = new Quest1();
        for (int part : List.of(1, 2, 3)) {
            String input = Input.read(1, part);
            int potions = quest.computePotions(input, part);
            System.out.println("Part " + part + ": " + potions);
        }
    }

    public int computePotions(String input, int groupSize) {
        int result = 0;
        for (int i = 0; i < input.length(); i += groupSize) {
            String group = input.substring(i, i + groupSize);
            result += group.chars().map(this::creatureToPotions).sum();
            result += extraPotions(group);
        }
        return result;
    }

    private int creatureToPotions(int creature) {
        return switch (creature) {
            case 'A', 'x' -> 0;
            case 'B' -> 1;
            case 'C' -> 3;
            case 'D' -> 5;
            default -> throw new IllegalArgumentException("Unknown creature: " + (char) creature);
        };
    }

    private int extraPotions(String group) {
        int nCreatures = (int) group.chars().filter(c -> c != 'x').count();
        return (nCreatures - 1) * nCreatures;
    }
}
