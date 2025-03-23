package HwSystem.Protocols;

/**
 * Implementation of the UART (Universal Asynchronous Receiver-Transmitter) communication protocol.
 * <p>
 * This class provides a concrete implementation of the Protocol interface
 */
public class UART implements Protocol
{
    /**
     * Simulates reading data from a UART device.
     * 
     * @return A string indicating that data is being read using the UART protocol
     */
    public String read()
        {return getProtocolName() + ": Readining.";}

    /**
     * Simulates writing data to a UART device.
     * 
     * @param data The string data to be written to the UART device
     */
    public void write(String data)
        {System.out.printf("%s: writing %s\n",getProtocolName(),data);}

    /**
     * Gets the name of this protocol.
     * 
     * @return The string "UART"
     */
    public String getProtocolName()
        {return "UART";}
}
