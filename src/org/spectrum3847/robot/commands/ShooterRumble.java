package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterRumble extends Command {

    public ShooterRumble() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.shooterPID.getSpeed() > 0 || Robot.shooterPID.getSpeed() < 0){
    		HW.Operator_Gamepad.rumble((float)0.3);
    	} else {
    		HW.Operator_Gamepad.rumble(0);
    	}
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		HW.Operator_Gamepad.rumble(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
