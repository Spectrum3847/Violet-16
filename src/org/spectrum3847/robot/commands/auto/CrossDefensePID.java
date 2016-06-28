package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CrossDefensePID extends Command {

    public CrossDefensePID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//setTimeout(SmartDashboard.getNumber("Auton Cross Time", 6));
    	double speed = SmartDashboard.getNumber("Auton Speed", 1);
    	double distance = SmartDashboard.getNumber("Auton Distance", 8);
    	Robot.drive.setDistanceSetpoint(distance, speed);	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.update();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setOpenLoop(new DriveSignal(0, 0));
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
