package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5401.robot.commands.Shoot;

/**
 *
 */
public class AutoTargetAndShoot extends CommandGroup {

    public AutoTargetAndShoot(double angle, boolean inAuto, boolean autoTarget) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
    	
    	System.out.println("AutoTargetAndShoot Started!");
    	System.out.println("Starting AutoTarget CmdGroup from AutoTargetAndShoot CmdGroup");
    	
    	addSequential(new AutoTarget(angle, inAuto, autoTarget));
    	addSequential(new Shoot());
    }
}
