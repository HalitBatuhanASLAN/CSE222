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
    /** The communication protocol used by this device. */
    protected Protocol protocol;
    
    /**
     * Enumeration of possible device states.
     */
    public enum DeviceState
    {
        /**
         * Device is powered on and operational.
         */
        ON,
        /**
         * Device is powered off.
         */
        OFF
    }

     /** Current state of the device (ON or OFF). */
    protected DeviceState state;

    /*constructor, initially device state is off */
    public Device()
        {state = DeviceState.OFF;}
    
     /**
     * Turns on the device.
     * Implementation depends on the specific device type.
     */
    public abstract void turnOn();
    
    /**
     * Turns off the device.
     * Implementation depends on the specific device type.
     */
    public abstract void turnOff();

    /**
     * Gets the name of this device.
     * 
     * @return The device name as a string
     */
    public abstract String getName();
    
    /**
     * Gets the type of this device.
     * 
     * @return The device type as a string
     */
    public abstract String getDevType();

    /**
     * Gets the current state of the device.
     * 
     * @return The current device state (ON or OFF)
     */
    public DeviceState getState()
        {return state;}

    /**
     * Gets the name of the protocol currently used by this device.
     * 
     * @return The protocol name as a string, or null if no protocol is set
     */
    public String getProtocol()
    {
        if(this.protocol == null)
            return null;
        return this.protocol.getProtocolName();
    }

    /**
     * Sets the communication protocol for this device.
     * Creates an appropriate protocol object based on the provided protocol name.
     * 
     * @param protocolName The name of the protocol to use ("UART", "SPI", "I2C", or "OneWire")
     */
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

    /**
     * Sets the state of the device.
     * 
     * @param dState The new state to set for the device
     */
    public void setState(DeviceState dState)
        {state = dState;}
}