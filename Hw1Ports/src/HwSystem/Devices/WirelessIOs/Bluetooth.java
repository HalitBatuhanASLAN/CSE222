package HwSystem.Devices.WirelessIOs;

import HwSystem.Protocols.UART;

public class Bluetooth extends WirelessIO
{
    public Bluetooth(String protocolName)
    {
        if(!protocolName.equals("UART"))
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
        else
        {
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
        }
    }
    public String getName()
    {
        return "Bluetooth";
    }
    public void sendData(String data)
    {
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
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
        else
        {
            return String.format("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
        }
    }
}
