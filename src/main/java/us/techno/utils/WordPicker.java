package us.techno.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
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
        if (wordObj instanceof String) {
            //System.out.println("DEBUG: " + wordObj);
            return (String) wordObj;
        }
        return null;
    }

    public static String getDefinition(String word) throws URISyntaxException {
        URL res = WordChecker.class.getClassLoader().getResource("wordlist.json");
        assert res != null;
        File file = Paths.get(res.toURI()).toFile();
        if (file.length() != 0) {
            try (BufferedReader br = Files.newBufferedReader(file.toPath())) {
                final JsonObject obj = new JsonParser().parse(br).getAsJsonObject();

                return obj.get(word).getAsString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
