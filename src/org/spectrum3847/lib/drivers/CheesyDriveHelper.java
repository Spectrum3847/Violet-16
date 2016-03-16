package org.spectrum3847.lib.drivers;

import org.spectrum3847.lib.util.Util;
import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * CheesyDriveHelper implements the calculations used in CheesyDrive, sending
 * power to the motors.
 */
public class CheesyDriveHelper {

    private Drive drive;
    double oldWheel, quickStopAccumulator;
    private double throttleDeadband = 0.12;
    private double wheelDeadband = 0.15;
    private double driveSensitivity = 0.2;
    private double driveLowSensitivity = 0.8;
    private double quickturnHighSensitivity = 0.5;
    private double quickturnLowSensitivity = 0.5;
    
    private DriveSignal signal = new DriveSignal(0, 0);

    public CheesyDriveHelper(Drive drive) {
        this.drive = drive;
    }

    public void cheesyDrive(double throttle, double wheel, boolean isQuickTurn,
                            boolean isHighGear) {
        if (DriverStation.getInstance().isAutonomous()) {
            return;
        }

        double wheelNonLinearity;

        wheel = handleDeadband(wheel, wheelDeadband);
        throttle = handleDeadband(throttle, throttleDeadband);
        SmartDashboard.putNumber("Drive wheel: ", wheel);
    	SmartDashboard.putNumber("Drive Throttle: ", throttle);
        
        double negInertia = wheel - oldWheel;
        oldWheel = wheel;

        if (isHighGear) {
            wheelNonLinearity = 0.6;
            // Apply a sin function that's scaled to make it feel better.
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
        } else {
            wheelNonLinearity = 0.5;
            // Apply a sin function that's scaled to make it feel better.
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
        }

        double leftPwm, rightPwm, overPower;
        double sensitivity;

        double angularPower;
        double linearPower;

        // Negative inertia!
        double negInertiaAccumulator = 0.0;
        double negInertiaScalar;
        if (isHighGear) {
            negInertiaScalar = 0.4;
            sensitivity = SmartDashboard.getNumber("Drive: High Sensitivity", driveSensitivity);
        } else {
            if (wheel * negInertia > 0) {
                negInertiaScalar = .25;
            } else {
                if (Math.abs(wheel) > 0.65) {
                    negInertiaScalar = .5;
                } else {
                    negInertiaScalar = .3;
                }
            }
            sensitivity = SmartDashboard.getNumber("Drive: Low Sensitivity", driveLowSensitivity);
        }
        
        double negInertiaPower = negInertia * negInertiaScalar;
        negInertiaAccumulator += negInertiaPower;

        wheel = wheel + negInertiaAccumulator;
        if (negInertiaAccumulator > 1) {
            negInertiaAccumulator -= 1;
        } else if (negInertiaAccumulator < -1) {
            negInertiaAccumulator += 1;
        } else {
            negInertiaAccumulator = 0;
        }
        linearPower = throttle;

        // Quickturn!
        if (isQuickTurn) {
            if (Math.abs(linearPower) < 0.2) {
                double alpha = 0.1;
                quickStopAccumulator = (1 - alpha) * quickStopAccumulator
                        + alpha * Util.limit(wheel, 1.0) * 5;
            }
            overPower = 0;
            if (isHighGear) {
                sensitivity = SmartDashboard.getNumber("Drive: Low Quick Turn Sensitivity", quickturnLowSensitivity);
            } else {
                sensitivity = SmartDashboard.getNumber("Drive: High Quick Turn Sensitivity", quickturnHighSensitivity);
            }
            angularPower = wheel;
            SmartDashboard.putNumber("Drive: Quick Turn angularPower", angularPower);
        } else {
            overPower = 0.0;
            angularPower = Math.abs(throttle) * wheel * sensitivity
                    - quickStopAccumulator;
            if (quickStopAccumulator > 1) {
                quickStopAccumulator -= 1;
            } else if (quickStopAccumulator < -1) {
                quickStopAccumulator += 1;
            } else {
                quickStopAccumulator = 0.0;
            }
        }

        rightPwm = leftPwm = linearPower;
        leftPwm += angularPower;
        rightPwm -= angularPower;

        if (leftPwm > 1.0) {
            rightPwm -= overPower * (leftPwm - 1.0);
            leftPwm = 1.0;
        } else if (rightPwm > 1.0) {
            leftPwm -= overPower * (rightPwm - 1.0);
            rightPwm = 1.0;
        } else if (leftPwm < -1.0) {
            rightPwm += overPower * (-1.0 - leftPwm);
            leftPwm = -1.0;
        } else if (rightPwm < -1.0) {
            leftPwm += overPower * (-1.0 - rightPwm);
            rightPwm = -1.0;
        }
        signal.leftMotor = leftPwm;
        signal.rightMotor = rightPwm;
        drive.setOpenLoop(signal);
    }

    public double handleDeadband(double val, double deadband) {
    	double value = 0;
    	double sign = (val < 0) ? -1 : 1; 
    	value = (Math.abs(val) > Math.abs(deadband)) ? (((val)/(1-deadband)) * sign) : 0.0;
        return value;
    }
}