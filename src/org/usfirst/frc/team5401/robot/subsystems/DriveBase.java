package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.robot.commands.XboxMove;

/**
 *
 */
public class DriveBase extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP leftDrive;
	private VictorSP rightDrive;
	private DoubleSolenoid gearShifter;
	private Timer driveTimer;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private ADXRS450_Gyro gyro;
	
	public DriveBase(){
		leftDrive  = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR);
		rightDrive = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR);
		gearShifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.DRIVE_SHIFT_IN, RobotMap.DRIVE_SHIFT_OUT);
		leftEncoder = new Encoder(RobotMap.DRIVE_ENC_LEFT_A, RobotMap.DRIVE_ENC_LEFT_B);
		rightEncoder = new Encoder(RobotMap.DRIVE_ENC_RIGHT_A, RobotMap.DRIVE_ENC_RIGHT_B);
		driveTimer = new Timer();
		gyro = new ADXRS450_Gyro();
		
		stop();
		
		SmartDashboard.putString("Drive Gear", "NULL");
		SmartDashboard.putNumber("Delta Time", 0);
		SmartDashboard.putNumber("Velocity of Robot", 0);
		SmartDashboard.putNumber("Gyro", gyro.getAngle());
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new XboxMove());
    }
    
    public void drive(double leftDriveDesired, double rightDriveDesired){
    	leftDrive .set(-1 * leftDriveDesired); //passes desired state to speed controllers
    	rightDrive.set(rightDriveDesired);
    	
    	System.out.println("LEFT DESIRED: " + leftDriveDesired);
    	System.out.println("RIGHT DESIRED: " + rightDriveDesired);
    	
    	SmartDashboard.putNumber("Left Drive", leftDriveDesired);
    	SmartDashboard.putNumber("Right Drive", rightDriveDesired);
    }

    public void stop(){
    	leftDrive .set(0);
    	rightDrive.set(0);
    	
    	SmartDashboard.putNumber("Left Drive", 0);
    	SmartDashboard.putNumber("Right Drive", 0);
    }

    public void shiftGearLowToHigh(){//Meaning Low speed to high speed
    	//Assumes Pneumatic forward/out shifts low to high
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    	
    	SmartDashboard.putString("Drive Gear", "High");
    }

    public void shiftGearHighToLow(){
    	//Assumes Pneumatic reverse/in shifts high to low
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    	
    	SmartDashboard.putString("Drive Gear", "Low");
    }
    
    public double getTimerValue(){
    	double timerValue = driveTimer.get();
    	//Also displays for testings
    	SmartDashboard.putNumber("Delta Time", timerValue);
    	return timerValue;
    }
    
    public void resetTimer(){
    	driveTimer.reset();
    	SmartDashboard.putNumber("Delta Time", 0);
    }
    
    public void startTimer(){
    	driveTimer.start();
    }
    
    public void stopTimer(){
    	driveTimer.stop();
    }

    public double getVelocityOfRobot(){
    	double velocity = (leftEncoder.getRate() + rightEncoder.getRate())/2;
    	//For testing
    	SmartDashboard.putNumber("Velocity of Robot", velocity);
    	return velocity;
    }
    
    public void reportGyro(){
    	gyro.getAngle();
    	SmartDashboard.putNumber("Gyro", gyro.getAngle());
    }
}