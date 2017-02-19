package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;


/**
 *
 */
public class Unjammer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private DoubleSolenoid unjammer;
	
	public Unjammer(){
		unjammer = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.UNJAMMER_IN, RobotMap.UNJAMMER_OUT);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void unjammerOut(){
    	unjammer.set(DoubleSolenoid.Value.kForward);
    }
    
    public void unjammerIn(){
    	unjammer.set(DoubleSolenoid.Value.kReverse);
    }
}