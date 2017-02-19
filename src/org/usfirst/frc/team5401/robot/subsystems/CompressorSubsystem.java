package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CompressorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Compressor compressor;
	
	public CompressorSubsystem(){
		compressor = new Compressor(RobotMap.PCM_ID);
		startCompressor();

		SmartDashboard.putBoolean("Compressor On/Off", true);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startCompressor(){
    	compressor.setClosedLoopControl(true);
    	compressor.start();
    	SmartDashboard.putBoolean("Compressor On/Off", true);
    }
    
    public void stopCompressor(){
    	compressor.stop();
    	SmartDashboard.putBoolean("Compressor On/Off", false);
    }
    
    public void getCompressorStatus(){
    	boolean enabled = compressor.enabled();
    	SmartDashboard.putBoolean("Compressor Enabled", enabled);
    	boolean loopOn = compressor.getClosedLoopControl();
    	SmartDashboard.putBoolean("Compressor in Closed Looop", loopOn);
    	double currentValue = compressor.getCompressorCurrent();
    	SmartDashboard.putNumber("Compressor Current Value", currentValue);
    	boolean pressureSwitch = compressor.getPressureSwitchValue();
    	SmartDashboard.putBoolean("Compressor Pressure Switch On/Off", pressureSwitch);
    }
    
    public boolean isEnabled(){
    	return compressor.enabled();
    }
    
    //Tested and works
    public void switchState(){
    	if (isEnabled()){
    		stopCompressor();
    	} else {
    		startCompressor();
    	}
    }
}

