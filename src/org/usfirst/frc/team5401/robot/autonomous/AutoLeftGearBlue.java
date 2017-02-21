package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team5401.robot.autonomous.AutoDrive;
import org.usfirst.frc.team5401.robot.autonomous.AutoTurnAngle;
import org.usfirst.frc.team5401.robot.commands.PopGear;

/**
 *
 */
public class AutoLeftGearBlue extends CommandGroup {

    public AutoLeftGearBlue() {
    	
    	addSequential(new AutoDrive(-75.8, .5));
    	addSequential(new AutoTurnAngle(50, true, false)); //should be 60
    	addSequential(new AutoDrive(-45.25, .5)); //54.5 start
    	addSequential(new AutoPopGear());
    	
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
