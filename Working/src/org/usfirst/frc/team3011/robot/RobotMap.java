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
	
	//Encoders and lights
	public static final int
			wheelEncoder1   = 0,
			wheelEncoder2   = 1,
			ringLight 	    = 2,
			light1 		    = 3,
			light2 		    = 4,
			light3 		    = 5;
			
	//Relay
	public static final int
			conveyor	= 0;
   
	//Sensors
	public static final int 
			potRightFront = 2,
			potRightRear  = 3,
			potLeftFront  = 0,  
			potLeftRear   = 1;
	 
	//POT values for zeroing
	public static final double
			potRFZero      = 2.450,
			potLFZero      = 2.504,
			potRRZero      = 2.463,
			potLRZero      = 2.506;
	
	//Autonomous -- May or may not be used in the final code
	public static final int
			autoSwag = 2;	//1 or 2
	public static final double speedLimit = 0.20;
	//it was .18
	
	public static final double wheelConstant = 6*Math.PI/3000;	//1 Encoder Tick = 6pi/250 inches = 6pi/3000 ft
}
