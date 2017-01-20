package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.FlyWheelControl;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter;

/**
 *
 */
public class Shooter extends PIDSubsystem {
	
	//declare shooter motors
	private VictorSP motors;
	
	//declare PE sensor
	//private DigitalInput photoswitch;
	
	//declare counter
	private Counter counter;
	
	//declare pid stuff?
	
	private final double MAX_COUNTER_SECONDS = 1;
	
    // Initialize your subsystem here
    public Shooter() {
    	super("shooter",1,2,3); //initializes pid //XXX Temporary to get rid of error status
    	
    	//instantiate shooter motors
    	motors  = new VictorSP(RobotMap.SHOOTER_MOTORS);
    	
    	
    	//instantiate PE
    	//photoswitch = new DigitalInput(RobotMap.PHOTOSWITCH_CHANNEL);
    	
    	//instantiate Counter
    	counter = new Counter(RobotMap.PHOTOSWITCH_CHANNEL);
    	counter.setMaxPeriod(MAX_COUNTER_SECONDS);
    	
    	reset();
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system //set to RPM
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new Command());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	
    	return Math.abs((1 / counter.getPeriod()) * 60.0); //RPM
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	
    	//PID stuff
    	//Shoot
    }
    
    public void startMotors(){
    	motors.set(.5);
    }
    
    public void reset(){
    	counter.reset();
    	stop();
    }
    
    public void stop(){
    	motors.set(0);
    	
    }
}