package HwSystem.Devices.Displays;
import HwSystem.Devices.Device;

/**
 * Abstract class representing display devices in the hardware system.
 * Extends the base Device class and provides specific functionality for displays.
 * Concrete display implementations must extend this class and implement the printData method.
 */
public abstract class Display extends Device
{
    public String getDevType()
        {return "Display";}
    
    public abstract void printData(String data);
}
