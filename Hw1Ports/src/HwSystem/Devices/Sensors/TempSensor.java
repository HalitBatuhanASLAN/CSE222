package HwSystem.Devices.Sensors;

public abstract class TempSensor extends Sensor
{
    public String getSensType()
    {
        return "TempSensor";
    }
    public abstract float getTemp();
    public String getDevType()
    {
        return "TempSensor Sensor";
    }
}
