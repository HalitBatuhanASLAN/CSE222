package HwSystem.Devices.Sensors;

import HwSystem.Protocols.SPI;
import HwSystem.Protocols.UART;
import java.lang.Math;

public class GY951 extends IMUSensor
{
    public GY951(String protocolName)
    {
        if(!protocolName.equals("UART") && !protocolName.equals("SPI"))
            System.out.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    public void turnOn()
    {
        /*state = true;
        System.out.printf("%s: Turning On\n",getName());*/
        //String data = String.format("%s: Turning On\n",getName());
        if(protocol.getProtocolName().equals("UART"))
        {
            System.out.printf("%s: Turning On\n",getName());
            UART tmp = new UART();
            tmp.write("Turning ON");
            state = DeviceState.On;
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            System.out.printf("%s: Turning On\n",getName());
            SPI tmp = new SPI();
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
        /*state = false;
        System.out.printf("%s: Turning Off\n",getName());*/
        //String data = String.format("%s: Turning Off\n",getName());
        if(protocol.getProtocolName().equals("UART"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            UART tmp = new UART();
            tmp.write("Turning OFF");
            state = DeviceState.Off;
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            SPI tmp = new SPI();
            tmp.write("Turning OFF");
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
        return "GY951";
    }
    public float getAccel()
    {
        float accel  = (float)Math.random();
        String readedString;
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            readedString = tmp.read();
            System.out.println(readedString);
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
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
        float rotational  = (float)Math.random();
        String readedString;
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            readedString = tmp.read();
            System.out.println(readedString);
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
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
