package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.FeederControl;

/**
 *
 */
public class Infeed extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private DoubleSolenoid feederArm;
	private VictorSP feederMotor;
	
	private double FEED_SPEED;

	
	public Infeed(){
		//declare victor locations
		feederArm =  new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.INFEEDER_IN, RobotMap.INFEEDER_OUT);
		feederMotor = new VictorSP(RobotMap.INFEEDER_MOTOR);
	
		FEED_SPEED = 0.9;
		
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
}

