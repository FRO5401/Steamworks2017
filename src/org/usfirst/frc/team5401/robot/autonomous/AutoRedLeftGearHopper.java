package org.usfirst.frc.team5401.robot.autonomous;

import org.usfirst.frc.team5401.robot.commands.UnjamToggle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedLeftGearHopper extends CommandGroup {

    public AutoRedLeftGearHopper() {
        addSequential(new AutoLeftGear());
        addSequential(new AutoTurnAngle(-53, true, false));
        addSequential(new AutoDrive(-82, .9));
        addSequential(new AutoTurnAngle(90, true, false));
        addParallel(new UnjamToggle(-1));
        addSequential(new AutoDrive(48, 0.9));
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
