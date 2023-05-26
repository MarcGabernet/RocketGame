package Treball;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

import javax.swing.JFrame;

public class Finestra extends JFrame implements KeyListener,WindowListener{
	
	Graphics g;
	Joc j;
	Image im;
	int blink=0;
	
	int Players=0,Dif=0;
	
	int AMPLADA=1000,ALÇADA=800;
	
	boolean dreta=false,esq=false,dalt=false,baix=false;
	boolean dreta2=false,esq2=false,dalt2=false,baix2=false;
	boolean bala=false,bala2=false;
	
	int replay=1;

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new Finestra();
	}
	Finestra() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		
		super("TREBALL JAVA");
		
		File intro= new File("intro.wav");
		File loop= new File("loop.wav");
		AudioInputStream audioStream_intro= AudioSystem.getAudioInputStream(intro);
		AudioInputStream audioStream_loop= AudioSystem.getAudioInputStream(loop);
		Clip clip_intro=AudioSystem.getClip();
		Clip clip_loop=AudioSystem.getClip();
		clip_intro.open(audioStream_intro);
		clip_loop.open(audioStream_loop);
		
		
		addWindowListener(this);
		addKeyListener(this);
		setSize(AMPLADA,ALÇADA);
		j=new Joc(this);
		setVisible(true);
		im=createImage(AMPLADA,ALÇADA);
		g=im.getGraphics();
		
		while(replay==1) {
			clip_loop.setMicrosecondPosition(0);
			clip_loop.loop(Clip.LOOP_CONTINUOUSLY);
			while(Players==0) {
				j.pantallaInici(blink);
				if(blink<30) {
					blink++;
				}else {
					blink=0;
				}
			}
			
			while(Dif==0) {
				j.selectDifficulty();
			}
			clip_loop.stop();
			clip_intro.setMicrosecondPosition(0);
			clip_intro.start();
			j.transicio();
			clip_loop.setMicrosecondPosition(0);
			clip_loop.loop(Clip.LOOP_CONTINUOUSLY);
			if(Players==1) {
				j.run(j.n1,7-2*Dif);
			}else {
				j.run(j.n2,7-2*Dif);
			}
			replay=0;
			while(replay==0) {
				clip_loop.stop();
				j.u.end(g, this);
				Players=0;
				Dif=0;
			}
		}
		System.exit(0); 
	}
	
	public void paint(Graphics g) {
		g.drawImage(im, 0, 0, null);
	}
	public void update(Graphics g) {
		paint(g);
	}
	
	public void keyTyped(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT: esq=true;
			break;
		case KeyEvent.VK_DOWN: baix=true;
			break;
		case KeyEvent.VK_RIGHT: dreta=true;
			break;
		case KeyEvent.VK_UP: dalt=true;
			break;
		case KeyEvent.VK_SPACE: bala=true;
		}
		switch(e.getKeyChar()) {
		case 'd': dreta2=true;
			break;
		case 's': baix2=true;
			break;
		case 'a': esq2=true;
			break;
		case 'w': dalt2=true;
			break;
		case 'f': bala2=true;
			break;
		case 'y': replay=1;
			break;
		case 'n': replay=2;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT: esq=false;
			break;
		case KeyEvent.VK_DOWN: baix=false;
			break;
		case KeyEvent.VK_RIGHT: dreta=false;
			break;
		case KeyEvent.VK_UP: dalt=false;
			break;
		case KeyEvent.VK_SPACE: bala=false;
		}
		switch(e.getKeyChar()) {
		case 'd': dreta2=false;
			break;
		case 's': baix2=false;
			break;
		case 'a': esq2=false;
			break;
		case 'w': dalt2=false;
			break;
		case 'f': bala2=false;
		}
		
		if(Players==0) {
			switch(e.getKeyChar()) {
			case '1': Players=1;
				break;
			case '2': Players=2;
			}
		}else {
			switch(e.getKeyChar()) {
			case '1': Dif=1;
				break;
			case '2': Dif=2;
				break;
			case '3': Dif=3;
			}
		}
	}
	
	public void windowOpened(WindowEvent e) {}
	
	public void windowClosing(WindowEvent e) {
		System.exit(0); 
	}
	
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}
