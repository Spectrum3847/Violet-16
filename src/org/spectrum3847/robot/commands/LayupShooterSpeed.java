package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.subsystems.ShooterNoPID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LayupShooterSpeed extends Command {
	ShooterNoPID shooter;
	double speed;

    public LayupShooterSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	shooter = Robot.shooter;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	speed = SmartDashboard.getNumber("Shooter: Layup Speed");
    	shooter.set(speed);
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
    	shooter.set(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
