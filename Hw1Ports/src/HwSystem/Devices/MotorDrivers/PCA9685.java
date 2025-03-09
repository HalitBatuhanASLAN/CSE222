package HwSystem.Devices.MotorDrivers;

import HwSystem.Protocols.I2C;

public class PCA9685 extends MotorDriver
{
    public PCA9685(String protocolName)
    {
        if(!protocolName.equals("I2C"))
            System.out.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    public void turnOn()
    {
        //System.out.printf("%s: Turning On\n",getName());
        
        //String data = String.format("%s: Turning On\n",getName());
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning On\n",getName());
            I2C tmp = new I2C();
            tmp.write("Turning ON");
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
        //String data = String.format("%s: Turning Off\n",getName());
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            I2C tmp = new I2C();
            tmp.write("Turning OFF");
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
        System.out.printf("%s: setting speed to %d\n",getName(),speed);
        if(protocol.getProtocolName().equals("I2C"))
        {
            I2C tmp = new I2C();
            String speedString = Integer.toString(speed);
            tmp.write(speedString);
        }
        else
        {
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
        }
        //System.out.printf("%s: setting speed to %d\n",getName(),speed);
    }
}