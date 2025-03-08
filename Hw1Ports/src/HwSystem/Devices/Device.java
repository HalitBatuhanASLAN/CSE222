package HwSystem.Devices;

import HwSystem.Protocols.I2C;
import HwSystem.Protocols.OneWire;
import HwSystem.Protocols.Protocol;
import HwSystem.Protocols.SPI;
import HwSystem.Protocols.UART;

public abstract class Device
{
    //private Protocol protocol;
    protected Protocol protocol;
    public enum DeviceState
    {
        On,
        Off
    }
    protected DeviceState state;

    public Device()
    {
        state = DeviceState.Off;
    }
    public abstract void turnOn();
    public abstract void turnOff();

    public abstract String getName();
    public abstract String getDevType();

    public DeviceState getState()
    {
        return state;
    }

    public String getProtocol()
    {
        return protocol.getProtocolName();
    }
    public void setProtocol(String protocolName)
    {
        if(protocolName.equals("UART"))
            protocol = new UART();
        else if(protocolName.equals("SPI"))
            protocol = new SPI();
        else if(protocolName.equals("I2C"))
            protocol = new I2C();
        else if(protocolName.equals("OneWire"))
            protocol = new OneWire();
    }
    public void setState(DeviceState dState)
    {
        state = dState;
    }
}