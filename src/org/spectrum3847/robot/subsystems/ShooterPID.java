package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSpeedController;
import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.lib.util.Util;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.ManualShooter;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterPID extends Subsystem {

	private SpectrumSpeedControllerCAN speedController;
	private double max = 1;
	private double min = -1;
	private double maxCurrentFwd = 10000;
	private double maxCurrentRev = -10000;
	private boolean currentLimit = false;
	
	public ShooterPID(String n, SpectrumSpeedControllerCAN sc, double maxVal, double minVal){
		super(n);
		speedController = sc;
		max = maxVal;
		min = minVal;
	}
	
	public ShooterPID(String n, int scNum, int scPDP, double maxVal, double minVal){
		this(n, new SpectrumSpeedControllerCAN(new CANTalon(scNum), scPDP), maxVal, minVal);
	}
	
	public ShooterPID(String n, int scNum, int scPDP){
		this(n, new SpectrumSpeedControllerCAN(new CANTalon(scNum), scPDP), 1, -1);
	}
	
	public ShooterPID(String n, SpectrumSpeedControllerCAN sc){
		this(n, sc, 1, -1);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		this.setDefaultCommand(new ManualShooter());
	}
	
	
	public void setOpenLoop(double value){
		value = Util.limit(value, max, min);
		
		//Check the current limit if it is enabled
		if (currentLimit){
			double current = getCurrent();
			if (current > maxCurrentFwd && value > 0){
				value = 0;
			} else if (current < maxCurrentRev && value < 0){
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
	
	public CANTalon getTalon(){
		return speedController.getTalon();
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
