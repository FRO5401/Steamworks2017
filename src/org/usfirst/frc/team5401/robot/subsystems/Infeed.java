package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.FeedInAndOut;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** <p> Infeed collects the balls on the arena floor and puts them in the hopper
 *  </p>
 */
public class Infeed extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private  DoubleSolenoid feederArm;
	private VictorSP feederMotor;
	
	private double FEED_SPEED;

	
	/** <p> Declares the location of the feederArm and feederMotor and sets a speed for the feeder also sends the Feeder Direction and the Feeder UpDown to the smartDashboard
	 *  </p>
	 */
	public Infeed(){
		//declare victor locations
		feederArm =  new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.INFEEDER_IN, RobotMap.INFEEDER_OUT);
		feederMotor = new VictorSP(RobotMap.INFEEDER_MOTOR);
	
		FEED_SPEED = 0.5;
		
		SmartDashboard.putNumber("Feeder Direction", 0);
		SmartDashboard.putNumber("Feeder UpDown", 0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new FeedInAndOut());
    }
    
    /** <p> Declares the direction in which the feeder takes in balls
     *  </p>
     * @param direction
     */
    public void feederDirection(int direction){
    	feederMotor.set(FEED_SPEED * direction);
    	SmartDashboard.putNumber("Feeder Direction", direction);
    }
   
    /** <p> Moves the feeder arm up or down
     *  </p>
     * @param direction
     */
    public void armUpDown(int direction){
    	if (direction == 1) {
    		feederArm.set(DoubleSolenoid.Value.kForward);
    	} else if (direction == -1) {
    		feederArm.set(DoubleSolenoid.Value.kReverse);
    	}
    	SmartDashboard.putNumber("Feeder UpDown", direction);
    }
}

