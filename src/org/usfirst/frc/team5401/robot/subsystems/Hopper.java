package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;


/**
 *
 */
public class Hopper extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private DoubleSolenoid unjammer;
	private DoubleSolenoid flap;
	private VictorSP blender;
	
	public Hopper(){
		unjammer = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.HOPPER_UNJAMMER_IN, RobotMap.HOPPER_UNJAMMER_OUT);
		flap = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.HOPPER_FLAP_IN, RobotMap.HOPPER_FLAP_OUT);
		blender = new VictorSP(RobotMap.HOPPER_SPINNER);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void unjammerUp(){
    	unjammer.set(DoubleSolenoid.Value.kForward);
    	
    }
    
    public void unjammerDown(){
    	unjammer.set(DoubleSolenoid.Value.kReverse);
    	
    }
    
    public void flapOpen(){
    	flap.set(DoubleSolenoid.Value.kForward);
    }
    
    public void flapClose(){
    	flap.set(DoubleSolenoid.Value.kReverse);    	
    }
    
    public void blenderTurn(int speed){
    	blender.set(speed);
    }
}

