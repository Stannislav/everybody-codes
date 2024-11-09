package everybody.codes;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Quest2 {
    public static void main(String[] args) throws IOException {
        Quest2 quest = new Quest2();
        List<String> input = Input.readLines(2, 1);
        String[] words = input.get(0).replaceFirst("WORDS:", "").split(",");
        int solution = quest.countRunicWords(words, input.get(2));
        System.out.println("Part 1: " + solution);
    }

    public int countRunicWords(String[] words, String inscription) {
        return Stream.of(words)
            .map(word -> countOccurrences(word, inscription))
            .flatMapToInt(IntStream::of)
            .sum();
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
