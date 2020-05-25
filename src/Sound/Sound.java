package Sound;

import javax.sound.sampled.*;
import java.io.*;


public class Sound {
    public static int Volume=50;

    public static void playSfx(final File initialFile) {
        ActivityManager.getInstance().submit(() -> {
            try {
                InputStream fileStream = new FileInputStream(initialFile);

                BufferedInputStream bufferedStream = new BufferedInputStream(fileStream);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedStream);

                final int BUFFER_SIZE = 128000;
                SourceDataLine sourceLine = null;

                AudioFormat audioFormat;
                audioFormat = audioInputStream.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

                sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                sourceLine.open(audioFormat);

                FloatControl gainControl = (FloatControl) sourceLine.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-70+Volume);

                if (sourceLine == null) {
                    return;
                }

                sourceLine.start();
                int nBytesRead = 0;
                byte[] abData = new byte[BUFFER_SIZE];
                while (nBytesRead != -1) {
                    try {
                        nBytesRead = bufferedStream.read(abData, 0, abData.length);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (nBytesRead >= 0) {
                        sourceLine.write(abData, 0, nBytesRead);
                    }
                }

                sourceLine.drain();
                sourceLine.close();
                bufferedStream.close();
                audioInputStream.close();

            } catch (IOException e) {
                //e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                //e.printStackTrace();
            } catch (LineUnavailableException e) {
                //e.printStackTrace();
                //System.exit(1);
            } catch (Exception e) {
                //e.printStackTrace();
                //System.exit(1);
            }
        });
    }

    public static void playSound(final String soundEffect) {
        if (Volume>0) {
            switch (soundEffect) {
                case "Shotgun": playSfx(new File("./rsc/Audio/Shotgun.wav"));break;
                case "Shotgun_Reload": playSfx(new File("./rsc/Audio/PumpShotgunReload.wav"));break;
                case "Sniper": playSfx(new File("./rsc/Audio/Sniper.wav"));break;
            }
        }
    }
}
