package project;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class Driver {

	// Set all magic numbers
	final static float WHEEL_DIAMETER = 56; // The diameter (mm) of the wheels
	final static float AXLE_LENGTH = 108; // The distance (mm) your two driven wheels
	final static double ANGULAR_SPEED = 180; // How fast around corners (degrees/sec)
	final static double ANGULAR_ACCELERATION = 50; // How fast the robot accelerates around a corner
	final static double LINEAR_SPEED = 500; // How fast in a straight line (mm/sec)
	final static double LINEAR_ACCELERATION = 10; // How fast the robot accelerates in a straight line
	final static int LIFT_MOTOR_SPEED = 150; // How fast the lift motor will raise/lower an object
	final static int LIFT_MOTOR_ACCELERATION = 3000; // The rate of change of lift speed
	final static double SLOW_SPEED = 200; // Speed when in a dark area
	final static float DARK = 0.5f; // Light level to count as a dark area
	final static float THRESHOLD_DISTANCE = 0.20f; // Distance of an object infront of the robot to react to
	
	public static void main(String[] args) {
		
		// Instantiate all objects required for the pilot
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
		Wheel wRight = WheeledChassis.modelWheel(mRight, WHEEL_DIAMETER).offset(AXLE_LENGTH / 2);
		Wheel wLeft = WheeledChassis.modelWheel(mLeft, WHEEL_DIAMETER).offset(-AXLE_LENGTH / 2);
		Chassis chassis = new WheeledChassis((new Wheel[] {wRight, wLeft}), WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot pilot = new MovePilot(chassis);

		// Set the settings for the robot pilot
		pilot.setLinearSpeed(LINEAR_SPEED);
		pilot.setLinearAcceleration(LINEAR_ACCELERATION);
		pilot.setAngularSpeed(ANGULAR_SPEED);
		pilot.setAngularAcceleration(ANGULAR_ACCELERATION);

		// Instantiate all peripheral objects
		BaseRegulatedMotor liftMotor = new EV3MediumRegulatedMotor(MotorPort.C);
		EV3UltrasonicSensor usSensor = new EV3UltrasonicSensor(SensorPort.S1);
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S2);
		EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S3);

		// Set the settings for the lift motor
		liftMotor.setSpeed(LIFT_MOTOR_SPEED);
		liftMotor.setAcceleration(LIFT_MOTOR_ACCELERATION);
		
		//Instantiate the control objects
		ArmOperator armOperator = new ArmOperator(liftMotor);
		UltrasonicObjectAvoidance usAvoid = new UltrasonicObjectAvoidance(pilot, usSensor, colorSensor, THRESHOLD_DISTANCE);
		
		// Instantiate the behaviours
		EmergencyStop emergencyStop = new EmergencyStop(pilot, liftMotor, touchSensor);
		Backup backup = new Backup(pilot, usSensor, usAvoid, armOperator);
		Light light = new Light(pilot, colorSensor, DARK, LINEAR_SPEED);
		Dark dark = new Dark(pilot, colorSensor, DARK, SLOW_SPEED);
		FollowLine followLine = new FollowLine(pilot, colorSensor);
		
		// Setup and instantiate the behaviour Arbitrator
		Arbitrator arbitrator = new Arbitrator(new Behavior[] {followLine, backup});

		/*===================================================================================================*/
		
		pilot.rotate(-105);
		armOperator.lower();
		pilot.travel(100);
		armOperator.lift();
		pilot.travel(-100);
		pilot.rotate(105);
			
		arbitrator.go();
		
		arbitrator.stop();

	}
	
}