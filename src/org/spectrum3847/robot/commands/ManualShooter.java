package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualShooter extends Command {
	private CANTalon shooter;

    public ManualShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterPID);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter = Robot.shooterPID.getTalon();
    	Debugger.println("Manual Shooter Control Enabled \n", Robot.commands, Debugger.warning4);
    	if (shooter.getControlMode() != CANTalon.TalonControlMode.PercentVbus) {
        	shooter.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        	Debugger.println("Set Control Mode to  Percent Vbus \n", Robot.commands, Debugger.warning4);   	
    	}
    	shooter.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = -1 * HW.Operator_Gamepad.getLeftY();
    	if (Math.abs(speed) > 0.2){
    		shooter.set(speed);
    	} else{
    		shooter.set(0);
    	}	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.set(0);
    	shooter.changeControlMode(CANTalon.TalonControlMode.Speed);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
