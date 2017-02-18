package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team5401.robot.commands.GetShooterUpToSpeed;
import org.usfirst.frc.team5401.robot.autonomous.AutoTurnAngle;

/**
 * This command group ONLY auto targets. It does NOT shoot.
 */
public class AutoTarget extends CommandGroup {
	
    public AutoTarget(double angle) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
    	
    	addSequential(new GetShooterUpToSpeed());
    	addParallel(new AutoTurnAngle(angle));
    }
}
