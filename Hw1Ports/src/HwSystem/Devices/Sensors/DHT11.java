package HwSystem.Devices.Sensors;

import HwSystem.Protocols.OneWire;
import java.lang.Math;

/**
 * Concrete implementation of a temperature sensor for the DHT11 device.
 * This class provides functionality for the DHT11 temperature sensor using the OneWire protocol.
 */
public class DHT11 extends TempSensor
{
    /**
     * Constructor that initializes the DHT11 device with the specified protocol.
     * Only accepts OneWire protocol as it's the only supported protocol for this device.
     * 
     * @param protocolName The name of the protocol to use (must be "OneWire")
     */
    public DHT11(String protocolName)
    {
        if(!protocolName.equals("OneWire"))
            System.out.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    /**
     * Turns on the DHT11 device using the OneWire protocol.
     */
    public void turnOn()
    {
        if(protocol.getProtocolName().equals("OneWire"))
        {
            System.out.printf("%s: Turning On\n",getName());
            OneWire tmp = new OneWire();
            tmp.write("Turning ON");
            state = DeviceState.On;
        }
        else
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
    /**
     * Turns off the DHT11 device using the OneWire protocol.
     */
    public void turnOff()
    {
        if(protocol.getProtocolName().equals("OneWire"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            OneWire tmp = new OneWire();
            tmp.write("Turning OFF");
            state = DeviceState.Off;
        }
        else
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
    /**
     * Gets the name of this device.
     * 
     * @return The string "DHT11"
     */
    public String getName()
        {return "DHT11";}

    /**
     * Gets the temperature value from the DHT11 sensor.
     * Simulates reading temperature data using the OneWire protocol.
     * 
     * @return A random float value representing temperature, or -999 if protocol error
     */
    public float getTemp()
    {
        float temp = (float)Math.random();
        String readedString;
        if(protocol.getProtocolName().equals("OneWire"))
        {
            OneWire tmp = new OneWire();
            readedString = tmp.read();
            System.out.println(readedString);
        }
        else
        {
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
            temp = -999;
        }
        return temp;
    }
    /**
     * Converts the sensor data to a string representation.
     * 
     * @return A formatted string containing the temperature value
     */
    public String data2String()
        {return String.format("Tempurature:%.2fC",getTemp());}
}