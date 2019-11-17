package program;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Driving {
	Robot wall_e;
	
	public Driving(Robot wall_e){
		this.wall_e = wall_e;
		wall_e.getPilot().setLinearSpeed(20);
		wall_e.getPilot().setAngularSpeed(100);
		wall_e.getPilot().setAngularAcceleration(10);
		wall_e.getPilot().setLinearAcceleration(4);
	}
	
	public void rotate(int deg){
		LCD.clear();
		LCD.drawString("rotate " + deg + "°", 0, 4);
		Delay.msDelay(1000);
		wall_e.getPilot().rotate(deg);
	}
	
	public void drive(int dist){
		LCD.clear();
		LCD.drawString("drive " + dist + "cm", 0, 4);
		Delay.msDelay(1000);
		wall_e.getPos()[0] = wall_e.getPos()[0] + dist;
		wall_e.resetGyro();
		wall_e.getPilot().travel(dist, true);
		
		//ref.: https://www.youtube.com/watch?v=U-LdBQ-vBkg
		double target = 0;
		double error = 0;
		double cp = 0.3;
		double ci = 0.001;
		double cd = 0.2;
		double integral = 0;
		double derivative = 0;
		double lastError = 0;
		
		while(wall_e.getPilot().isMoving()) {
			error = target - wall_e.getGyroSample()[0];
			integral = integral + error;
			derivative = error - lastError;
			wall_e.getPilot().rotate((error * cp) + (integral * ci) + (derivative * cd), false);
			lastError = error;
		}
	}
	
	public void test(){
		rotate(360);
		drive(100);
		rotate(180);
		drive(100);
	}
}
