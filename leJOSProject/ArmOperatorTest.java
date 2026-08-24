package leJOSProject;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class ArmOperatorTest {
	
	public static void main(String[] args) {

        BaseRegulatedMotor liftMotor = new EV3MediumRegulatedMotor(MotorPort.C);
        ArmOperator armOperator = new ArmOperator(liftMotor);

        armOperator.lift();
        //Delay.msDelay(300);
        //armOperator.lower();

	}

}