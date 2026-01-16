package net.slidingpuzzle.events;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import net.slidingpuzzle.game.Difficulty;
import net.slidingpuzzle.game.MainGameManager;
import net.slidingpuzzle.pieces.Coords;
import net.slidingpuzzle.utils.ButtonHelper;
import net.slidingpuzzle.utils.SoundManager;
import net.slidingpuzzle.utils.Delay;
import net.slidingpuzzle.utils.SceneHelper;
import net.slidingpuzzle.utils.Web;
import net.slidingpuzzle.utils.Distance;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button resetButton;

    private void initializeChoiceBox()
    {
        var boxContent = new String[]
        { Difficulty.EASY.toString(), Difficulty.MEDIUM.toString(), Difficulty.HARD.toString() };

        comboBox.getItems().addAll(boxContent);
        comboBox.getSelectionModel().select(MainGameManager.difficulty.toString());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        initializeChoiceBox();
        initializePressedPieceEvents();
        MainGameManager.canReset = false;
        ButtonHelper.setButtonActiveLook(resetButton, false);
    }
    public void onCreateButtonPressed(ActionEvent e)
    {
        if (!MainGameManager.canGenerate)
        { return; }

        MainGameManager.init(MainGameManager.difficulty);
        MainGameManager.canGenerate = false;

        ButtonHelper.setButtonActiveLook((Button)e.getSource(), MainGameManager.canGenerate);

        Delay.executeAfter(2.65f, () ->
        {
            MainGameManager.canReset = true;
            ButtonHelper.setButtonActiveLook(resetButton, true);
        });
    }
    public void onComboBoxSelectionChanged()
    {
        MainGameManager.difficulty = Difficulty.convertStringToDifficulty(comboBox.getSelectionModel().getSelectedItem());
    }
    public void onResetButtonPressed(ActionEvent e)
    {
        if (!MainGameManager.canReset)
        { return; }

        float fadeTime = 0.5f;

        for (var piece : MainGameManager.abstractPieces)
        { PieceMover.fade(piece.getPane(), 0, fadeTime); }

        MainGameManager.canReset = false;
        ButtonHelper.setButtonActiveLook(resetButton, false);

        Delay.executeAfter(fadeTime, () ->
        {
            Window win = ((Node)e.getSource()).getScene().getWindow();

            try
            { SceneHelper.loadScene((Stage)win, "gui.fxml", "style.css"); }
            catch (IOException ex)
            { throw new RuntimeException("Failed to load scene."); }

            MainGameManager.awake();
            MainGameManager.canGenerate = true;
        });
    }
    public void initializePressedPieceEvents()
    {
        Delay.executeAfter(2f, () ->
        {
            for (var piece : MainGameManager.pieces)
            { piece.setOnMouseClicked(this::onPressedPiece); }
        });
    }
    public void onPressedPiece(MouseEvent e)
    {
        if (!MainGameManager.playerCanInput)
        { return; }

        var piece = (Pane)e.getSource();

        if (Distance.getDistance(new Coords(piece.getLayoutX(), piece.getLayoutY()), MainGameManager.currentFreeSquare) <= 240)
        {
            PieceMover.move(piece, MainGameManager.currentFreeSquare, MainGameManager.moveToFreeTime);
            MainGameManager.currentFreeSquare = new Coords(piece.getLayoutX(), piece.getLayoutY());
            MainGameManager.playerCanInput = false;

            Delay.executeAfter(MainGameManager.moveToFreeTime, () -> MainGameManager.playerCanInput = true);
            SoundManager.playSound("slide.wav", -30, false);
        }
    }
    public void onMajoranaPressed() throws URISyntaxException, IOException
    {
        Web.browse("https://www.majoranatermoli.edu.it/");
    }
    public void onOpenDayPressed() throws URISyntaxException, IOException
    {
        Web.browse("https://www.majoranatermoli.edu.it/orientamento/");
    }
    public void onNamePressed() throws URISyntaxException, IOException
    {
        Web.browse("https://github.com/bo-bo0");
    }
}
