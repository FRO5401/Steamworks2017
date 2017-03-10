package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import org.usfirst.frc.team5401.robot.RobotMap;



/**
 *
 */
public class Unjammer extends Subsystem {
	
	private DoubleSolenoid unjammer;
	
	public Unjammer(){
		unjammer = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.UNJAMMER_IN, RobotMap.UNJAMMER_OUT);
		
		SmartDashboard.putString("Unjammer_text", "Unjammer");
		SmartDashboard.putString("UnjammerOut_text", "GREEN = Unjammer Out");
		SmartDashboard.putString("UnjammerIn_text" , "RED = Unjammer In");
		if ((DoubleSolenoid.Value.kForward).equals(unjammer.get())){
			SmartDashboard.putNumber("Unjammer", -1); //Unjammer is out
		} else {
			SmartDashboard.putNumber("Unjammer", 1); //Unjammer is in
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public void unjammerOut(){
    	unjammer.set(DoubleSolenoid.Value.kForward);
    	SmartDashboard.putNumber("Unjammer", -1); //Unjammer is out
    }
    
    public void unjammerIn(){
    	unjammer.set(DoubleSolenoid.Value.kReverse);
    	SmartDashboard.putNumber("Unjammer", 1); //Unjammer is in
    }
}