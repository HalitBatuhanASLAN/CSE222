package System.Protocols;

public interface Protocol
{
    String read();
    void write(String data);
    String getProtocolName();
}