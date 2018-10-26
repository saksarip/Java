package BalloonGame;
import java.awt.*;
public class Missile {
	
	private int startX = 0;
	private int startY = 600;
	private int velocity;
	private double angle = 45;
	public int radius = 25;
	public int x =startX;
	public int y =startY;
	
	
	
	public Missile(int initialVel, double angle){
		System.out.println("Missile velocity = "+initialVel+", angle "+angle);
		this.velocity = initialVel;
		this.angle = angle;
		
		
	}
	
	public void draw(Graphics g, float time){
	    x =  (int)(startX + velocity*Math.cos(angleRadians())*time);
	    y = (int) (startY - (velocity*Math.sin(angleRadians()) * time - .981 * Math.pow(time, 2)/2));
		
		System.out.println("drawing missile at" + x + ", " + y + " Velocity "+velocity +", Angle "+angle);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillOval(x-radius, y-radius, radius*2, radius*2);

	}


	private double angleRadians() {
		return angle * (Math.PI/180);
	}

}
