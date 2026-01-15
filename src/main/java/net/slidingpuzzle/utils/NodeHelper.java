package net.slidingpuzzle.utils;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class NodeHelper
{
    public static void removeNode(Node node)
    {
        var parent = node.getParent();

        if (parent instanceof Pane pane)
        { pane.getChildren().remove(node); }
    }
}
