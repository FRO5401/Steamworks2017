package org.usfirst.frc.team5401.robot.autonomous;

import org.usfirst.frc.team5401.robot.commands.LoadShooter;
import org.usfirst.frc.team5401.robot.commands.Shoot;
import org.usfirst.frc.team5401.robot.commands.ShooterToggle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoRedShootAndBaseline extends CommandGroup {

    public AutoRedShootAndBaseline() {
    	//From KevinsWay XXX update with KevinsWay Red
    	addSequential(new AutoDrive(-4, .5));
    	addSequential(new AutoTurnAngle(-7, true,false));
    	addSequential(new Shoot());
    	addSequential(new WaitCommand(4));
    	addSequential(new ShooterToggle());
    	addSequential(new LoadShooter());
    	//End code from KevinsWay
    	addSequential(new AutoTurnAngle(53.25, true, false));
    	addSequential(new AutoDrive(-120,0.9));
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
