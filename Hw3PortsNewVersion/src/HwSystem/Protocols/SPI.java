package HwSystem.Protocols;

import java.io.File;
import java.io.FileWriter;
import java.util.Stack;

/**
 * Implementation of the SPI (Serial Peripheral Interface) communication protocol.
 * <p>
 * This class provides a concrete implementation of the Protocol interface
 */
public class SPI implements Protocol
{
    /** The port identifier for this SPI connection. */
    private int portID;

    /** The directory where log files will be stored. */
    private File logPath;

    /** Counter for the number of log entries waiting to be written. */
    private int logCount;

    /** Stack to store log messages before they are written to file. */
    private Stack<String> logs = new Stack<>();

    /**
     * Default constructor for creating an SPI protocol instance without logging.
     */
    public SPI(){}

    /**
     * Constructor for creating an SPI protocol instance with logging capabilities.
     * 
     * @param portID The port identifier for this SPI connection
     * @param logPath The directory where log files will be stored
     */
    public SPI(int portID,File logPath)
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
        String fileName = "SPI_" + portID + ".log";
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
     * Closes the SPI connection and writes any pending logs to file.
     */
    public void close()
    {
        writeLogFile();
    }

    /**
     * Simulates reading data from an SPI device.
     * 
     * @return A string indicating that data is being read using the SPI protocol
     */
    public String read()
    {
        logs.push("Readining");
        logCount++;
        return getProtocolName() + ": Readining.";
    }

    /**
     * Simulates writing data to an SPI device.
     * 
     * @param data The string data to be written to the SPI device
     */
    public void write(String data)
    {
        logs.push("Writing:\"" + data + "\"");
        logCount++;
    }

    /**
     * Gets the name of this protocol.
     * 
     * @return The string "SPI"
     */
    public String getProtocolName()
        {return "SPI";}
}
