package net.slidingpuzzle.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import net.slidingpuzzle.Main;
import net.slidingpuzzle.game.MainGameManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class SceneHelper
{
    public static void loadScene(Stage currentStage, String scene, String css) throws IOException
    {
        var sceneResource = Main.class.getResource(scene);
        Parent root = FXMLLoader.load(Objects.requireNonNull(sceneResource));
        var mainScene = new Scene(root);
        MainGameManager.pieces = new ArrayList<>(root.lookupAll(".piece"));
        var cssResource = Objects.requireNonNull(Main.class.getResource(css)).toExternalForm();
        mainScene.getStylesheets().add(cssResource);
        currentStage.setScene(mainScene);
    }
}
