package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.LoadShooter;

/**
 *
 */
public class Loader extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
		
	private VictorSP MeteringMotor;
	private VictorSP ConveyorMotor;
		
	private double LOADER_SPEED;
	private boolean enabled;
	
	/** 
	 *  <p>
	 *  This subsystem is used to load balls into the shooter. It runs
	 *  all the time starting in autonomous.
	 *  </p>
	 *  
	 *  <p>
	 *  Instantiates the left and right conveyer. Sets the loader speed.
	 *  Puts conveyer status on the SmartDashboard.
	 *  </p>
	 */
	public Loader(){
		//initialize Victor motor
		MeteringMotor  = new VictorSP(RobotMap.METERING_ROLLER);
		ConveyorMotor = new VictorSP(RobotMap.HOPPER_CONVEYOR);
		
		LOADER_SPEED = -.8;
		SmartDashboard.putBoolean("Loader Conveyors", false);
		enabled = false;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new LoadShooter());
    }
    
    /** Runs the conveyers
     *  
     *  <p> 
     *  NOTE: Fix spelling in code
     *  </p><p>
     *  Sets the conveyers to run at the default speed
     *  </p>
     */
    public void runConveyorsAndMeteringMotor(){
    	enabled = true;
    	MeteringMotor.set(LOADER_SPEED);
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
	   if (enabled){
		   runConveyorsAndMeteringMotor();
	   } else {
		   stopConveyorsAndMeteringMotor();
	   }
   }
    
}

