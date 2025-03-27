package HwSystem.Protocols;

/**
 * Interface defining the standard communication protocol methods
 * for hardware devices in the system.
 * <p>
 * This interface provides a common contract for all communication protocols
 * used by hardware devices, ensuring they implement basic read/write operations
 * and can identify themselves by name.
 */
public interface Protocol
{
    /**
     * Closes the protocol connection and releases any resources.
     * Should be called when the protocol is no longer needed.
     */
    void close();
    
    /**
     * Reads data from the device using this protocol.
     * 
     * @return A string containing the data read from the device
     */
    String read();

    /**
     * Writes data to the device using this protocol.
     * 
     * @param data The string data to be written to the device
     */
    void write(String data);

    /**
     * Gets the name of this protocol.
     * 
     * @return A string representing the protocol name (e.g., "I2C", "SPI", "OneWire")
     */
    String getProtocolName();
}