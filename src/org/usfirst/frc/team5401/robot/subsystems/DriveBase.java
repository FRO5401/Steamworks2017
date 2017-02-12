package org.usfirst.frc.team5401.robot.subsystems;

/**
 * Steamworks 2017 DriveBase code
 * (c) 2017 Bensalem High School Fightin' Robotic Owls
 */

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.robot.commands.XboxMove;


public class DriveBase extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP leftDrive;
	private VictorSP rightDrive;
//	private DoubleSolenoid gearShifter;
//	private Timer timer;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private ADXRS450_Gyro MainGyro;

	final float DEFAULT_TURN_PRECISION = 0;
	
	public DriveBase(){
		leftDrive  = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR);
		rightDrive = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR);
//		gearShifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.DRIVE_SHIFT_IN, RobotMap.DRIVE_SHIFT_OUT);
		leftEncoder = new Encoder(RobotMap.DRIVE_ENC_LEFT_A, RobotMap.DRIVE_ENC_LEFT_B);
		rightEncoder = new Encoder(RobotMap.DRIVE_ENC_RIGHT_A, RobotMap.DRIVE_ENC_RIGHT_B);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//    	setDefaultCommand(new XboxMove());
    }
    
    public void drive(double leftDriveDesired, double rightDriveDesired){
    	leftDrive .set(-1 * leftDriveDesired); //passes desired state to speed controllers
    	rightDrive.set(rightDriveDesired);
    	
//    	System.out.println("LEFT DESIRED: " + leftDriveDesired);
//    	System.out.println("RIGHT DESIRED: " + rightDriveDesired);
    }

    public void stop(){
    	leftDrive .set(0);
    	rightDrive.set(0);
    }

/*    public void shiftGearLowToHigh(){//Meaning Low speed to high speed
    	//Assumes Pneumatic forward/out shifts low to high
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    }

    public void shiftGearHighToLow(){
    	//Assumes Pneumatic reverse/in shifts high to low
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    }
*/    
    /*
    public double getTimerValue(){

    	double timerValue = timer.get();
    	//Also displays for testing
    	SmartDashboard.putNumber("Time", timerValue);
    	return timerValue;
    }
    
    public void resetTimer(){
    	timer.reset();
    }
    
    public void startTimer(){
    	timer.start();
    }
    public void stopTimer(){
    	timer.stop();
    }

    public double getVelocityOfRobot(){
    	double velocity = (leftEncoder.getRate() + rightEncoder.getRate())/2;
    	//For testing
    	SmartDashboard.putNumber("Velocity of Robot", velocity);
    	return velocity;
    }
*/   
    public boolean autoTurnAngle(float desiredTurnAngle) {
    	float CurrentAngle = reportGyro();
    	boolean finished = false;
    	if (Math.abs(desiredTurnAngle) <= RobotMap.AUTO_TURN_THRESHOLD){
    		//DesiredTurnAngle too small
    		finished = true;
    	} else {
    		if (desiredTurnAngle > 0 && (CurrentAngle < Math.abs(desiredTurnAngle) - RobotMap.AUTO_TURN_THRESHOLD)){
    			drive(RobotMap.AUTO_TURN_SPEED * RobotMap.AUTO_TURN_PRECISION, -RobotMap.AUTO_TURN_SPEED * RobotMap.AUTO_TURN_PRECISION);
    			finished = false;
    		} else if (desiredTurnAngle < 0 && (CurrentAngle > RobotMap.AUTO_TURN_THRESHOLD - Math.abs(desiredTurnAngle))) {
    			drive(-RobotMap.AUTO_TURN_SPEED * RobotMap.AUTO_TURN_PRECISION, RobotMap.AUTO_TURN_SPEED * RobotMap.AUTO_TURN_PRECISION);
    			finished = false;
    		} else { //error or exactly 0
    			//Finished
    			finished = true;
    		}
    	}
    	return finished;
    }
    
    public float reportGyro()
    {
    	//This adjusts for gyro creep which is current nonexistent
     	float Angle = (GyroScalar * MainGyro.getAngle());
       	double Time = TimeCount.get();
       	float AdjAngle = Angle - (GyroLinearAdj * Time + GyroOffset);//Compensates for gyro creep - basically subtracts out mx+b the linear creep function
      	return AdjAngle;
    
  	//error: cannot convert double to float, so I used casting
    	return (float) MainGyro.getAngle();
    }



    
}

