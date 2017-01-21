package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class GearMechanism extends Subsystem {

	private DoubleSolenoid gearManip;

	public GearMechanism(){
		gearManip = new DoubleSolenoid(RobotMap.PCM_ID_2, RobotMap.GEAR_MANIPULATOR_IN, RobotMap.GEAR_MANIPULATOR_OUT);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void gearInOut(int direction){
    	if (direction == 1) {
    		gearManip.set(DoubleSolenoid.Value.kForward);
    	} else if (direction == -1) {
    		gearManip.set(DoubleSolenoid.Value.kReverse);
    	}

    }
    
}

