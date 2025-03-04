package System.Devices.WirelessIOs;
import System.Devices.Device;

public abstract class WirelessIO extends Device
{
    public String getDevType()
    {
        return "WirelessIO";
    }
    public abstract void sendData(String data);
    public abstract String recvData();
}
