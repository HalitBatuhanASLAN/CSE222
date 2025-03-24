package HwSystem.Protocols;

import java.util.Stack;

/**
 * Implementation of the SPI (Serial Peripheral Interface) communication protocol.
 * <p>
 * This class provides a concrete implementation of the Protocol interface
 */
public class SPI implements Protocol
{
    private int portID;
    private String logPath;
    private Stack<String> logs = new Stack<>();

    public SPI()
    {
        logs.push("Port Opened");
    }
    public void setPortID(int id)
        {portID = id;}
    public int getPortID()
        {return portID;}
    public void setLogPath(String path)
        {logPath = path;}
    public void writeLogFile()
    {

    }
    /**
     * Simulates reading data from an SPI device.
     * 
     * @return A string indicating that data is being read using the SPI protocol
     */
    public String read()
    {
        logs.push("Readining");
        return getProtocolName() + ": Readining.";
    }
    /**
     * Simulates writing data to an SPI device.
     * 
     * @param data The string data to be written to the SPI device
     */
    public void write(String data)
    {
        logs.push(data);
    }
    /**
     * Gets the name of this protocol.
     * 
     * @return The string "SPI"
     */
    public String getProtocolName()
        {return "SPI";}
}
