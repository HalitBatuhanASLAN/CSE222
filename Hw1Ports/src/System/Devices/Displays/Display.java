package System.Devices.Displays;
import System.Devices.Device;

public abstract class Display extends Device
{
    public String getDevType()
    {
        return "Display";
    }
    public abstract void printData(String data);
}
