package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumDigitalInput;
import org.spectrum3847.lib.drivers.SpectrumSpeedController;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.lib.util.Util;
import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.ManualScale;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ScaleSubsystem extends Subsystem {

	private SpectrumSpeedController speedController;
	private double max = 1;
	private double min = -1;
	private double maxCurrentFwd = 10000;
	private double maxCurrentRev = -10000;
	private boolean currentLimit = false;
	private SpectrumDigitalInput downLimit;
	private SpectrumDigitalInput upLimit;
	private boolean limitswitches = false;
	
	public ScaleSubsystem(SpeedController sc1, SpeedController sc2){
		SpeedController winch1 = sc1;
		SpeedController winch2 = sc2;
		speedController = new SpectrumSpeedController(new SpeedController[] {winch1, winch2}, new int[] {HW.Winch_1_PDP, HW.Winch_2_PDP});
		downLimit = new SpectrumDigitalInput(HW.ScaleDownLimit_4);
		upLimit = new SpectrumDigitalInput(HW.ScaleUpLimit_5);
		
		max = 1;
		min = -1;
	}
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new ManualScale());
	}
	
	
	public void set(double value){
		limitswitches = SmartDashboard.getBoolean("Scale Limit Switches Enabled?", false);
		value = Util.limit(value, max, min);
		
		if (Math.abs(value) < 0.1){
			value = 0;
		}
		
		//Check the current limit if it is enabled
		if (currentLimit){
			double current = getCurrent();
			if (current > maxCurrentFwd && value > 0){
				value = 0;
			} else if (current < maxCurrentRev && value < 0){
				value = 0;
			}
		}
		
		if (limitswitches){
			if (upLimit != null && upLimit.get() && value > 0){
				value = 0;
			} if (downLimit != null && downLimit.get() && value < 0){
				value = 0;
			}
		}
		
		speedController.set(value);
		Debugger.println("MOTOR - " + getName() + ": " + value, Robot.output, Debugger.debug2);
	}
	
	public void setMax(double m){
		max = m;
	}
	
	public void setMin(double m){
		min = m;
	}
	
	//Set the max fwd current
	public void setMaxCurrentFwd(double c){
		maxCurrentFwd = c;
		currentLimit = true;
	}
	
	//Set the max rev current, SHOULD BE NEGATIVE
	public void setMaxCurrentRev(double c){
		maxCurrentRev = c;
		currentLimit = true;
	}
	
	public void disableCurrentLimit(){
		currentLimit = false;
	}
	
	public void enableCurrentLimit (){
		currentLimit = true;
	}
	
	public double getSpeed(){
		return speedController.get();
	}
	
	public double getCurrent(){
		return speedController.getCurrent();
	}
	
	public void setInverted(boolean value){
		speedController.setInverted(value);
	}
	
	public void disable(){
		speedController.disable();
	}

}
