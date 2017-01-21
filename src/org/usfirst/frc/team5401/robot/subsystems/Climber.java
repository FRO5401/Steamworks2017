package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

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
	private DigitalInput climbSwitch;
	//private boolean limitSwitch;
	private final double SPEED;
	
	
	
	
	public Climber(){
		//declare victor location
		climberMotor = new VictorSP(RobotMap.CLIMBER_MOTOR);
		//declare switch locations
		climbSwitch = new DigitalInput(RobotMap.CLIMBER_LIMITSWITCH);
		//limitSwitch = false;
		SPEED = 1;
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Climb());
    }
    

    public void climbUp(){
    	climberMotor.set(SPEED);
    }
    
    public void climbStop(){
    	climberMotor.set(0);
    }
    
    public boolean checkSwitch(){
    	return climbSwitch.get();
    }
    	
}
    

