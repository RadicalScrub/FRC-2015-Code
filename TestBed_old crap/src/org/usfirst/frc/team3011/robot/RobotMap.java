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
    		driveRightFront = 3,
			steerRightFront = 7,
			driveRightRear  = 1,
			steerRightRear  = 5,
			driveLeftFront  = 2,
			steerLeftFront  = 6,
			driveLeftRear   = 0,
			steerLeftRear   = 4;
	
			
    
	//Sensors
	public static final int 
			potRightFront = 1,
			potRightRear  = 2,
			potLeftFront  = 0,
			potLeftRear   = 3;
	
	//motor speed
	public static final double speedLimit = 0.15;
	
}
