package HwSystem.Devices.WirelessIOs;

import HwSystem.Protocols.UART;
import HwSystem.Protocols.SPI;

/**
 * Wifi implementation of the WirelessIO abstract class.
 * This class provides functionality for WiFi communication using either UART or SPI protocols.
 */
public class Wifi extends WirelessIO
{
    /**
     * Constructor that initializes the Wifi device with the specified protocol.
     * Supports both UART and SPI protocols.
     * 
     * @param protocolName The name of the protocol to use (must be either "UART" or "SPI")
     */
    public Wifi(String protocolName)
    {
        if(!protocolName.equals("UART") && !protocolName.equals("SPI"))
            System.err.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    /**
     * Turns on the Wifi device using the configured protocol.
     */
    public void turnOn()
    {
        if(protocol.getProtocolName().equals("UART"))
        {
            System.out.printf("%s: Turning On\n",getName());
            UART tmp = new UART();
            tmp.write("Turning ON");
            state = DeviceState.On;
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            System.out.printf("%s: Turning On\n",getName());
            SPI tmp = new SPI();
            tmp.write("Turning ON");
            state = DeviceState.On;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
    /**
     * Turns off the Wifi device using the configured protocol.
     */
    public void turnOff()
    {
        if(protocol.getProtocolName().equals("UART"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            UART tmp = new UART();
            tmp.write("Turning OFF");
            state = DeviceState.Off;
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            System.out.printf("%s: Turning Off\n",getName());            
            SPI tmp = new SPI();
            tmp.write("Turning OFF");
            state = DeviceState.Off;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
    public String getName()
        {return "Wifi";}

    /**
     * Sends data through the Wifi interface using the configured protocol.
     * 
     * @param data The string data to be transmitted wirelessly
     */
    public void sendData(String data)
    {
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            tmp.write(data);
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
            tmp.write(data);
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }

    /**
     * Receives data from the Wifi interface using the configured protocol.
     * 
     * @return The string data received from the wireless interface
     */
    public String recvData()
    {
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            String data = tmp.read();
            return data;
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
            String data = tmp.read();
            return data;

        }
        else
            return String.format("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }    
}