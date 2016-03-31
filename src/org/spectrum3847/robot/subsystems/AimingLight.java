package org.spectrum3847.robot.subsystems;

import org.spectrum3847.robot.HW;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AimingLight extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Relay light = new Relay(HW.AIMING_LIGHT_0);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void lightOn(){
    	light.set(Relay.Value.kOn);
    }
    
    public void lightoff(){
    	light.set(Relay.Value.kOff);
    }
}

