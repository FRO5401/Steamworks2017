package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5401.robot.commands.GetShooterUpToSpeed;
import org.usfirst.frc.team5401.robot.commands.Shoot;
/**
 *
 */
public class KevinsWay extends CommandGroup {

    public KevinsWay() {
    	
    	addParallel(new GetShooterUpToSpeed());
    	addSequential(new AutoDrive(-13.5, .5));
    	addSequential(new AutoTurnAngle(-7, true,false));
    	addSequential(new Shoot());
    	
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
