package System.Protocols;

public class UART implements Protocol
{
    public String read()
    {
        return getProtocolName() + ": Readining.";
    }

    public void write(String data)
    {
        System.out.printf("%s: writing %s\n",getProtocolName(),data);
    }
}