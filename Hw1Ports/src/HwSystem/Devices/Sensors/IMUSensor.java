package HwSystem.Devices.Sensors;

public abstract class IMUSensor extends Sensor
{
    public String getSensType()
    {
        return "IMUSensor";
    }
    public abstract float getAccel(); 
    public abstract float getRot();
    public String getDevType()
    {
        return "IMUSensor Sensor";
    }
}
