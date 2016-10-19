package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {

	public TankDrive() {
		// TODO Auto-generated constructor stub
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.drive.setOpenLoop(new DriveSignal(HW.Driver_Gamepad.getLeftY(),HW.Driver_Gamepad.getRightY()));
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}

	@Override
	protected void interrupted() {
		// T0ODO Auto-generated method stub
		end();
	}

}
