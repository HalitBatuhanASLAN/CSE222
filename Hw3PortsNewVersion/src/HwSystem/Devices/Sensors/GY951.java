package HwSystem.Devices.Sensors;

import HwSystem.Protocols.SPI;
import HwSystem.Protocols.UART;
import java.lang.Math;

/**
 * Concrete implementation of an IMU sensor for the GY951 device.
 * This class provides functionality for the GY951 IMU sensor using either UART or SPI protocols.
 */
public class GY951 extends IMUSensor
{
    /**
     * Constructor that initializes the GY951 device with the specified protocol.
     * Accepts either UART or SPI protocols as they are the supported protocols for this device.
     * 
     * @param protocolName The name of the protocol to use (must be "UART" or "SPI")
     */
    public GY951(String protocolName)
    {
        if(!protocolName.equals("UART") && !protocolName.equals("SPI"))
            System.err.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    /**
     * Turns on the GY951 device using either UART or SPI protocol.
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
     * Turns off the GY951 device using either UART or SPI protocol.
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
    /**
     * Gets the name of this device.
     * 
     * @return The string "GY951"
     */
    public String getName()
        {return "GY951";}

    /**
     * Gets the acceleration value from the GY951 sensor.
     * Simulates reading acceleration data using either UART or SPI protocol.
     * 
     * @return A random float value representing acceleration, or -999 if protocol error
     */
    public float getAccel()
    {
        float accel  = (float)Math.random();
        String readedString;
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            readedString = tmp.read();
            System.out.println(readedString);
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
            readedString = tmp.read();
            System.out.println(readedString);
        }
        else
        {
            System.err.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
            accel = -999;
        }
        return accel;
    } 
    /**
     * Gets the rotation value from the GY951 sensor.
     * Simulates reading rotation data using either UART or SPI protocol.
     * 
     * @return A random float value representing rotation, or -999 if protocol error
     */
    public float getRot()
    {
        float rotational  = (float)Math.random();
        String readedString;
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            readedString = tmp.read();
            System.out.println(readedString);
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
            readedString = tmp.read();
            System.out.println(readedString);
        }
        else
        {
            System.err.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
            rotational = -999;
        }
        return rotational;
    }

    /**
     * Converts the sensor data to a string representation.
     * 
     * @return A formatted string containing the acceleration and rotation values
     */
    public String data2String()
        {return String.format("Accel:%.2f, Rot:%.2f",getAccel(),getRot());}
}