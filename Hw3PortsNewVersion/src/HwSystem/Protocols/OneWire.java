package HwSystem.Protocols;
import java.io.File;
import java.io.FileWriter;
import java.util.Stack;
/**
 * Implementation of the OneWire communication protocol.
 * <p>
 * This class provides a concrete implementation of the Protocol interface
 */
public class OneWire implements Protocol
{
    private int portID;
    private File logPath;
    private int logCount;
    private Stack<String> logs = new Stack<>();

    public OneWire(){}
    public OneWire(int portID,File logPath)
    {
        this.portID = portID;
        this.logPath = logPath;
        logCount = 0;
        logs.push("Port Opened");
        logs.push("abc");
        logCount++;
        logCount++;
    }
    /*public void setPortID(int id)
        {portID = id;}
    public int getPortID()
        {return portID;}
    public void setLogPath(File path)
        {logPath = path;}*/
    private void writeLogFile()
    {
        String fileName = "OneWire_" + portID + ".log";
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
                System.out.println(log);
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
     * Simulates reading data from a OneWire device.
     * 
     * @return A string indicating that data is being read using the OneWire protocol
     */
    public String read()
    {
        logs.push("Readining");
        logCount++;
        return "abc";
        //return getProtocolName() + ": Readining.";
    }

    /**
     * Simulates writing data to a OneWire device.
     * 
     * @param data The string data to be written to the OneWire device
     */
    public void write(String data)
    {
        logs.push(data);
        logCount++;
    }

    /**
     * Gets the name of this protocol.
     * 
     * @return The string "OneWire"
     */
    public String getProtocolName()
        {return "OneWire";}
}
