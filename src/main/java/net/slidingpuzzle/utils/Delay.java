package net.slidingpuzzle.utils;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public abstract class Delay
{
    public static void executeAfter(float time, Runnable action)
    {
        var tran = new PauseTransition();
        tran.setDuration(Duration.seconds(time));
        tran.setOnFinished(_ -> action.run());
        tran.play();
    }
}
