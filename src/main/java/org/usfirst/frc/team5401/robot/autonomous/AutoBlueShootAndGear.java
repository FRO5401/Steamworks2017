package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.frc.team5401.robot.commands.GetShooterUpToSpeed;
import org.usfirst.frc.team5401.robot.commands.Shoot;

/**
 *Load balls in left and center lanes
 */
public class AutoBlueShootAndGear extends CommandGroup {

    public AutoBlueShootAndGear() {
    	
    	addSequential(new AutoDrive(-12, .5));
    	addParallel(new GetShooterUpToSpeed());
    	addSequential(new AutoTurnAngle(90, true, false));
    	addSequential(new AutoDrive(-10,0.5));
    	
    	addSequential(new Shoot());
    	addSequential(new WaitCommand(4));
    	addSequential(new AutoTurnAngle(-6, true, false));
    	addSequential(new AutoDrive(-50,0.9));
    	

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
