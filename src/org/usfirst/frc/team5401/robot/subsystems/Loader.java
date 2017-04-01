package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.RobotMap;

/**
 * The Loader Subsystem controls the conveyers and the metering roller on the robot.
 * 
 * This subsystem is used to load balls into the shooter. It runs
 * all the time starting in autonomous.
 */
public class Loader extends Subsystem {
		
	private VictorSP MeteringMotor;
	private VictorSP ConveyorMotor;
	
	private double LOADER_SPEED;
	private double METERING_SPEED;
	private boolean enabled;
	
	/** 
	 *  <p>
	 *  Instantiates the left and right conveyer. Sets the loader speed.
	 *  Puts conveyer status on the SmartDashboard.
	 *  </p>
	 */
	public Loader(){
		//Initialize Loader Motors
		MeteringMotor = new VictorSP(RobotMap.METERING_ROLLER);
		ConveyorMotor = new VictorSP(RobotMap.HOPPER_CONVEYOR);
		
		LOADER_SPEED = -0.7; //TODO Put in RobotMap
		METERING_SPEED = -0.9;
		enabled = false;
		SmartDashboard.putBoolean("Loader Conveyors", enabled);
	}
	
    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
    }
    
    /** Runs the conveyers
     *  
     *  <p>
     *  Sets the conveyers to run at the default speed
     *  </p>
     */
    public void runConveyorsAndMeteringMotor(){
    	enabled = true;
    	MeteringMotor.set(METERING_SPEED);
    	ConveyorMotor.set(LOADER_SPEED);
    }
    
    /** Turns off the conveyers
     * 
     */
    public void stopConveyorsAndMeteringMotor(){
    	enabled = false;
    	MeteringMotor.set(0);
    	ConveyorMotor.set(0);
   }
    
   public boolean isEnabled(){
	   return enabled;
   }
   
   public void switchState(){
	   if (!enabled){
		   runConveyorsAndMeteringMotor();
	   } else {
		   stopConveyorsAndMeteringMotor();
	   }
   }  
}