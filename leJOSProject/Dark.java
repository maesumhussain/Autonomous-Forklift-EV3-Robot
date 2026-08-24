package project;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Dark implements Behavior {

	private static boolean suppressed = false;
	private MovePilot pilot;
	private EV3ColorSensor colorSensor;
	private float DARK;
	private double SLOW_SPEED;
	
	public Dark(MovePilot pilot, EV3ColorSensor sensor, float DARK, double slowSpeed) { 
		this.pilot = pilot; 
		this.colorSensor = sensor;  
		this.DARK = DARK;
		this.SLOW_SPEED = slowSpeed;
	}

	@Override
	public boolean takeControl() { 
		float[] lightLevel = new float[1];
		colorSensor.getRedMode().fetchSample(lightLevel, 0);
		return lightLevel[0] < DARK && pilot.getLinearSpeed() >= SLOW_SPEED;
	}

	@Override
	public void action() {
		pilot.setLinearSpeed(SLOW_SPEED);		
	}

	@Override
	public void suppress() {
		 suppressed = true;
	} 
}