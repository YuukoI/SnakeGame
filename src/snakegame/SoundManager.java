package snakegame;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {

    public static void playSound(String soundFilePath, float volume) {
        try {
            URL soundUrl = SoundManager.class.getClassLoader().getResource(soundFilePath);
            if (soundUrl != null) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volume); // Ajusta el volumen aquí

                clip.start();
            } else {
                System.err.println("No se pudo encontrar el recurso: " + soundFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playBackgroundMusic(String musicFilePath, float volume) {
        try {
            URL musicUrl = SoundManager.class.getClassLoader().getResource(musicFilePath);
            if (musicUrl != null) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicUrl);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volume); // Ajusta el volumen aquí

                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.err.println("No se pudo encontrar el recurso: " + musicFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        playSound("resources/eating.wav", -35.0f);
        playBackgroundMusic("resources/background.wav", -20.0f);
    }
}
