package program;

import lejos.hardware.Sound;
import lejos.hardware.ev3.*;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.hardware.sensor.*;
import lejos.hardware.Button;
import lejos.robotics.*;
import lejos.utility.*;

public class Main {

	public static void main(String[] args) {
		RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.C);
		RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
		
		motorA.setSpeed(100);
		motorB.setSpeed(100);
		
		LCD.drawString("test lol", 0, 4);
		Delay.msDelay(5000);
		
		forward(motorA, motorB, 3000);
		turnLeft(motorA, motorB, 1500);
		backward(motorA, motorB, 2000);
		//turnRight(motorA, motorB, 300);
		forward(motorA, motorB, 1500);
		
		motorA.close();
		motorB.close();
		
		Sound.beep();
		LCD.clear();
		LCD.drawString("END OF PROGRAM", 0, 4);
		Delay.msDelay(3000);
		Sound.twoBeeps();
		
		LCD.clear();
		LCD.drawString("PRESS ENTER TO EXIT", 0, 4);
		Button.ENTER.waitForPress();
	}
	
	public static void forward(RegulatedMotor a, RegulatedMotor b, int ms){
		LCD.clear();
		LCD.drawString("forward, delay:", 0, 4);
		LCD.drawString(" " + ms, 0, 5);
		Delay.msDelay(100);
		a.forward();
		b.forward();
		Delay.msDelay(ms);
		a.stop();
		b.stop();
		Delay.msDelay(100);
	}
	
	public static void backward(RegulatedMotor a, RegulatedMotor b, int ms){
		LCD.clear();
		LCD.drawString("backward, delay:", 0, 4);
		LCD.drawString(" " + ms, 0, 5);
		Delay.msDelay(100);
		a.backward();
		b.backward();
		Delay.msDelay(ms);
		a.stop();
		b.stop();
		Delay.msDelay(100);
	}
	
	public static void turnLeft(RegulatedMotor a, RegulatedMotor b, int ms){
		LCD.clear();
		LCD.drawString("left, delay:", 0, 4);
		LCD.drawString(" " + ms, 0, 5);
		Delay.msDelay(100);
		a.forward();
		Delay.msDelay(ms);
		a.stop();
		Delay.msDelay(100);
	}
	
	public static void turnRight(RegulatedMotor a, RegulatedMotor b, int ms){
		LCD.clear();
		LCD.drawString("right, delay:", 0, 4);
		LCD.drawString(" " + ms, 0, 5);
		Delay.msDelay(100);
		b.forward();
		Delay.msDelay(ms);
		b.stop();
		Delay.msDelay(100);
	}

}