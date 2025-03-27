package HwSystem.Devices.MotorDrivers;


/**
 * PCA9685 motor driver implementation that uses I2C protocol for communication.
 * This class provides specific functionality for the PCA9685 PWM controller chip
 * which is commonly used for motor speed control.
 */
public class PCA9685 extends MotorDriver
{
    /**
     * Constructor that initializes the PCA9685 with the specified protocol.
     * Only accepts I2C protocol as it's the only supported protocol for this device.
     * 
     * @param protocolName The name of the protocol to use (must be "I2C")
     */
    public PCA9685(String protocolName)
    {
        if(!protocolName.equals("I2C"))
            System.err.println("Protocol does not match with device!!!(PCA9685)");
        else
            setProtocol(protocolName);
    }

    /**
     * Turns on the PCA9685 motor driver.
     * Only works if the device is configured with the I2C protocol.
     * Changes the device state to ON when successful.
     */
    public void turnOn()
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning On\n",getName());
            state = DeviceState.ON;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol(turnON part of PCA9685)\n", 
                getName(), protocol.getProtocolName());
    }

    /**
     * Turns off the PCA9685 motor driver.
     * Only works if the device is configured with the I2C protocol.
     * Changes the device state to OFF when successful.
     */
    public void turnOff()
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            state = DeviceState.OFF;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol(turnOFF part of PCA9685)\n", 
                getName(), protocol.getProtocolName());
    }

    /**
     * Returns the name of this device.
     * 
     * @return String containing "PCA9685" as the device name
     */
    public String getName()
        {return "PCA9685";}

    
    /**
     * Sets the motor speed by sending the speed value over I2C protocol.
     * 
     * @param speed The desired motor speed value
     */
    public void setMotorSpeed(int speed)
    {
        System.out.printf("%s: setting speed to %d\n",getName(),speed);
        if(protocol.getProtocolName().equals("I2C"))
            return;
        else
            System.err.printf("Error: %s is not configured with %s protocol(setMotorSpeed part of PCA9685)\n", 
                getName(), protocol.getProtocolName());
    }
}