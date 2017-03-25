package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

/** Uses PID to lineup and shoot, also uses motors to shoot
*
*	<p> MOTOR_SPEED</p>
*/
public class Shooter extends Subsystem {
	
	//declare talon speed controller
	CANTalon _talonMaster;
	CANTalon _talonSlave;	
	
	
	private double VOLTAGE_MOTOR_SPEED = -7.8;
	private double PID_MOTOR_SPEED = -19875;//-23750 original
	private double MOTOR_SPEED = PID_MOTOR_SPEED;
	private double feed_forward;
	
	private double kP, kI, kD; //TODO Put in RobotMap

    private int Izone;
    private double rampRate;
    private int channel;
    private boolean compressorEnabled;
    private boolean pidEnabled;
    private int THRESH;
    
	// Initialize your subsystem here
	   /**
	    * <p> Instantiates motors and counter, as well as resetting at the end </p>
	    * 
	    * @param kP Proportional Gain
	    * @param kI Integral Gain
	    * @param kD Derivative Gain
	    */
	public Shooter() {
	   	
	   	_talonMaster = new CANTalon(0);
	   	_talonSlave = new CANTalon(1);
	    	
    	_talonMaster.changeControlMode(TalonControlMode.Speed); //XXX Testing with this
//	   	_talonMaster.setProfile(0);//XXX  Probably don't need this, but if shooter doesn't work try putting this back in first
	   	_talonSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
    	_talonSlave.set(_talonMaster.getDeviceID());
	   	
	   	SmartDashboard.putNumber("motor_speed", MOTOR_SPEED);
	   	SmartDashboard.putBoolean("AutoTargeting", false);
	    	
	 	_talonMaster.getEncPosition();
	   	SmartDashboard.putNumber("Position", _talonMaster.getEncPosition());
	   	_talonMaster.getEncVelocity();
	   	SmartDashboard.putNumber("Velocity",  _talonMaster.getEncVelocity());
	   	_talonMaster.setEncPosition(0);
	   	
	   	
	   feed_forward = .033;
	   //kP = SmartDashboard.getNumber("kP", kP);
	   //kI = SmartDashboard.getNumber("kI", kI);
	   //kD = SmartDashboard.getNumber("kD", kD);
	   
	   
	   kP = .1;
	   kI = .000000005;
	   kD = 2;
	   
	   
	   Izone = 0;
	   rampRate = 10.23;
	   channel = 0;
	  
	   pidEnabled = true;
	   SmartDashboard.putBoolean("PID_Enabled", pidEnabled);

	   SmartDashboard.putNumber("feed_forward", feed_forward);
	   SmartDashboard.putNumber("kP", kP);
	   SmartDashboard.putNumber("kI", kI);
	   SmartDashboard.putNumber("kD", kD);
	   SmartDashboard.putNumber("rampRate", rampRate);
	    	
	   	_talonMaster.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	
	   	THRESH = 200;
	   	
    	reset();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand();
    }
    
    public void startMotors(){
    	if (pidEnabled) {
    		_talonMaster.changeControlMode(TalonControlMode.Speed);
    		System.out.println("mode: Speed");
    	} else {
    		_talonMaster.changeControlMode(TalonControlMode.Voltage);
    		System.out.println("mode: Voltage");
    	}
      //MOTOR_SPEED = SmartDashboard.getNumber("motor_speed", MOTOR_SPEED);
      //feed_forward = SmartDashboard.getNumber("feed_forward", feed_forward);
      SmartDashboard.putNumber("feed_forward_test", feed_forward);
      /** Uncomment to get PID values from the dashboard **/
      //kP = SmartDashboard.getNumber("kP", kP);
      //kI = SmartDashboard.getNumber("kI", kI);
      //kD = SmartDashboard.getNumber("kD", kD);
    
      _talonMaster.setPID(kP,  kI, kD, feed_forward, Izone, rampRate, channel); //in percentVBus this is ignored
      _talonMaster.set(MOTOR_SPEED);
	    SmartDashboard.putNumber("Position", _talonMaster.getEncPosition());
	    SmartDashboard.putNumber("Velocity",  _talonMaster.getEncVelocity());
	   	
	  compressorEnabled = true;
    }
    
    
    public void reset(){
    	stop();
    	SmartDashboard.putBoolean("AutoTargeting", false);
    	SmartDashboard.putBoolean("Shooter OnOff", false);
    	compressorEnabled = false;
    }
    
    /** Sets the shooter motors to 0
    * 
    */
    public void stop(){
    	_talonMaster.changeControlMode(TalonControlMode.PercentVbus);
    	_talonMaster.set(0);
    	compressorEnabled = false;
    }
    
    public double getTargetSpeed(){
    	return MOTOR_SPEED;
    }
    
    public double getVelocity(){
    	return _talonMaster.getEncVelocity();
    }
    
    public boolean isEnabled(){
    	return compressorEnabled;
    }
    
    public void switchState(){
    	if (compressorEnabled){
    		reset();
    	} else {
    		startMotors();
    	}
    }
    
    public void shootOverrideSwitchState() {
    	if (pidEnabled) {
    		_talonMaster.changeControlMode(TalonControlMode.Voltage);
    		MOTOR_SPEED = VOLTAGE_MOTOR_SPEED;
    		pidEnabled = false;
    		System.out.println("switch to Voltage");
    	} else {
    		_talonMaster.changeControlMode(TalonControlMode.Speed);
    		MOTOR_SPEED = PID_MOTOR_SPEED;
    		pidEnabled = true;
    		System.out.println("switch to Speed");
    	}
    	SmartDashboard.putBoolean("PID_Enabled", pidEnabled);
    }

    public void printReadyToShoot(){
    	if (_talonMaster.getEncVelocity() < MOTOR_SPEED + THRESH || _talonMaster.getEncVelocity() > MOTOR_SPEED - THRESH){
    		SmartDashboard.putBoolean("Ready to Shoot", true);        	
    	} else {
    		SmartDashboard.putBoolean("Ready to Shoot", false);
    	}
    }
}