package HwSystem.Devices.Sensors;

import HwSystem.Protocols.I2C;
import HwSystem.Protocols.SPI;

/**
 * Concrete implementation of a temperature sensor for the BME280 device.
 * This class provides functionality for the BME280 temperature sensor using either I2C or SPI protocols.
 */
public class BME280 extends TempSensor
{
    /**
     * Constructor that initializes the BME280 device with the specified protocol.
     * Accepts either I2C or SPI protocols as they are the supported protocols for this device.
     * 
     * @param protocolName The name of the protocol to use (must be "I2C" or "SPI")
     */
    public BME280(String protocolName)
    {
        if(!protocolName.equals("I2C") && !protocolName.equals("SPI"))
            System.err.println("Protocol does not match with device!!!");
        
        else
            setProtocol(protocolName);
    }
    /**
     * Turns on the BME280 device using either I2C or SPI protocol.
     */
    public void turnOn()
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning On\n",getName());
            I2C tmp = new I2C();
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
     * Turns off the BME280 device using either I2C or SPI protocol.
     */
    public void turnOff()
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            I2C tmp = new I2C();
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
     * @return The string "BME280"
     */
    public String getName()
        {return "BME280";}
    
    /**
     * Gets the temperature value from the BME280 sensor.
     * Simulates reading temperature data using either I2C or SPI protocol.
     * 
     * @return A random float value representing temperature, or -999 if protocol error
     */
    public float getTemp()
    {
        float temp = (float)24.00;
        String readedString;
        if(protocol.getProtocolName().equals("I2C"))
        {
            I2C tmp = new I2C();
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