package us.techno.game;

import us.techno.listeners.KeyPressListener;
import us.techno.utils.WordChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

public class Game {
    private String correctWord;
    private GameStatus gameStatus;
    private String[] guesses;
    private static Game game;

    public Game(){
        createGuiWindow();
        gameStatus = GameStatus.PLAYING;
        correctWord = "later";
        game = this;
    }

    public void createGuiWindow(){
        //Create the frame
        JFrame frame = new JFrame("Compudle");
        frame.setLayout(new GridLayout(3, 1));
        frame.addKeyListener(new KeyPressListener());

        JLabel label = new JLabel("Compudle", SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(300, 200));
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setMinimumSize(new Dimension(50, 25));
        label.setMaximumSize(new Dimension(300, 200));
        label.setFont(new Font("Georgia", Font.PLAIN, 50));
        frame.add(label);

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(6,5,3,3));
        frame.add(grid);

        JTextField guesser = new JTextField("Guess your answer", SwingConstants.BOTTOM);
        guesser.setHorizontalAlignment(SwingConstants.CENTER);
        guesser.setSize(frame.getWidth(), frame.getHeight()/10);
        guesser.addActionListener(e -> {
            String data = "Guess: " + guesser.getText();
            System.out.println(data);
            try {
                boolean result = WordChecker.verifyWordWithDictionary(guesser.getText());
                System.out.println(result);
                if (!result){
                    System.out.println("Not a word :|");
                    return;
                }
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
            WordChecker.checkWord(Game.getGame(), guesser.getText()).forEach(letterPosition ->
                    System.out.println(letterPosition.getCharacter().toString() + letterPosition.getCorrect() + letterPosition.getPresent() + letterPosition.getDuplicate()));
        });
        frame.add(guesser);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setSize(500,500);
        frame.setMinimumSize(new Dimension(300,300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public String[] getGuesses() {
        return guesses;
    }

    public void setGuesses(String[] guesses) {
        this.guesses = guesses;
    }

    public String getCorrectWord() {
        return correctWord;
    }

    public void setCorrectWord(String correctWord) {
        this.correctWord = correctWord;
    }

    public static Game getGame() {
        return game;
    }
}
