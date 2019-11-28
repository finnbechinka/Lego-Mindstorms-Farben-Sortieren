package program;

import program.Robot.direction;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Driving {
	private Robot wall_e;

	public Driving(Robot wall_e) {
		this.wall_e = wall_e;
		wall_e.getPilot().setLinearSpeed(10);
		wall_e.getPilot().setAngularSpeed(100);
		wall_e.getPilot().setAngularAcceleration(15);
		wall_e.getPilot().setLinearAcceleration(6);
	}

	public void rotateLeft(int deg) {
		LCD.clear();
		LCD.drawString("rotateLeft " + deg + "°", 0, 4);
		Delay.msDelay(1000);
		wall_e.resetGyro();
		wall_e.getPilot().rotate(-360, true);
		boolean targetHit = false;
		while (!targetHit) {
			if (wall_e.getGyroSample()[0] >= deg - 32.32) {
				wall_e.getPilot().stop();
				LCD.clear();
				LCD.drawString("end deg: " + wall_e.getGyroSample()[0], 0, 5);
				Delay.msDelay(666);
				targetHit = true;
			}
		}
		
		if(wall_e.getDir() == direction.FRONT){
			wall_e.setDir(direction.LEFT);
		}else if(wall_e.getDir() == direction.LEFT){
			wall_e.setDir(direction.BACK);
		}else if(wall_e.getDir() == direction.RIGHT){
			wall_e.setDir(direction.FRONT);
		}else if(wall_e.getDir() == direction.BACK){
			wall_e.setDir(direction.RIGHT);
		}
	}

	public void rotateRight(int deg) {
		LCD.clear();
		LCD.drawString("rotateRight " + deg + "°", 0, 4);
		Delay.msDelay(1000);
		wall_e.resetGyro();
		wall_e.getPilot().rotate(360, true);
		boolean targetHit = false;
		while (!targetHit) {
			if (wall_e.getGyroSample()[0] <= (deg - 10) * -1) {
				wall_e.getPilot().stop();
				targetHit = true;
			}
		}
		
		if(wall_e.getDir() == direction.FRONT){
			wall_e.setDir(direction.RIGHT);
		}else if(wall_e.getDir() == direction.LEFT){
			wall_e.setDir(direction.FRONT);
		}else if(wall_e.getDir() == direction.RIGHT){
			wall_e.setDir(direction.BACK);
		}else if(wall_e.getDir() == direction.BACK){
			wall_e.setDir(direction.LEFT);
		}
	}

	public void drive(int dist) {
		LCD.clear();
		LCD.drawString("drive " + dist + "cm", 0, 4);
		Delay.msDelay(1000);
		wall_e.getPilot().travel(dist, true);
		
		if(wall_e.getDir() == direction.FRONT){
			wall_e.getPos()[0] = wall_e.getPos()[0] + dist;			
		}else if(wall_e.getDir() == direction.BACK){
			wall_e.getPos()[0] = wall_e.getPos()[0] - dist;	
		}else if(wall_e.getDir() == direction.LEFT){
			wall_e.getPos()[0] = wall_e.getPos()[1] - dist;	
		}else if(wall_e.getDir() == direction.RIGHT){
			wall_e.getPos()[0] = wall_e.getPos()[1] + dist;
		}

//		// ref.: https://www.youtube.com/watch?v=U-LdBQ-vBkg
//		double target = 0;
//		double error = 0;
//		double cp = 0.3;
//		double ci = 0.001;
//		double cd = 0.2;
//		double integral = 0;
//		double derivative = 0;
//		double lastError = 0;
//
//		wall_e.resetGyro();
//		while (wall_e.getPilot().isMoving()) {
//			error = target - wall_e.getGyroSample()[0];
//			integral = integral + error;
//			derivative = error - lastError;
//			wall_e.getPilot().rotate(
//					(error * cp) + (integral * ci) + (derivative * cd), false);
//			lastError = error;
//		}
	}

	public void test() {
		rotateLeft(180);
		rotateLeft(180);
		rotateLeft(180);
		rotateLeft(180);
		rotateLeft(180);
		rotateLeft(180);
		//drive(50);
	}
}
