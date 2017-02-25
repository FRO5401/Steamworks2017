package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.commands.GetShooterUpToSpeed;
import org.usfirst.frc.team5401.robot.autonomous.AutoTurnAngle;
import org.usfirst.frc.team5401.robot.commands.TargetHigh;

/**
 * This command group ONLY auto targets. It does NOT shoot.
 */
public class AutoTarget extends CommandGroup {
	
    public AutoTarget(double angle, boolean inAuto, boolean autoTarget) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
    	
    	System.out.println("AutoTarget Started!");

    	SmartDashboard.putBoolean("AutoTargeting", true);
    	
    	addParallel(new AutoTurnAngle(angle, inAuto, autoTarget));
    	addSequential(new GetShooterUpToSpeed());
    	
    	SmartDashboard.putBoolean("AutoTargeting", false);
    }
}
