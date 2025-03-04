package HwSystem.Devices.MotorDrivers;
import HwSystem.Devices.Device;

public abstract class MotorDriver extends Device
{
    public String getDevType()
    {
        return "MotorDriver"; 
    }
    public abstract void setMotorSpeed(int speed);
}