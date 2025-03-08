package HwSystem.Devices.Sensors;

import HwSystem.Protocols.I2C;
import java.lang.Math;

public class MPU6050 extends IMUSensor
{
    public MPU6050(String protocolName)
    {
        if(!protocolName.equals("I2C"))
            System.out.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    public void turnOn()
    {
        /*state = true;
        System.out.printf("%s: Turning On\n",getName());*/
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
        /*state = false;
        System.out.printf("%s: Turning Off\n",getName());*/
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
    }
    public String getName()
    {
        return "MPU6050";
    }
    public float getAccel()
    {
        float accel = (float)Math.random();
        String readedString;
        if(protocol.getProtocolName().equals("I2C"))
        {
            I2C tmp = new I2C();
            readedString = tmp.read();
            System.out.println(readedString);
        }
        else
        {
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
            accel = -999;
        }
        return accel;
    } 
    public float getRot()
    {
        float rotational = (float)Math.random();
        String readedString;
        if(protocol.getProtocolName().equals("I2C"))
        {
            I2C tmp = new I2C();
            readedString = tmp.read();
            System.out.println(readedString);
        }
        else
        {
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
            rotational = -999;
        }
        return rotational;
    }

    public String data2String()
    {
        return String.format("Accel:%.2f, Rot:%.2f",getAccel(),getRot());
    }
}
