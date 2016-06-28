package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FeedBallToShooter extends Command {
	private Intake intake;
	private double speed;

    public FeedBallToShooter(Intake in) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(in);
    	intake = in;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	speed = SmartDashboard.getNumber("Intake: Feed Ball Speed");
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(Robot.shooterPID1.getError()) < 2000 && Math.abs(Robot.shooterPID1.get()) > 0.3){
    		intake.set(.7, speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.set(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
