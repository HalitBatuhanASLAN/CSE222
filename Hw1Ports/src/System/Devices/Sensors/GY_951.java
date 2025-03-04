package System.Devices.Sensors;

public class GY_951 extends IMUSensor
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
        return "GY_951";
    }
    public float getAccel()
    {
        return 9999;
    } 
    public float getRot()
    {
        return 1111;
    }
    public String data2String()
    {
        return "aaaaa";
    }
}
