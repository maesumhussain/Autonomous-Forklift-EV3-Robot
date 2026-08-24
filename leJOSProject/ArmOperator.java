package leJOSProject;

import lejos.hardware.motor.BaseRegulatedMotor;

public class ArmOperator {
    
    private BaseRegulatedMotor liftMotor;

    public ArmOperator(BaseRegulatedMotor liftMotor) {
        this.liftMotor = liftMotor;
    }
    
    private static void moveClaw(BaseRegulatedMotor liftMotor, int direction) {
        if (direction == -1) { liftMotor.rotate(-180); }
        else if (direction == 1){ liftMotor.rotate(180); }
    }

    public void lift() {
        moveClaw(liftMotor, -1);
    }

    public void lower() {
        moveClaw(liftMotor, 1);
    }
}