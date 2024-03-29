package us.techno.game;

import us.techno.events.ActionListener;
import us.techno.events.KeyPressListener;
import us.techno.utils.WordChecker;
import us.techno.utils.WordPicker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private String correctWord;
    private GameStatus gameStatus;
    private static Game game;
    private final JLabel[] squares = new JLabel[30];
    private int squareIndex = 0;
    private boolean canEditGuess = true;
    private final String[] alphabet = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M"};
    private final Font georgia = new Font("Georgia", Font.PLAIN, 50);
    JPanel titlePanel;
    JFrame frame;
    JLabel headingLabel;
    JButton playAgainButton;

    /*
    Some Colors, taken from nyt site
     */
    static Color dark = new Color(0, 0, 0);
    static Color yellow = new Color(181, 159, 59);
    static Color lightGray = new Color(128, 130, 132);
    static Color green = new Color(83, 141, 79);
    static Color darkGray = new Color(59, 59, 60);
    static Color offWhite = new Color(255, 255, 242);
    static Color blue = new Color(56, 76, 189); //our new color for duplicate letters

    public Game(){
        createGuiWindow();
        gameStatus = GameStatus.PLAYING;
        try {
            setCorrectWord(WordPicker.pickNewWord());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        if (correctWord == null) throw new RuntimeException("No word created!");
        game = this;
    }

    public void createGuiWindow(){
        //Create the frame
        frame = new JFrame("Compudle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFont(georgia);
        frame.setSize(500, 550);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().setBackground(dark);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(new KeyPressListener());


        titlePanel = new JPanel();
        titlePanel.setBounds(0, 5, frame.getWidth(), 50);
        titlePanel.setLayout(new BorderLayout(42, 10));
        titlePanel.setBorder(new MatteBorder(0, 0, 2, 0, darkGray));
        titlePanel.setBackground(dark);

        headingLabel = new JLabel("COMPUDLE", SwingConstants.LEFT);
        headingLabel.setFont(georgia);
        headingLabel.setBounds(125,40, 125, 35);
        headingLabel.setForeground(offWhite);

        playAgainButton = new JButton("P");
        playAgainButton.setFont(georgia);
        playAgainButton.setForeground(offWhite);
        playAgainButton.setFocusable(false);
        playAgainButton.setBackground(dark);
        playAgainButton.setBorderPainted(false);
        playAgainButton.addActionListener(new ActionListener());
        titlePanel.add(playAgainButton, BorderLayout.WEST);
        titlePanel.add(headingLabel);


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

        frame.add(titlePanel);
        frame.add(grid);
        frame.setVisible(true);
    }

    public void processGuess(int index, String guess) throws URISyntaxException {
        if (index > 29 || index < 0) {
            throw new IndexOutOfBoundsException("Index not in bounds for amount of squares"); //should never happen, but this would be pretty bad! This means somehow we're guessing outside the grid.
        }

        List<JLabel> labelList = new ArrayList<>();

        int i = index - 4;
        while (i <= index) {
            labelList.add(squares[i]);
            i++;
        }
        if (!WordChecker.verifyWordWithDictionary(guess.toString())) {
            JOptionPane.showMessageDialog(frame, "Not in word list!",
                    "Word Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<LetterPosition> letterPositionList = WordChecker.checkWord(this, guess.toString());

        java.awt.EventQueue.invokeLater(() -> {
            int x = 0;
            while (x < letterPositionList.size()) {
                LetterPosition letterPosition = letterPositionList.get(x);
                JLabel square = labelList.get(x);
                if (letterPosition.getCorrect()){
                    square.setBackground(green);
                } else if (letterPosition.getPresent()){
                    square.setBackground(yellow);
                } else {
                    square.setBackground(lightGray);
                }
                if (letterPosition.getDuplicate()) square.setBorder(new LineBorder(blue, 4));
                x++;
            }
        });

        if (guess.toString().equalsIgnoreCase(correctWord)) {
            JOptionPane.showMessageDialog(frame, "You won!");
            setGameStatus(GameStatus.WIN);
            return;
        }

        if (index == 29) /*final square*/ {
            JOptionPane.showMessageDialog(frame, "You lost!");
            setGameStatus(GameStatus.LOSS);
            return;
        }
        setSquareIndex(getSquareIndex() + 1);
        setCanEditGuess(true);
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        if (gameStatus != GameStatus.PLAYING) {
            try {
                defineWord();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public void defineWord() throws URISyntaxException {
        JOptionPane.showMessageDialog(frame, "Thanks for playing Compudle! The word was " + getCorrectWord() + ". \nDefinition: " + WordPicker.getDefinition(getCorrectWord()),
                "What does this word mean?", JOptionPane.QUESTION_MESSAGE);
    }

    public GameStatus getGameStatus() {
        return gameStatus;
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

    public void newGame() {
        frame.setVisible(false);
        game = null;
        Game newInstance = new Game();
    }

    public JLabel[] getSquares(){
        return squares;
    }

    public int getSquareIndex() {
        return squareIndex;
    }

    public void setSquareIndex(int squareIndex) {
        this.squareIndex = squareIndex;
    }

    public String[] getAlphabet() {
        return alphabet;
    }

    public boolean isCanEditGuess() {
        return canEditGuess;
    }

    public void setCanEditGuess(boolean canEditGuess) {
        this.canEditGuess = canEditGuess;
    }

    public JButton getPlayAgainButton() {
        return playAgainButton;
    }

}