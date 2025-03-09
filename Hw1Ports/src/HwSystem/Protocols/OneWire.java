package HwSystem.Protocols;

public class OneWire implements Protocol
{
    public String read()
    {
        return getProtocolName() + ": Readining.";
    }

    public void write(String data)
    {
        System.out.printf("%s:%s\n",getProtocolName(),data);
    }

    public String getProtocolName()
    {
        return "OneWire";
    }    
}
