package org.usfirst.frc.team5401.robot.subsystems;



import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Counter;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

/** Uses PID to lineup and shoot, also uses motors to shoot
*
*	<p> Declares VictorSP, Counter, PIDSource, PIDOutput, RPM, MAX_COUNTER_SECONDS, MOTOR_SPEED</p>
*/
public class Shooter extends Subsystem {
	
	
	
	//declare talon speed controller
	CANTalon _talonMaster;
	CANTalon _talonSlave;

	
	//declare counter
	private Counter counter;
	
	//declare pid stuff?
	
		//private PIDSource source;
		//private PIDOutput output;
		
	private double RPM; 
	
	private double MAX_COUNTER_SECONDS = 100;
	private double MOTOR_SPEED = -.82;
	private double feed_forward;
	
	private double kP, kI, kD;
    
	// Initialize your subsystem here
	   /**
	    * <p> Instantiates motors and counter, as well as resetting at the end </p>
	    * 
	    * @param kP Proportional Gain
	    * @param kI Integral Gain
	    * @param kD Derivative Gain
	    */
	public Shooter() {
	   	//super(kP, kI, kD); //initializes pid //XXX Temporary to get rid of error status
	   	
	   	_talonMaster = new CANTalon(0);
	   	_talonSlave = new CANTalon(1);
		 //XXX Set mode is ok, here, add a set mode to V% in the stop command, set v% to 0 to stop
	    	
	   	_talonMaster.setProfile(0);
	   	_talonMaster.changeControlMode(TalonControlMode.PercentVbus);
	   	_talonMaster.set(MOTOR_SPEED);
	   	_talonSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
    	_talonSlave.set(_talonMaster.getDeviceID());
	   	
	   	SmartDashboard.putNumber("motor_speed", MOTOR_SPEED);
	    	
	 	_talonMaster.getEncPosition();
	   	SmartDashboard.putNumber("Position", _talonMaster.getEncPosition());
	   	_talonMaster.getEncVelocity();
	   	SmartDashboard.putNumber("Velocity",  _talonMaster.getEncVelocity());
	   	_talonMaster.setEncPosition(0);
	   	
	   feed_forward = 0.034;
	   kP = 0;
	   kI = 0;
	   kD = 0;
	   
	   SmartDashboard.putNumber("feed_forward", feed_forward);
	   SmartDashboard.putNumber("kP", kP);
	   SmartDashboard.putNumber("kI", kI);
	   SmartDashboard.putNumber("kD", kD);
	    	
	   	_talonMaster.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	    	
	   	
    	 	
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
    }
    
    public void startMotors(){
    	MOTOR_SPEED = SmartDashboard.getNumber("motor_speed", MOTOR_SPEED);
      feed_forward = SmartDashboard.getNumber("feed_forward", feed_forward);
      kP = SmartDashboard.getNumber("kP", kP);
      kI = SmartDashboard.getNumber("kI", kI);
      kD = SmartDashboard.getNumber("kD", kD);
      _talonMaster.setF(feed_forward);
      _talonMaster.setPID(kP, kI, kD);//XXX Comment this out next
    	_talonMaster.set(MOTOR_SPEED);
     	_talonMaster.getEncPosition();
	   	SmartDashboard.putNumber("Position", _talonMaster.getEncPosition());
	   	_talonMaster.getEncVelocity();
	   	SmartDashboard.putNumber("Velocity",  _talonMaster.getEncVelocity());
    }
    
    
    public void reset(){
    	//counter.reset();
    	
    	RPM = 0;
    	stop();
    	SmartDashboard.putBoolean("Shooter OnOff", false);
    	SmartDashboard.putNumber("RPM", RPM);
    }
    
    /** Sets the shooter motors to 0
    * 
    */
    public void stop(){
    	//_talonMaster.set(0);
    	_talonMaster.changeControlMode(TalonControlMode.PercentVbus);
    	_talonMaster.set(0);
    }
}