package net.slidingpuzzle.pieces;

import javafx.scene.layout.Pane;
import net.slidingpuzzle.events.PieceMover;
import net.slidingpuzzle.game.MainGameManager;
import net.slidingpuzzle.utils.Delay;

public abstract class PiecesGenerator
{
    public static void generate()
    {
        generatePiece(MainGameManager.pieces.size(), 0);
    }
    private static void generatePiece(int piecesCount, int count)
    {
        if (count < piecesCount)
        {
            var piece = (Pane) MainGameManager.pieces.get(count);
            double originalPos = piece.getLayoutY();
            double downPos = originalPos + 50;
            piece.setLayoutY(downPos);
            PieceMover.move(piece, new Coords(piece.getLayoutX(), originalPos), 0.125f);
            piece.setOpacity(0);
            PieceMover.fade(piece, 1, 0.4f);
            piece.setVisible(true);

            int i = count + 1;
            Delay.executeAfter(0.15f, () -> generatePiece(piecesCount, i));
        }
    }
}
