package net.slidingpuzzle.pieces;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public final class AbstractPiece
{

    public int value;

    private void setValue(int value) throws IllegalArgumentException
    {
        if (value < 1 || value > 15)
        { throw new IllegalArgumentException("A piece value out of the (1 - 15) range was provided."); }

        this.value = value;
    }

    public AbstractPiece(int value, Pane pane)
    {
        setValue(value);

        var children = pane.getChildren();
        for (var child : children)
        {
            if (child instanceof Label childLabel)
            {
                childLabel.setText(Integer.toString(this.value));
                break;
            }
        }
    }

}
