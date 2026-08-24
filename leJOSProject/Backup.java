package leJOSProject;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Backup implements Behavior {
	
	private EV3UltrasonicSensor usSensor;
	private float[] distance = new float[1];
	private UltrasonicObjectAvoidance objectDetector;
	private ArmOperator armOperator;
	private MovePilot pilot;

	public Backup(MovePilot pilot, EV3UltrasonicSensor usSensor, UltrasonicObjectAvoidance objectDetector, ArmOperator armOperator) {
		this.pilot = pilot;
		this.usSensor = usSensor;
		this.objectDetector = objectDetector;
		this.armOperator = armOperator;
	}
	
	@Override
	public void action() {
        objectDetector.zigzag();
        objectDetector.alignWithTop();
        objectDetector.zigzag();
        objectDetector.alignWithSide();
        pilot.rotate(-105);
        pilot.travel(100);
        armOperator.lower();
        pilot.travel(-100);
        armOperator.lift();
	}

	@Override
	public boolean takeControl() {
		usSensor.getDistanceMode().fetchSample(distance, 0);
		return distance[0] < 0.20f;
	}
	
	@Override
	public void suppress() {}
}