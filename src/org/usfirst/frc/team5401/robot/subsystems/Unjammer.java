package org.usfirst.frc.team5401.robot.subsystems;

import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Unjammer extends Subsystem {
	
	private DoubleSolenoid unjammer;
	
	public Unjammer(){
		unjammer = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.UNJAM_IN, RobotMap.UNJAM_OUT);
		
		SmartDashboard.putString("Unjammer_text", "Unjammer");
		SmartDashboard.putString("UnjammerOut_text", "GREEN = Unjammer Out");
		SmartDashboard.putString("UnjammerIn_text", "RED = Unjammer In");
		if ((DoubleSolenoid.Value.kForward).equals(unjammer.get())){
			SmartDashboard.putNumber("Unjammer:", -1); //Unjammer is out
		} else {
			SmartDashboard.putNumber("Unjammer:", 1); //Unjammer is in
		}
	}
	
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void unjammerOut(){
    	unjammer.set(DoubleSolenoid.Value.kForward);
    	SmartDashboard.putNumber("Unjammer", -1);
    	
    }
    public void unjammerIn(){
    	unjammer.set(DoubleSolenoid.Value.kReverse);
    	SmartDashboard.putNumber("Unjammer", 1);
    }
}

