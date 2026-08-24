package leJOSProject;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class FollowLine implements Behavior {

    private MovePilot pilot;
    private EV3ColorSensor colorSensor;
    private float[] colorSample = new float[1];
    private boolean isMovingForward = false;
    private boolean suppressed;

    public FollowLine(MovePilot pilot, EV3ColorSensor colorSensor) {
        this.pilot = pilot;
        this.colorSensor = colorSensor;
        pilot.setAngularSpeed(90);
        pilot.setLinearAcceleration(250);
        pilot.setLinearSpeed(50);
    }
    
    @Override
	public void action() {
    	suppressed = false;
    	colorSensor.getRedMode().fetchSample(colorSample, 0);
        
        if ((0.1f < colorSample[0]) && (colorSample[0] < 0.3f)) {
            if(!isMovingForward) {
            	pilot.forward();
            	isMovingForward = true;
            }

        } else {
        	if (isMovingForward) {
        		pilot.stop();
        		isMovingForward = false;
        	}
        	
        	pilot.travel(-50);
        	pilot.rotate(-105);
            for (int i = -1; i <= 1; i++) {
            	if (suppressed == true) {isMovingForward = false; break; }
                pilot.travel(100);
                colorSensor.getRedMode().fetchSample(colorSample, 0);
                if ((0.1f < colorSample[0]) && (colorSample[0] < 0.3f)) { break; }
                pilot.travel(-100);
                pilot.rotate(105);
            }
        }
    }
    
    public boolean takeControl() {
    	return true;
    }
    
    public void suppress() {
    	suppressed = true;
    }
}