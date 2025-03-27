package HwSystem.Devices.Displays;

/**
 * OLED display implementation that uses SPI protocol for communication.
 * This class provides specific functionality for OLED displays including
 * power management and data display capabilities.
 */

public class OLED extends Display
{
    /**
     * Constructor that initializes the OLED with the specified protocol.
     * Only accepts SPI protocol as it's the only supported protocol for OLED displays.
     * 
     * @param protocolName The name of the protocol to use (must be "SPI")
     */
    public OLED(String protocolName)
    {
        if(!protocolName.equals("SPI"))
            System.err.println("Protocol does not match with device!!!(OLED)");
        else
            setProtocol(protocolName);
    }
    public void turnOn()
    {
        if(protocol.getProtocolName().equals("SPI"))
        {
            System.out.printf("%s: Turning On\n",getName());
            state = DeviceState.ON;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol(turnON part of OLED)\n", 
                getName(), protocol.getProtocolName());
    }
    public void turnOff()
    {
        if(protocol.getProtocolName().equals("SPI"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            state = DeviceState.OFF;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol(tunrOFF part of OLED)\n", 
                getName(), protocol.getProtocolName());
    }
    public String getName()
        {return "OLED";}
    
    public void printData(String data)
    {
        if(protocol.getProtocolName().equals("SPI"))
            System.out.printf("%s: writing %s\n",getName(),data);
        else
            System.err.printf("Error: %s is not configured with %s protocol(printData part of OLED)\n", 
                getName(), protocol.getProtocolName());
    }
}
