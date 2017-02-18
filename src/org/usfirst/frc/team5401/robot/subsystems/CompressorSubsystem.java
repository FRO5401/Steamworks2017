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
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startCompressor(){
    	compressor.setClosedLoopControl(true);
    	compressor.start();
    }
    
    public void stopCompressor(){
    	compressor.stop();
    }
    
    public void getCompressorStatus(){
    	//boolean enabled = compressor.enabled();
    	//SmartDashboard.putBoolean("Compressor Enabled", enabled);
    	//boolean loopOn = compressor.getClosedLoopControl();
    	//SmartDashboard.putBoolean("Compressor in Closed Looop", loopOn);
    	//double currentValue = compressor.getCompressorCurrent();
    	//SmartDashboard.putNumber("Compressor Current Value", currentValue);
    	//boolean pressureSwitch = compressor.getPressureSwitchValue();
    	//SmartDashboard.putBoolean("Pressure Switch On/Off", pressureSwitch);
    }
}

