package System.Devices.Sensors;

import System.Devices.Device;

public abstract class Sensor extends Device
{
    public abstract String getDevType();
    public abstract String getSensType();
    public abstract String data2String();
}
