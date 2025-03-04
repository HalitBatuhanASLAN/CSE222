package System.Devices.MotorDrivers;

import System.Protocols.I2C;

public class PCA9685 extends MotorDriver
{
    public void turnOn()
    {
        //System.out.printf("%s: Turning On\n",getName());
        
        String data = String.format("%s: Turning On\n",getName());
        if(protocol.getProtocolName().equals("I2C"))
        {
            I2C tmp = new I2C();
            tmp.write(data);
            state = DeviceState.On;
        }
        else
        {
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
        }
    }
    public void turnOff()
    {
        String data = String.format("%s: Turning Off\n",getName());
        if(protocol.getProtocolName().equals("I2C"))
        {
            I2C tmp = new I2C();
            tmp.write(data);
            state = DeviceState.Off;
        }
        else
        {
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
        }
        /*state = false;
        System.out.printf("%s: Turning Off\n",getName());*/
    }
    public String getName()
    {
        return "PCA9685";
    }
    public void setMotorSpeed(int speed)
    {
        String data = String.format("%s: setting speed to %d\n",getName(),speed);
        if(protocol.getProtocolName().equals("I2C"))
        {
            I2C tmp = new I2C();
            tmp.write(data);
        }
        else
        {
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
        }
        //System.out.printf("%s: setting speed to %d\n",getName(),speed);
    }
}