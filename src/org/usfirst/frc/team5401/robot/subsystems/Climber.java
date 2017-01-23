package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team5401.robot.commands.Climb;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
	private DigitalInput climbSwitch;
	
	private final double CLIMB_SPEED;
	
	public Climber(){
		//declare victor location
		climberMotor = new VictorSP(RobotMap.CLIMBER_MOTOR);
		//declare switch locations
		climbSwitch = new DigitalInput(RobotMap.CLIMBER_LIMITSWITCH);

		CLIMB_SPEED = 1;
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Climb());
    }
    

    public void climbUp(){
    	climberMotor.set(CLIMB_SPEED);
    	SmartDashboard.putString("Climb:", "On");
    }
    
    public void climbStop(){
    	climberMotor.set(0);
    	SmartDashboard.putString("Climb:", "Off");
    }
    
    public boolean checkSwitch(){
    	SmartDashboard.putBoolean("Climb Switch:", climbSwitch.get());
    	return climbSwitch.get();
    }
    	
}
    

