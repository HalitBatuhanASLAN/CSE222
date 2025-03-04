package System.Devices.Displays;

public class LED extends Display
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
    public void printData(String data)
    {
        /*look later */
    }
}
