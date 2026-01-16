package net.slidingpuzzle;

import javafx.application.Application;
import javafx.stage.Stage;

import net.slidingpuzzle.game.MainGameManager;
import net.slidingpuzzle.utils.SceneHelper;
import net.slidingpuzzle.utils.SoundManager;

import java.io.IOException;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        SoundManager.playSound("song.wav", -20, true);

        var mainStage = new Stage();
        mainStage.setResizable(false);
        SceneHelper.loadScene(mainStage, "gui.fxml", "style.css");
        mainStage.show();
        MainGameManager.awake();
    }
}
