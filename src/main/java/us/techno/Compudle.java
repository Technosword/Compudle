package us.techno;

import us.techno.game.Game;
import us.techno.utils.WordChecker;

import java.net.URISyntaxException;
import java.util.Scanner;

public class Compudle {
    public static void main(String[] args) {
        //Create new game instance.
        Game game = new Game();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println(WordChecker.verifyWordWithDictionary(sc.next()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
