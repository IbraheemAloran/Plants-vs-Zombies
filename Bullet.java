import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class Bullet {
	private int x,y,v,damage, type;
	private Image pic;
    public Bullet(int xx, int yy, int dam, Image pik, int tipe){
    	x = xx;
    	y = yy;
    	damage = dam;
    	v = 3;
    	pic = pik;
    	type = tipe;
    }
    public void draw(Graphics g){
		g.drawImage(pic,x,y,null);
	}
    public void move(){
    	x += v;
    }
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
    public int getV(){
    	return v;
    }
    public int getDam(){
    	return damage;
    }
    public int getType(){
    	return type;
    }
}

class Mower{
	private int x,y,v,damage,flag,slow;
	private Rectangle rect;
	private Image pic; 
	public Mower(int yy, Image p){
		x = 155;
		y = yy;
		v = 2;
		pic = p;
		damage = 1000;
		flag = 0;
		slow = 0;
		rect = new Rectangle(x,y,60,60);
	}
	public void draw(Graphics g){
		g.drawImage(pic,x,y,null);
	}
	public void cut(){
		if(flag == 1){
			slow += 1;
			if(slow == 10){
				x += v;
				rect.setLocation(x,y);
				slow = 0;
			}
		}
	}
	public boolean hits(Zombie z){
		return rect.intersects(z.getWrekt());
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getDam(){
		return damage;
	}
	public int getFlag(){
		return flag;
	}
	public void setFlag(){
		flag = 1;
	}
	public Rectangle getWrekt(){
		return rect;
	}
	
}

class Sun {
	private int x,y;
	private Rectangle rect;
	private Image pic;
	public Sun(int xx, int yy){
		x = xx;
		y = yy;
		pic = new ImageIcon("Sun.png").getImage();
		pic = pic.getScaledInstance(75,75,Image.SCALE_SMOOTH);
	}
	public void draw(Graphics g){
		g.drawImage(pic,x,y,null);
	}
	public Rectangle getRect(){
		return new Rectangle (x, y, 75, 75);
	}
	public String toString(){
		return "" + getRect();
	}
}
