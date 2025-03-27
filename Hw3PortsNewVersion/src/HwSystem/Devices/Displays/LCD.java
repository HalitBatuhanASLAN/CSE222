package HwSystem.Devices.Displays;


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
            System.err.println("Protocol does not match with device!!!(LCD)");
        else
            setProtocol(protocolName);
    }
    public void turnOn()
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning On\n",getName());
            state = DeviceState.ON;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol(turnON part of LCD)\n", 
                getName(), protocol.getProtocolName());
    }
    public void turnOff()
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            state = DeviceState.OFF;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol(tunrOFF part of LCD)\n", 
                getName(), protocol.getProtocolName());
    }
    public String getName()
        {return "LCD";}

    public void printData(String data)
    {
        if(protocol.getProtocolName().equals("I2C"))
            System.out.printf("%s: writing %s\n",getName(),data);
        else
            System.err.printf("Error: %s is not configured with %s protocol(printData part of LCD)\n", 
                getName(), protocol.getProtocolName());
    }
}