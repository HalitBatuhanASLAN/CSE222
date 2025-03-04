package System.Devices.MotorDrivers;
import System.Devices.Device;

public abstract class MotorDriver extends Device
{
    public String getDevType()
    {
        return "MotorDriver"; 
    }
    public abstract void setMotorSpeed(int speed);
}