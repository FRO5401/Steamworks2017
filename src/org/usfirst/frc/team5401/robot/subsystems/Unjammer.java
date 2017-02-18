package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;


/**
 *
 */
public class Unjammer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
//	private Solenoid leftUnjammer;
//	private Solenoid rightUnjammer;
	private Solenoid unjammer;
	
	public Unjammer(){
//		leftUnjammer  = new Solenoid(RobotMap.HOPPER_UNJAMMER_LEFT);
//		rightUnjammer = new Solenoid(RobotMap.HOPPER_UNJAMMER_RIGHT);
		unjammer = new Solenoid(RobotMap.UNJAMMER);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void unjammerOut(){
//    	leftUnjammer .set(true);
//    	rightUnjammer.set(true);
    	unjammer.set(true);
    }
    
    public void unjammerIn(){
//    	leftUnjammer .set(false);
//    	rightUnjammer.set(false);
    	unjammer.set(false);
    }
}