package HwSystem.Devices.Sensors;

import HwSystem.Devices.Device;

/**
 * Abstract class representing sensor devices in the hardware system.
 * Extends the base Device class and provides specific functionality for sensors.
 * Concrete sensor implementations must extend this class and implement the getSensType and data2String methods.
 */
public abstract class Sensor extends Device
{
    /**
     * Default constructor for Sensor class.
     * Initializes a new sensor device with default settings.
     */
    public Sensor()
        {super();}
    /**
     * Gets the device type description, which includes the sensor type.
     * 
     * @return A string describing the device type based on the sensor type
     */
    public String getDevType()
        {return getSensType();}
        
    /**
     * Gets the specific type of the sensor.
     * 
     * @return A string representing the specific sensor type
     */
    public abstract String getSensType();
    
    /**
     * Converts the sensor data to a string representation.
     * 
     * @return A string representation of the sensor data
     */
    public abstract String data2String();
}
