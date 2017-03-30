package org.usfirst.frc.team5401.robot.autonomous;

import org.usfirst.frc.team5401.robot.commands.GetShooterUpToSpeed;
import org.usfirst.frc.team5401.robot.commands.LoadShooter;
import org.usfirst.frc.team5401.robot.commands.Shoot;
import org.usfirst.frc.team5401.robot.commands.ShooterToggle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class MOEsWayRed extends CommandGroup {

    public MOEsWayRed() {
     	addSequential(new GetShooterUpToSpeed());
    	addSequential(new AutoDrive(-2.5, .5));
    	addSequential(new AutoTurnAngle(-7, true,false));
    	addSequential(new Shoot());
    	addSequential(new WaitCommand(4));
    	addSequential(new ShooterToggle());
    	addSequential(new LoadShooter());
    	addSequential(new AutoTurnAngle(12, true, false));
    	addSequential(new AutoDrive(-112, .9));
    	//addSequential(new AutoPopGearOnly(-1)); //out
		//addSequential(new WaitCommand(1));
		//addSequential(new AutoDrive(28, .9));
		//addSequential(new AutoPopGearOnly(1)); //in
    	//addSequential(new AutoTurnAngle(-46, true, false));//turn 45 //XXX need to change angle
    	
    	/**UNCOMMENT THESE FOR HOPPER **/
    	//addSequential(new AutoDrive (75, .5));//forward 80
    	//addSequential(new UnjamToggle(-1));
    	
    }
}
