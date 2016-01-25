
package org.usfirst.frc.team3011.robot;

import org.usfirst.frc.team3011.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team3011.robot.subsystems.RobotRotation;
import org.usfirst.frc.team3011.robot.subsystems.WheelFrontLeft;
import org.usfirst.frc.team3011.robot.subsystems.WheelFrontRight;
import org.usfirst.frc.team3011.robot.subsystems.WheelRearLeft;
import org.usfirst.frc.team3011.robot.subsystems.WheelRearRight;

import com.kauailabs.nav6.frc.IMU;

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
	
	public static SerialPort serial_port;
	public static IMU imu; 
	public static boolean first_Iteration;

    Command autonomousCommand;
    Command driveWithJoysticks;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    
    	serial_port = new SerialPort(57600,SerialPort.Port.kOnboard);
    	//update rate can be between 4-100 based on cpu usage
    	byte update_rate_hz = 50;
    	imu = new IMU(serial_port,update_rate_hz);
        LiveWindow.addSensor("IMU", "Gyro", imu);
    	first_Iteration = true;
		oi = new OI();
		
		// instantiate the command used for the autonomous period
		SmartDashboard.putData("Rotator",  robotRotation.getPIDController());;
		
		
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        driveWithJoysticks = new DriveWithJoysticks();
    	
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
        Scheduler.getInstance().run();
        driveWithJoysticks.start();
        while (imu.isCalibrating()) {}
        boolean is_calibrating = imu.isCalibrating();
        if ( first_Iteration && !is_calibrating ) {
            Timer.delay( 0.3 );
            imu.zeroYaw();
            first_Iteration = false;
        }
        
     // Update the dashboard with status and orientation
        // data from the nav6 IMU
        
        SmartDashboard.putNumber(   "IMU_Yaw",              imu.getYaw());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
