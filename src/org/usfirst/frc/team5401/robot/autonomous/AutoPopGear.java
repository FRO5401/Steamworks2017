package org.usfirst.frc.team5401.robot.autonomous;

import org.usfirst.frc.team5401.robot.autonomous.AutoDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoPopGear extends CommandGroup {

    public AutoPopGear() {
		addSequential(new AutoPopGearOnly(-1)); //out
		addSequential(new WaitCommand(1));
		addSequential(new AutoDrive(50, .9));
		addSequential(new AutoPopGearOnly(1)); //in
    }
}
