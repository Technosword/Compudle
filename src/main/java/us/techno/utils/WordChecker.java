package us.techno.utils;

import us.techno.game.Game;
import us.techno.game.LetterPosition;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordChecker {
    public static boolean verifyWordWithDictionary(String input) throws URISyntaxException {
        URL res = WordChecker.class.getClassLoader().getResource("dictionary.txt");
        assert res != null;
        File file = Paths.get(res.toURI()).toFile();
        AtomicBoolean found = new AtomicBoolean(false);
        try (Stream<String> stream = Files.lines(file.getAbsoluteFile().toPath())) {
            stream.filter(lines -> lines.equalsIgnoreCase(input)).forEach(s ->
                    found.set(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return found.getOpaque();
    }
    public static List<LetterPosition> checkWord(Game game, String guess) {
        String correctWord = game.getCorrectWord();
        List<LetterPosition> resultList = new ArrayList<>();
        Map<Character, Long> characterFrequency = guess.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        for (int i = 0; i < game.getCorrectWord().length(); i++) {
            boolean isPresent = false;
            boolean isCorrect = false;
            boolean isDuplicate = false;
            char character = guess.charAt(i);
            if (correctWord.charAt(i) == character) {
                isCorrect = true;
                isPresent = true;
            }
            if (characterFrequency.get(character) > 1) {
                isDuplicate = true;
            }
            resultList.add(new LetterPosition(character, isPresent, isCorrect, isDuplicate)) ;
        }
        return resultList;
    }
}
