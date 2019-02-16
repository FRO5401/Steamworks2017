package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class Loader extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private VictorSP Conveyors;
	private VictorSP meteringBar;
	private double METERING_SPEED;
	private double LOADER_SPEED;
	
	private boolean enabled;
	
	public Loader(){
	
	Conveyors = new VictorSP(RobotMap.HOPPER_BELTS);
	meteringBar = new VictorSP(RobotMap.METERING_BAR);
	
	LOADER_SPEED = -0.8;
	METERING_SPEED = -0.85;
	enabled = false;

	}
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
	}
    	public void runLoader(){
    		enabled = true; 
    		Conveyors.set(LOADER_SPEED);
    		meteringBar.set(METERING_SPEED);
    	
    }
    	
    	public void stopLoader(){
    		enabled = false;
    		Conveyors.set(0);
    		meteringBar.set(0);
    	}
    	
    	public boolean isEnabled(){
    		return enabled;
    	}
    	
    	public void switchState(){
    		if(!enabled){
    			runLoader();
    		}
    		else{
    			stopLoader();
    		}
    	}
}



