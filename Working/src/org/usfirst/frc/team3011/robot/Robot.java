/**
 * @author FRC_Coder
 * 
 * WELCOME TO OUR CODE, I'LL BE YOUR GUIDE
 */
package org.usfirst.frc.team3011.robot;

import org.usfirst.frc.team3011.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team3011.robot.commands.AutoCommandGroup;
import org.usfirst.frc.team3011.robot.subsystems.Conveyor;
import org.usfirst.frc.team3011.robot.subsystems.LEDStrip;
import org.usfirst.frc.team3011.robot.subsystems.RobotRotation;
import org.usfirst.frc.team3011.robot.subsystems.Tower;
import org.usfirst.frc.team3011.robot.subsystems.WheelFrontLeft;
import org.usfirst.frc.team3011.robot.subsystems.WheelFrontRight;
import org.usfirst.frc.team3011.robot.subsystems.WheelRearLeft;
import org.usfirst.frc.team3011.robot.subsystems.WheelRearRight;
import org.usfirst.frc.team3011.robot.subsystems.WheelEncoder;
import org.usfirst.frc.team3011.robot.RobotMap;

import com.kauailabs.nav6.frc.IMU;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static WheelFrontRight wheelFR = new WheelFrontRight();
	public static WheelRearRight wheelRR = new WheelRearRight();
	public static WheelFrontLeft wheelFL = new WheelFrontLeft();
	public static WheelRearLeft wheelRL = new WheelRearLeft();
	public static RobotRotation robotRotation = new RobotRotation();
	public static final Tower tower = new Tower();
	public static final Conveyor conveyor = new Conveyor();
	public static final LEDStrip lightStrip = new LEDStrip();
	public static final WheelEncoder distanceEncoder = new WheelEncoder ();
	
	public static boolean isAutoMode = false;
	public static SerialPort serial_port;
	public static IMU imu; 
	public static boolean first_Iteration;
	
	Command autoCommandGroup;
    Command autonomousCommand;
    Command driveWithJoysticks;
        
    CameraServer server;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    public Robot() {
        //server = CameraServer.getInstance();
        //server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        //server.startAutomaticCapture("cam1");
    }
    
    public void operatorControl() {

        while (isOperatorControl() && isEnabled()) {
            Timer.delay(0.005);		// wait for a motor update time
        }
    }
    
    public void robotInit() {
    	
    	//NAV6 Initialization
    	serial_port = new SerialPort(57600,SerialPort.Port.kOnboard); 
    	byte update_rate_hz = 50; //update rate can be between 4-100 based on cpu usage
    	imu = new IMU(serial_port,update_rate_hz);
        LiveWindow.addSensor("IMU", "Gyro", imu);
    	first_Iteration = true;
		oi = new OI();
		
		// instantiate the command used for the autonomous period
		SmartDashboard.putData("Rotator",  robotRotation.getPIDController());
		
		//SmartDashboard.putData("WheelFR", wheelFR.getPIDController());
		//SmartDashboard.putData("WheelRR", wheelRR.getPIDController());
		//SmartDashboard.putData("WheelFL", wheelFL.getPIDController());
		//SmartDashboard.putData("WheelRL", wheelRL.getPIDController());
    	
		wheelFL.enable();
		wheelFR.enable();
		wheelRL.enable();
		wheelRR.enable();

    	Robot.wheelFR.setSetpoint(2.5);	//stop when finished
    	Robot.wheelRR.setSetpoint(2.5);	//stop when finished
    	Robot.wheelFL.setSetpoint(2.5);	//stop when finished
    	Robot.wheelRL.setSetpoint(2.5);	//stop when finished
    	
    	SmartDashboard.putBoolean("IMU_Calibrating", true); 				//Calibration period
    	while (imu.isCalibrating()) {}										//Goes through on startup
    	SmartDashboard.putBoolean("IMU_Calibrating", imu.isCalibrating());	//Should be false here
        boolean is_calibrating = imu.isCalibrating();
        
        if ( first_Iteration && !is_calibrating ) {
            Timer.delay( 0.3 );  
            imu.zeroYaw();
            first_Iteration = false;
        }
        
        SmartDashboard.putNumber(   "IMU_Yaw",              imu.getYaw());	//orientation data from the NAV6
        
        autoCommandGroup = new AutoCommandGroup(); //New autonomous command group

    }
	
	public void disabledPeriodic() {	//No need to do anything here
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	isAutoMode = true;						// We're in auto
    	Robot.distanceEncoder.resetDistance();	// Encoder is at 0 ft
    	autoCommandGroup.start();				// Let's start moving
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	isAutoMode = false;	//We're not in autonomous
        if (autonomousCommand != null) autonomousCommand.cancel();	//Drop out of autonomous
        Robot.isAutoMode = false;									//Ditto
        driveWithJoysticks = new DriveWithJoysticks();				//Allows us to drive
    	//wheelFL.enable();
		//wheelFR.enable();
		//wheelRL.enable();
		//wheelRR.enable();

		wheelFL.setSetpoint(2.5);
		wheelFR.setSetpoint(2.5);
		wheelRL.setSetpoint(2.5);
		wheelRR.setSetpoint(2.5);
		
		wheelFL.setSpeed(0);
		wheelFR.setSpeed(0);
		wheelRL.setSpeed(0);
		wheelRR.setSpeed(0);
		
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();	//Keep going with teleop, OI, etc.
        driveWithJoysticks.start();
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
