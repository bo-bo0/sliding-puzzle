package net.slidingpuzzle.events;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import net.slidingpuzzle.game.Difficulty;
import net.slidingpuzzle.game.MainGameManager;
import net.slidingpuzzle.pieces.Coords;
import net.slidingpuzzle.utils.Delay;
import net.slidingpuzzle.utils.Distance;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    private ComboBox<String> comboBox;

    private void initializeChoiceBox()
    {
        var boxContent = new String[]
        { Difficulty.EASY.toString(), Difficulty.MEDIUM.toString(), Difficulty.HARD.toString() };

        comboBox.getItems().addAll(boxContent);
        comboBox.getSelectionModel().select(1);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        initializeChoiceBox();
        initializePressedPieceEvents();
    }
    @SuppressWarnings("unused")
    public void onCreateButtonPressed(ActionEvent e)
    {
        var difficulty = Difficulty.convertStringToDifficulty(comboBox.getSelectionModel().getSelectedItem());
        MainGameManager.init(difficulty);
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

        var piece = (Pane) e.getSource();

        if (Distance.getDistance(new Coords(piece.getLayoutX(), piece.getLayoutY()), MainGameManager.currentFreeSquare) <= 240)
        {
            PieceMover.move(piece, MainGameManager.currentFreeSquare, MainGameManager.moveToFreeTime);
            MainGameManager.currentFreeSquare = new Coords(piece.getLayoutX(), piece.getLayoutY());
            MainGameManager.playerCanInput = false;

            Delay.executeAfter(MainGameManager.moveToFreeTime, () -> MainGameManager.playerCanInput = true);
        }
    }
}
