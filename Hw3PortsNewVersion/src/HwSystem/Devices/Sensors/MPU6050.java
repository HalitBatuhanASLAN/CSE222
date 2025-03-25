package HwSystem.Devices.Sensors;

/**
 * Concrete implementation of an IMU sensor for the MPU6050 device.
 * This class provides functionality for the MPU6050 IMU sensor using the I2C protocol.
 */
public class MPU6050 extends IMUSensor
{
    /**
     * Constructor that initializes the MPU6050 device with the specified protocol.
     * Only accepts I2C protocol as it's the only supported protocol for this device.
     * 
     * @param protocolName The name of the protocol to use (must be "I2C")
     */
    public MPU6050(String protocolName)
    {
        if(!protocolName.equals("I2C"))
            System.err.println("Protocol does not match with device!!!(MPU6050)");
        else
            setProtocol(protocolName);
    }
    /**
     * Turns on the MPU6050 device using the I2C protocol.
     */
    public void turnOn()
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning On\n",getName());
            /*I2C tmp = new I2C();
            tmp.write("Turning ON");*/
            state = DeviceState.ON;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol(turnON part of MPU6050)\n", 
                getName(), protocol.getProtocolName());
    }
    /**
     * Turns off the MPU6050 device using the I2C protocol.
     */
    public void turnOff()
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            /*I2C tmp = new I2C();
            tmp.write("Turning OFF");*/
            state = DeviceState.OFF;
        }
        else
            System.err.printf("Error: %s is not configured with %s protocol(turnOFF part of MPU6050)\n", 
                getName(), protocol.getProtocolName());
    }
    /**
     * Gets the name of this device.
     * 
     * @return The string "MPU6050"
     */
    public String getName()
        {return "MPU6050";}

    /**
     * Gets the acceleration value from the MPU6050 sensor.
     * Simulates reading acceleration data using the I2C protocol.
     * 
     * @return A random float value representing acceleration, or -999 if protocol error
     */
    public float getAccel()
    {
        float accel = (float)1.00;
        String readedString;
        if(protocol.getProtocolName().equals("I2C"))
        {
            /*I2C tmp = new I2C();
            readedString = tmp.read();
            System.out.println(readedString);*/
            readedString = protocol.read();
        }
        else
        {
            System.err.printf("Error: %s is not configured with %s protocol(getAccel part of MPU6050)\n", 
                getName(), protocol.getProtocolName());
            accel = -999;
        }
        return accel;
    }
    /**
     * Gets the rotation value from the MPU6050 sensor.
     * Simulates reading rotation data using the I2C protocol.
     * 
     * @return A random float value representing rotation, or -999 if protocol error
     */
    public float getRot()
    {
        float rotational = (float)0.50;
        String readedString;
        if(protocol.getProtocolName().equals("I2C"))
        {
            /*I2C tmp = new I2C();
            readedString = tmp.read();
            System.out.println(readedString);*/
            readedString = protocol.read();
        }
        else
        {
            System.err.printf("Error: %s is not configured with %s protocol(getRot part of MPU6050)\n", 
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