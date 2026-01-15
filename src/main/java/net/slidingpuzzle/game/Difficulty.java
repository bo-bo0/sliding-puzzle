package net.slidingpuzzle.game;

public enum Difficulty
{
    EASY,
    MEDIUM,
    HARD;

    public static final String easyString = "Facile";
    public static final String mediumString = "Media";
    public static final String hardString = "Difficile";

    @Override
    public String toString()
    {
        return switch (this)
        {
            case EASY -> easyString;
            case MEDIUM -> mediumString;
            case HARD -> hardString;
        };
    }
    public static Difficulty convertStringToDifficulty(String difficulty) throws IllegalArgumentException
    {
        return switch (difficulty)
        {
            case Difficulty.easyString -> Difficulty.EASY;
            case Difficulty.mediumString -> Difficulty.MEDIUM;
            case Difficulty.hardString -> Difficulty.HARD;

            default -> throw new IllegalArgumentException("\"" + difficulty +"\" is not a valid difficulty.");
        };
    }
}
