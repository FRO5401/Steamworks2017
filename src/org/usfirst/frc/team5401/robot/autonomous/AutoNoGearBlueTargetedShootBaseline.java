package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5401.robot.subsystems.Shooter;
import org.usfirst.frc.team5401.robot.autonomous.AutoTurnAngle;
import org.usfirst.frc.team5401.robot.autonomous.AutoDrive;
/**
 *
 */
public class AutoNoGearBlueTargetedShootBaseline extends CommandGroup {

    public AutoNoGearBlueTargetedShootBaseline() {
  /*		
   * 		targeted
   * 		addSequential(AutoShoot());
   *		addSequential(AutoTurnAngle(some angle));
   *		addSequential(AutoDrive()); 	
   */
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
