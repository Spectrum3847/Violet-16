package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AimingLightOn extends Command {

    public AimingLightOn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.light);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.light.lightOn();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.light.lightoff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
