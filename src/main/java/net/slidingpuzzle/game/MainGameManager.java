package net.slidingpuzzle.game;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import net.slidingpuzzle.pieces.AbstractPiece;
import net.slidingpuzzle.pieces.Coords;
import net.slidingpuzzle.pieces.PiecesGenerator;
import net.slidingpuzzle.utils.NodeHelper;

import java.util.ArrayList;
import java.util.Random;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public abstract class MainGameManager
{
    public static final float moveToFreeTime = 0.35f;

    public static ArrayList<Node> pieces;
    public static AbstractPiece[] abstractPieces = new AbstractPiece[15];
    public static boolean playerCanInput = false;
    public static Coords currentFreeSquare;
    public static boolean canGenerate = true;
    public static boolean canReset = false;
    public static Difficulty difficulty = Difficulty.MEDIUM;

    public static void awake()
    {
        makeEverythingInvisible();
    }

    public static void init(Difficulty difficulty)
    {
        removeRandomPiece();
        PiecesGenerator.generate();

        var numbers = new int[15];

        for (int i = 0; i < 15; i++)
        { numbers[i] = i + 1; }

        setPiecesNumbers(numbers, difficulty);
    }
    private static void makeEverythingInvisible()
    {
        for (var piece : pieces)
        { piece.setVisible(false); }
    }
    private static void removeRandomPiece()
    {
        var ran = new Random().nextInt(pieces.size());
        NodeHelper.removeNode(pieces.get(ran));
        currentFreeSquare = new Coords(pieces.get(ran).getLayoutX(), pieces.get(ran).getLayoutY());
        pieces.remove(pieces.get(ran));
    }
    private static void setPiecesNumbers(int[] arr, Difficulty difficulty) throws NullPointerException
    {
        Objects.requireNonNull(difficulty);

        int intDiff = switch (difficulty)
        {
            case EASY -> 1;
            case MEDIUM -> 2;
            case HARD -> 3;
        };

        int n = arr.length;

        if (n < 2)
        { return; }

        double[] intensities = {0.20, 0.55, 0.90};
        int k = (int) Math.round(n * intensities[intDiff - 1]);
        k = Math.max(1, Math.min(k, n));

        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        int[] indices = new int[n];
        for (int i = 0; i < n; i++)
        { indices[i] = i; }

        for (int i = n - 1; i > 0; i--)
        {
            int j = rnd.nextInt(i + 1);
            int tmp = indices[i];
            indices[i] = indices[j];
            indices[j] = tmp;
        }

        if (k == 1)
        {
            int i = indices[0];
            int j = rnd.nextInt(n - 1);
            if (j >= i) j++;
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        else
        {
            int[] sel = new int[k];
            System.arraycopy(indices, 0, sel, 0, k);

            int[] vals = new int[k];
            for (int t = 0; t < k; t++)
            { vals[t] = arr[sel[t]]; }

            int shift = rnd.nextInt(1, k);

            for (int t = 0; t < k; t++)
            { arr[sel[t]] = vals[(t + shift) % k]; }
        }

        for (int i = 0; i < abstractPieces.length; i++)
        {
            var currentPane = (Pane)pieces.get(i);
            abstractPieces[i] = new AbstractPiece(arr[i], currentPane);
        }
    }
}
