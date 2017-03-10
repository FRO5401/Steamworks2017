package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class GearMechanism extends Subsystem {

	private DoubleSolenoid gearManip;

	public GearMechanism(){
		gearManip = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.GEAR_MANIPULATOR_IN, RobotMap.GEAR_MANIPULATOR_OUT);
		
		//SmartDashboard Output
		SmartDashboard.putString("GearMechanism_text", "Gear Mechanism");
    	SmartDashboard.putString("GearOut_text" , "GREEN = Gear Out");
    	SmartDashboard.putString("GearIn_text"  , "RED = Gear In");
    	if ((DoubleSolenoid.Value.kForward).equals(gearManip.get())){
			SmartDashboard.putNumber("Gear Mechanism", 1); //Gear Mechanism is in
		} else {
			SmartDashboard.putNumber("Gear Mechanism", -1); //Gear Mechanism is out
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public void gearInOut(int direction){
    	if (direction == 1) {
    		gearManip.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putNumber("Gear Mechanism", 1); //Gear Mechanism is in
    	} else if (direction == -1) {
    		gearManip.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putNumber("Gear Mechanism", -1); //Gear Mechanism is out
    	}
    }  
}