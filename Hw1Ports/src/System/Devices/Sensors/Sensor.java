package System.Devices.Sensors;

import System.Devices.Device;

public abstract class Sensor extends Device
{
    public String getDevType()
    {
        return "Sensor";
    }
    public abstract String getSensType();
    public abstract String data2String();
}
