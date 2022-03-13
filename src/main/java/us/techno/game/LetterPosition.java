package us.techno.game;

public record LetterPosition(Character character, Boolean isPresent, Boolean isCorrect,
                             Boolean isDuplicate) {

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
