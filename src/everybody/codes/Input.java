package everybody.codes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Input {
    public static String read(int quest, int part) throws IOException {
        String path = String.format("input/everybody_codes_e2024_q%d_p%d.txt", quest, part);
        return Files.readString(Path.of(path));
    }
}
