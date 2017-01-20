package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.FlyWheelControl;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter;

/**
 *
 */
public class Shooter extends PIDSubsystem {
	
	//declare shooter motors
	private Victor leftMotor;
	private Victor rightMotor;
	
	//declare PE sensor
	//private DigitalInput photoswitch;
	
	//declare counter
	private Counter counter;
	
	//declare pid stuff?

    // Initialize your subsystem here
    public Shooter() {
    	super("shooter",1,2,3); //initializes pid //XXX Temporary to get rid of error status
    	
    	//instantiate shooter motors
    	leftMotor  = new Victor(RobotMap.SHOOTER_LEFT_MOTOR);
    	rightMotor = new Victor(RobotMap.SHOOTER_RIGHT_MOTOR);
    	
    	//instantiate PE
    	//photoswitch = new DigitalInput(RobotMap.PHOTOSWITCH_CHANNEL);
    	
    	//instantiate Counter
    	counter = new Counter(RobotMap.PHOTOSWITCH_CHANNEL);
    	counter.reset();
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system //set to RPM
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new FlyWheelControl());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	
    	int count = 0;
    	
    	//return counter 
        return count;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	
    	//set motor 
    	//maybe ready to shoot with if 
    }
    
    public void startMotors(){
    	leftMotor .set(.5);
    	rightMotor.set(.5);
    }
    
    public void reset(){
    	counter.reset();
    	stop();
    }
    
    public void stop(){
    	leftMotor .set(0);
    	rightMotor.set(0);
    }
}