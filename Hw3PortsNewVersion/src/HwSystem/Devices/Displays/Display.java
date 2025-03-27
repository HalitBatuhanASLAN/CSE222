package HwSystem.Devices.Displays;
import HwSystem.Devices.Device;

/**
 * Abstract class representing display devices in the hardware system.
 * Extends the base Device class and provides specific functionality for displays.
 * Concrete display implementations must extend this class and implement the printData method.
 */
public abstract class Display extends Device
{
    /**
     * Default constructor for Display class.
     * Initializes a new display device with default settings.
     */
    public Display()
        {super();}

     /**
     * Returns the type of this device.
     * @return String containing "Display" as the device type
     */
    public String getDevType()
        {return "Display";}
    
    /**
     * Prints data to the display.
     * @param data The string data to be displayed
     */
    public abstract void printData(String data);
}
