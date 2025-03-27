package HwSystem.Devices.MotorDrivers;
import HwSystem.Devices.Device;

/**
 * Abstract class representing motor driver devices in the hardware system.
 * Extends the base Device class and provides specific functionality for motor control.
 * Concrete motor driver implementations must extend this class and implement the setMotorSpeed method.
 */
public abstract class MotorDriver extends Device
{
    /**
     * Default constructor for MotorDriver class.
     * Initializes a new motordrivers device with default settings.
     */
    public MotorDriver()
        {super();}
    /**
     * Returns the device type as a string.
     * 
     * @return String containing "MotorDriver" as the device type
     */
    public String getDevType()
        {return "MotorDriver";}
    
    /**
     * Sets the speed of the motor controlled by this driver.
     * 
     * @param speed The desired motor speed value
     */
    public abstract void setMotorSpeed(int speed);
}