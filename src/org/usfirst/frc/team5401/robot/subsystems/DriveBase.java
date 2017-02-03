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
	
	double LOW_GEAR_LEFT_DPP;
	double LOW_GEAR_RIGHT_DPP;
	double HIGH_GEAR_LEFT_DPP;
	double HIGH_GEAR_RIGHT_DPP;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP leftDrive;
	private VictorSP rightDrive;
	private DoubleSolenoid gearShifter;
	private Timer driveTimer;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
//	private ADXRS450_Gyro gyro;
	
	public DriveBase(){
		
		LOW_GEAR_LEFT_DPP = -1/50.49238;//NEED TO CHANGE
		LOW_GEAR_RIGHT_DPP = 1/50.49238;//NEED TO CHANGE

		HIGH_GEAR_LEFT_DPP = -1/51.27586;//NEED TO CHANGE
		HIGH_GEAR_RIGHT_DPP = 1/51.27586;//NEED TO CHANGE
		
		leftDrive  = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR);
		rightDrive = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR);
		gearShifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.DRIVE_SHIFT_IN, RobotMap.DRIVE_SHIFT_OUT);
		leftEncoder = new Encoder(RobotMap.DRIVE_ENC_LEFT_A, RobotMap.DRIVE_ENC_LEFT_B, true, Encoder.EncodingType.k4X);
		//																					vvv if this was false, DPP doesn't have to be negative
		rightEncoder = new Encoder(RobotMap.DRIVE_ENC_RIGHT_A, RobotMap.DRIVE_ENC_RIGHT_B, true, Encoder.EncodingType.k4X);
		driveTimer = new Timer();

//		gyro = new ADXRS450_Gyro();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new XboxMove());
    }
    
    public void drive(double leftDriveDesired, double rightDriveDesired){
    	leftDrive .set(leftDriveDesired); //passes desired state to speed controllers
    	rightDrive.set(-1* rightDriveDesired);
    	
    	System.out.println("LEFT DESIRED: " + leftDriveDesired);
    	System.out.println("RIGHT DESIRED: " + rightDriveDesired);
    	
    	
    }

    public void stop(){
    	leftDrive .set(0);
    	rightDrive.set(0);
    	

    }

    public void shiftGearLowToHigh(){//Meaning Low speed to high speed
    	//Assumes Pneumatic forward/out shifts low to high
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    	
    }

    public void shiftGearHighToLow(){
    	//Assumes Pneumatic reverse/in shifts high to low
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    	
    }
/*    
    public double getTimerValue(){
    	double timerValue = driveTimer.get();
    	//Also displays for testings
    	
    	return timerValue;
    }
    
    public void resetTimer(){
    	driveTimer.reset();
    	
    }
    
    public void startTimer(){
    	driveTimer.start();
    }
    
    public void stopTimer(){
    	driveTimer.stop();
    }
*/
    public double getVelocityOfRobot(){
    	double velocity = (Math.abs(leftEncoder.getRate()) + Math.abs(rightEncoder.getRate()))/2;
    	//For testing
    	SmartDashboard.putNumber("Velocity (With DPP", velocity);
    	return velocity;
    }
    
    public void setDPPLowGear(){
    	leftEncoder.setDistancePerPulse(LOW_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(LOW_GEAR_RIGHT_DPP);
    }
    
    public void setDPPHighGear(){
    	leftEncoder.setDistancePerPulse(HIGH_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(HIGH_GEAR_RIGHT_DPP);
    }
    
    public void getEncoderValue(){
    	double leftDistanceRaw = leftEncoder.get();
    	double rightDistanceRaw = rightEncoder.get();
    	SmartDashboard.putNumber("leftDistanceRaw", leftDistanceRaw);
    	SmartDashboard.putNumber("rightDistanceRaw", rightDistanceRaw);
    	double leftDistance = leftEncoder.getDistance();
    	double rightDistance = rightEncoder.getDistance();
    	SmartDashboard.putNumber("leftDistance", leftDistance);
    	SmartDashboard.putNumber("rightDistance", rightDistance);
    }
/*    
    public void reportGyro(){
    	gyro.getAngle();
    }
*/
}