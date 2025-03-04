package System.Devices.MotorDrivers;

import System.Protocols.UART;

public class PCA9685 extends MotorDriver
{
    public void turnOn()
    {
        state = true;
        System.out.printf("%s: Turning On\n",getName());
    }
    public void turnOff()
    {
        state = false;
        System.out.printf("%s: Turning Off\n",getName());
    }
    public String getName()
    {
        return "PCA9685";
    }
    public void setMotorSpeed(int speed)
    {
        String data = String.format("%s: setting speed to %d\n",getName(),speed);
        if(protocol.getProtocolName().equals("UART"))
        {
            UART tmp = new UART();
            tmp.write(data);
        }
        //System.out.printf("%s: setting speed to %d\n",getName(),speed);
    }
}