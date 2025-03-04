package System.Devices.MotorDrivers;

public class SparkFunMD extends MotorDriver
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
        return "SparkFunMD";
    }
    public void setMotorSpeed(int speed)
    {
        System.out.printf("%s: setting speed to %d\n",getName(),speed);
    }
}
