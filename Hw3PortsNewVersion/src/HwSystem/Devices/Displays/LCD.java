package HwSystem.Devices.Displays;

import HwSystem.Protocols.I2C;

/**
 * LCD display implementation that uses I2C protocol for communication.
 * This class provides specific functionality for LCD displays including
 * power management and data display capabilities.
 */
public class LCD extends Display
{
    /**
     * Constructor that initializes the LCD with the specified protocol.
     * Only accepts I2C protocol as it's the only supported protocol for LCD displays.
     * 
     * @param protocolName The name of the protocol to use (must be "I2C")
     */
    public LCD(String protocolName)
    {
        if(!protocolName.equals("I2C"))
            System.err.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    public void turnOn()
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning On\n",getName());
            I2C tmp = new I2C();
            tmp.write("Turning ON");
            state = DeviceState.On;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
    public void turnOff()
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            I2C tmp = new I2C();
            tmp.write("Turning OFF");
            state = DeviceState.Off;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
    public String getName()
        {return "LCD";}

    public void printData(String data)
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            I2C tmp = new I2C();
            tmp.write(data);
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
}