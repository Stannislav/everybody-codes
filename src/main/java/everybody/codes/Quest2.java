package everybody.codes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Quest2 {
    public static void main(String[] args) throws IOException {
        Quest2 quest = new Quest2();

        quest.part1(Input.readLines(2, 1));
        quest.part2(Input.readLines(2, 2));
        quest.part3(Input.readLines(2, 3));
    }

    private void part1(List<String> inputLines) {
        List<String> words = parseWords(inputLines);
        String line = inputLines.get(2);
        System.out.println("Part 1: " + countRunicWords(words, line));
    }

    private void part2(List<String> inputLines) {
        List<String> words = parseWords(inputLines);
        List<String> lines = inputLines.subList(2, inputLines.size());
        int total = lines.stream()
            .mapToInt(line -> countRunicSymbols(words, line))
            .sum();
        System.out.println("Part 2: " + total);
    }

    private void part3(List<String> inputLines) {
        List<String> words = parseWords(inputLines);
        List<String> lines = inputLines.subList(2, inputLines.size());
        System.out.println("Part 3: " + countRunicScales(words, lines));
    }

    private List<String> parseWords(List<String> inputLines) {
        String[] words = inputLines.getFirst().replaceFirst("WORDS:", "").split(",");
        return Arrays.asList(words);
    }

    private List<String> addReversed(List<String> words) {
        ArrayList<String> withReversed = new ArrayList<>(words);
        for (String word : words) {
            withReversed.add(new StringBuilder(word).reverse().toString());
        }
        return withReversed;
    }

    public int countRunicWords(List<String> words, String line) {
        return words.stream()
            .mapToInt(word -> countOccurrences(word, line))
            .sum();
    }

    private int countOccurrences(String word, String line) {
        int count = 0;
        int idx = 0;
        while ((idx = line.indexOf(word, idx)) != -1) {
            count += 1;
            idx += word.length();
        }
        return count;
    }

    public int countRunicSymbols(List<String> words, String line) {
        return addReversed(words).stream()
            .flatMap(word -> getSymbolPositions(word, line).stream())
            .collect(Collectors.toSet())
            .size();
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

    public int countRunicScales(List<String> words, List<String> lines) {
        words = addReversed(words);
        int height = lines.size();
        int width = lines.getFirst().length();
        for (String line : lines) {
            if (line.length() != width) {
                throw new IllegalArgumentException("Bad grid: not all lines are of the same length.");
            }
        }

        char[][] grid = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        HashSet<Pos> scalePositions = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (matchesHorizontally(word, grid, i, j)) {
                        for (int k = 0; k < word.length(); k++) {
                            scalePositions.add(new Pos(i, (j + k) % width));
                        }
                    }
                    if (matchesVertically(word, grid, i, j)) {
                        for (int k = 0; k < word.length(); k++) {
                            scalePositions.add(new Pos(i + k, j));
                        }
                    }
                }
            }
        }
        return scalePositions.size();
    }

    private boolean matchesHorizontally(String word, char[][] grid, int i, int j) {
        int width = grid[0].length;
        for (int pos = 0; pos < word.length(); pos++) {
            int k = (j + pos) % width;
            if (word.charAt(pos) != grid[i][k]) {
                return false;
            }
        }

        return true;
    }

    private boolean matchesVertically(String word, char[][] grid, int i, int j) {
        int height = grid.length;
        for (int pos = 0; pos < word.length(); pos++) {
            int k = i + pos;
            if (k >= height) {
                return false;
            }
            if (word.charAt(pos) != grid[k][j]) {
                return false;
            }
        }
        return true;
    }
}
