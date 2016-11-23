package model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music implements Runnable  {
	SourceDataLine soundLine = null;
	int BUFFER_SIZE = 64*1024;
	File soundFile = new File("sound/Pacman.wav");

	private boolean onPause = false;
	@Override
	public void run() {
		while(!isOnPause()){
			try {

				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
				AudioFormat audioFormat = audioInputStream.getFormat();
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
				soundLine = (SourceDataLine) AudioSystem.getLine(info);
				soundLine.open(audioFormat);
				soundLine.start();
				int nBytesRead = 0;
				byte[] sampledData = new byte[BUFFER_SIZE];
				while (nBytesRead != -1 ) {
					while(isOnPause()){
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
					if (nBytesRead >= 0) {
						// Writes audio data to the mixer via this source data line.
						soundLine.write(sampledData, 0, nBytesRead);
					}
				}
			} catch (UnsupportedAudioFileException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (LineUnavailableException ex) {
				ex.printStackTrace();
			} finally {
				soundLine.drain();
				soundLine.close();
			}

		}
	}
	public boolean isOnPause() {
		return onPause;
	}
	public void setOnPause(boolean onPause) {
		this.onPause = onPause;
	}

}
