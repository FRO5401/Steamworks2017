package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5401.robot.autonomous.AutoDrive;
import org.usfirst.frc.team5401.robot.autonomous.AutoTurnAngle;
import org.usfirst.frc.team5401.robot.commands.PopGear;
import org.usfirst.frc.team5401.robot.subsystems.Shooter;

/**
 *
 */
public class AutoCenterGearRedShoot extends CommandGroup {

    public AutoCenterGearRedShoot() {
    	
 		addSequential(new AutoCenterGear());
// 		addSequential(new AutoTurnAngle());
// 		addSequential(new AutoDrive());
//		addSequnntial(new AutoTurnAngle());
 		addSequential(new AutoShoot());
    	
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
