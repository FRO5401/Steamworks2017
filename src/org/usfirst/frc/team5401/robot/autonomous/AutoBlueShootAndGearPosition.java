package org.usfirst.frc.team5401.robot.autonomous;

import org.usfirst.frc.team5401.robot.commands.GetShooterUpToSpeed;
import org.usfirst.frc.team5401.robot.commands.LoadShooter;
import org.usfirst.frc.team5401.robot.commands.Shoot;
import org.usfirst.frc.team5401.robot.commands.ShooterStop;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoBlueShootAndGearPosition extends CommandGroup {

    public AutoBlueShootAndGearPosition() {
    	//The following is from Moe's way blue. This is not the same as its red counterpart
    	
    	addParallel(new GetShooterUpToSpeed());
    	addSequential(new AutoDrive(-2.5, .5));
    	addSequential(new AutoTurnAngle(7, true,false));
    	addSequential(new Shoot());
    	addSequential(new WaitCommand(3));
    	addSequential(new ShooterStop());
    	addSequential(new LoadShooter());
    	addSequential(new AutoTurnAngle(-28, true, false));
    	addSequential(new AutoDrive(-81, .9));
    	addSequential(new AutoTurnAngle(30, true, false));

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
