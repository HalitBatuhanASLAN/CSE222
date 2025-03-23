package HwSystem.Devices.MotorDrivers;

import HwSystem.Protocols.I2C;

/**
 * PCA9685 motor driver implementation that uses I2C protocol for communication.
 * This class provides specific functionality for the PCA9685 PWM controller chip
 * which is commonly used for motor speed control.
 */
public class PCA9685 extends MotorDriver
{
    /**
     * Constructor that initializes the PCA9685 with the specified protocol.
     * Only accepts I2C protocol as it's the only supported protocol for this device.
     * 
     * @param protocolName The name of the protocol to use (must be "I2C")
     */
    public PCA9685(String protocolName)
    {
        if(!protocolName.equals("I2C"))
            System.out.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    public void turnOn()
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning On\n",getName());
            I2C tmp = new I2C();
            tmp.write("Turning ON");
            state = DeviceState.On;
        }
        else
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
    public void turnOff()
    {
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            I2C tmp = new I2C();
            tmp.write("Turning OFF");
            state = DeviceState.Off;
        }
        else
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
    public String getName()
        {return "PCA9685";}
    /**
     * Sets the motor speed by sending the speed value over I2C protocol.
     * 
     * @param speed The desired motor speed value
     */
    public void setMotorSpeed(int speed)
    {
        System.out.printf("%s: setting speed to %d\n",getName(),speed);
        if(protocol.getProtocolName().equals("I2C"))
        {
            I2C tmp = new I2C();
            String speedString = Integer.toString(speed);
            tmp.write(speedString);
        }
        else
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
    }
}