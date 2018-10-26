package BalloonGame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.applet.Applet;


public class BalloonGame2 extends Applet implements  Runnable{
	private static final int SLEEP_INTERVAL = 50;
	int baloonSpeed = 10;
	int missile_init_speed = 46;
	int missile_init_angle = 45;
	
	Balloon balloon;
	Missile missile;
	Thread t = null;  
    float now_time= 0;
	private boolean boom_time = false;
	private boolean game_over;
	private boolean init_paint = true;
	private boolean fire = false;
    

    Button fire_button = new Button("Fire");
	private int angle;
	private int velocity;
	
	int hits;
	int misses;

	private Image bging;
	Image img;

	public void xinit(){
		try {
			img = ImageIO.read(new File("bg.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		baloonSpeed = (8 + (int)(Math.random() * ((15 - 8) + 1)));
		System.out.println("xinit");
		removeAll();
//		setBackground(Color.cyan);

		setSize(1200,601);
		setSize(1200,602);

	    now_time= 0;
		balloon = new Balloon(baloonSpeed);
		missile = new Missile(missile_init_speed, missile_init_angle);
		
	    TextField angle_tf = new TextField(missile_init_angle+"", 11);

	    TextField velocity_tf = new TextField(missile_init_speed+"", 11);
	    Label baloonspeed_label = new Label("Balloon Speed : "+baloonSpeed+"    ");
	    baloonspeed_label.setSize(100,10);
//	    baloonspeed_label.setBackground(Color.GREEN);

	    add(baloonspeed_label);

	    add(new Label("Missile:"));
		
		add(new Label("Velocity"));
		add(velocity_tf);

		add(new Label("Angle"));
		add(angle_tf);
		fire_button.setLabel("Fire");

		
		add(fire_button);

	    Label hits_l = new Label("Hits:"+ hits);
	    add((hits_l));

	    Label misses_l = new Label("Misses:"+ misses);
	    add((misses_l));

		
		fire_button.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
				try {
					velocity =	Integer.parseInt(velocity_tf.getText());
					System.out.println(" input speed = "+velocity);
				} catch (NumberFormatException nfe) {			nfe.printStackTrace();
				}
				try {
					angle =	Integer.parseInt(angle_tf.getText());
					System.out.println(" input angle="+angle);
				} catch (NumberFormatException nfe) {				nfe.printStackTrace();
				}
				System.out.println("missile_button was pressed");
	     		fire_button.setLabel("FIRED");
	     		fire = true;
	    		balloon = new Balloon(baloonSpeed);
	    		missile = new Missile(velocity, angle);
	    		missile_init_angle = angle;
	    		missile_init_speed = velocity;


	        }
	    });
				
		repaint();
	}

	public void start() {
//				setBackground(Color.cyan);
				setSize(1200,600);
				
		        t = new Thread( this );  
		        t.start();  
	 }  
	 
	public void paint(Graphics g){
		System.out.println("paint()"+balloon.y);
		g.drawImage(img, 0, 0, null);
		super.paint(g);

		if(fire) {

	    balloon.draw(g, now_time);
	    missile.draw(g,now_time);
	    
	    int distance = length(balloon.x, missile.x, balloon.y, missile.y);		    
	    if(distance <= balloon.radius + missile.radius){
	    	boom_time  = true;
	    	boom(balloon.x, balloon.y, g);

	    }			
	}

	}
	
	
	private void boom(int x, int y, Graphics g) {
		hits++;
		System.out.println("boom("+x+","+y+")");

		int boom_radius = 10;
		for (int i = 0; i < 10; i++) {
				boom_radius+=5;
				g.setColor(Color.RED);
				g.fillOval(x-boom_radius, y-boom_radius, boom_radius*2, boom_radius*2);
				try {
					Thread.sleep( 50 );
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
		}
		
	}
	
	@Override
	public void run() {
		System.out.println("run()");
		while(true) {
			xinit();
			while(!fire) {
				 try {
					Thread.sleep( 20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
	
			fire();
			fire = false;
			
		}
	}
	private void fire() {
		System.out.println("fire(angle="+angle+", speed = "+velocity+")");
		fire = true;
		boom_time = false;
		try {  
			 for (int i = 0; i < 100; i++) {	
				 if(balloon.y <0) {
					 misses++;
				 }
				 if(boom_time ||  balloon.y <0) {
					break;
				 }else {
	        	 now_time++;
	        	 System.out.println(now_time);
	             repaint();  
	             Thread.sleep( SLEEP_INTERVAL ); 
				 }// interval given in milliseconds  
	         }  
	      }   catch (Exception e) { }
	}
	

		
	public int length(int x1, int x2, int y1, int y2){
		return (int)(Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)));	
	}
	
	

}
