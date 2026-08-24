package leJOSProject;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.motor.BaseRegulatedMotor;

public class EmergencyStop implements Behavior {
	
	private EV3TouchSensor touchSensor;
	private MovePilot pilot;
	private BaseRegulatedMotor liftMotor;
	private boolean suppressed = false;

	public EmergencyStop(MovePilot pilot, BaseRegulatedMotor liftMotor, EV3TouchSensor touchSensor) {
		this.pilot = pilot;
		this.liftMotor = liftMotor;
		this.touchSensor = touchSensor;
	}

	public boolean takeControl() {
		float[] sample = new float[1];
		touchSensor.getTouchMode();
		touchSensor.fetchSample(sample, 0);
		touchSensor.close();
		return Button.ESCAPE.isDown() || sample[0] == 1;
	}
		
	public void action() {
		pilot.stop();
		liftMotor.stop();
		suppressed = false;
	}
	
	public void suppress() {
		suppressed = true;
	}
	
}


		
