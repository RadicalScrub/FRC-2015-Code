package org.usfirst.frc.team3011.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3011.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 * @author FRC_Coder
 */
public class LEDStrip extends Subsystem{
    private static DigitalOutput red = new DigitalOutput(RobotMap.light1);
    private static DigitalOutput gre = new DigitalOutput(RobotMap.light2);
    private static DigitalOutput blu = new DigitalOutput(RobotMap.light3);
    
    public void initDefaultCommand () {
        LEDStrip.red.enablePWM(0.0);
        LEDStrip.gre.enablePWM(0.0);
        LEDStrip.blu.enablePWM(0.0);
        //System.out.println("StatusLight initialized");
    }
    
    /**
     * 
     * @param a - red 0 - 255
     * @param b - green 0 - 255
     * @param c - blue 0 - 255
     */
    public void setRGB (double a, double b, double c) {
        LEDStrip.red.updateDutyCycle(a/255.0);
        LEDStrip.gre.updateDutyCycle(b/255.0);
        LEDStrip.blu.updateDutyCycle(c/255.0);
    }
    
}
