package HwSystem.Devices.WirelessIOs;

import HwSystem.Protocols.UART;

/**
 * Bluetooth implementation of the WirelessIO abstract class.
 * This class provides functionality for Bluetooth communication using the UART protocol.
 */
public class Bluetooth extends WirelessIO
{
    /**
     * Constructor that initializes the Bluetooth device with the specified protocol.
     * Only accepts UART protocol as it's the only supported protocol for this device.
     * 
     * @param protocolName The name of the protocol to use (must be "UART")
     */
    public Bluetooth(String protocolName)
    {
        if(!protocolName.equals("UART"))
            System.out.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    /**
     * Turns on the Bluetooth device using the UART protocol.
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
        else
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
    /**
     * Turns off the Bluetooth device using the UART protocol.
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
        else
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
    public String getName()
        {return "Bluetooth";}

    /**
     * Sends data through the Bluetooth interface using the UART protocol.
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
        else
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }

    /**
     * Receives data from the Bluetooth interface using the UART protocol.
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
        else
            return String.format("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
}