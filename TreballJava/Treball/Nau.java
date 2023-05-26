package Treball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Nau extends Objecte{
	
	Color d;
	int vides;
	
	int pixelSize=4;
	Joc p;

	Nau(int x, int y, int vel, Color d, int vides) {
		super.x = x;
		super.y = y;
		super.vel = vel;
		this.d=d;
		this.vides=vides;
	}
	
	Rectangle outBounds_UP(Finestra f) {
		Rectangle r=new Rectangle(-100,0,f.AMPLADA+200,bounds+15);
		return r;
	}
	Rectangle outBounds_DOWN(Finestra f) {
		Rectangle r=new Rectangle(-100,f.ALÇADA-bounds+10,f.AMPLADA+200,bounds);
		return r;
	}
	Rectangle outBounds_LEFT(Finestra f) {
		Rectangle r=new Rectangle(-100,0,110,f.ALÇADA);
		return r;
	}
	Rectangle outBounds_RIGHT(Finestra f) {
		Rectangle r=new Rectangle(f.AMPLADA-10,0,100,f.ALÇADA);
		return r;
	}
	
	void moureNau(Finestra f) {
		
		int mov=pixelSize*vel/10;
		
		if(d==Color.orange) {
			if(hitbox_ales().intersects(outBounds_UP(f))) {
				f.dalt=false;
			}if(hitbox_ales().intersects(outBounds_DOWN(f))) {
				f.baix=false;
			}if(hitbox_ales().intersects(outBounds_LEFT(f))) {
				f.esq=false;
			}if(hitbox_cos().intersects(outBounds_RIGHT(f))) {
				f.dreta=false;
			}
			if(f.dreta==true) {
				x+=mov;
			}if(f.esq==true) {
				x-=mov;
			}if(f.baix==true) {
				y+=mov;
			}if(f.dalt==true) {
				y-=mov;
			}
		}
		if(d==Color.green) {
			if(hitbox_ales().intersects(outBounds_UP(f))) {
				f.dalt2=false;
			}if(hitbox_ales().intersects(outBounds_DOWN(f))) {
				f.baix2=false;
			}if(hitbox_ales().intersects(outBounds_LEFT(f))) {
				f.esq2=false;
			}if(hitbox_cos().intersects(outBounds_RIGHT(f))) {
				f.dreta2=false;
			}
			if(f.dreta2==true) {
				x+=mov;
			}if(f.esq2==true) {
				x-=mov;
			}if(f.baix2==true) {
				y+=mov;
			}if(f.dalt2==true) {
				y-=mov;
			}
		}
	}
	
	int[] novaBala(Finestra f, ArrayList<BalesBones> bb, int[] perc) {
		if(vides!=0) {
			if(d==Color.orange) {
				if(f.bala==true && perc[0]>5) {
					bb.add(new BalesBones(x+pixelSize*8,y+pixelSize*6,45));
					if(perc[0]>0) {
						perc[0]=perc[0]-5;
					}
				}
			}
			if(d==Color.green) {
				if(f.bala2==true  && perc[1]>5) {
					bb.add(new BalesBones(x+pixelSize*8,y+pixelSize*6,45));
					if(perc[1]>0) {
						perc[1]=perc[1]-5;
					}
				}
			}
		}
		return perc;
	}
	
	Rectangle hitbox_cos() {
		Rectangle r= new Rectangle(x,y+pixelSize*3, pixelSize*14, pixelSize*8);
		return r;
	}
	
	Rectangle hitbox_ales() {
		Rectangle r= new Rectangle(x,y+pixelSize, pixelSize*6, pixelSize*12);
		return r;
	}
	
	void focNau(int x, int y, Graphics g) {
		g.setColor(Color.red);
		for(int i=0;i<2;i++){
			pintarPixel(x,y,-3-i,5,g,pixelSize);
			pintarPixel(x,y,-3-i,10,g,pixelSize);
		}
		for(int i=0;i<2;i++){
			pintarPixel(x,y,-2,6+3*i,g,pixelSize);
		}
		pintarPixel(x,y,-5,6,g,pixelSize);
		pintarPixel(x,y,-5,9,g,pixelSize);
		pintarPixel(x,y,-6,7,g,pixelSize);
		pintarPixel(x,y,-6,8,g,pixelSize);
		
		g.setColor(Color.orange);
		for(int i=0;i<2;i++){
			pintarPixel(x,y,-3-i,6,g,pixelSize);
			pintarPixel(x,y,-3-i,9,g,pixelSize);
		}
		for(int i=0;i<2;i++){
			pintarPixel(x,y,-2,7+i,g,pixelSize);
			pintarPixel(x,y,-5,7+i,g,pixelSize);
		}
		
		g.setColor(Color.yellow);
		for(int j=0;j<2;j++) {
			for(int i=0;i<2;i++) {
				pintarPixel(x,y,-3-i,7+j,g,pixelSize);
			}
		}
	}
	
	void focNau2(int x, int y, Graphics g) {
		g.setColor(Color.red);
		for(int i=0;i<3;i++){
			pintarPixel(x,y,-2-i,6,g,pixelSize);
			pintarPixel(x,y,-2-i,9,g,pixelSize);
		}
		for(int i=0;i<2;i++){
			pintarPixel(x,y,-2,6+3*i,g,pixelSize);
		}
		
		for(int i=0;i<2;i++){
			pintarPixel(x,y,-5,7+i,g,pixelSize);
		}
		
		g.setColor(Color.orange);
		for(int j=0;j<2;j++) {
			for(int i=0;i<3;i++) {
				pintarPixel(x,y,-2-i,7+j,g,pixelSize);
			}
		}
	}
	
	void pintarNau(Graphics g,boolean flameCounter, int immunity) {
		g.setColor(Color.red);
		for(int j=0;j<3;j++) {
			for(int i=0;i<5;i++) {
				pintarPixel(x,y,i+j,j,g,pixelSize);
			}
		}
		for(int j=0;j<3;j++) {
			for(int i=0;i<5;i++) {
				pintarPixel(x,y,i+j,13-j,g,pixelSize);
			}
		}
		for(int j=0;j<8;j++) {
			for(int i=0;i<3;i++) {
				pintarPixel(x,y,10+i,3+j,g,pixelSize);
			}
		}
		for(int i=0;i<6;i++) {
			pintarPixel(x,y,13,4+i,g,pixelSize);
		}
		for(int i=0;i<4;i++) {
			pintarPixel(x,y,14,5+i,g,pixelSize);
		}
		for(int i=0;i<2;i++) {
			pintarPixel(x,y,15,6+i,g,pixelSize);
		}
		if(immunity==0 || immunity==51) {
			g.setColor(d);
		}else {
			g.setColor(Color.white);
		}
		
		for(int j=0;j<8;j++) {
			for(int i=0;i<9;i++) {
				pintarPixel(x,y,2+i,3+j,g,pixelSize);
			}
		}
		
		g.setColor(Color.gray.darker());
		for(int j=0;j<6;j++) {
			for(int i=0;i<2;i++) {
				pintarPixel(x,y,i,4+j,g,pixelSize);
			}
		}
		for(int i=0;i<4;i++) {
			pintarPixel(x,y,-1,5+i,g,pixelSize);
		}
		pintarPixel(x,y,5,6,g,pixelSize);
		pintarPixel(x,y,5,7,g,pixelSize);
		pintarPixel(x,y,6,5,g,pixelSize);
		pintarPixel(x,y,6,8,g,pixelSize);
		pintarPixel(x,y,7,5,g,pixelSize);
		pintarPixel(x,y,7,8,g,pixelSize);
		pintarPixel(x,y,8,6,g,pixelSize);
		pintarPixel(x,y,5,7,g,pixelSize);
		pintarPixel(x,y,8,7,g,pixelSize);
		
		g.setColor(Color.cyan.darker());
		for(int j=0;j<2;j++) {
			for(int i=0;i<2;i++) {
				pintarPixel(x,y,6+i,6+j,g,pixelSize);
			}
		}
		if(flameCounter==true) {
			focNau(x,y-pixelSize*1,g);
		}else{
			focNau2(x,y-pixelSize*1,g);
		}
		
	}
}
