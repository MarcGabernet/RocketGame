package Treball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class NauEnemiga extends Objecte{
	
	int vel_v=20;
	int pixelSize=3;
	int vides;
	
	NauEnemiga(int x, int y, int vel, int vides){
		super.x=x;
		super.y=y;
		super.vel=-vel;
		this.vides=vides;
	}
	
	void novaBala(Finestra f, ArrayList<BalesEnemigues> be) {
		be.add(new BalesEnemigues(x,y+pixelSize*10,40));
	}
	
	Rectangle hitbox_cos() {
		Rectangle r= new Rectangle(x+pixelSize*2,y+pixelSize*8, pixelSize*13, pixelSize*7);
		return r;
	}
	
	Rectangle hitbox_ales() {
		Rectangle r= new Rectangle(x+pixelSize*11,y+pixelSize*2, pixelSize*5, pixelSize*19);
		return r;
	}
	
	Rectangle hitbox_ales2() {
		Rectangle r= new Rectangle(x+pixelSize*8,y+pixelSize*5, pixelSize*8, pixelSize*13);
		return r;
	}
	
	void moureVerticalment(Finestra f) {
		y-=vel_v;
		if(y<bounds+10 || y>f.ALÇADA-bounds-pixelSize*23) {
			vel_v=-vel_v;
		}
	}
	
	void focNauE(int x, int y, Graphics g) {
		g.setColor(Color.red);
		for(int i=0;i<2;i++){
			pintarPixel(x,y,0,3*i,g,pixelSize);
		}
		g.setColor(Color.orange);
		for(int i=0;i<2;i++){
			pintarPixel(x,y,0,i+1,g,pixelSize);
		}
		for(int i=0;i<2;i++){
			pintarPixel(x,y,1,3*i,g,pixelSize);
		}
		
		g.setColor(Color.yellow.darker().darker());
		for(int i=0;i<2;i++){
			pintarPixel(x,y,1,i+1,g,pixelSize);
		}
		for(int i=0;i<2;i++){
			pintarPixel(x,y,2,3*i,g,pixelSize);
		}
		
	}
		
	void focNauE2(int x, int y, Graphics g) {
		g.setColor(Color.red);
		for(int i=0;i<2;i++){
			pintarPixel(x,y,0,3*i,g,pixelSize);
		}
		for(int i=0;i<2;i++){
			pintarPixel(x,y,1,1+i,g,pixelSize);
		}
		
		g.setColor(Color.orange);
		for(int i=0;i<2;i++){
			pintarPixel(x,y,0,1+i,g,pixelSize);
		}
		
	}
	
	void pintarNE(Graphics g, boolean flameCounter) {
		
		g.setColor(Color.lightGray);
		for(int j=0;j<5;j++) {
			for(int i=0;i<11;i++) {
				pintarPixel(x,y,3+i,9+j,g,pixelSize);
			}
		}
		
		g.setColor(Color.cyan.darker());
		for(int j=0;j<3;j++) {
			for(int i=0;i<3;i++) {
				pintarPixel(x,y,2+i,10+j,g,pixelSize);
			}
		}
		g.setColor(Color.lightGray);
		pintarPixel(x,y,1,11,g,pixelSize);
		for(int i=0;i<2;i++) {
			pintarPixel(x,y,2,10+2*i,g,pixelSize);
		}
		for(int j=0;j<4;j++) {
			for(int i=0;i<6;i++) {
				pintarPixel(x,y,8+i,5+j,g,pixelSize);
				pintarPixel(x,y,8+i,14+j,g,pixelSize);
			}
		}
		for(int j=0;j<3;j++) {
			for(int i=0;i<3;i++) {
				pintarPixel(x,y,11+i,2+j,g,pixelSize);
				pintarPixel(x,y,11+i,18+j,g,pixelSize);
			}
		}
		
		g.setColor(Color.darkGray);
		pintarPixel(x,y,0,11,g,pixelSize);
		for(int i=0;i<2;i++) {
			pintarPixel(x,y,1+i,10-i,g,pixelSize);
			pintarPixel(x,y,1+i,12+i,g,pixelSize);
		}
		for(int i=0;i<5;i++) {
			pintarPixel(x,y,3+i,8,g,pixelSize);
			pintarPixel(x,y,3+i,14,g,pixelSize);
			pintarPixel(x,y,7+i,11,g,pixelSize);
		}
		for(int j=0;j<2;j++) {
			for(int i=0;i<7;i++) {
				pintarPixel(x,y,7+i,6+j-i,g,pixelSize);
				pintarPixel(x,y,7+i,15+j+i,g,pixelSize);
			}
		}
		for(int i=0;i<23;i++) {
			pintarPixel(x,y,14,i,g,pixelSize);
		}
		for(int j=0;j<3;j++) {
			for(int i=0;i<3;i++) {
				pintarPixel(x,y,13,5+5*j+i,g,pixelSize);
			}
		}
		for(int k=0;k<2;k++) {
			for(int j=0;j<2;j++) {
				for(int i=0;i<4;i++) {
					pintarPixel(x,y,15,1+11*k+6*j+i,g,pixelSize);
				}
			}
		}
		if(flameCounter==true) {
			for(int i=0;i<2;i++) {
				focNauE(x+16*pixelSize,y+pixelSize*(1+6*i),g);
				focNauE(x+16*pixelSize,y+pixelSize*(12+6*i),g);
			}
		}else{
			for(int i=0;i<2;i++) {
				focNauE2(x+16*pixelSize,y+pixelSize*(1+6*i),g);
				focNauE2(x+16*pixelSize,y+pixelSize*(12+6*i),g);
			}
		}	
	}
}
