package leJOSProject;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Light implements Behavior{

    private static boolean suppressed = false;
	private MovePilot pilot;
	private EV3ColorSensor colorSensor;
	private float DARK;
	private double NORMAL_SPEED;  

    public Light(MovePilot pilot, EV3ColorSensor sensor, float DARK, double linearSpeed) { 
		this.pilot = pilot; 
		this.colorSensor = sensor;  
		this.DARK = DARK;
		this.NORMAL_SPEED = linearSpeed;
    }
    
    @Override
    public boolean takeControl() {		
        float[] lightLevel = new float[1];
        colorSensor.getRedMode().fetchSample(lightLevel, 0);
        return lightLevel[0] > DARK;
    }
    
    @Override
    public void action() {
        pilot.setLinearSpeed(NORMAL_SPEED);
    }
    
    @Override
    public void suppress() {
        suppressed = true;
    }
}