package HwSystem.Devices.Sensors;

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
            System.err.println("Protocol does not match with device!!!(DHT11)");
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
            System.out.printf("%s: Turning ON\n",getName());
            state = DeviceState.ON;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol(turnON part of DHT11)\n", 
                getName(), protocol.getProtocolName());
    }
    /**
     * Turns off the DHT11 device using the OneWire protocol.
     */
    public void turnOff()
    {
        if(protocol.getProtocolName().equals("OneWire"))
        {
            System.out.printf("%s: Turning OFF\n",getName());
            state = DeviceState.OFF;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol(turnOFF part of DHT11)\n", 
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
        float temp;
        if(protocol.getProtocolName().equals("OneWire"))
            temp = (float)24.00;
        else
        {
            System.err.printf("Error: %s is not configured with %s protocol(getTemp part of DHT11)\n", 
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
        {return String.format("Temp:%.2fC",getTemp());}
}