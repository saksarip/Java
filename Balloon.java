package BalloonGame;
import java.awt.*;


public class Balloon {
	
	
	
	public int speed;
	public int radius = 30;
	public int startX = 1200;
	public int startY = 600;
	
	public int x = startX;
	public int y = startY;
	
	public Balloon(int speed) {
		System.out.println("new Balloon(speed="+speed+")");
		
		this.speed = speed;	
	}
	
	public void draw(Graphics g, float time){
		x= startX;
		y = (int) ((float) startY - (float) speed * time);
		
		System.out.println("Drawing Balloon at "+x+", "+y + " Speed "+speed);
		Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(3));
			
		g2.setColor(Color.BLACK);
		g2.fillOval(x-radius*2, y-radius, radius*2, radius*2);
		g2.setColor(Color.GREEN);
		g2.fillOval(x-radius*2+2, y-radius+2, radius*2-4, radius*2-4);
		
		g2.setColor(Color.BLACK);
		g2.drawLine(x-radius, y+radius, x-radius, y+radius + 50);
			
	}
		
	
}
