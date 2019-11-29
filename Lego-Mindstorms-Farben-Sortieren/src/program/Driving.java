package program;

import program.Robot.direction;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Driving {
	private Robot wall_e;

	public Driving(Robot wall_e) {
		this.wall_e = wall_e;
		wall_e.getPilot().setLinearSpeed(100);
		wall_e.getPilot().setAngularSpeed(100);
		wall_e.getPilot().setAngularAcceleration(25);
		wall_e.getPilot().setLinearAcceleration(20);
	}

	public void rotateLeft() {
		LCD.clear();
		LCD.drawString("rotateLeft 90°", 0, 4);
		Delay.msDelay(1000);
		wall_e.getPilot().rotate(90);

		if (wall_e.getDir() == direction.FRONT) {
			wall_e.setDir(direction.LEFT);
		} else if (wall_e.getDir() == direction.LEFT) {
			wall_e.setDir(direction.BACK);
		} else if (wall_e.getDir() == direction.RIGHT) {
			wall_e.setDir(direction.FRONT);
		} else if (wall_e.getDir() == direction.BACK) {
			wall_e.setDir(direction.RIGHT);
		}
	}

	public void rotateRight() {
		LCD.clear();
		LCD.drawString("rotateRight 90°", 0, 4);
		Delay.msDelay(1000);
		wall_e.getPilot().rotate(-90);
		
		if (wall_e.getDir() == direction.FRONT) {
			wall_e.setDir(direction.RIGHT);
		} else if (wall_e.getDir() == direction.LEFT) {
			wall_e.setDir(direction.FRONT);
		} else if (wall_e.getDir() == direction.RIGHT) {
			wall_e.setDir(direction.BACK);
		} else if (wall_e.getDir() == direction.BACK) {
			wall_e.setDir(direction.LEFT);
		}
	}

	public void drive(int dist) {
		LCD.clear();
		LCD.drawString("drive " + dist + "cm", 0, 4);
		Delay.msDelay(1000);
		wall_e.getPilot().travel(dist);

		if (wall_e.getDir() == direction.FRONT) {
			wall_e.getPos()[0] = wall_e.getPos()[0] + dist;
		} else if (wall_e.getDir() == direction.BACK) {
			wall_e.getPos()[0] = wall_e.getPos()[0] - dist;
		} else if (wall_e.getDir() == direction.LEFT) {
			wall_e.getPos()[0] = wall_e.getPos()[1] - dist;
		} else if (wall_e.getDir() == direction.RIGHT) {
			wall_e.getPos()[0] = wall_e.getPos()[1] + dist;
		}

		// // ref.: https://www.youtube.com/watch?v=U-LdBQ-vBkg
		// double target = 0;
		// double error = 0;
		// double cp = 0.3;
		// double ci = 0.001;
		// double cd = 0.2;
		// double integral = 0;
		// double derivative = 0;
		// double lastError = 0;
		//
		// wall_e.resetGyro();
		// while (wall_e.getPilot().isMoving()) {
		// error = target - wall_e.getGyroSample()[0];
		// integral = integral + error;
		// derivative = error - lastError;
		// wall_e.getPilot().rotate(
		// (error * cp) + (integral * ci) + (derivative * cd), false);
		// lastError = error;
		// }
	}

	public void test() {
		wall_e.getPilot().travel(500);
		wall_e.getPilot().rotate(180);
		wall_e.getPilot().travel(500);
	}
}
