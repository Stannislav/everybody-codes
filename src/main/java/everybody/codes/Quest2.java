package everybody.codes;

import java.io.IOException;
import java.util.ArrayList;
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
        quest.part3(Input.readLines(2, 3));
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

    private void part3(List<String> inputLines) {
        String[] words = inputLines.getFirst().replaceFirst("WORDS:", "").split(",");
        int total = countRunicScales(words, inputLines.subList(2, inputLines.size()));

        System.out.println("Part 3: " + total);
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

    public int countRunicScales(String[] words, List<String> lines) {
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


        ArrayList<String> allWords = new ArrayList<>(Arrays.asList(words));
        for (String word : words) {
            allWords.add(new StringBuilder(word).reverse().toString());
        }
        HashSet<Pos> scalePositions = new HashSet<>();
        for (String word : allWords) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (matchesHorizontally(word, grid, i, j)) {
//                        System.out.println("Horizontal match " + word + " at " + i + ", " + j);
                        for (int k = 0; k < word.length(); k++) {
                            scalePositions.add(new Pos(i, (j + k) % width));
                        }
                    }
                    if (matchesVertically(word, grid, i, j)) {
//                        System.out.println("Vertical match " + word + " at " + i + ", " + j);
                        for (int k = 0; k < word.length(); k++) {
                            scalePositions.add(new Pos(i + k, j));
                        }
                    }
                }
            }
        }
//        System.out.println(scalePositions);
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                System.out.print(scalePositions.contains(new Pos(i, j)) ? "X" : ".");
//            }
//            System.out.println();
//        }
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                System.out.print(grid[i][j]);
//            }
//            System.out.println();
//        }
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
