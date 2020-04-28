package main_pack;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private static Clip clip;
    private static File file;

    public static void init(){
        file = new File("./rsc/Audio/Shotgun.wav");

    }

    public static void play() {

        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        clip.setFramePosition(0);
        clip.start();
    }



}
