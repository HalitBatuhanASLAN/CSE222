package System.Protocols;

public class I2C implements Protocol
{
    public String read()
    {
        return getProtocolName() + ": Readining.";
    }

    public void write(String data)
    {
        System.out.printf("%s: writing %s\n",getProtocolName(),data);
    }

    public String getProtocolName()
    {
        return "I2C";
    }
}
