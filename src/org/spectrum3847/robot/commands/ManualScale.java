package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.util.Util;
import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ManualScale extends Command {

    public ManualScale() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.scale);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.scale.setMaxCurrentFwd(SmartDashboard.getNumber("Winch Current Limit"));
    	Robot.scale.setMaxCurrentRev(-1 * SmartDashboard.getNumber("Winch Current Limit"));
    	Robot.scale.enableCurrentLimit();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.scale.set(HW.Operator_Gamepad.getLeftY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.scale.set(0);
    	Robot.scale.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
