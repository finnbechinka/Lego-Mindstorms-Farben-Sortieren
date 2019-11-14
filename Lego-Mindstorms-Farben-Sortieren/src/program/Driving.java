package program;

import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class Driving {
	MovePilot pilot;
	
	public Driving(MovePilot pilot){
		this.pilot = pilot;
		this.pilot.setLinearSpeed(20);
		this.pilot.setAngularSpeed(100);
		this.pilot.setAngularAcceleration(10);
		this.pilot.setLinearAcceleration(4);
	}
	
	public void rotate(int deg){
		LCD.clear();
		LCD.drawString("rotate " + deg + "°", 0, 4);
		Delay.msDelay(1000);
		pilot.rotate(deg);
	}
	
	public void drive(int dist){
		LCD.clear();
		LCD.drawString("drive " + dist + "cm", 0, 4);
		Delay.msDelay(1000);
		pilot.travel(dist);
	}
	
	public void test(){
		rotate(360);
		drive(100);
		rotate(180);
		drive(100);
	}
}
