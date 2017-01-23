package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.FeedInAndOut;

/**
 *
 */
public class Infeed extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private  DoubleSolenoid feederArm;
	private VictorSP feederMotor;
	
	private double FEED_SPEED;

	
	public Infeed(){
		//declare victor locations
		feederArm =  new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.INFEEDER_IN, RobotMap.INFEEDER_OUT);
		feederMotor = new VictorSP(RobotMap.INFEEDER_MOTOR);
	
		FEED_SPEED = 0.5;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new FeedInAndOut());
    }
    
    public void feederDirection(int direction){
    	feederMotor.set(FEED_SPEED * direction);
    }
   
    public void armUpDown(int direction){
    	if (direction == 1) {
    		feederArm.set(DoubleSolenoid.Value.kForward);
    	} else if (direction == -1) {
    		feederArm.set(DoubleSolenoid.Value.kReverse);
    	}

    }
}

