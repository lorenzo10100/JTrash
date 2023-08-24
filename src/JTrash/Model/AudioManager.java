package JTrash.Model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;

/**
 * Classe che gestisce l'audio del gioco
 */
public class AudioManager {

    /**
     * Istanza della classe
     */
    private static AudioManager instance;

    /**
     * Clip audio
     */
    private Clip clip;

    /**
     * Nome del file audio per distinguere i vari effetti
     */
    private String sound;

    /**
     * Percorso della cartella contenente i file audio
     */
    private final String path = "sounds/";

    /**
     * Percorso del file audio per la musica di sottofondo
     */
    private final String music = path + "background.wav";

    /**
     * Metodo che restituisce l'istanza della classe
     *
     * @return Istanza della classe
     */
    public static AudioManager getInstance() {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    /**
     * Costruttore privato
     */
    private AudioManager() {
        playMusic(music);
    }

    /**
     * Metodo che riproduce la musica di sottofondo
     *
     * @param filename Percorso del file audio
     */
    public void playMusic(String filename) {

        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filename));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    playMusic(filename);
                }
            });
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
            e1.printStackTrace();
        }
    }


    /**
     * Metodo che riproduce gli effetti sonori
     *
     * @param effect effetto da riprodurre
     */
    public void playCardEffect(String effect) {
        if (effect.equals("carta"))
            sound = "card.wav";
        else if (effect.equals("vittoria"))
            sound = "winner.wav";
        else
            sound = "loser.wav";
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(path + sound));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
            e1.printStackTrace();
        }
    }
}