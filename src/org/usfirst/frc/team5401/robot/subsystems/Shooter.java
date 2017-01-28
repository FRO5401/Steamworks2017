package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;


import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Counter;

/**
 *
 */
public class Shooter extends PIDSubsystem {
	
	//declare shooter motors
	private VictorSP motors;
	
	//declare counter
	private Counter counter;
	
	//declare pid stuff?
	
	private PIDSource source;
	private PIDOutput output;
	
	private double RPM;
	
	private double MAX_COUNTER_SECONDS = 1;
	private double MOTOR_SPEED = 0.5;
    
	// Initialize your subsystem here
    public Shooter(double kP, double kI, double kD) {
    	super(kP, kI, kD); //initializes pid //XXX Temporary to get rid of error status
    	
    	//instantiate shooter motors
    	motors = new VictorSP(RobotMap.SHOOTER_MOTORS);
    	
    	//instantiate counter
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
        //setDefaultCommand();
    }

    protected double returnPIDInput() {
    	if(counter.getStopped()){
    		RPM = 0;
    	} else {
    		RPM = Math.abs((1/counter.getPeriod()) * 60); //RPM
    	}
    	SmartDashboard.putNumber("RPM", RPM);
    	return RPM;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	
    	//PID stuff
    	motors.set(output);
    }
    
    public void startMotors(){
    	motors.set(MOTOR_SPEED);
    }
    
    public void reset(){
    	counter.reset();
    	RPM = 0;
    	stop();
    	SmartDashboard.putBoolean("Shooter OnOff", false);
    	SmartDashboard.putNumber("RPM", RPM);
    }
    
    public void stop(){
    	motors.set(0);
    	setSetpoint(0);
    }
    
}