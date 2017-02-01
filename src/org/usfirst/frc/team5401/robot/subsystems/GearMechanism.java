package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearMechanism extends Subsystem {

	private DoubleSolenoid gearManip;
	private Compressor compressor;

	public GearMechanism(){
		compressor = new Compressor(RobotMap.PCM_ID);
		gearManip = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.GEAR_MANIPULATOR_IN, RobotMap.GEAR_MANIPULATOR_OUT);
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
    	SmartDashboard.putNumber("Gear Mech:", direction);
    }
    
    public void startCompressor(){
    	compressor.setClosedLoopControl(true);
    	compressor.start();
    }
    
    public void getCompressorStatus(){
    	boolean enabled = compressor.enabled();
    	SmartDashboard.putBoolean("enabled", enabled);
    	boolean loopOn = compressor.getClosedLoopControl();
    	SmartDashboard.putBoolean("In closed Loop control", loopOn);
    	double currentValue = compressor.getCompressorCurrent();
    	SmartDashboard.putNumber("Current", currentValue);
    	boolean pressureSwitch = compressor.getPressureSwitchValue();
    	SmartDashboard.putBoolean("Pressure Switch", pressureSwitch);
    }
    
}

