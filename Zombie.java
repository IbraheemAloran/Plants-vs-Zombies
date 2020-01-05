import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class Zombie {
	private int v,hp, damage, x, y, cycle, type,s, attack, cycl;
	private Rectangle rect;
	private Image pik;
	private ArrayList<Image> pics = new ArrayList<Image>();
	private ArrayList<Image> attak = new ArrayList<Image>();
	
    public Zombie(int xx, int yy, int n){
    	x = xx;
    	y = yy;
    	cycle = 0;
    	type = n;
    	attack = 0;
    	if(type == 0){
    		v = 4;
    		hp = 100;
    		damage = 20;
    		for(int i=0;i<6;i++){
				pik = new ImageIcon("normal"+i+".png").getImage();
				pik = pik.getScaledInstance(60,110,Image.SCALE_SMOOTH);
				pics.add(pik);
			}
    	}
    	else if(type == 1){
    		v = 5;
    		hp = 100;
    		damage = 20;
    		for(int i=0;i<10;i++){
				pik = new ImageIcon("Walk"+(i+1)+".png").getImage();
				pik = pik.getScaledInstance(75,100,Image.SCALE_SMOOTH);
				pics.add(pik);
			}
			for(int i=0;i<8;i++){
				pik = new ImageIcon("Attack"+i+".png").getImage();
				pik = pik.getScaledInstance(75,100,Image.SCALE_SMOOTH);
				attak.add(pik);
			}
    	}
    	else if(type == 2){
    		v = 7;
    		hp = 100;
    		damage = 20;
    		for(int i=0;i<5;i++){
				pik = new ImageIcon("run"+i+".png").getImage();
				pik = pik.getScaledInstance(60,110,Image.SCALE_SMOOTH);
				pics.add(pik);
			}
    	}
    	else if(type == 3){
    		v = 4;
    		hp = 1000;
    		damage = 100;
    		for(int i=0;i<3;i++){
				pik = new ImageIcon("boss"+i+".png").getImage();
				pik = pik.getScaledInstance(50,50,Image.SCALE_SMOOTH);
				pics.add(pik);
			}
    	}
    	s = v-2;
    	rect = new Rectangle(x,y+20,50,75);
    	
    }
    public boolean shot(Bullet ball){
    	if(rect.contains(ball.getX(),ball.getY())){
    		hp -= ball.getDam();
    		if(ball.getType() == 2){
    			v = s;
    		}
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    public int getV(){
    	return v;
    }
    public int getType(){
    	return type;
    }
    public int gethp(){
    	return hp;
    }
    public int getDam(){
    	return damage;
    }
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
    public void draw(Graphics g){
    	if(attack == 0){
	    	if(type == 3){
	    		g.drawImage(pics.get(cycle/20),x,y+25,null);
	    	}
	    	else{
	    		g.drawImage(pics.get(cycle/20),x,y,null);
	    	}
    	}
    	else{
	    	if(type == 1){
	    		g.drawImage(attak.get(cycl/20),x,y,null);
	    	}
    	}
    }
    public void sprite(){
    	if(attack == 0){
    		cycle += 1;
	    	if(type == 0){
	    		if(cycle == 100 && hp > 0){
	    			cycle = 0;
	    		}
	    	}
	    	else if(type == 1){
	    		if(cycle == 200 && hp > 0){
	    			cycle = 0;
	    		}
	    	}
	    	else if(type == 2){
	    		if(cycle == 80 && hp>0){
	    			cycle = 0;
	    		}
	    	}
	    	else{
	    		if(cycle == 40 && hp > 0){
	    			cycle = 0;
	    		}
	    	}
	    	if(cycle == 1 || cycle == 59){
	    		x -= v;
	    		rect.setLocation(x,y+20);
	    	}
    	}
    	else{
    		cycl += 1;
    		if(type == 1){
    			if(cycl == 160){
    				cycl = 0;
    			}
    		}
    	}
   	}
   	public boolean hits(Zombie zomb){
   		return rect.intersects(zomb.getWrekt());
   	}
    public Rectangle getWrekt(){
    	return rect;
    }
    public int getS(){
    	return cycle;
    }
    public int getC(){
    	return cycl;
    }
    public int getat(){
    	return attack;
    }
    public void setS(int n){
    	cycle = n;
    }
    public void sethp(int n){
    	hp = n;
    }
    public void hits(Plant plan){
		if(rect.intersects(plan.getWrekt())){
			if(plan.getType()==5){
				hp -= plan.getDam();
			}
			else{
				attack = 1;
			}
			if(type == 1){
				if(cycl == 80){
					plan.eaten(damage);
				}
			}
			if(plan.gethp() <= 0){
				attack= 0;
			}
		}
		
	}
    
}

class Plant{
	private int hp,damage,x,y,cycle,attack,type,spot,cost;
	private int sunwait = 1;
	private Rectangle rect;
	private ArrayList<Image> p = new ArrayList<Image>();
	private Image plant;
	
	public Plant(int xx, int yy, int n, int s){
		//4=walnut, 1=peashooter, 3=doublepeas, 2=icepea, 5=cherry, 0=sunflower
		cycle = 0;
		attack = 0;
		type = n;
		spot = s;
    	if(n == 4){
    		x = xx;
    		y = yy;
    		cost = 50;
    		hp = 300;
    		damage = 0;
    		attack = 0;
    		plant = new ImageIcon("walnut.png").getImage();
			plant = plant.getScaledInstance(140,180,Image.SCALE_SMOOTH);
			p.add(plant);
    	}
    	else if(n == 1){
    		x = xx;
    		y = yy;
    		hp = 100;
    		damage = 20;
    		cost = 100;
    		for(int i=0;i<3;i++){
				plant = new ImageIcon("peashooting"+i+".png").getImage();
				plant = plant.getScaledInstance(50,60,Image.SCALE_SMOOTH);
				p.add(plant);
			}
    		
    	}
    	else if(n == 3){
    		x = xx;
    		y = yy;
    		hp = 100;
    		damage = 20;
    		cost = 200;
    		for(int i=0;i<3;i++){
				plant = new ImageIcon("dpea"+i+".png").getImage();
				plant = plant.getScaledInstance(50,60,Image.SCALE_SMOOTH);
				p.add(plant);
			}
    	}
    	else if(n == 2){
    		x = xx;
    		y = yy;
    		hp = 100;
    		damage = 20;
    		cost = 175;
    		for(int i=0;i<3;i++){
				plant = new ImageIcon("ipeashoot"+i+".png").getImage();
				plant = plant.getScaledInstance(50,60,Image.SCALE_SMOOTH);
				p.add(plant);
			}
    	}
    	else if(n == 5){
    		x = xx;
    		y = yy;
    		hp = 50;
    		damage = 150;
    		attack = 1;
    		cost = 150;
    		for(int i=0;i<15;i++){
				plant = new ImageIcon("cbomb"+i+".png").getImage();
				plant = plant.getScaledInstance(50,60,Image.SCALE_SMOOTH);
				p.add(plant);
			}
			rect = new Rectangle(x-75,y-75,200,200);
    		
    	}
    	else if(n == 0){
    		x = xx;
    		y = yy;
    		hp = 100;
    		damage = 0;
    		attack = 0;
    		cost = 50;
    		plant = new ImageIcon("sunflower.gif").getImage();
    		p.add(plant);
    	}
    	rect = new Rectangle(x,y,50,50);
	}
	public void suns(ArrayList<Sun>sunCollect){
		if(type == 0){
			sunwait -=1;
			//System.out.println(sunwait);
			Random rand = new Random();
			Random rant = new Random();
			if (sunwait == 0){
				Sun s1 = new Sun(x,y);
				sunCollect.add(s1);
				System.out.println(1);
				sunwait = (int)(Math.random()*700)+800;
			}
		}
	}
    public int gethp(){
    	return hp;
    }
    public int getcost(){
    	return cost;
    }
    public int sethp(int n){
    	return hp -= n;
    }
    public int getSpot(){
    	return spot;
    }
    public ArrayList<Image> getPics(){
    	return p;
    }
    public void draw(Graphics g){
    	if(type == 4){
    		g.drawImage(p.get(cycle/50),x-40,y-70,null);
    	}
    	else{
    		g.drawImage(p.get(cycle/50),x,y,null);
    	}
    }
    public int getType(){
    	return type;
    }
    public int getDam(){
    	return damage;
    }
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
    public int getS(){
    	return cycle;
    }
    public void setS(){
    	cycle = 0;
    }
    public void eaten(int n){
    	hp -= n;
    }
    public void sprite(){
    	if(type == 3 || type == 5){
    		cycle += 2;
    	}
    	else{
    		cycle += 1;
    	}
    }
    public void setAt(int n){
    	attack = n;
    }
    public int getAt(){
    	return attack;
    }
    public Rectangle getWrekt(){
    	return rect;
    }
}
