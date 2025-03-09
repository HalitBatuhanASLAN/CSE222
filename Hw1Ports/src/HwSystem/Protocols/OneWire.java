package HwSystem.Protocols;

/**
 * Implementation of the OneWire communication protocol.
 * <p>
 * This class provides a concrete implementation of the Protocol interface
 */
public class OneWire implements Protocol
{
    /**
     * Simulates reading data from a OneWire device.
     * 
     * @return A string indicating that data is being read using the OneWire protocol
     */
    public String read()
        {return getProtocolName() + ": Readining.";}

    /**
     * Simulates writing data to a OneWire device.
     * 
     * @param data The string data to be written to the OneWire device
     */
    public void write(String data)
        {System.out.printf("%s:%s\n",getProtocolName(),data);}

    /**
     * Gets the name of this protocol.
     * 
     * @return The string "OneWire"
     */
    public String getProtocolName()
        {return "OneWire";}
}
