package org.usfirst.frc.team5401.robot.subsystems;

import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDDriveBase extends PIDSubsystem {
	
	private VictorSP leftDrive1;
	private VictorSP rightDrive1;
	private VictorSP leftDrive2;
	private VictorSP rightDrive2;
	
	private Encoder leftEncoder;
	private Encoder rightEncoder;
    // Initialize your subsystem here
    public PIDDriveBase() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super(1,0,0);
    	
    	leftDrive1   = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR_1);
		rightDrive1  = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR_1);
		leftDrive2  = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR_2);
		rightDrive2 = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR_2);
		
		leftEncoder = new Encoder(RobotMap.DRIVE_ENC_LEFT_A, RobotMap.DRIVE_ENC_LEFT_B, true, Encoder.EncodingType.k4X);
		rightEncoder = new Encoder(RobotMap.DRIVE_ENC_RIGHT_A, RobotMap.DRIVE_ENC_RIGHT_B, true, Encoder.EncodingType.k4X);
		
		SmartDashboard.putNumber("DriveStraight Distance", 0);
		
		setAbsoluteTolerance(.5);
		getPIDController().setContinuous(false);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(double leftDriveDesired, double rightDriveDesired){
    	leftDrive1 .set(leftDriveDesired); //passes desired state to speed controllers
    	rightDrive1.set(-1 * rightDriveDesired);
    	leftDrive2 .set(leftDriveDesired);
    	rightDrive2.set(-1 * rightDriveDesired);
    }
    
    public double getEncoderDistance() {
    	double leftDistance = leftEncoder.getDistance();
    	double rightDistance = rightEncoder.getDistance();
    	double encoderDistance = (leftDistance + rightDistance)/2;
    	return encoderDistance;
    }
    
    public void encoderReset() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return getEncoderDistance();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	SmartDashboard.putNumber("PIDOutput", output);
    	drive(output, output);
    }
}
