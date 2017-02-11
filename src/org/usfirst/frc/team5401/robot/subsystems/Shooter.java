package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;


import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Counter;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;


/** Uses PID to lineup and shoot, also uses motors to shoot
 *
 *	<p> Declares VictorSP, Counter, PIDSource, PIDOutput, RPM, MAX_COUNTER_SECONDS, MOTOR_SPEED</p>
 */
public class Shooter extends PIDSubsystem {
	
	
	//declare talon speed controller
	CANTalon _talon = new CANTalon(0);
	
	//declare shooter motors
	private VictorSP motors;
	
	//declare counter
	private Counter counter;
	
	//declare pid stuff?
	
	private PIDSource source;
	private PIDOutput output;
	
	private double RPM;
	
	private double MAX_COUNTER_SECONDS = 100;
	private double MOTOR_SPEED = 0.5;
    
	// Initialize your subsystem here
    /**
     * <p> Instantiates motors and counter, as well as resetting at the end </p>
     * 
     * @param kP Proportional Gain
     * @param kI Integral Gain
     * @param kD Derivative Gain
     */
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
    		System.out.println("counter is stopped, rpm is 0");
    	} else {
    		RPM = Math.abs((1/counter.getPeriod()) * 60); //RPM
    		System.out.println("counter works, rpm is below"); //XXX USING STARTMOTORS
    	}
    	SmartDashboard.putNumber("RPM", RPM);
    	System.out.println("RPM: " + RPM);
    	return RPM;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	
    	//PID stuff
    	motors.set(output);
    }
    
    /** Starts the shooter motors
     * 
     */
    public void startMotors(){
    	motors.set(MOTOR_SPEED);
    /*	if(counter.getStopped()){
    		RPM = 0;
    		System.out.println("counter is stopped, rpm is 0");
    	} else {
    		RPM = Math.abs((1/counter.getPeriod()) * 60); //RPM
    		System.out.println("counter works, rpm is below");
    	} */
    	//counter.reset();
    	//System.out.println("counter.get(): " + counter.get());
    	RPM = counter.getPeriod();
    	SmartDashboard.putNumber("RPM", RPM);
    	System.out.println("RPM: " + RPM);
    }
    
    /** Resets the shooter motors, then sends SmartDashboard the values
     * 
     */
    public void reset(){
    	counter.reset();
    	RPM = 0;
    	stop();
    	SmartDashboard.putBoolean("Shooter OnOff", false);
    	SmartDashboard.putNumber("RPM", RPM);
    }
    
    /** Sets the shooter motors to 0
     * 
     */
    public void stop(){
    	motors.set(0);
    	//setSetpoint(0);
    }
    
}