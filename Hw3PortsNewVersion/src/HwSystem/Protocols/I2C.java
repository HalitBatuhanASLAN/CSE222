package HwSystem.Protocols;

import java.io.File;
import java.io.FileWriter;
import java.util.Stack;

/**
 * Implementation of the I2C (Inter-Integrated Circuit) communication protocol.
 * <p>
 * This class provides a concrete implementation of the Protocol interface
 */
public class I2C implements Protocol
{
    private int portID;
    private File logPath;
    private int logCount;
    private Stack<String> logs = new Stack<>();

    public I2C(){}
    public I2C(int portID,File logPath)
    {
        this.portID = portID;
        this.logPath = logPath;
        logCount = 0;
        logs.push("Port Opened");
        logCount++;
    }
    private void writeLogFile()
    {
        String fileName = "I2C_" + portID + ".log";
        String filePath = logPath + File.separator + fileName;
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
     * Simulates reading data from an I2C device.
     * 
     * @return A string indicating that data is being read using the I2C protocol
     */
    public String read()
    {
        logs.push("Readining");
        logCount++;
        return "";
    }
     /**
     * Simulates writing data to an I2C device.
     * 
     * @param data The string data to be written to the I2C device
     */
    public void write(String data)
    {
        logs.push("Writing:\"" + data + "\"");
        logCount++;
        
    }
    /**
     * Gets the name of this protocol.
     * 
     * @return The string "I2C"
     */
    public String getProtocolName()
        {return "I2C";}
}
