package HwSystem.Devices.WirelessIOs;

import HwSystem.Protocols.UART;
import HwSystem.Protocols.SPI;

public class Wifi extends WirelessIO
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
        return "LED";
    }
    public void sendData(String data)
    {
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            tmp.write(data);
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
            tmp.write(data);
        }
        else
        {
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
        }
    }
    public String recvData()
    {
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            String data = tmp.read();
            return data;
        }
        else if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
            String data = tmp.read();
            return data;

        }
        else
        {
            return String.format("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
        }
    }    
}
