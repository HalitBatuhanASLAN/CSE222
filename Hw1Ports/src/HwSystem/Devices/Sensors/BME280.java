package HwSystem.Devices.Sensors;

import HwSystem.Protocols.I2C;
import HwSystem.Protocols.SPI;
import java.lang.Math;

public class BME280 extends TempSensor
{
    public BME280(String protocolName)
    {
        if(!protocolName.equals("I2C") && !protocolName.equals("SPI"))
            System.out.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    public void turnOn()
    {
        /*state = true;
        System.out.printf("%s: Turning On\n",getName());*/
        //String data = String.format("%s: Turning On\n",getName());
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning On\n",getName());
            I2C tmp = new I2C();
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
        if(protocol.getProtocolName().equals("I2C"))
        {
            System.out.printf("%s: Turning Off\n",getName());
            I2C tmp = new I2C();
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
        return "BME280";
    }
    
    public float getTemp()
    {
        float temp = (float)Math.random();
        String readedString;
        if(protocol.getProtocolName().equals("I2C"))
        {
            I2C tmp = new I2C();
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
            temp = -999;
        }
        return temp;
    }

    /*control the following from pdf for all sensors*/
    public String data2String()
    {
        return String.format("Tempurature:%.2fC",getTemp());
    }

}
