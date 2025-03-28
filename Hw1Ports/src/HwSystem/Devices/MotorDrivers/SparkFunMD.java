package HwSystem.Devices.MotorDrivers;

import HwSystem.Protocols.SPI;

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
            System.out.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    public void turnOn()
    {
        if(protocol.getProtocolName().equals("SPI"))
        {
            System.out.printf("%s: Turning On\n",getName());
            SPI tmp = new SPI();
            tmp.write("Turning ON");
            state = DeviceState.On;
        }
        else
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
    public void turnOff()
    {
        if(protocol.getProtocolName().equals("SPI"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            SPI tmp = new SPI();
            tmp.write("Turning OFF");
            state = DeviceState.Off;
        }
        else
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
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
        {
            SPI tmp = new SPI();
            String speedString = Integer.toString(speed);
            tmp.write(speedString);
        }
        else
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
}
