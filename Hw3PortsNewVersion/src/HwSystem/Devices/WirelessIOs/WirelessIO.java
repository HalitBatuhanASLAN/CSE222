package HwSystem.Devices.WirelessIOs;
import HwSystem.Devices.Device;

/**
 * Abstract class representing wireless input/output devices in the hardware system.
 * Extends the base Device class and provides specific functionality for wireless communication.
 * Concrete wireless IO implementations must extend this class and implement the sendData and recvData methods.
 */
public abstract class WirelessIO extends Device
{
    /**
     * Default constructor for WirelessIO class.
     * Initializes a new wireless device with default settings.
     */
    public WirelessIO() 
        {super();}

    public String getDevType()
        {return "WirelessIO";}

    /**
     * Sends data through the wireless interface.
     * 
     * @param data The string data to be transmitted wirelessly
     */
    public abstract void sendData(String data);

    /**
     * Receives data from the wireless interface.
     * 
     * @return The string data received from the wireless interface
     */
    public abstract String recvData();
}
