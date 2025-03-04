package System.Devices.Sensors;

public class DHT11 extends TempSensor
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
        return "DHT11";
    }
    public String data2String()
    {
        return "aaa";
    }
    public float getTemp()
    {
        return 15;
    }
}
