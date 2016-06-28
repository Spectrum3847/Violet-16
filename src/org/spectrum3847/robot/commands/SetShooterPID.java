package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetShooterPID extends Command {
	private CANTalon shooter;
	private double p = 0;
	private double i = 0;
	private double d = 0;
	private double f = 0;
	private double speed = 0;
	private boolean highGoal = true;

    public SetShooterPID(boolean h) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterPID);
    	highGoal = h;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (highGoal){
    		speed = SmartDashboard.getNumber("Shooter PID Layup Speed", -27000);
    	} else {
    		speed = SmartDashboard.getNumber("Shooter PID Low Goal Speed" , 30000);	
    	}
    	shooter = Robot.shooterPID.getTalon();
    	shooter.changeControlMode(CANTalon.TalonControlMode.Speed);
    	shooter.enable();
		p = SmartDashboard.getNumber("Shooter P", 0.3);
		i = SmartDashboard.getNumber("Shooter I", 0);
		d = SmartDashboard.getNumber("Shooter D", 0.055);
		f = SmartDashboard.getNumber("Shooter F", .024);
    	shooter.setPID(p, i, d, f, 0, 100, 0);
    	shooter.set(speed);
    	Debugger.println("Set Shooter PID Setpoint: " + shooter.getSpeed() + " P: " + p + " D: " + d + " F: " + f + " \n", Robot.commands, Debugger.warning4);
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
    	shooter.set(0);
    	shooter.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
