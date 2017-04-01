package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5401.robot.commands.GetShooterUpToSpeed;
import org.usfirst.frc.team5401.robot.commands.LoadShooter;
import org.usfirst.frc.team5401.robot.commands.Shoot;
import org.usfirst.frc.team5401.robot.commands.ShooterStop;

import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.frc.team5401.robot.commands.UnjamToggle;
/**
 *Balls on the left side of the robot
 */
public class KevinsWay extends CommandGroup {

    public KevinsWay() {
    	
    	addParallel(new GetShooterUpToSpeed());
    	addSequential(new AutoDrive(-2.5, .5));// changed from -19 to -4 due to change in Shooting Range
    	addSequential(new AutoTurnAngle(7, true,false));
    	addSequential(new Shoot());
    	addSequential(new WaitCommand(4));
    	addSequential(new ShooterStop());
    	addSequential(new LoadShooter());
    	addSequential(new AutoTurnAngle(-7, true, false));//Returns to Original path when the shooting Range used -19 in first AutoDrive. At the time, didn't have time to adjust
    	addSequential(new AutoDrive(-15,0.9));//Compensate for the -15 missing from the -4
    	addSequential(new AutoTurnAngle(-25.0, true, false));
    	addSequential(new AutoDrive(-104, .9));
    	addSequential(new AutoPopGearOnly(-1)); //out
		addSequential(new WaitCommand(1));
		addSequential(new AutoDrive(28, .9));
		addSequential(new AutoPopGearOnly(1)); //in
    	addSequential(new AutoTurnAngle(46, true, false));//turn 45 //XXX need to change angle
		addSequential(new AutoDrive(75, .5));//forward 80
		addSequential(new UnjamToggle(-1));
		
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
