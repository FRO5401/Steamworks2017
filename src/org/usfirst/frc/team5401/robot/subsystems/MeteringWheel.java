package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5401.robot.commands.RunMeteringWheel;

import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class MeteringWheel extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private VictorSP MeteringMotor;
	private Timer timer; 
	private double METERING_SPEED;
	boolean meteringEnabled;
	
	public MeteringWheel(){
		MeteringMotor = new VictorSP(RobotMap.METERING_ROLLER);
		timer = new Timer();
		METERING_SPEED = 0.95;
		meteringEnabled = false;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new RunMeteringWheel());
    }
    
    public void runMeteringMotor(){
    	meteringEnabled = true;
    	MeteringMotor.set(METERING_SPEED);
    	
    }
    
    /** Turns off the conveyers
     * 
     */
    public void stopMeteringMotor(){
    	meteringEnabled = false;
    	MeteringMotor.set(0);    	
   }
    
   public void timerStop(){
	   timer.stop();
   }
   
   public void timerStart(){
	   timer.start();
   }
   
   public void timerReset(){
	   timer.reset();
   }
  
   public double getTimeInSeconds()
   {
	   return timer.getFPGATimestamp();
   }
}


