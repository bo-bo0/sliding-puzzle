package net.slidingpuzzle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.slidingpuzzle.game.MainGameManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        var sceneResource = getClass().getResource("gui.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(sceneResource));
        var mainScene = new Scene(root);
        MainGameManager.pieces = new ArrayList<>(root.lookupAll(".piece"));
        var mainStage = new Stage();
        var css = Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm();
        mainScene.getStylesheets().add(css);
        mainStage.setResizable(false);
        mainStage.setScene(mainScene);
        mainStage.show();
        MainGameManager.init();
    }
}
