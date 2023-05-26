package Treball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BalesBones extends Bales {
	
	
	int pixelSize=super.pixelSize;
	
	BalesBones(int x, int y, int vel){
		this.x=x;
		this.y=y;
		this.vel=vel;
	}
	
	void pintarBalaBona(Graphics g, boolean flameConunter) {
		pintarBala(g,Color.yellow, flameConunter);
	}
	
	Rectangle hitbox() {
		Rectangle r=super.hitbox(x,y);
		return r;
	}
	
}
