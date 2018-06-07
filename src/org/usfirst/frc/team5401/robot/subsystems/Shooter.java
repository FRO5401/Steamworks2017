package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import com.ctre.CANTalon;
//import com.ctre.CANTalon.TalonControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/** Uses PID to lineup and shoot, also uses motors to shoot
*
*	<p> MOTOR_SPEED</p>
*/
public class Shooter extends Subsystem {
	
	//declare talon speed controller
	TalonSRX _talonMaster;
	TalonSRX _talonSlave;	
	
	
	private double VOLTAGE_MOTOR_SPEED = -7.8 / 10;
	private double PID_MOTOR_SPEED = -19900; //-19875 //-23750 original
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
	   	
	   	_talonMaster = new TalonSRX(0);
	   	_talonSlave = new TalonSRX(1);
	    
	   	/*
    	_talonMaster.changeControlMode(TalonControlMode.Speed); //XXX Testing with this
//	   	_talonMaster.setProfile(0);//XXX  Probably don't need this, but if shooter doesn't work try putting this back in first
	   	_talonSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
    	_talonSlave.set(_talonMaster.getDeviceID());
    	*/
	   	_talonMaster.set(ControlMode.Velocity, 0);
	   	_talonSlave.set(ControlMode.Follower, _talonMaster.getDeviceID());
	   	
	   	SmartDashboard.putNumber("motor_speed", MOTOR_SPEED);
	   	SmartDashboard.putBoolean("AutoTargeting", false);
	    
	   	/*
	 	_talonMaster.getEncPosition();
	   	SmartDashboard.putNumber("Position", _talonMaster.getEncPosition());
	   	_talonMaster.getEncVelocity();
	   	SmartDashboard.putNumber("Velocity",  _talonMaster.getEncVelocity());
	   	SmartDashboard.putNumber("Velocity Raw",  _talonMaster.getEncVelocity()); //not to be used for graph
	   	_talonMaster.setEncPosition(0);
	   	*/
	   	_talonMaster.getSensorCollection().getQuadraturePosition();
	   	SmartDashboard.putNumber("Position", _talonMaster.getSensorCollection().getQuadraturePosition());
	   	_talonMaster.getSensorCollection().getQuadratureVelocity();
	   	SmartDashboard.putNumber("Velocity",  _talonMaster.getSensorCollection().getQuadratureVelocity());
	   	SmartDashboard.putNumber("Velocity Raw",  _talonMaster.getSensorCollection().getQuadratureVelocity()); //not to be used for graph
	   	_talonMaster.getSensorCollection().setQuadraturePosition(0, 10); //second parameter is timeout value in ms. 10 is default value
	   	
	   feed_forward = .033;
	   //kP = SmartDashboard.getNumber("kP", kP);
	   //kI = SmartDashboard.getNumber("kI", kI);
	   //kD = SmartDashboard.getNumber("kD", kD);
	   
	   
	   kP = .1;
	   kI = .000005;//4
	   kD = 2;//was 4 <--- if 1 is not used on practice field, use 2
	   //MOTOR_SPEED = SmartDashboard.getNumber("velocity", MOTOR_SPEED);
	   
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
	    	
	   //_talonMaster.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	   _talonMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10); //second parameter is timeout value in ms. 10 is default value
	   	THRESH = 200;
	   	
    	reset();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand();
    }
    
    public void startMotors(){
    	if (pidEnabled) {
    		_talonMaster.config_kP(0, kP, 10);
    		_talonMaster.config_kI(0, kD, 10);
    		_talonMaster.config_kD(0, kD, 10);
    		_talonMaster.config_kF(0, feed_forward, 10);
    		_talonMaster.config_IntegralZone(0, Izone, 10);
    		_talonMaster.set(ControlMode.Velocity, 0);
    		System.out.println("mode: Velocity");
    	} else {
    		_talonMaster.set(ControlMode.PercentOutput, MOTOR_SPEED);
    		System.out.println("mode: PercentOutput");
    	}
    	
    	
      //MOTOR_SPEED = SmartDashboard.getNumber("motor_speed", MOTOR_SPEED);
      //feed_forward = SmartDashboard.getNumber("feed_forward", feed_forward);
      SmartDashboard.putNumber("feed_forward_test", feed_forward);
     /** Uncomment to get PID values from the dashboard **/
      //kP = SmartDashboard.getNumber("kP", kP);
      //kI = SmartDashboard.getNumber("kI", kI);
      //kD = SmartDashboard.getNumber("kD", kD);
      //MOTOR_SPEED = SmartDashboard.getNumber("Velocity", MOTOR_SPEED);
    
      //_talonMaster.setPID(kP,  kI, kD, feed_forward, Izone, rampRate, channel); //in percentVBus this is ignored
      //_talonMaster.set(MOTOR_SPEED);
	    SmartDashboard.putNumber("Position", _talonMaster.getSensorCollection().getQuadraturePosition());
	    SmartDashboard.putNumber("Velocity",  _talonMaster.getSensorCollection().getQuadratureVelocity());
	    SmartDashboard.putNumber("Velocity Raw",  _talonMaster.getSensorCollection().getQuadratureVelocity()); //not to be used for graph
	   	
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
    	_talonMaster.set(ControlMode.PercentOutput, 0);
    	compressorEnabled = false;
    }
    
    public double getTargetSpeed(){
    	return MOTOR_SPEED;
    }
    
    public double getVelocity(){
    	return _talonMaster.getSensorCollection().getQuadratureVelocity();
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
    		_talonMaster.set(ControlMode.PercentOutput, MOTOR_SPEED);
    		//MOTOR_SPEED = VOLTAGE_MOTOR_SPEED;
    		pidEnabled = false;
    		System.out.println("switch to Voltage");
    	} else {
    		_talonMaster.set(ControlMode.Velocity, MOTOR_SPEED);
    		pidEnabled = true;
    		System.out.println("switch to Speed");
    	}
    	SmartDashboard.putBoolean("PID_Enabled", pidEnabled);
    }

    public void printReadyToShoot(){
    	if (_talonMaster.getSensorCollection().getQuadratureVelocity() < MOTOR_SPEED + THRESH || _talonMaster.getSensorCollection().getQuadratureVelocity() > MOTOR_SPEED - THRESH){
    		SmartDashboard.putBoolean("Ready to Shoot", true);        	
    	} else {
    		SmartDashboard.putBoolean("Ready to Shoot", false);
    	}
    }
}