package net.slidingpuzzle;

import javafx.application.Application;
import javafx.stage.Stage;
import net.slidingpuzzle.game.MainGameManager;
import net.slidingpuzzle.utils.SceneHelper;

import java.io.IOException;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        var mainStage = new Stage();
        mainStage.setResizable(false);
        SceneHelper.loadScene(mainStage, "gui.fxml", "style.css");
        mainStage.show();
        MainGameManager.awake();
    }
}
