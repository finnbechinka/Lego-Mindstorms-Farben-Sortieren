package program;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.Button;
import lejos.utility.*;

public class Main {

	public static void main(String[] args) {
		Robot wall_e = new Robot();
		Driving driving = new Driving(wall_e);
		driving.test();
		new Main(wall_e);
		
		Sound.beep();
		LCD.clear();
		LCD.drawString("END OF PROGRAM", 0, 4);
		Delay.msDelay(1500);
		
		LCD.clear();
		LCD.drawString("PRESS ENTER TO EXIT", 0, 4);
		Button.ENTER.waitForPress();
	}
	
	public Main(Robot wall_e){
		LCD.clear();
		LCD.drawString("Press ENTER", 0, 3);
		LCD.drawString("to scan color", 0, 4);
		Button.ENTER.waitForPress();
		LCD.clear();
		LCD.drawString("R: " + wall_e.getColorSample()[0], 0, 4);
		LCD.drawString("G: " + wall_e.getColorSample()[1], 0, 4);
		LCD.drawString("B: " + wall_e.getColorSample()[2], 0, 4);
		Button.ENTER.waitForPress();
		
		
	}

}
