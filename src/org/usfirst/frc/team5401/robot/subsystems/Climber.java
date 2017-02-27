package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team5401.robot.commands.Climb;


/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	/*
	 * Private victor; 1 victor smotor
	 * UpperLimitSwitch;
	 * TouchLimitSwitch;
	 */
	private VictorSP climberMotor;
	
	private double SPEED;
	
	public Climber(){
		climberMotor = new VictorSP(RobotMap.CLIMBER_MOTOR);

		SPEED = .8;
		SmartDashboard.putNumber("ClimbSpeed", SPEED);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    

    public void climbUp(){
    	climberMotor.set(SPEED);
    }
    
    public void climbStop(){
    	climberMotor.set(0);
    }	
}
    

