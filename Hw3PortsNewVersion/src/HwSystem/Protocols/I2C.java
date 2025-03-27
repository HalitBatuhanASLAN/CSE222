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
    /** The port identifier for this I2C connection. */
    private int portID;

    /** The directory where log files will be stored. */
    private File logPath;

    /** Counter for the number of log entries waiting to be written. */
    private int logCount;

    /** Stack to store log messages before they are written to file. */
    private Stack<String> logs = new Stack<>();

    /**
     * Default constructor for creating an I2C protocol instance without logging.
     */
    public I2C(){}

    /**
     * Constructor for creating an I2C protocol instance with logging capabilities.
     * 
     * @param portID The port identifier for this I2C connection
     * @param logPath The directory where log files will be stored
     */
    public I2C(int portID,File logPath)
    {
        this.portID = portID;
        this.logPath = logPath;
        logCount = 0;
        logs.push("Port Opened");
        logCount++;
    }

    /**
     * Writes all pending log messages to the log file.
     * The log file is named based on the port ID and stored in the specified log path.
     */
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

    /**
     * Closes the I2C connection and writes any pending logs to file.
     */
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
