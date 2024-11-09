package everybody.codes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Input {
    public static String read(int quest, int part) throws IOException {
        return Files.readString(makePath(quest, part));
    }

    public static List<String> readLines(int quest, int part) throws IOException {
        return Files.readAllLines(makePath(quest, part));
    }

    private static Path makePath(int quest, int part) {
        String path = String.format("input/everybody_codes_e2024_q%d_p%d.txt", quest, part);
        return Path.of(path);
    }
}
