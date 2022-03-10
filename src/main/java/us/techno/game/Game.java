package us.techno.game;

import us.techno.utils.WordChecker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.net.URISyntaxException;

public class Game {
    private String correctWord;
    private GameStatus gameStatus;
    private String[] guesses;
    private static Game game;
    private JLabel[] squares = new JLabel[30];
    private final String[] alphabet = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M"};
    private final Font georgia = new Font("Georgia", Font.PLAIN, 50);
    JPanel titlePanel;
    JLabel headingLabel;
    JButton helpButton;

    /*
    Some Colors, taken from nyt site
     */
    static Color dark = new Color(0, 0, 0);
    static Color yellow = new Color(181, 159, 59);
    static Color lightGray = new Color(128, 130, 132);
    static Color green = new Color(83, 141, 79);
    static Color darkGray = new Color(59, 59, 60);
    static Color offWhite = new Color(255, 255, 242);

    public Game(){
        createGuiWindow();
        gameStatus = GameStatus.PLAYING;
        correctWord = "later";
        game = this;
    }

    public void createGuiWindow(){
        //Create the frame
        JFrame frame = new JFrame("Compudle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFont(georgia);
        frame.setSize(500, 700);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().setBackground(dark);
        frame.setLocationRelativeTo(null);


        titlePanel = new JPanel();
        titlePanel.setBounds(0, 5, frame.getWidth(), 50);
        titlePanel.setLayout(new BorderLayout(0, 10));
        titlePanel.setBorder(new MatteBorder(0, 0, 2, 0, darkGray));
        titlePanel.setBackground(dark);

        headingLabel = new JLabel("COMPUDLE", SwingConstants.CENTER);
        headingLabel.setFont(georgia);
        headingLabel.setBounds(125,40, 125, 35);
        headingLabel.setForeground(offWhite);

        helpButton = new JButton("?");
        helpButton.setFont(georgia);
        helpButton.setForeground(offWhite);
        helpButton.setFocusable(false);
        helpButton.setBackground(dark);
        helpButton.setBorderPainted(false);
        titlePanel.add(helpButton, BorderLayout.WEST);
        titlePanel.add(headingLabel, BorderLayout.CENTER);


        JPanel grid = new JPanel();
        grid.setBounds(100, 100, 300, 350);
        grid.setLayout(new GridLayout(6,5,3,3));
        grid.setOpaque(true);
        grid.setBackground(dark);
        for (int i = 0; i < squares.length; i++) {
            squares[i] = new JLabel("", SwingConstants.CENTER);
            squares[i].setFont(georgia);
            squares[i].setBackground(dark);
            squares[i].setForeground(offWhite);
            squares[i].setOpaque(true);
            squares[i].setBorder(new LineBorder(darkGray, 2));

            grid.add(squares[i]);
        }

        JTextField guesser = new JTextField("Guess your answer", SwingConstants.BOTTOM);
        guesser.setHorizontalAlignment(SwingConstants.CENTER);
        guesser.setSize(frame.getWidth(), 50);
        guesser.setBounds(250, 450, 50, 50);
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

        frame.add(titlePanel);
        frame.add(grid);
        frame.add(guesser);
        frame.setVisible(true);
        System.out.println(headingLabel.getWidth() + " " + headingLabel.getX());
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
