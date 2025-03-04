package HwSystem.Protocols;

public class SPI implements Protocol
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
        return "SPI";
    }
}
