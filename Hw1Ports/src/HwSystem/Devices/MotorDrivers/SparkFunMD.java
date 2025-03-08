package HwSystem.Devices.MotorDrivers;

import HwSystem.Protocols.SPI;

public class SparkFunMD extends MotorDriver
{
    public SparkFunMD(String protocolName)
    {
        if(!protocolName.equals("SPI"))
            System.out.println("Protocol does not match with device!!!");
        else
            setProtocol(protocolName);
    }
    public void turnOn()
    {
        /*state = true;
        System.out.printf("%s: Turning On\n",getName());*/
        String data = String.format("%s: Turning On\n",getName());
        if(protocol.getProtocolName().equals("SPI"))
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
        if(protocol.getProtocolName().equals("SPI"))
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
        return "SparkFunMD";
    }
    public void setMotorSpeed(int speed)
    {
        //System.out.printf("%s: setting speed to %d\n",getName(),speed);
       System.out.printf("%s: setting speed to %d\n",getName(),speed);
        if(protocol.getProtocolName().equals("SPI"))
        {
            SPI tmp = new SPI();
            String speedString = Integer.toString(speed);
            tmp.write(speedString);
        }
        else
        {
            System.out.printf("Error: %s is not configured with %s protocol\n", 
                getName(), protocol.getProtocolName());
        }
    }
}
