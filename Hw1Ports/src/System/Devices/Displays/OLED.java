package System.Devices.Displays;

public class OLED extends Display
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
        return "OLED";
    }
    public void printData(String data)
    {
        /*look later */
    }
}
