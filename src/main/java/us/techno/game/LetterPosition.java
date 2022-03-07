package us.techno.game;

public class LetterPosition {
    private final Character character;
    private final Boolean isPresent;
    private final Boolean isCorrect;
    private final Boolean isDuplicate;

    public LetterPosition(Character character, Boolean isPresent, Boolean isCorrect, Boolean isDuplicate){
        this.character = character;
        this.isPresent = isPresent;
        this.isCorrect = isCorrect;
        this.isDuplicate = isDuplicate;
    }

    public Character getCharacter() {
        return character;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public Boolean getDuplicate() {
        return isDuplicate;
    }
}
