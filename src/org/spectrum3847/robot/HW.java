package org.spectrum3847.robot;

import org.spectrum3847.lib.drivers.Gamepad;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class HW {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	//Use ecplise refacotr tool to rename values for your specific robot
	
	//OI
	public static OI oi;
	public static final Gamepad Driver_Gamepad = new Gamepad("Driver", HW.USBPORT_0);
    public static final Gamepad Operator_Gamepad = new Gamepad("Operator", HW.USBPORT_1);
	
	//PDP Panel
	public static PowerDistributionPanel PDP = new PowerDistributionPanel();
	
    /**PDP Slots**/
	public static final int INTAKE_775_PDP = 0;
	public static final int INTAKE_BAG_PDP = 1;
	public static final int SHOOTER_1_PDP = 2;
	public static final int SHOOTER_2_PDP = 3;
	public static final int CAT_TAIL_PDP = 4;
	public static final int PWM_5_PDP = 5;
	public static final int PWM_6_PDP = 6;
	public static final int PWM_7_PDP = 7;
	public static final int Empty_8_PDP = 8;
	public static final int Empty_9_PDP = 9;
	public static final int Winch_1_PDP = 10;
	public static final int Winch_2_PDP = 11;
	public static final int RIGHT_REAR_DRIVE_MOTOR_PDP = 12;
	public static final int RIGHT_FRONT_DRIVE_MOTOR_PDP = 13;
	public static final int LEFT_REAR_DRIVE_MOTOR_PDP = 14;
	public static final int LEFT_FRONT_DRIVE_MOTOR_PDP = 15;
	
	
	
	/**MOTOR ASSIGNMENTS (PWM)**/
    public static final int RIGHT_REAR_DRIVE_MOTOR_0 = 0;
    public static final int RIGHT_FRONT_DRIVE_MOTOR_1 = 1;
    public static final int PWM_2 = 2;
    public static final int PWM_3 = 3;
    public static final int Winch_1_4 = 4;
    public static final int Winch_2_5 = 5;
    public static final int PWM_6 = 6;
    public static final int PWM_7 = 7;
    public static final int LEFT_REAR_DRIVE_MOTOR_8 = 8;
    public static final int LEFT_FRONT_DRIVE_MOTOR_9 = 9;
    public static final int PWM_10 = 10;
    public static final int PWM_11 = 11;
    public static final int PWM_12 = 12;
    public static final int PWM_13 = 13;
    public static final int PWM_14 = 14;
    public static final int PWM_15 = 15;
    public static final int PWM_16 = 16;
    public static final int PWM_17 = 17;
    public static final int PWM_18 = 18;
    public static final int PWM_19 = 19;


    /**NON-DRIVEBASE MOTOR ASSIGNMENTS (CAN)**/
    public static final int INTAKE_775_1 = 1;
    public static final int INTAKE_BAG_2 = 2;
    public static final int SHOOTER_MOTOR_1_3 = 8;
    public static final int SHOOTER_MOTOR_2_4 = 9;
    public static final int WINCH_CAN_1_3 = 3;
    public static final int WINCH_CAN_2_5 = 5;
    public static final int SHOOTER_PID_1_7 = 7;
    public static final int SHOOTER_PID_2_4	 = 4	;
    
    /**DIGITAL SENSOR ALLOCATIONS**/
    public static final int LEFT_ENCODER_0 = 0; 
    public static final int LEFT_ENCODER_1 = 1;
    public static final int BALL_SENSOR_2 = 2;
    public static final int DIGITAL_IO_3 = 3;
    public static final int ScaleDownLimit_4 = 4; 
    public static final int ScaleUpLimit_5 = 5;
    public static final int DIGITAL_IO_6 = 6; 
    public static final int DIGITAL_IO_7 = 7; 
    public static final int RIGHT_ENCODER_8 = 8;
    public static final int RIGHT_ENCODER_9 = 9;  
    
    /**Pneumatics**/
    public static final int SOL_0 = 0;
    public static final int INTAKE_SOL_DOWN_2 = 2;
    public static final int SOL_3 = 3;
    public static final int SOL_4 = 4;
    public static final int SHIFTING_SOL_HIGH_5 = 5;
    public static final int INTAKE_SOL_UP_6 = 6;
    public static final int SOL_7 = 7;
    
    /**ANALOG SENSOR ALLOCATIONS**/
    public static final int PRESSURE_TRANSDUCEDR_0 = 0;
    public static final int ANALOG_IN_1 = 1;
    public static final int ANALOG_IN_2 = 2;
    public static final int ANALOG_IN_3 = 3;
    
    /**RELAY ALLOCATIONS**/
    public static final int AIMING_LIGHT_0 = 0;
    public static final int RELAY_ONE = 1;
    public static final int RELAY_TWO = 2;
    public static final int RELAY_THREE = 3;

    /**JOYSTICKS/GAMEPAD ASSIGNMENTS**/
    public static final int USBPORT_0 = 0;
    public static final int USBPORT_1 = 1;
    public static final int USBPORT_2 = 2;
    public static final int USBPORT_3 = 3;
    public static final int USBPORT_4 = 4;
    public static final int DSControllerPort = 5;
    
    /**CAMERA NAMES**/
    public static final String FRONT_CAMERA = "cam3";
    public static final String REAR_CAMERA = "cam1";
    
}
