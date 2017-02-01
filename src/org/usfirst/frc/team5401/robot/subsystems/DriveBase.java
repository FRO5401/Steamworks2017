package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;

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
//	private DoubleSolenoid gearShifter;
//	private Timer timer;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	
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
}