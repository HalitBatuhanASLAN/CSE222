package HwSystem.Protocols;

import java.util.Stack;

/**
 * Implementation of the UART (Universal Asynchronous Receiver-Transmitter) communication protocol.
 * <p>
 * This class provides a concrete implementation of the Protocol interface
 */
public class UART implements Protocol
{
    private int portID;
    private String logPath;
    private Stack<String> logs = new Stack<>();

    public UART()
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
     * Simulates reading data from a UART device.
     * 
     * @return A string indicating that data is being read using the UART protocol
     */
    public String read()
    {
        logs.push("Readining");
        return getProtocolName() + ": Readining.";
    }
    /**
     * Simulates writing data to a UART device.
     * 
     * @param data The string data to be written to the UART device
     */
    public void write(String data)
    {
        logs.push(data);
    }
    /**
     * Gets the name of this protocol.
     * 
     * @return The string "UART"
     */
    public String getProtocolName()
        {return "UART";}
}
