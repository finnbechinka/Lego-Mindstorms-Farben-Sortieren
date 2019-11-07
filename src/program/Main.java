package program;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.hardware.sensor.*;
import lejos.hardware.Button;
import lejos.robotics.*;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.*;
import lejos.utility.*;

public class Main {

	public static void main(String[] args) {
		LCD.drawString("gyro and pilot", 0, 4);
		LCD.drawString("navigation test", 0, 3);
		Delay.msDelay(3000);
		
		
		RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.C);
		RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
		// EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S4);
		@SuppressWarnings("resource")
		EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S2);

		//Wheel diameter 5.5
		//Track width 15
		Wheel leftWheel = new WheeledChassis.Modeler(motorA, 5.5).offset(-7.5);
		Wheel rightWheel = new WheeledChassis.Modeler(motorB, 5.5).offset(7.5);
		Chassis chassis = new WheeledChassis(new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL); 
		MovePilot pilot = new MovePilot(chassis);
		
		pilot.setLinearSpeed(5);
		pilot.setAngularSpeed(50);
				
		
		pilot.travel(50);
		
		final SampleProvider sp = gyroSensor.getAngleMode();
		float[] angleSample = new float[sp.sampleSize()];
		sp.fetchSample(angleSample, 0);
		
		while(angleSample[0] <= 180){
			pilot.rotateRight();
			sp.fetchSample(angleSample, 0);
			
		}
		
		pilot.travel(50);
		
		Sound.beep();
		LCD.clear();
		LCD.drawString("END OF PROGRAM", 0, 4);
		Delay.msDelay(3000);
		Sound.twoBeeps();
		
		LCD.clear();
		LCD.drawString("PRESS ENTER TO EXIT", 0, 4);
		Button.ENTER.waitForPress();
	}

}
