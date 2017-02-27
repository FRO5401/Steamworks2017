package org.usfirst.frc.team5401.robot.subsystems;
import edu.wpi.first.wpilibj.AnalogInput;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.FeederControl;

/**
 * The pressure sensor is a part of this subsystem since this is one of the only ones
 * that has a default command that runs all the time.
 */
public class Infeed extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private DoubleSolenoid feederArm;
	private VictorSP feederMotor;
	
	private double FEED_SPEED;

	private AnalogInput input; //The analog input that the sensor is on
	private double inputVoltage; //The input voltage provided to the sensor
    private final static double DEFAULT_VOLTS = 5.0; //What to use if we are no provided with another input voltage
	private final int SLOPE = 250; //The slope of the conversion of the return volts to pressure. From documentation.
	private final int Y_INTERCEPT = -20; //The Y intercept of the conversion of the return volts to pressure. From the docs. //Manually added 5

	
	public Infeed(){
		//declare victor locations
		feederArm =  new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.INFEEDER_IN, RobotMap.INFEEDER_OUT);
		feederMotor = new VictorSP(RobotMap.INFEEDER_MOTOR);
	
		FEED_SPEED = 0.9;
		
		input = new AnalogInput(RobotMap.PRESSURE_SENSOR);
		inputVoltage = DEFAULT_VOLTS; //5 is how many volts are typically provided to the sensor according to AndyMark
		
		SmartDashboard.putString("FeederArm_text", "Feeder Arm");
		SmartDashboard.putString("FeederOut_text", "GREEN = Feeder Out");
		SmartDashboard.putString("FeederIn_text" , "RED = Feeder In");
		if ((DoubleSolenoid.Value.kForward).equals(feederArm.get())){
			SmartDashboard.putNumber("Feeder Arm", -1); //Feeder Arm is out
		} else {
			SmartDashboard.putNumber("Feeder Arm", 1); //Feeder Arm is in
		}
		
		SmartDashboard.putString("FeederRollers_text", "Feeder Rollers");
		SmartDashboard.putString("FeederRollersOut_text", "GREEN = Rollers Out");
		SmartDashboard.putString("FeederRollersIn_text" , "RED = Rollers In");
		SmartDashboard.putNumber("Feeder Rollers", 0);
		
		SmartDashboard.putNumber("Pressure", SLOPE * (input.getVoltage()/inputVoltage) + Y_INTERCEPT);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new FeederControl());
    }
    
    public void feederDirection(int direction){
    	feederMotor.set(FEED_SPEED * direction);
    	SmartDashboard.putNumber("Feeder Rollers", direction); //XXX Might have to reverse
    }
   
    public void armUpDown(int direction){
    	if (direction == 1) {
    		feederArm.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putNumber("Feeder Arm", -1); //Feeder Arm is out
    	} else if (direction == -1) {
    		feederArm.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putNumber("Feeder Arm", 1); //Feeder Arm is in
    	}
    }
    
    /**
	 * Formula comes from the official documentation
	 * The pressure in PSI the sensor is reading
	 */
	public void reportPressure(){
		SmartDashboard.putNumber("Pressure", SLOPE * (input.getVoltage()/inputVoltage) + Y_INTERCEPT);
	}
}

