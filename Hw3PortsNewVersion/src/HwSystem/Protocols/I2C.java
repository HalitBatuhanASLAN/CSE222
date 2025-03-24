package HwSystem.Protocols;

import java.util.Stack;

/**
 * Implementation of the I2C (Inter-Integrated Circuit) communication protocol.
 * <p>
 * This class provides a concrete implementation of the Protocol interface
 */
public class I2C implements Protocol
{
    private int portID;
    private String logPath;
    private Stack<String> logs = new Stack<>();

    public I2C()
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
     * Simulates reading data from an I2C device.
     * 
     * @return A string indicating that data is being read using the I2C protocol
     */
    public String read()
    {
        logs.push("Readining");
        return getProtocolName() + ": Readining.";
    }
     /**
     * Simulates writing data to an I2C device.
     * 
     * @param data The string data to be written to the I2C device
     */
    public void write(String data)
    {
        logs.push(data);
    }
    /**
     * Gets the name of this protocol.
     * 
     * @return The string "I2C"
     */
    public String getProtocolName()
        {return "I2C";}
}
