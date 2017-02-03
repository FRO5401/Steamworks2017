package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/** <p> Hopper stores all the balls on the robot
 *	</p>
 */
public class Hopper extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private DoubleSolenoid unjammer;
	private DoubleSolenoid flap;
	private VictorSP blender;
	
	/**	<p>Declares the location of the unjammer, flap, and blender</p>
	 * 
	 */
	public Hopper(){
		unjammer = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.HOPPER_UNJAMMER_IN, RobotMap.HOPPER_UNJAMMER_OUT);
		flap = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.HOPPER_FLAP_IN, RobotMap.HOPPER_FLAP_OUT);
		blender = new VictorSP(RobotMap.HOPPER_SPINNER);
		
		SmartDashboard.putString("Unjammer", "Down");
		SmartDashboard.putString("Flap", "Closed");
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /** <p> Pushes the piston in the hopper up so that the piston can jostle the jammed balls
     *  </p>
     */
    public void unjammerUp(){
    	unjammer.set(DoubleSolenoid.Value.kForward);
        SmartDashboard.putString("Unjammer", "Up");
    }
    
    /** <p> Pushes the piston in the hopper down when to finish jostling the jammed balls
     * 	</p>
     */
    public void unjammerDown(){
    	unjammer.set(DoubleSolenoid.Value.kReverse);
    	SmartDashboard.putString("Unjammer", "Down");
    }
    
    /** <p> Opens the flap on the top of the hopper to receive balls from one of the arena hoppers
     * 	</p>
     */
    public void flapOpen(){
    	flap.set(DoubleSolenoid.Value.kForward);
    	SmartDashboard.putString("Flap", "Open");
    }
    
    /** <p> Closes the flap on the top of the hopper to secure the balls in the hopper
     *  </p>
     */
    public void flapClose(){
    	flap.set(DoubleSolenoid.Value.kReverse);
    	SmartDashboard.putString("Flap", "Closed");
    }
    
    /** <p> Jostles the balls
     * 	</p>
     *  @param speed
     */
    public void blenderTurn(int speed){
    	blender.set(speed);
    }
}

