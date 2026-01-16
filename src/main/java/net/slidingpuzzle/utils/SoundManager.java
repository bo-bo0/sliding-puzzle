package net.slidingpuzzle.utils;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Objects;

public abstract class SoundManager
{
    public static void playSound(String sound, float decibels, boolean infinite)
    {
        try
        {
            InputStream audioSrc = SoundManager.class.getResourceAsStream("/" + sound);
            Objects.requireNonNull(audioSrc);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new BufferedInputStream(audioSrc));

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(decibels);

            if (infinite)
            { clip.loop(Clip.LOOP_CONTINUOUSLY); }

            clip.start();
        }
        catch (Exception ex)
        { System.out.println(ex.getMessage()); }
    }
}
