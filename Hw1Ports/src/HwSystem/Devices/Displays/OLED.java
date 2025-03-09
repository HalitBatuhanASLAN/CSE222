package HwSystem.Devices.Displays;

import HwSystem.Protocols.SPI;

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
        {return "OLED";}
    
        public void printData(String data)
    {
        if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
            tmp.write(data);
        }
        else
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
}
