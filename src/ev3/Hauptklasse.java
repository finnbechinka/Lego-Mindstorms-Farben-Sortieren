package ev3;

import javax.swing.text.DefaultEditorKit.BeepAction;

import lejos.hardware.Sound;
import lejos.hardware.ev3.*;
import lejos.hardware.lcd.LCD;
import lejos.robotics.*;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;
import lejos.hardware.sensor.EV3ColorSensor;



public class Hauptklasse {

	public static void main(String[] args) throws Exception {
	
RegulatedMotor motorB = new EV3LargeRegulatedMotor (MotorPort.B);
RegulatedMotor motorC = new EV3LargeRegulatedMotor (MotorPort.C);
RegulatedMotor motorD = new EV3MediumRegulatedMotor (MotorPort.D);

EV3ColorSensor colorSensor4 = new EV3ColorSensor(SensorPort.S4);

int colorId = -1;

motorB.setSpeed(100);
motorC.setSpeed(100);

motorD.rotate(-100);

do {

	colorId = colorSensor4.getColorID();
	
}while (colorId != 2);
 
if (colorId == 2) {
	
	motorD.rotate(100);
	
	Sound.systemSound(true, 2);
	
		
		motorB.forward();
		motorC.forward();
		
		Delay.msDelay(500);
		
	
	motorB.setSpeed(50);
	motorC.setSpeed(50);
	
	motorB.rotateTo(360);
	motorC.rotateTo(-360);
	
	
	
}else {
	
	for(int i = 0; i <=2; i++) {
	Sound.systemSound(true, 3);
	}
	
	LCD.drawString("Test nicht \n erfolgreich!", 0, 0);
}


	}
}