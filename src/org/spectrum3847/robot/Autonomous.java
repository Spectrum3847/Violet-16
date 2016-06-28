package org.spectrum3847.robot;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.robot.commands.CrossDefense;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;


public class Autonomous {
	private static Command autoCommand;

    public static void init() {
    	//autoCommand = (Command) Dashboard.autoChooser.getSelected();
    	//autoCommand.start();
    	new CrossDefense().start();
    }

    //Periodic method called roughly once every 20ms
    public static void periodic() {
        Scheduler.getInstance().run();
        Dashboard.updateDashboard();
    }

    public static void cancel() {
        Scheduler.getInstance().removeAll();
        Robot.drive.setOpenLoop(new DriveSignal(0,0));
    }
}
