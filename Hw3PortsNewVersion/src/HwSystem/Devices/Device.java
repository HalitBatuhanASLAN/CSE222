package HwSystem.Devices;

import HwSystem.Protocols.I2C;
import HwSystem.Protocols.OneWire;
import HwSystem.Protocols.Protocol;
import HwSystem.Protocols.SPI;
import HwSystem.Protocols.UART;

/**
 * Abstract base class for all hardware devices in the system.
 * Provides common functionality for device state management and protocol handling.
 * All specific device types must extend this class and implement the abstract methods.
 */

public abstract class Device
{
    protected Protocol protocol;
    public enum DeviceState
    {
        On,
        Off
    }
    protected DeviceState state;

    /*constructor, initially device state is off */
    public Device()
        {state = DeviceState.Off;}
    
    public abstract void turnOn();
    public abstract void turnOff();

    public abstract String getName();
    public abstract String getDevType();

    public DeviceState getState()
        {return state;}

    public String getProtocol()
    {
        if(this.protocol == null)
            return null;
        return this.protocol.getProtocolName();
    }
    public void setProtocol(String protocolName)
    {
        if(protocolName.equals("null"))
            return;
        else if(protocolName.equals("UART"))
            protocol = new UART();
        else if(protocolName.equals("SPI"))
            protocol = new SPI();
        else if(protocolName.equals("I2C"))
            protocol = new I2C();
        else if(protocolName.equals("OneWire"))
            protocol = new OneWire();
    }
    public void setState(DeviceState dState)
        {state = dState;}
}