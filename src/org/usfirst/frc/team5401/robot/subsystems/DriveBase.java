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
///* XXX Comment this line to use Double Encoder
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
	
	private Encoder leftEncoder;
	private Encoder rightEncoder;
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
		leftEncoder = new Encoder(RobotMap.DRIVE_ENC_LEFT_A, RobotMap.DRIVE_ENC_LEFT_B, true, Encoder.EncodingType.k4X);
		//																					vvv if this was false, DPP doesn't have to be negative
		rightEncoder = new Encoder(RobotMap.DRIVE_ENC_RIGHT_A, RobotMap.DRIVE_ENC_RIGHT_B, true, Encoder.EncodingType.k4X);
		
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
		
		SmartDashboard.putNumber("Left Enc Raw" , leftEncoder.get());
		SmartDashboard.putNumber("Right Enc Raw", rightEncoder.get());
		SmartDashboard.putNumber("Left Enc Adj" , leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Enc Adj", rightEncoder.getDistance());
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//    	setDefaultCommand(new XboxMove());
    }
    
    public void drive(double leftDriveDesired, double rightDriveDesired){
    	leftDrive1 .set(leftDriveDesired); //passes desired state to speed controllers
    	rightDrive1.set(-1* rightDriveDesired);
    	leftDrive2.set(leftDriveDesired);
    	rightDrive2.set(-1 * rightDriveDesired);
    	
    	SmartDashboard.putNumber("Left Enc Raw" , leftEncoder.get());
		SmartDashboard.putNumber("Right Enc Raw", rightEncoder.get());
		SmartDashboard.putNumber("Left Enc Adj" , leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Enc Adj", rightEncoder.getDistance());
    }

    public void stop(){
    	leftDrive1 .set(0);
    	rightDrive1.set(0);
    	leftDrive2 .set(0);
    	rightDrive2.set(0);
    	SmartDashboard.putNumber("Robot Velocity", 0);
    }

    public void shiftGearLowToHigh(){//Meaning Low speed to high speed
    	//Assumes Pneumatic forward/out shifts low to high
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    	leftEncoder.setDistancePerPulse(HIGH_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(HIGH_GEAR_RIGHT_DPP);
    	SmartDashboard.putNumber("Transmission", -1); //Transmisison is High
    	System.out.println("Shifting Drive Gear to High Gear");
    }

    public void shiftGearHighToLow(){
    	//Assumes Pneumatic reverse/in shifts high to low
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    	leftEncoder.setDistancePerPulse(LOW_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(LOW_GEAR_RIGHT_DPP);
    	SmartDashboard.putNumber("Transmission", 1); //Transmisison is Low
    	System.out.println("Shifting Drive Gear to Low Gear");
    }
    public double getVelocityOfRobot(){
    	double velocity = (Math.abs(leftEncoder.getRate()) + Math.abs(rightEncoder.getRate()))/2;
    	//For testing
    	SmartDashboard.putNumber("Robot Velocity", velocity);
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
    
    public double getEncoderDistance(){
    	double leftDistanceRaw = leftEncoder.get();
    	double rightDistanceRaw = rightEncoder.get();
    	SmartDashboard.putNumber("Left Enc Raw", leftDistanceRaw);
    	SmartDashboard.putNumber("Right Enc Raw", rightDistanceRaw);
    	double leftDistance = leftEncoder.getDistance();
    	double rightDistance = rightEncoder.getDistance();
    	SmartDashboard.putNumber("Left Enc Adj", leftDistance);
    	SmartDashboard.putNumber("Right Enc Adj", rightDistance);
    	double encoderDistance = (leftDistance + rightDistance)/2;
    	return encoderDistance;
    }
    
    public void encoderReset(){
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public double reportGyro(){
    	double currentAngle = gyro.getAngle();
    	SmartDashboard.putNumber("Current Angle", currentAngle);
    	//currentAngle *= GYRO_OFFSET; //XXX How does this work if GYRO_OFFSET is undefined? Used in AutoTurnAngle
//    	SmartDashboard.putNumber("Adjusted Gyro (NOT ADJUSTING)", currentAngle);
    	return currentAngle;
    }
    
    public void recalibrateGyro(){
    	gyro.calibrate();
    }
    
    public void resetGyro(){
    	gyro.reset();
    }

}
//*/ // XXX Comment this line to use Double encoder
/* XXX Remove front two slashes to comment Single encoder and use Double Encoder on Drive
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
		//Encoder DIO slots say left, but is just for one encoder. Location Variable may be changed
		encoder = new Encoder(RobotMap.DRIVE_ENC_LEFT_A, RobotMap.DRIVE_ENC_LEFT_B, true, Encoder.EncodingType.k4X);
		
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
		
		SmartDashboard.putNumber("Enc Raw" , encoder.get());
		SmartDashboard.putNumber("Enc Adj" , encoder.getDistance());
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//    	setDefaultCommand(new XboxMove());
    }
    
    public void drive(double leftDriveDesired, double rightDriveDesired){
    	leftDrive1 .set(leftDriveDesired); //passes desired state to speed controllers
    	rightDrive1.set(-1* rightDriveDesired);
    	leftDrive2.set(leftDriveDesired);
    	rightDrive2.set(-1 * rightDriveDesired);
    	
    	SmartDashboard.putNumber("Enc Raw" , encoder.get());
		SmartDashboard.putNumber("Enc Adj" , encoder.getDistance());
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
    	encoder.setDistancePerPulse(HIGH_GEAR_LEFT_DPP);
    	SmartDashboard.putNumber("Transmission", -1); //Transmisison is High
    	System.out.println("Shifting Drive Gear to High Gear");
    }

    public void shiftGearHighToLow(){
    	//Assumes Pneumatic reverse/in shifts high to low
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    	encoder.setDistancePerPulse(LOW_GEAR_LEFT_DPP);
    	SmartDashboard.putNumber("Transmission", 1); //Transmisison is Low
    	System.out.println("Shifting Drive Gear to Low Gear");
    }
    public double getVelocityOfRobot(){
    	double velocity = Math.abs(encoder.getRate());
    	//For testing
    	SmartDashboard.putNumber("Robot Velocity", velocity);
    	return velocity;
    }

    
    public void setDPPLowGear(){
    	encoder.setDistancePerPulse(LOW_GEAR_LEFT_DPP);
    }
    
    public void setDPPHighGear(){
    	encoder.setDistancePerPulse(HIGH_GEAR_LEFT_DPP);
    }
    
    public double getEncoderDistance(){
    	double distanceRaw = encoder.get();
    	SmartDashboard.putNumber("Enc Raw", distanceRaw);
    	double distance = encoder.getDistance();
    	SmartDashboard.putNumber("Enc Adj", distance);
    	double encoderDistance = distance;
    	return encoderDistance;
    }
    
    public void encoderReset(){
    	encoder.reset();
    }
    
    public double reportGyro(){
    	double currentAngle = gyro.getAngle();
    	SmartDashboard.putNumber("Current Angle", currentAngle);
    	//currentAngle *= GYRO_OFFSET; //XXX How does this work if GYRO_OFFSET is undefined? Used in AutoTurnAngle
//    	SmartDashboard.putNumber("Adjusted Gyro (NOT ADJUSTING)", currentAngle);
    	return currentAngle;
    }
    
    public void recalibrateGyro(){
    	gyro.calibrate();
    }
    
    public void resetGyro(){
    	gyro.reset();
    }

}
*/ //XXX Remove front two slashes to use double encoders on Drive