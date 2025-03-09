package HwSystem.Protocols;

/**
 * Implementation of the I2C (Inter-Integrated Circuit) communication protocol.
 * <p>
 * This class provides a concrete implementation of the Protocol interface
 */
public class I2C implements Protocol
{
    /**
     * Simulates reading data from an I2C device.
     * 
     * @return A string indicating that data is being read using the I2C protocol
     */
    public String read()
        {return getProtocolName() + ": Readining.";}

     /**
     * Simulates writing data to an I2C device.
     * 
     * @param data The string data to be written to the I2C device
     */
    public void write(String data)
        {System.out.printf("%s: writing %s\n",getProtocolName(),data);}

    /**
     * Gets the name of this protocol.
     * 
     * @return The string "I2C"
     */
    public String getProtocolName()
        {return "I2C";}
}
