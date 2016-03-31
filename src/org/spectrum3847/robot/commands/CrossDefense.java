package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CrossDefense extends Command {

    public CrossDefense() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.shiftSol.extend();
    	setTimeout(SmartDashboard.getNumber("Auton Cross Time", 6));
    	double speed = SmartDashboard.getNumber("Auton Speed", 1);
    	Robot.drive.setOpenLoop(new DriveSignal(speed, speed));
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
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
