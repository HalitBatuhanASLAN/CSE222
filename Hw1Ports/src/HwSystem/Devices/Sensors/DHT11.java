package HwSystem.Devices.Sensors;

import HwSystem.Protocols.OneWire;
import java.lang.Math;

public class DHT11 extends TempSensor
{
    public DHT11(String protocolName)
    {
        if(!protocolName.equals("OneWire"))
            System.out.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    public void turnOn()
    {
        /*state = true;
        System.out.printf("%s: Turning On\n",getName());*/
        //String data = String.format("%s: Turning On\n",getName());
        if(protocol.getProtocolName().equals("OneWire"))
        {
            System.out.printf("%s: Turning On\n",getName());
            OneWire tmp = new OneWire();
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
        if(protocol.getProtocolName().equals("OneWire"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            OneWire tmp = new OneWire();
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
        return "DHT11";
    }
    public float getTemp()
    {
        float temp = (float)Math.random();
        String readedString;
        if(protocol.getProtocolName().equals("OneWire"))
        {
            OneWire tmp = new OneWire();
            readedString = tmp.read();
            System.out.println(readedString);
        }
        else
        {
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
            temp = -999;
        }
        return temp;
    }

    /*control the following from pdf */
    public String data2String()
    {
        return String.format("Tempurature:%.2fC",getTemp());
    }

}
