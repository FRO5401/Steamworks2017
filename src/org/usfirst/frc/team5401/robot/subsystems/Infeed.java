package org.usfirst.frc.team5401.robot.subsystems;

import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Infeed extends Subsystem {
	
	//private VictorSP leftMotor;
	//private VictorSP rightMotor;
	private VictorSP upDownMotor1;
	private VictorSP inOutMotor1;
	
		final double feedSpeed;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//private victorSP; 2 victorSP motors
	
	

	
	public Infeed(){
		//leftMotor  = new VictorSP(RobotMap.infeedOuterMotor_Channel);
		//rightMotor = new VictorSP(RobotMap.infeedInnerMotor_Channel);
		upDownMotor1 = new VictorSP(RobotMap.UP_DOWN_MOTOR);
		inOutMotor1 = new VictorSP(RobotMap.IN_OUT_MOTOR);
		//declare victorSP locations
		
		feedSpeed = .5;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    //ArmDownandUp(motor direction)
    //ArmPull()
    
   /* left just in case InOut is wrong.
    public void runLeft(double direction){
    	leftMotor.set(feedSpeed * direction);
    }
    
    public void runRight(double direction){
    	rightMotor.set(feedSpeed * direction);
    }
    */
    public void runInOut(double direction){
    	inOutMotor1.set(feedSpeed * direction);
    }
    
    public void runUpDown(double direction){
    	upDownMotor1.set(feedSpeed * direction);
    	
    }
    
    public void stopFeed(){
    	inOutMotor1.set(0);   
    }
    
}

