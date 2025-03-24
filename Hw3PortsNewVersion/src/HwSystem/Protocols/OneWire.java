package HwSystem.Protocols;
import java.io.File;
import java.util.Iterator;
import java.util.Stack;
/**
 * Implementation of the OneWire communication protocol.
 * <p>
 * This class provides a concrete implementation of the Protocol interface
 */
public class OneWire implements Protocol
{
    private int portID;
    private String logPath;
    private Stack<String> logs = new Stack<>();

    public OneWire()
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
        File oneWireLog = new File("OneWire_" + portID + ".log");
        Iterator<String> iterator = logs.iterator();
        while(iterator.hasNext())
        {
            
        }
    }
    /**
     * Simulates reading data from a OneWire device.
     * 
     * @return A string indicating that data is being read using the OneWire protocol
     */
    public String read()
    {
        logs.push("Readining");
        return "";
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
    }

    /**
     * Gets the name of this protocol.
     * 
     * @return The string "OneWire"
     */
    public String getProtocolName()
        {return "OneWire";}
}
