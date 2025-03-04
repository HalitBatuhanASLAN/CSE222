package HwSystem.Devices.Displays;
import HwSystem.Devices.Device;

public abstract class Display extends Device
{
    public String getDevType()
    {
        return "Display";
    }
    public abstract void printData(String data);
}
