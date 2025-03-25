package HwSystem.Protocols;

import java.io.File;
import java.io.FileWriter;
import java.util.Stack;

/**
 * Implementation of the UART (Universal Asynchronous Receiver-Transmitter) communication protocol.
 * <p>
 * This class provides a concrete implementation of the Protocol interface
 */
public class UART implements Protocol
{
    private int portID;
    private File logPath;
    private int logCount;
    private Stack<String> logs = new Stack<>();

    public UART(){}
    public UART(int portID,File logPath)
    {
        this.portID = portID;
        this.logPath = logPath;
        logCount = 0;
        logs.push("Port Opened");
        logCount++;
    }
    /*public void setPortID(int id)
        {portID = id;}
    public int getPortID()
        {return portID;}
    public void setLogPath(String path)
        {logPath = path;}*/
    private void writeLogFile()
    {
        String fileName = "UART_" + portID + ".log";
        String filePath = logPath + File.separator + fileName;

        //File file = new File(filePath);
        try
        {
            FileWriter writer = new FileWriter(filePath,true);
            String log;
            while(logCount > 0)
            {
                log = logs.pop();
                writer.write(log);
                writer.write(System.lineSeparator());
                logCount--;
            }
            writer.close();
        }
        catch(Exception e)
            {System.out.println(e.getMessage());}
    }

    public void close()
    {
        writeLogFile();
    }
    /**
     * Simulates reading data from a UART device.
     * 
     * @return A string indicating that data is being read using the UART protocol
     */
    public String read()
    {
        logs.push("Readining");
        logCount++;
        return getProtocolName() + ": Readining.";
    }
    /**
     * Simulates writing data to a UART device.
     * 
     * @param data The string data to be written to the UART device
     */
    public void write(String data)
    {
        logs.push("Writing:\"" + data + "\"");
        logCount++;
    }
    /**
     * Gets the name of this protocol.
     * 
     * @return The string "UART"
     */
    public String getProtocolName()
        {return "UART";}
}
