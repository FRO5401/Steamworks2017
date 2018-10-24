package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */
public class GearMechanism extends Subsystem {
	
    private DoubleSolenoid gearMech;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public GearMechanism(){
    	gearMech = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.GEAR_DOOR_CLOSE, RobotMap.GEAR_DOOR_OPEN);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void gearInOut(int gearDirection){
    	if(gearDirection == -1){
    		gearMech.set(DoubleSolenoid.Value.kForward);
    	}
    	else if(gearDirection == 1){
    		gearMech.set(DoubleSolenoid.Value.kReverse);
    	}
    }
}

