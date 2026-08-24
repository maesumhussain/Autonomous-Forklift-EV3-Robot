import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class FollowLineTester {
    final static float WHEEL_DIAMETER = 56;
	final static float AXLE_LENGTH = 110;
	final static float ANGULAR_SPEED = 180;
	final static float LINEAR_SPEED = 200;

	public static void main(String[] args) {
    	BaseRegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.A);
        BaseRegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.B);
        Wheel wLeft = WheeledChassis.modelWheel(mL, WHEEL_DIAMETER).offset(-AXLE_LENGTH / 2);
        Wheel wRight = WheeledChassis.modelWheel(mR, WHEEL_DIAMETER).offset(AXLE_LENGTH / 2);
        Chassis chassis = new WheeledChassis(new Wheel[] {wLeft, wRight}, WheeledChassis.TYPE_DIFFERENTIAL);
        MovePilot pilot = new MovePilot(chassis);
        EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S2);
        
        
        FollowLine follower = new FollowLine(pilot, colorSensor);
        follower.followingLine();
        
    }
}
