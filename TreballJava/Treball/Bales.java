package Treball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bales extends Objecte {
	int x=super.x,y=super.y,vel=super.vel;
	int pixelSize=3;
	
	void pintarBala1(Graphics g, Color c) {
		g.setColor(c.darker());
		for(int i=0;i<8;i++) {
			pintarPixel(x,y,1+i,0,g,pixelSize);
			pintarPixel(x,y,1+i,2,g,pixelSize);
		}
		pintarPixel(x,y,0,1,g,pixelSize);
		pintarPixel(x,y,9,1,g,pixelSize);
		
		g.setColor(c);
		for(int i=0;i<8;i++) {
			pintarPixel(x,y,1+i,1,g,pixelSize);
		}
	}
	
	void pintarBala2(Graphics g, Color c) {
		g.setColor(c.darker());
		for(int i=0;i<8;i++) {
			pintarPixel(x,y,1+i,1,g,pixelSize);
		}
	}
	
	void pintarBala(Graphics g, Color c, boolean flameConunter) {
		if(flameConunter==true) {
			pintarBala1(g, c);
		}else {
			pintarBala2(g, c);
		}
	}
	
	void moureB(Finestra f) {
		x+=vel;
	}
	
	Rectangle hitbox(int x, int y) {
		Rectangle r= new Rectangle(x,y, pixelSize*10,pixelSize*3);
		return r;
	}
	
}

