package us.techno.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class WordChecker {
    public static boolean verifyWordWithDictionary(String input) throws URISyntaxException {
        URL res = WordChecker.class.getClassLoader().getResource("dictionary.txt");
        assert res != null;
        File file = Paths.get(res.toURI()).toFile();
        AtomicBoolean found = new AtomicBoolean(false);
        try (Stream<String> stream = Files.lines(file.getAbsoluteFile().toPath())) {
            stream.filter(lines -> lines.contains(input)).forEach(s ->
                    found.set(true));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return found.getOpaque();
    }
}
