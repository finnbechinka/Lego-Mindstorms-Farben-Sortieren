package program;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.Button;
import lejos.utility.*;

public class Main {

	public static void main(String[] args) {
		LCD.clear();
		LCD.drawString("starting up...", 0, 4);
		
		Robot wall_e = new Robot();
		Driving driving = new Driving(wall_e);
		// wall_e.testGyro();
		driving.test();

		Sound.beep();
		LCD.clear();
		LCD.drawString("END OF PROGRAM", 0, 4);
		Delay.msDelay(1500);

		LCD.clear();
		LCD.drawString("PRESS ENTER TO EXIT", 0, 4);
		Button.ENTER.waitForPress();
	}

}
