package us.techno.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class WordChecker {
    public static boolean verifyWordWithDictionary(String input) {
        AtomicBoolean found = new AtomicBoolean(false);
        try (Stream<String> stream = Files.lines(Paths.get("resources/dictionary.txt"))) {
            stream.filter(lines -> lines.contains(input)).forEach(s ->
                    found.set(true));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return found.getOpaque();
    }
}
