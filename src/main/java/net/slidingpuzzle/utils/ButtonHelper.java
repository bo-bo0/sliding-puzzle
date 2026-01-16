package net.slidingpuzzle.utils;

import javafx.scene.control.Button;

public abstract class ButtonHelper
{
    public static void setButtonActiveLook(Button button, boolean active)
    {
        if (active)
        {
            button.setStyle("-fx-background-color: linear-gradient(to bottom right, #4CAF50, #2E7D32);" +
            " -fx-text-fill: white;");

        }
        else
        {
            button.setStyle("-fx-text-fill: linear-gradient(#c7c9c8, #959695);" +
            " -fx-background-color: linear-gradient(to bottom right, #245426, #112e12);");
        }
    }
}
