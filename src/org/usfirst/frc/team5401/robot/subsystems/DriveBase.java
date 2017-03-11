package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid;

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
	double GYRO_OFFSET;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP leftDrive1;
	private VictorSP rightDrive1;
	private VictorSP leftDrive2;
	private VictorSP rightDrive2;

	private DoubleSolenoid gearShifter;
	
	private Encoder encoder;
	private ADXRS450_Gyro gyro;
	
	public DriveBase(){
		
		LOW_GEAR_LEFT_DPP = -0.019125;
		LOW_GEAR_RIGHT_DPP = .020268;//<--- for comp //0.019125; //<--- for practice

		HIGH_GEAR_LEFT_DPP = -0.0192999;
		HIGH_GEAR_RIGHT_DPP = .019423; //<--- for comp //0.0192999;<--- for practice
		
		leftDrive1   = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR_1);
		rightDrive1  = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR_1);
		leftDrive2  = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR_2);
		rightDrive2 = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR_2);
		gearShifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.DRIVE_SHIFT_IN, RobotMap.DRIVE_SHIFT_OUT);
		encoder = new Encoder(RobotMap.DRIVE_ENC_A, RobotMap.DRIVE_ENC_B, true, Encoder.EncodingType.k4X);
		//																					XXX if this was false, DPP doesn't have to be negative
		
		gyro = new ADXRS450_Gyro();
		
		SmartDashboard.putString("Transmission_text", "Transmission");
		SmartDashboard.putString("HighGear_text", "GREEN = High");
		SmartDashboard.putString("LowGear_text" , "RED = Low");
		if ((DoubleSolenoid.Value.kForward).equals(gearShifter.get())){
			SmartDashboard.putNumber("Transmission", -1); //Transmisison is High
		} else {
			SmartDashboard.putNumber("Transmission", 1); //Transmisison is Low
		}
		
		SmartDashboard.putNumber("Robot Velocity", 0);
		SmartDashboard.putNumber("Gyro", reportGyro());
		
		SmartDashboard.putNumber("Encoder Raw" , encoder.get());
		SmartDashboard.putNumber("Encoder Adj", encoder.getDistance());
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public void drive(double leftDriveDesired, double rightDriveDesired){
    	leftDrive1 .set(leftDriveDesired); //passes desired state to speed controllers
    	rightDrive1.set(-1* rightDriveDesired);
    	leftDrive2.set(leftDriveDesired);
    	rightDrive2.set(-1 * rightDriveDesired);
    	
    	SmartDashboard.putNumber("Encoder Raw" , encoder.get());
		SmartDashboard.putNumber("Encoder Adj", encoder.getDistance());
    }

    public void stop(){
    	leftDrive1 .set(0);
    	rightDrive1.set(0);
    	leftDrive2.set(0);
    	rightDrive2.set(0);
    	SmartDashboard.putNumber("Robot Velocity", 0);
    }

    public void shiftGearLowToHigh(){//Meaning Low speed to high speed
    	//Assumes Pneumatic forward/out shifts low to high
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    	encoder.setDistancePerPulse(HIGH_GEAR_RIGHT_DPP);
    	SmartDashboard.putNumber("Transmission", -1); //Transmisison is High
    	System.out.println("Shifting Drive Gear to High Gear");
    }

    public void shiftGearHighToLow(){
    	//Assumes Pneumatic reverse/in shifts high to low
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    	encoder.setDistancePerPulse(LOW_GEAR_RIGHT_DPP);
    	SmartDashboard.putNumber("Transmission", 1); //Transmisison is Low
    	System.out.println("Shifting Drive Gear to Low Gear");
    }
    public double getVelocityOfRobot(){
    	double velocity = (Math.abs(encoder.getRate()));
    	//For testing
    	SmartDashboard.putNumber("Robot Velocity", velocity);
    	return velocity;
    }

    
    public void setDPPLowGear(){
    	encoder.setDistancePerPulse(LOW_GEAR_RIGHT_DPP);
    }
    
    public void setDPPHighGear(){
    	encoder.setDistancePerPulse(HIGH_GEAR_RIGHT_DPP);
    }
    
    public double getEncoderDistance(){
    	double distanceRaw = encoder.get();
    	SmartDashboard.putNumber("Encoder Raw", distanceRaw);
    	double distance = encoder.getDistance();
    	SmartDashboard.putNumber("Encoder Adj", distance);
    	return distance;
    }
    
    public void encoderReset(){
    	encoder.reset();
    }
    
    public double reportGyro(){
    	double currentAngle = gyro.getAngle();
    	SmartDashboard.putNumber("Current Angle", currentAngle);
    	return currentAngle;
    }
    
    public void recalibrateGyro(){
    	gyro.calibrate();
    }
    
    public void resetGyro(){
    	gyro.reset();
    }

}