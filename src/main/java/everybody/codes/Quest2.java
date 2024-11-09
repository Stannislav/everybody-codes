package everybody.codes;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Quest2 {
    public static void main(String[] args) throws IOException {
        Quest2 quest = new Quest2();

        quest.part1(Input.readLines(2, 1));
        quest.part2(Input.readLines(2, 2));
    }

    private void part1(List<String> inputLines) {
        String[] words = inputLines.getFirst().replaceFirst("WORDS:", "").split(",");
        int solution = countRunicWords(words, inputLines.get(2));
        System.out.println("Part 1: " + solution);
    }

    private void part2(List<String> inputLines) {
        String[] words = inputLines.getFirst().replaceFirst("WORDS:", "").split(",");
        int total = 0;
        for (String line : inputLines.subList(2, inputLines.size())) {
            total += countRunicSymbols(words, line);
        }

        System.out.println("Part 2: " + total);
    }

    public int countRunicWords(String[] words, String inscription) {
        return Stream.of(words)
            .map(word -> countOccurrences(word, inscription))
            .flatMapToInt(IntStream::of)
            .sum();
    }

    public int countRunicSymbols(String[] words, String inscription) {
        HashSet<Integer> symbolPositions = new HashSet<>();
        for (String word : words) {
            symbolPositions.addAll(getSymbolPositions(word, inscription));

            String antiWord = new StringBuilder(word).reverse().toString();
            symbolPositions.addAll(getSymbolPositions(antiWord, inscription));
        }

        return symbolPositions.size();
    }

    private Set<Integer> getSymbolPositions(String word, String inscription) {
        Set<Integer> symbolPositions = new HashSet<>();
        int idx = 0;
        while ((idx = inscription.indexOf(word, idx)) != -1) {
            for (int i = 0; i < word.length(); i++) {
                symbolPositions.add(idx + i);
            }
            idx += 1;
        }

        return symbolPositions;
    }

    private int countOccurrences(String token, String phrase) {
        int count = 0;
        int idx = 0;
        while ((idx = phrase.indexOf(token, idx)) != -1) {
            count += 1;
            idx += token.length();
        }
        return count;
    }
}
