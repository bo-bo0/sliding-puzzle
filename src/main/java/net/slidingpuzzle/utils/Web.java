package net.slidingpuzzle.utils;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class Web
{
    public static void browse(String url) throws URISyntaxException, IOException
    {
        Desktop.getDesktop().browse(new URI(url));
    }
}
