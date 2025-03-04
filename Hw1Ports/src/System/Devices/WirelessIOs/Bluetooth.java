package System.Devices.WirelessIOs;

public class Bluetooth extends WirelessIO
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
        return "LED";
    }
    public void sendData(String data)
    {
        /*look at later */
    }
    public String recvData()
    {
        /*look at later */
        return "LAter look at";
    }
}
