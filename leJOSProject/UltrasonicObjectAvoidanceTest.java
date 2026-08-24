package src;

import lejos.hardware.Button;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class UltrasonicObjectDetectionTest {
	
	private static final float WHEEL_DIAMETER = 56; // The diameter (mm) of the wheels
    private static final float AXLE_LENGTH = 110; // The distance (mm) between your two driven wheels

	public static void main(String[] args) throws Exception {
		
		BaseRegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.A);
	    Wheel wLeft = WheeledChassis.modelWheel(mL, WHEEL_DIAMETER).offset(-AXLE_LENGTH / 2);
	    BaseRegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.B);
	    Wheel wRight = WheeledChassis.modelWheel(mR, WHEEL_DIAMETER).offset(AXLE_LENGTH / 2);
	    Chassis chassis = new WheeledChassis((new Wheel[] {wRight, wLeft}), WheeledChassis.TYPE_DIFFERENTIAL);
	    MovePilot pilot = new MovePilot(chassis);
        EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S1); // need to replace with actual port
        
        float detectionDistance = 20.0f; // in cm (might need adjustment)

        UltrasonicObjectDetection objectDetector = new UltrasonicObjectDetection(ultrasonicSensor, detectionDistance, pilot);
        Button.ENTER.waitForPressAndRelease();
        objectDetector.zigzag();
        objectDetector.alignWithTop();
        objectDetector.zigzag();
        objectDetector.alignWithSide();
        
        
    }
}
