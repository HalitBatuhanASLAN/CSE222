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
    /*public void setPortID(int id)
        {portID = id;}
    public int getPortID()
        {return portID;}
    public void setLogPath(String path)
        {logPath = path;}*/
    private void writeLogFile()
    {
        String fileName = "I2C_" + portID + ".log";
        String filePath = logPath + File.separator + fileName;

        //File file = new File(filePath);
        try
        {
            FileWriter writer = new FileWriter(filePath,true);
            String log;
            while(logCount != 0)
            {
                log = logs.pop();
                writer.write(log);
                logCount--;
            }
            writer.close();
        }
        catch(Exception e)
            {}
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
