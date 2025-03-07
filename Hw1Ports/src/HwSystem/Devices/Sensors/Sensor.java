package HwSystem.Devices.Sensors;

import HwSystem.Devices.Device;

public abstract class Sensor extends Device
{
    public String getDevType()
    {
        return "Sensor type is the output of " + getSensType();
    }
    public abstract String getSensType();
    public abstract String data2String();
}
