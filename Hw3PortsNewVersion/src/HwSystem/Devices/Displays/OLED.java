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

    /**
     * Turns on the OLED display.
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
            System.err.printf("Error: %s is not configured with %s protocol(turnON part of OLED)\n", 
                getName(), protocol.getProtocolName());
    }

    /**
     * Turns off the OLED display.
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
            System.err.printf("Error: %s is not configured with %s protocol(tunrOFF part of OLED)\n", 
                getName(), protocol.getProtocolName());
    }

    /**
     * Returns the name of this device.
     * 
     * @return String containing "OLED" as the device name
     */
    public String getName()
        {return "OLED";}
    
    /**
     * Displays the provided data on the OLED screen.
     * Only works if the device is configured with the SPI protocol.
     * 
     * @param data The string data to be displayed on the OLED
     */
    public void printData(String data)
    {
        if(protocol.getProtocolName().equals("SPI"))
            System.out.printf("%s: writing %s\n",getName(),data);
        else
            System.err.printf("Error: %s is not configured with %s protocol(printData part of OLED)\n", 
                getName(), protocol.getProtocolName());
    }
}
