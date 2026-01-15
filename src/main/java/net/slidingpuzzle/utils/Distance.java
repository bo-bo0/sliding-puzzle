package net.slidingpuzzle.utils;

import net.slidingpuzzle.pieces.Coords;

public abstract class Distance
{
    public static double getDistance(Coords start, Coords end)
    {
        double result = Math.pow(start.x() - end.x(), 2) + Math.pow(start.y() - end.y(), 2);
        result = Math.sqrt(result);

        return result;
    }
}
