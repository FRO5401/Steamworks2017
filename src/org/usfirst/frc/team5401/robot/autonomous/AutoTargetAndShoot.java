package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5401.robot.commands.Shoot;

/**
 *
 */
public class AutoTargetAndShoot extends CommandGroup {

    public AutoTargetAndShoot(double angle) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
    	
    	addSequential(new AutoTarget(angle));
    	addSequential(new Shoot());
    }
}
