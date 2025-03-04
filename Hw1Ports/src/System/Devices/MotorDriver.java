package System.Devices;

public abstract class MotorDriver extends Device
{
    public abstract String getDevType()
    {
        return "MotorDriver"; 
    }
    public abstract void setMotorSpeed(int speed);
}