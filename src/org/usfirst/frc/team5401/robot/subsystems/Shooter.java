package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.ShootHigh;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 */
public class Shooter extends PIDSubsystem {
	
	//declare shooter motors
	private Victor leftMotor;
	private Victor rightMotor;
	
	//declare pid stuff?

    // Initialize your subsystem here
    public Shooter() {
    	super("shooter",1,2,3); //initializes pid //XXX Temporary to get rid of error status
    	
    	//initialize shooter motors
    	leftMotor  = new Victor(RobotMap.SHOOTER_LEFT_MOTOR);
    	rightMotor = new Victor(RobotMap.SHOOTER_RIGHT_MOTOR);
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system //set to RPM
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ShootHigh());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	
    	//return PE sensor 
        return 0.0;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	
    	//set motor 
    	//maybe ready to shoot with if 
    }
}