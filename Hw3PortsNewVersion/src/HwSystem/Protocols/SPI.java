package HwSystem.Protocols;

/**
 * Implementation of the SPI (Serial Peripheral Interface) communication protocol.
 * <p>
 * This class provides a concrete implementation of the Protocol interface
 */
public class SPI implements Protocol
{
    /**
     * Simulates reading data from an SPI device.
     * 
     * @return A string indicating that data is being read using the SPI protocol
     */
    public String read()
        {return getProtocolName() + ": Readining.";}

    /**
     * Simulates writing data to an SPI device.
     * 
     * @param data The string data to be written to the SPI device
     */
    public void write(String data)
        {System.out.printf("%s: writing %s\n",getProtocolName(),data);}

    /**
     * Gets the name of this protocol.
     * 
     * @return The string "SPI"
     */
    public String getProtocolName()
        {return "SPI";}
}
