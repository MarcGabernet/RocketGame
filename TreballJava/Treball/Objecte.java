package Treball;

import java.awt.Graphics;

public class Objecte {
	
	int x,y,vel;
	int bounds=140; 
	
	void pintarPixel(int x, int y,int pos_x, int pos_y, Graphics g, int pixelSize) {
		g.fillRect(x+pixelSize*pos_x, y+pixelSize*pos_y, pixelSize, pixelSize);
	}
	
	void moure(Finestra f) {
		x+=vel;
	}
	
}
