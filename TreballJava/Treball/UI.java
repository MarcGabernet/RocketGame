package Treball;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class UI extends Objecte{
	
	int x,y;
	int pixelSize=4;
	Finestra f;
	
	void deadHeart(int x,int y, Graphics g) {
		g.setColor(Color.white);
		for(int i=0;i<3;i++) {
			pintarPixel(x,y,i+3,0,g,pixelSize);
		}
		for(int i=0;i<3;i++) {
			pintarPixel(x,y,i+9,0,g,pixelSize);
		}
		pintarPixel(x,y,2,1,g,pixelSize);
		pintarPixel(x,y,6,1,g,pixelSize);
		pintarPixel(x,y,8,1,g,pixelSize);
		pintarPixel(x,y,12,1,g,pixelSize);
		pintarPixel(x,y,1,2,g,pixelSize);
		pintarPixel(x,y,7,2,g,pixelSize);
		pintarPixel(x,y,13,2,g,pixelSize);
		for(int j=0;j<2;j++) {
			for(int i=0;i<4;i++) {
				pintarPixel(x,y,14*j,3+i,g,pixelSize);
			}
		}
		for(int j=0;j<6;j++) {
			for(int i=0;i<2;i++) {
				pintarPixel(x,y,(12-2*j)*i+1+j,7+j,g,pixelSize);
			}
		}
		pintarPixel(x,y,7,13,g,pixelSize);
		for(int i=0;i<3;i++) {
			pintarPixel(x,y,2+i,4-i,g,pixelSize);
		}
	}
	void fillHeart(int x,int y, Graphics g,Color c){
		g.setColor(c);
		for(int i=0;i<3;i++) {
			pintarPixel(x,y,i+3,1,g,pixelSize);
		}
		for(int i=0;i<3;i++) {
			pintarPixel(x,y,i+9,1,g,pixelSize);
		}
		for(int i=0;i<5;i++) {
			pintarPixel(x,y,i+2,2,g,pixelSize);
		}
		for(int i=0;i<5;i++) {
			pintarPixel(x,y,i+8,2,g,pixelSize);
		}
		for(int j=0;j<4;j++) {
			for(int i=0;i<13;i++) {
				pintarPixel(x,y,i+1,3+j,g,pixelSize);
			}
		}
		for(int j=0;j<6;j++) {
			for(int i=0;i<(11-2*j);i++) {
				pintarPixel(x,y,i+j+2,7+j,g,pixelSize);
			}
		}
		
		
	}
	
	void paintHeart(int x,int y, Graphics g,Color c) {
		fillHeart(x,y,g,c);
		deadHeart(x,y,g);	
	}
	
	void LivesPlayer(int x, int y, Graphics g,Color c,int Lives, int max_Lives) {
		for(int i=0;i<Lives;i++) {
			paintHeart(x+70*i,y,g,c);
		}for(int i=0;i<max_Lives-Lives;i++) {
			deadHeart(x+70*(Lives+i),y,g);
		}
	}
	
	void screenBounds(int y, Graphics g, Finestra f) {
		g.setColor(Color.white);
		g.drawLine(0, y, f.AMPLADA, y);
		g.drawLine(0, f.ALÇADA-y+20, f.AMPLADA, f.ALÇADA-y+20);
	}
	
	void star(int x, int y, Graphics g, int tamany) {
		g.setColor(Color.blue.darker().darker());
		for(int i=0;i<3;i++) {
			pintarPixel(x,y,i,2-i,g,pixelSize*tamany);
			pintarPixel(x,y,2+i,4-i,g,pixelSize*tamany);
		}
		pintarPixel(x,y,1,3,g,pixelSize*tamany);
		pintarPixel(x,y,3,1,g,pixelSize*tamany);
		
		g.setColor(Color.blue);
		for(int i=0;i<2;i++) {
			pintarPixel(x,y,i+1,2-i,g,pixelSize*tamany);
			pintarPixel(x,y,i+2,3-i,g,pixelSize*tamany);
		}
		
		g.setColor(Color.white);
		pintarPixel(x,y,2,2,g,pixelSize*tamany);
	}
	
	void starSky(int bound, Graphics g,Finestra f) {
		
		g.fillRect(0, bound, f.AMPLADA, f.ALÇADA-2*bound+20);
		
		int upperbound_x=f.AMPLADA-pixelSize*5;
		int upperbound_y=f.ALÇADA-pixelSize*5-2*bound+20;
		int upperbound2_x=f.AMPLADA-pixelSize*5*2;
		int upperbound2_y=f.ALÇADA-pixelSize*5*2-2*bound+20;
		int n_star1=40;
		int n_star2=3;
		int stars=n_star1+n_star2;
		
		Random rand_x = new Random();
		Random rand_y = new Random();
	    int[] arr_x = new int[stars];
	    int[] arr_y = new int[stars];
	    for (int i = 0; i < n_star1; i++) {
	         arr_x[i] = rand_x.nextInt(upperbound_x);
	         arr_y[i] = rand_y.nextInt(upperbound_y);
	         star(arr_x[i], arr_y[i]+bound, g, 1);
	      }
	    for (int i = n_star1; i < stars; i++) {
	         arr_x[i] = rand_x.nextInt(upperbound2_x);
	         arr_y[i] = rand_y.nextInt(upperbound2_y);
	         star(arr_x[i], arr_y[i]+bound, g, 2);
	      }
	    
	    
	}
	
	void backround(int bound, Graphics g,Finestra f) {
		g.setColor(Color.black);
		g.fillRect(0, 0, f.AMPLADA, f.ALÇADA);
		g.setColor(Color.blue.darker().darker().darker().darker());
		starSky(bound,g,f); //El canvi constant de la posició de les estrelles dona una sensació de velocitat
		//En cas que sembli molest simplement comenta la linea anterior i des-comenta la següent.
		//g.fillRect(0, bound, f.AMPLADA, f.ALÇADA-2*bound+20);
	}
	
	void stamina(int x, int y, Graphics g,Finestra f, int perc) {
		g.setColor(Color.yellow.darker());
		g.fillRect(x, y, perc, 40);
		g.setColor(Color.white);
		g.drawRect(x, y, 150, 40);
	}
	
	void title(Graphics g,Finestra f, int blink) {
		g.setColor(Color.red);
		g.setFont(new Font("Joystix",Font.ITALIC,90));
		g.drawString("ROCKET",270,300);
		g.setFont(new Font("Joystix",Font.ITALIC,175));
		g.drawString("WARS",200,440);
		if (blink<15) {
			g.setFont(new Font("Joystix",Font.PLAIN,25));
			g.drawString("PREM 1 PER 1 JUGADOR",280,570);
			g.drawString("PREM 2 PER 2 JUGADORS",270,620);
		}
	}
	
	void dificultat(Graphics g, Finestra f) {
		g.setColor(Color.red);
		g.setFont(new Font("Joystix",Font.PLAIN,40));
		g.drawString("SELECCIONA LA DIFICULTAT",95,250);
		g.setFont(new Font("Joystix",Font.ITALIC,30));
		g.drawString("1.FÀCIL",230,380);
		g.drawString("2.NORMAL",230,500);
		g.drawString("3.DIFÍCIL",230,620);
	}
	
	void replay(Graphics g, Finestra f) {
		g.setFont(new Font("Joystix",Font.PLAIN,35));
		g.drawString("TORNAR A JUGAR? (Y/N)",195,630);
	}
	
	void end(Graphics g, Finestra f) {
		g.setColor(Color.red);
		g.setFont(new Font("Joystix",Font.PLAIN,150));
		g.drawString("GAME",240,335);
		g.drawString("OVER!",195,510);
	}
	
	void rondaCounter(int x, int y, Graphics g, Finestra f, int ronda) {
		g.setColor(Color.red);
		g.setFont(new Font("Joystix",Font.PLAIN,40));
		g.drawString("RONDA: "+ronda,x,y);
	}
	
	
}
