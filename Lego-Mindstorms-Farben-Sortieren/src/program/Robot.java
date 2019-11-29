package program;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class Robot {
	// Wheel diameter 5.6cm
	// Track width 12.15cm
	private Wheel leftWheel;
	private Wheel rightWheel;
	private Chassis chassis;
	private MovePilot pilot;

	private RegulatedMotor motorA;
	private RegulatedMotor motorB;
	private EV3ColorSensor colorSensor;
	private EV3GyroSensor gyroSensor;

	private SampleProvider colorSampleProvider;
	private float[] colorSample;
	private SampleProvider gyroSampleProvider;
	private float[] gyroSample;

	private int[] pos;

	public enum direction {
		FRONT, RIGHT, LEFT, BACK
	};

	private direction dir;

	public Robot() {
		this.motorA = new EV3LargeRegulatedMotor(MotorPort.C);
		this.motorB = new EV3LargeRegulatedMotor(MotorPort.B);

		this.leftWheel = WheeledChassis.modelWheel(motorA, 56).offset(-53.28);
		this.rightWheel = WheeledChassis.modelWheel(motorB, 56).offset(53.28);
		this.chassis = new WheeledChassis(
				new Wheel[] { leftWheel, rightWheel },
				WheeledChassis.TYPE_DIFFERENTIAL);
		this.pilot = new MovePilot(chassis);

		this.colorSensor = new EV3ColorSensor(SensorPort.S4);
		this.gyroSensor = new EV3GyroSensor(SensorPort.S2);

		this.colorSampleProvider = colorSensor.getRGBMode();
		this.gyroSampleProvider = gyroSensor.getAngleMode();

		this.gyroSample = new float[gyroSampleProvider.sampleSize()];
		this.colorSample = new float[colorSampleProvider.sampleSize()];

		LCD.clear();
		LCD.drawString("Calibrating gyro", 0, 3);
		LCD.drawString("please wait...", 0, 4);
		this.gyroSensor.setCurrentMode("Rate");
		this.gyroSensor.setCurrentMode("Angle");
		Delay.msDelay(500);
		while (this.getGyroSample()[0] != 0) {
			Delay.msDelay(10);
		}
		LCD.clear();
		LCD.drawString("Calibration done", 0, 4);
		Delay.msDelay(1000);
		Sound.beep();

		this.pos = new int[] { 0, 0 };
		this.dir = direction.FRONT;
	}

	public MovePilot getPilot() {
		return this.pilot;
	}

	public int[] getPos() {
		return this.pos;
	}

	public direction getDir() {
		return this.dir;
	}

	public void setDir(direction newDir) {
		this.dir = newDir;
	}

	public float[] getColorSample() {
		colorSampleProvider.fetchSample(colorSample, 0);
		return this.colorSample;
	}

	public float[] getGyroSample() {
		gyroSampleProvider.fetchSample(gyroSample, 0);
		return this.gyroSample;
	}

	public void resetGyro() {
		this.gyroSensor.reset();
	}

	public void testGyro() {
		Delay.msDelay(1000);
		while (Button.ENTER.isUp()) {
			float deg = 0;
			if (deg != this.getGyroSample()[0]) {
				deg = this.getGyroSample()[0];
				LCD.clear();
				LCD.drawString("gs: " + deg, 0, 4);
			}
			Delay.msDelay(50);
		}
	}
}
