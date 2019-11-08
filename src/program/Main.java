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
	
	static RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.B);
	static RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.C);
	static EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S4);

	//Wheel diameter 5.6cm (Calibrated to L5.6 and R5.55)
	//Track width 12cm (Calibrated to 11cm);
	static Wheel leftWheel = new WheeledChassis.Modeler(motorA, 5.6).offset(-5.5);
	static Wheel rightWheel = new WheeledChassis.Modeler(motorB, 5.55).offset(5.5);
	static Chassis chassis = new WheeledChassis(new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL); 
	static MovePilot pilot = new MovePilot(chassis);

	public static void main(String[] args) {
		new Calibration(pilot);
		
		Sound.beep();
		LCD.clear();
		LCD.drawString("END OF PROGRAM", 0, 4);
		Delay.msDelay(2000);
		
		LCD.clear();
		LCD.drawString("PRESS ENTER TO EXIT", 0, 4);
		Button.ENTER.waitForPress();
	}
	
	public Main(){
		LCD.drawString("pilot", 0, 3);
		LCD.drawString("navigation test", 0, 4);
		Delay.msDelay(3000);
		
	}

}
