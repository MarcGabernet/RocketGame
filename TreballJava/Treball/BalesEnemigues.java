package Treball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BalesEnemigues extends Bales {
	
int pixelSize=super.pixelSize;
	
	BalesEnemigues(int x, int y, int vel){
		super.x=x-10*pixelSize;
		super.y=y;
		super.vel=-vel;
	}
	
	void pintarBalaE(Graphics g, boolean flameConunter) {
		pintarBala(g,Color.red, flameConunter);
	}
	
	Rectangle hitbox() {
		Rectangle r=super.hitbox(this.x,y);
		return r;
	}
	
}
