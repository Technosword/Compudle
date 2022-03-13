package us.techno.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

public class WordPicker {

    public static String pickNewWord() throws URISyntaxException, IOException {
        URL res = WordChecker.class.getClassLoader().getResource("wordlist.txt");
        assert res != null;
        File file = Paths.get(res.toURI()).toFile();
        Stream<String> stream = Files.lines(file.getAbsoluteFile().toPath());
        Object[] array = stream.toArray();
        int random = new Random().nextInt(array.length - 1);
        Object wordObj = array[random];
        if (wordObj instanceof String) return (String) wordObj;
        return null;
    }
}
