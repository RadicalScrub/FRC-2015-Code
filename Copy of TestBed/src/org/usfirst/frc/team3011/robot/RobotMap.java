package org.usfirst.frc.team3011.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    //Motors
	public static final int
    		driveRightFront = 2,
			steerRightFront = 6,
			driveRightRear  = 3,
			steerRightRear  = 7,
			driveLeftFront  = 0,
			steerLeftFront  = 4,
			driveLeftRear   = 1,
			steerLeftRear   = 5,
			liftSystem      = 8;
	
	//Relay
	public static final int
			conveyor	= 0,
			ringLight   = 1;
   
	//Sensors
	public static final int 
			potRightFront = 2,
			potRightRear  = 3,
			potLeftFront  = 0,
			potLeftRear   = 1;
	
	//POT values for zeroing
	public static final double
			potRFZero      = 1.737,
			potLFZero      = 1.730,
			potRRZero      = 1.785,
			potLRZero      = 1.70;
	//motor speed
	public static final double speedLimit = 0.2;
	
}
