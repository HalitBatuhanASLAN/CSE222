package System.Devices;

import System.Protocols.Protocol;

public abstract class Device
{
    //private Protocol protocol;
    protected Protocol protocol;
    protected enum DeviceState
    {
        On,
        Off
    }
    protected DeviceState state;

    public abstract void turnOn();
    public abstract void turnOff();

    public abstract String getName();
    public abstract String getDevType();

    public DeviceState getState()
    {
        return state;
    }

    public Protocol getProtocol()
    {
        return protocol;
    }
}