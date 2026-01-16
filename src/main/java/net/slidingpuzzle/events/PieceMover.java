package net.slidingpuzzle.events;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import net.slidingpuzzle.pieces.Coords;

public class PieceMover
{
    public static void move(Pane piece, Coords destination, float time)
    {
        var timeline = new Timeline
        (
            new KeyFrame
            (
                Duration.seconds(time),
                new KeyValue(piece.layoutXProperty(), destination.x()),
                new KeyValue(piece.layoutYProperty(), destination.y())
            )
        );

        timeline.play();
    }
    public static void fade(Pane piece, double finalValue, float time)
    {
        var timeline = new Timeline
        (
            new KeyFrame
            (
                Duration.seconds(time),
                new KeyValue(piece.opacityProperty(), finalValue)
            )
        );

        timeline.play();
    }
}