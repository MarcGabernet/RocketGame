package Treball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Asteroide extends Objecte{
	
	int tamany=4;
	
	Asteroide(int x, int y,int vel,int tamany){
		super.x=x;
		super.y=y;
		super.vel=-vel;
		this.tamany=tamany;
	}
	
	void pintarAsteroide(Graphics g) {
		
		g.setColor(Color.darkGray);
		for(int j=0;j<7;j++) {
			for(int i=0;i<13;i++) {
				pintarPixel(x,y,i+1,4+j,g,tamany);
			}
		}
		pintarPixel(x,y,5,3,g,tamany);
		for(int i=0;i<3;i++) {
			pintarPixel(x,y,i+9,11,g,tamany);
		}
		
		g.setColor(Color.lightGray);
		for(int j=0;j<4;j++) {
			for(int i=0;i<6;i++) {
				pintarPixel(x,y,6+i,2+j,g,tamany);
			}
		}
		
		g.setColor(Color.gray);
		for(int i=0;i<4;i++) {
			pintarPixel(x,y,i+7,1,g,tamany);
		}
		pintarPixel(x,y,5,2,g,tamany);
		pintarPixel(x,y,6,2,g,tamany);
		pintarPixel(x,y,11,2,g,tamany);
		
		pintarPixel(x,y,3,3,g,tamany);
		pintarPixel(x,y,4,3,g,tamany);

		pintarPixel(x,y,3,4,g,tamany);
		pintarPixel(x,y,5,4,g,tamany);
		pintarPixel(x,y,6,4,g,tamany);
		
		pintarPixel(x,y,4,5,g,tamany);
		for(int i=0;i<3;i++) {
			pintarPixel(x,y,i+6,5,g,tamany);
		}
		for(int i=0;i<5;i++) {
			pintarPixel(x,y,i+6,6,g,tamany);
		}
		pintarPixel(x,y,7,7,g,tamany);
		pintarPixel(x,y,8,7,g,tamany);
		for(int j=0;j<2;j++) {
			for(int i=0;i<3;i++) {
				pintarPixel(x,y,i+10-j,7+j,g,tamany);
			}
		}
		for(int i=0;i<4;i++) {
			pintarPixel(x,y,12,3+i,g,tamany);
		}
		pintarPixel(x,y,13,6,g,tamany);
		
		for(int k=0;k<2;k++) {
			for(int j=0;j<2;j++) {
				for(int i=0;i<2;i++) {
					pintarPixel(x,y,2+i+k,7+j+k,g,tamany);
				}
			}
		}
		
		
		g.setColor(Color.darkGray.darker().darker());
		for(int i=0;i<4;i++) {
			pintarPixel(x,y,7+i,0,g,tamany);
			pintarPixel(x,y,2+i,11,g,tamany);
			pintarPixel(x,y,14,6+i,g,tamany);
		}
		for(int i=0;i<5;i++) {
			pintarPixel(x,y,0,5+i,g,tamany);
		}
		for(int j=0;j<2;j++) {
			for(int i=0;i<2;i++) {
				pintarPixel(x,y,3+i+2*j,2-j,g,tamany);
			}
			pintarPixel(x,y,6+j,10,g,tamany);
		}
		for(int i=0;i<3;i++) {
			pintarPixel(x,y,9+i,12,g,tamany);
			pintarPixel(x,y,13,3+i,g,tamany);
		}
		for(int i=0;i<2;i++) {
			pintarPixel(x,y,1+i,4-i,g,tamany);
			pintarPixel(x,y,11+i,1+i,g,tamany);
			pintarPixel(x,y,12+i,11-i,g,tamany);
			pintarPixel(x,y,1+7*i,10+i,g,tamany);
		}
		
	}

	Rectangle hitbox1() {
		Rectangle r= new Rectangle(x+tamany,y+tamany*4, tamany*13, tamany*7);
		return r;
	}
	
	Rectangle hitbox2() {
		Rectangle r= new Rectangle(x+tamany*5,y+tamany, tamany*7, tamany*10);
		return r;
	}
}
