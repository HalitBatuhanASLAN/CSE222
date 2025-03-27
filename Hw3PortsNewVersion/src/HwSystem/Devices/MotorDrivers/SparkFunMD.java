package HwSystem.Devices.MotorDrivers;


/**
 * SparkFunMD motor driver implementation that uses SPI protocol for communication.
 * This class provides specific functionality for the SparkFun Motor Driver
 * which controls motor speed through SPI interface.
 */
public class SparkFunMD extends MotorDriver
{
    /**
     * Constructor that initializes the SparkFunMD with the specified protocol.
     * Only accepts SPI protocol as it's the only supported protocol for this device.
     * 
     * @param protocolName The name of the protocol to use (must be "SPI")
     */
    public SparkFunMD(String protocolName)
    {
        if(!protocolName.equals("SPI"))
            System.err.println("Protocol does not match with device!!!(SparkFunMD)");
        else
            setProtocol(protocolName);
    }

    /**
     * Turns on the SparkFunMD motor driver.
     * Only works if the device is configured with the SPI protocol.
     * Changes the device state to ON when successful.
     */
    public void turnOn()
    {
        if(protocol.getProtocolName().equals("SPI"))
        {
            System.out.printf("%s: Turning On\n",getName());
            state = DeviceState.ON;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol(turnON part of SparkFunMD)\n", 
                getName(), protocol.getProtocolName());
    }

    /**
     * Turns off the SparkFunMD motor driver.
     * Only works if the device is configured with the SPI protocol.
     * Changes the device state to OFF when successful.
     */
    public void turnOff()
    {
        if(protocol.getProtocolName().equals("SPI"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            state = DeviceState.OFF;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol(turnOFF part of SparkFunMD)\n", 
                getName(), protocol.getProtocolName());
    }

    /**
     * Returns the name of this device.
     * 
     * @return String containing "SparkFunMD" as the device name
     */

    public String getName()
        {return "SparkFunMD";}

    
    /**
     * Sets the motor speed by sending the speed value over SPI protocol.
     * 
     * @param speed The desired motor speed value
     */
    public void setMotorSpeed(int speed)
    {
        System.out.printf("%s: setting speed to %d\n",getName(),speed);
        if(protocol.getProtocolName().equals("SPI"))
            return;
        else
            System.err.printf("Error: %s is not configured with %s protocol(setMotorSpeed part of SparkFunMD)\n", 
                getName(), protocol.getProtocolName());
    }
}
