package Treball;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Joc {
	
	Finestra f;
	
	UI u= new UI();
	
	Nau n1[]=new Nau[1];
	Nau n2[]=new Nau[2];
	
	Joc(Finestra f){
		this.f=f;
	}
	
	void asteroidSpawn(ArrayList<Asteroide> a) {
		
		int n_asteroides=70;
		
		int upperbound_x=10*f.AMPLADA;
		int upperbound_y=f.ALÇADA-2*u.bounds-10*8;
		int upperbound_mida=7;
		
		Random rand_x = new Random();
		Random rand_y = new Random();
		Random rand_mida = new Random();
		for (int i = 0; i < n_asteroides; i++) {
			a.add(new Asteroide(f.AMPLADA+rand_x.nextInt(upperbound_x),u.bounds+rand_y.nextInt(upperbound_y),25,3+rand_mida.nextInt(upperbound_mida)));
	      }
	}
	void asteroid_deSpawn(ArrayList<Asteroide> a) {
		for(int i=0;i<a.size();i++) {
			if(a.get(i).x<-200) {
				a.remove(i);
			}
		}
	}
	
	void enemySpawn(ArrayList<NauEnemiga> ne, int nauE_vides) {
		int[] y=new int[7];
		y[0]=155;y[1]=155+70*5;y[2]=155+70*3;y[3]=155+70*1;y[4]=155+70*6;y[5]=155+70*4;y[6]=155+70*2;
		for(int i=0;i<7;i++) {
			ne.add(new NauEnemiga(f.AMPLADA+(6-i)*90,y[i],20,nauE_vides));
		}
	}
	void enemy_deSpawn(ArrayList<NauEnemiga> ne, ArrayList<BalesBones> bb) {//també elimina bales
		
		for (int j = 0; j<ne.size(); j++) {
			for (int i = 0; i<bb.size(); i++) {
				if(bb.get(i).hitbox().intersects(ne.get(j).hitbox_cos()) ||
				   bb.get(i).hitbox().intersects(ne.get(j).hitbox_ales()) ||
				   bb.get(i).hitbox().intersects(ne.get(j).hitbox_ales2())) {
					ne.get(j).vides--;
					if(ne.get(j).vides==0) {
						ne.remove(j);
					}
					bb.remove(i);
					break;
				}
			}
		}
	}
	
	
	int[] balaBSpawn(Nau n[], ArrayList<BalesBones> bb,int[] perc) {
		for(int i=0;i<n.length;i++) {
			perc=n[i].novaBala(f, bb,perc);
		}
		return perc;
	}
	void balaB_deSpawn(ArrayList<BalesBones> bb, ArrayList<Asteroide> a) {
		
		for (int j = 0; j<a.size(); j++) {
			for (int i = 0; i<bb.size(); i++) {
				if(bb.get(i).hitbox().intersects(a.get(j).hitbox1()) ||
					bb.get(i).hitbox().intersects(a.get(j).hitbox2())) {
					bb.remove(i);
					break;
				}
			}
		}
		for (int i = 0; i<bb.size(); i++) {
			if(bb.get(i).x>f.AMPLADA+100) {
				bb.remove(i);
			}
		}
	}
	
	
	void balaESpawn(ArrayList<NauEnemiga> ne,ArrayList<BalesEnemigues> be, int dif) {
		
		int one_in_x=60-5*dif;
		int x;
		Random chance = new Random();
		for(int i=0;i<ne.size();i++) {
			x=chance.nextInt(one_in_x);
			if(x==1) {
				be.add(new BalesEnemigues(ne.get(i).x+5,ne.get(i).y+11*3,30));
			}
		}
	}
	void balaE_deSpawn(Nau n[],ArrayList<BalesEnemigues> be) {
		
		for (int j = 0; j<n.length; j++) {
			for (int i = 0; i<be.size(); i++) {
				if(be.get(i).hitbox().intersects(n[j].hitbox_cos()) ||
					be.get(i).hitbox().intersects(n[j].hitbox_ales())) {
					be.remove(i);
					break;
				}
			}
		}
		for (int i = 0; i<be.size(); i++) {
			if(be.get(i).x<-100) {
				be.remove(i);
			}
		}
		
	}
	
	void run(Nau n[], int vides) {
		
		ArrayList<Asteroide> asteroide=new ArrayList<Asteroide>();
		ArrayList<NauEnemiga> enemic=new ArrayList<NauEnemiga>();
		ArrayList<BalesBones> balesB=new ArrayList<BalesBones>();
		ArrayList<BalesEnemigues> balesE=new ArrayList<BalesEnemigues>();
		
		boolean phase=true;
		boolean continuar=true;
		
		int ronda=0;
		int[] perc=new int[2];
		perc[0]=150;
		perc[1]=150;
		
		int[] ronda_final = new int[2];
		ronda_final[0]=0;
		ronda_final[1]=0;
		
		int nE_vides=5*f.Dif;
		int freq_bales=f.Dif;
		
		boolean flameCounter=true;
		int[] immunity = new int[2];
		immunity[0]=0;
		immunity[1]=0;
		if(n.length==1) {
			immunity[1]=51;
		}
		
		n[0]=new Nau(50,300,35  ,Color.orange,vides);
		if(n.length==2) {
			n[1]=new Nau(50,450,n[0].vel,Color.green,vides);
		}
		while(continuar==true) {
			
			//Creació de tots els objectes de la pantalla
			if(asteroide.size()==0 && enemic.size()==0 && phase==false) {
				enemySpawn(enemic,nE_vides+(ronda-2));
				ronda++;
			}
			if(asteroide.size()==0 && enemic.size()==0 && phase==true) {
				asteroidSpawn(asteroide);
				ronda++;
			}
			perc=balaBSpawn(n, balesB,perc);
			balaESpawn(enemic,balesE,freq_bales);
			
			//Canvi de posició dels elements
			moureElements(n,asteroide,balesB,balesE,enemic);
			
			//Detecció de col·lisió si la nau no té immunitat
			for(int i=0;i<n.length;i++) {
				immunity[i]=detectarColisions(n[i],asteroide,enemic,balesE,immunity[i]);
				if(n[i].vides!=0) {
					ronda_final[i]=ronda;
				}
			}
			
			if(immunity[0]==51 && immunity[1]==51) {
				continuar=false;
			}else{
				continuar=true;
			}
			
			//Eliminació dels objectes no desitjats
			if(asteroide.size()>0) {
				asteroid_deSpawn(asteroide);
				if(asteroide.size()==0) {
					phase=false;
				}
			}
			if(enemic.size()>0) {
				enemy_deSpawn(enemic, balesB);
				if(enemic.size()==0) {
					phase=true;
				}
			}
			balaB_deSpawn(balesB,asteroide);
			balaE_deSpawn(n,balesE);
			if(perc[0]<150) {
				perc[0]++;
			}
			if(perc[1]<150) {
				perc[1]++;
			}
			
			repintarPantalla(ronda_final[0],ronda_final[1], flameCounter,immunity[0],immunity[1],perc,n,vides,asteroide,balesB,balesE,enemic);
			
			if(flameCounter==true) {
				flameCounter=false;
			}else{
				flameCounter=true;
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	void loseLife(Nau n) {
		n.vides--;
		if(n.vides!=0) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	int detectarColisions(Nau n,ArrayList<Asteroide> a, ArrayList<NauEnemiga> ne, ArrayList<BalesEnemigues> be, int immunity) {
		if(immunity==51) {
			return 51;
		}
		if(immunity>0) {
			immunity+=1;
			if(immunity==50) {
				immunity=0;
			}
			return immunity;
		}
		for(int j=0;j<a.size();j++) { //ASTEROIDES
			if(n.hitbox_cos().intersects(a.get(j).hitbox1()) || n.hitbox_ales().intersects(a.get(j).hitbox1()) || 
					   n.hitbox_cos().intersects(a.get(j).hitbox2()) || n.hitbox_ales().intersects(a.get(j).hitbox2())	) {
						if(n.vides>0) {
							loseLife(n);
							if(immunity==0) {
								immunity++;
							}
							if(n.vides==0) {
								return 51;
							}else {
								return 1;
							}
						}
					}
		}
		for(int j=0;j<ne.size();j++) { //NAU ENEMIGA
			if(n.hitbox_cos().intersects(ne.get(j).hitbox_cos()) || n.hitbox_ales().intersects(ne.get(j).hitbox_cos()) || 
					   n.hitbox_cos().intersects(ne.get(j).hitbox_ales()) || n.hitbox_ales().intersects(ne.get(j).hitbox_ales()) ||
					   n.hitbox_cos().intersects(ne.get(j).hitbox_ales2()) || n.hitbox_ales().intersects(ne.get(j).hitbox_ales2())) {
						if(n.vides>0) {
							loseLife(n);
							if(n.vides==0) {
								return 51;
							}else {
								return 1;
							}
						}
					}
		}
		for(int j=0;j<be.size();j++) { //BALA ENEMIGA
			if(n.hitbox_cos().intersects(be.get(j).hitbox()) || n.hitbox_ales().intersects(be.get(j).hitbox())) {
				if(n.vides>0) {
					loseLife(n);
					if(n.vides==0) {
						return 51;
					}else {
						return 1;
					}
				}
			}
		}
		return 0;
	}
	
	
	void moureArmy(ArrayList<NauEnemiga> ne) {
		
		boolean stop_army=false;
		
		for(int j=0;j<ne.size();j++) {
			if(ne.get(j).x<=350) {
				stop_army=true;
			}
		}
		if(stop_army==false) {
			for(int i=0;i<ne.size();i++) {
				ne.get(i).moure(f);
			}
		}
		
	}
	
	void moureElements(Nau n[],ArrayList<Asteroide> a, ArrayList<BalesBones> bb,ArrayList<BalesEnemigues> be, ArrayList<NauEnemiga> ne) {
		
		for(Nau naus:n) {
			if(naus.vides>0) {
				naus.moureNau(f);
			}
		}
		for(int i=0;i<a.size();i++) {
			a.get(i).moure(f);
		}
		for(int i=0;i<bb.size();i++) {
			bb.get(i).moureB(f);
		}
		for(int i=0;i<be.size();i++) {
			be.get(i).moureB(f);
		}
		
		moureArmy(ne);
		
		boolean stop_army=false;
		
		for(int i=0;i<ne.size();i++) {
			if(ne.get(i).x<=350) {
				stop_army=true;
			}
		}if(stop_army==true) {
			for(int i=0;i<ne.size();i++) {
				ne.get(i).moureVerticalment(f);
			}
		}
	}
	
	void pantallaInici(int blink) {
		u.backround(0, f.g, f);
		u.title(f.g, f,blink);
		f.repaint();
		try {
			Thread.sleep(45);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void selectDifficulty() {
		u.backround(0, f.g, f);
		u.dificultat(f.g, f);
		f.repaint();
		try {
			Thread.sleep(45);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void transicio() {
		for(double i=0;i<u.bounds;i=i+1.37) {
			u.backround((int)i, f.g, f);
			u.screenBounds((int)i, f.g, f);
			f.repaint();
			try {
				Thread.sleep(45);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	void repintarPantalla(int ronda,int ronda2, boolean flameCounter,int immunity,int immunity2,int[] perc, Nau n[], int vides,ArrayList<Asteroide> a,ArrayList<BalesBones> bb,ArrayList<BalesEnemigues> be, ArrayList<NauEnemiga> ne) {
		
		u.backround(u.bounds, f.g, f);
		u.screenBounds(u.bounds, f.g, f);
		if(immunity!=51) {
			n[0].pintarNau(f.g,flameCounter,immunity);
			u.stamina(430,65, f.g, f,perc[0]);
		}
		if(n.length==1) {
			u.LivesPlayer(30,60,f.g,Color.red,n[0].vides,vides);
		}
		if(n.length==2) {
			u.LivesPlayer(30,60,f.g,Color.orange.darker(),n[0].vides,vides);
			u.LivesPlayer(30,710,f.g,Color.green.darker(),n[1].vides,vides);
			if(immunity2!=51) {
				n[1].pintarNau(f.g,flameCounter,immunity2);
				u.stamina(430, 720,f.g, f,perc[1]);
			}
		}
		u.rondaCounter(650, 100, f.g, f, ronda);
		if(n.length==2) {
			u.rondaCounter(650,750,f.g, f, ronda2);
		}
		for(int i=0;i<a.size();i++) {
			a.get(i).pintarAsteroide(f.g);
		}
		for(int i=0;i<ne.size();i++) {
			ne.get(i).pintarNE(f.g, flameCounter);
		}
		for(int i=0;i<bb.size();i++) {
			bb.get(i).pintarBalaBona(f.g,flameCounter);
		}
		for(int i=0;i<be.size();i++) {
			be.get(i).pintarBalaE(f.g,flameCounter);
		}
		if(n[0].vides==0 && n[n.length-1].vides==0) {
			u.end(f.g, f);
			u.replay(f.g,f);
		}
		f.repaint();
	}
}
