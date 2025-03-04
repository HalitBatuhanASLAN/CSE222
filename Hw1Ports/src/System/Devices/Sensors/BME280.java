package System.Devices.Sensors;

import System.Protocols.I2C;
import System.Protocols.SPI;

public class BME280 extends TempSensor
{
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
        if(protocol.getProtocolName().equals("I2C"))
        {
            I2C tmp = new I2C();
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
        return "BME280";
    }
    
    public float getTemp()
    {
        float temp;
        if(protocol.getProtocolName().equals("I2C"))
        {
            I2C tmp = new I2C();
            temp = Float.parseFloat(tmp.read());
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
            temp = Float.parseFloat(tmp.read());
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
