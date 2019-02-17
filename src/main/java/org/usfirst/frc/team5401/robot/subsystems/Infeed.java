package org.usfirst.frc.team5401.robot.subsystems;

import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.FeederControl;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Infeed extends Subsystem {
	
	private DoubleSolenoid feederArm;
	private VictorSP feederMotor;
	
	private double FEED_SPEED;
	
	private AnalogInput pressureSensor;
	private double inputVoltage;
	private final static double DEFAULT_VOLTS = 5.0;
	private final int SLOPE = 250;
	private final int Y_INTERCEPT = -20;
	
	public Infeed(){
		feederArm = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.INFEEDER_IN, RobotMap.INFEEDER_OUT);
		feederMotor = new VictorSP(RobotMap.INFEEDER_MOTOR);
		
		FEED_SPEED = 0.9;
		
		pressureSensor = new AnalogInput(RobotMap.PRESSURE_SENSOR);
		inputVoltage = DEFAULT_VOLTS;
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new FeederControl());
    }
    
    public void feederDirection(int feedDirection){
    	feederMotor.set(FEED_SPEED * feedDirection);
    	SmartDashboard.putNumber("Feeder Rollers", feedDirection);
    }
    
    public void armUpDown(int armDirection){
    	if (armDirection == 1) {
    		feederArm.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putNumber("Feeder Arm", -1);
    	} else if (armDirection == -1) {
    		feederArm.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putNumber("Feeder Arm", 1);
    	}
        //setDefaultCommand(new MySpecialCommand());
    }
    public void reportPressure(){
    	SmartDashboard.putNumber("Pressure", SLOPE * (pressureSensor.getVoltage()/inputVoltage)+ Y_INTERCEPT);
    }
}

