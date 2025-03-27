package HwSystem.Devices.Sensors;

/**
 * Abstract class representing temperature sensors.
 * Extends the Sensor class and provides specific functionality for temperature sensors.
 * Concrete temperature sensor implementations must extend this class and implement the getTemp method.
 */
public abstract class TempSensor extends Sensor
{
    /**
     * Default constructor for TempSensor class.
     * Initializes a new tempsesor device with default settings.
     */
    public TempSensor()
        {super();}
    /**
     * Gets the specific type of the sensor.
     * 
     * @return A string indicating this is a temperature sensor
     */
    public String getSensType()
        {return "TempSensor Sensor";}

    /**
     * Gets the temperature value from the sensor.
     * 
     * @return The temperature value as a float
     */
    public abstract float getTemp();
}