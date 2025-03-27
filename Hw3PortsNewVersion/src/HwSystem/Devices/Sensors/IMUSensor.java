package HwSystem.Devices.Sensors;

/**
 * Abstract class representing Inertial Measurement Unit (IMU) sensors.
 * Extends the Sensor class and provides specific functionality for IMU sensors.
 * Concrete IMU sensor implementations must extend this class and implement the getAccel and getRot methods.
 */
public abstract class IMUSensor extends Sensor
{
    /**
     * Default constructor for IMUSensor class.
     * Initializes a new imusensor device with default settings.
     */
    public IMUSensor()
        {super();}
    /**
     * Gets the specific type of the sensor.
     * 
     * @return A string indicating this is an IMU sensor
     */
    public String getSensType()
        {return "IMUSensor Sensor";}

    /**
     * Gets the acceleration value from the IMU sensor.
     * 
     * @return The acceleration value as a float
     */
    public abstract float getAccel(); 
    
    /**
     * Gets the rotation value from the IMU sensor.
     * 
     * @return The rotation value as a float
     */
    public abstract float getRot();
}
