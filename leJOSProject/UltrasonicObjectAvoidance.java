package leJOSProject;

import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class UltrasonicObjectAvoidance {

    private EV3UltrasonicSensor ultrasonicSensor;
    private float thresholdDistance;
    private MovePilot pilot;
    private EV3ColorSensor colorSensor;
    

    public UltrasonicObjectAvoidance(MovePilot pilot, EV3UltrasonicSensor ultrasonicSensor, EV3ColorSensor colorSensor, float thresholdDistance) {
        this.ultrasonicSensor = ultrasonicSensor;
        this.thresholdDistance = thresholdDistance;
        this.pilot = pilot;
        this.colorSensor = colorSensor;
    }

    public boolean isObjectDetected() {
        SampleProvider distanceProvider = ultrasonicSensor.getDistanceMode();
        float[] sample = new float[distanceProvider.sampleSize()];
        distanceProvider.fetchSample(sample, 0);
        float distance = sample[0] * 100; // Convert to centimeters 
        return distance <= thresholdDistance;
    }
    
    public void zigzag() {
    	boolean objectDetected = isObjectDetected();
    	while (objectDetected) {
            if (objectDetected) {
                pilot.rotate(100);
                pilot.travel(100);
                pilot.rotate(-100); 
            }

            objectDetected = isObjectDetected();
        }
    }
    
    public void alignWithTop() {
    	pilot.rotate(100);
        pilot.travel(300);
        pilot.rotate(-100);
        pilot.travel(280);
        pilot.rotate(-100);
    }
    
    public void alignWithSide() {
    	pilot.rotate(100);
        pilot.travel(300);
        pilot.rotate(-100);
        float[] color = new float[1];
        colorSensor.getRedMode().fetchSample(color, 0);
        pilot.forward();
        while (!(0.1f < color[0]) && (color[0] < 0.3f)) {
        	colorSensor.getRedMode().fetchSample(color, 0);
        }
        pilot.stop();
        pilot.rotate(105);
    }
}