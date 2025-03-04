package HwSystem.Devices.Sensors;

import HwSystem.Protocols.SPI;
import HwSystem.Protocols.UART;

public class GY_951 extends IMUSensor
{
    public void turnOn()
    {
        /*state = true;
        System.out.printf("%s: Turning On\n",getName());*/
        String data = String.format("%s: Turning On\n",getName());
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            tmp.write(data);
            state = DeviceState.On;
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
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
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            tmp.write(data);
            state = DeviceState.Off;
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
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
        return "GY_951";
    }
    public float getAccel()
    {
        float accel;
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            accel = Float.parseFloat(tmp.read());
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
            accel = Float.parseFloat(tmp.read());
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
        float rotational;
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            rotational = Float.parseFloat(tmp.read());
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
            rotational = Float.parseFloat(tmp.read());
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
