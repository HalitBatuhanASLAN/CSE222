package Main;
import HwSystem.*;
import HwSystem.Protocols.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        HwSystem system = new HwSystem();
    }
    public Boolean readiningConfigurationFile(final String fileName,HwSystem system)
    {
        ArrayList<Protocol> proList;
        
        try
        {
            File configurationFile = new File(fileName);
            Scanner lineScanner = new Scanner(configurationFile);
            String firstLine = lineScanner.nextLine();
            if(firstLine.contains(":"))
            {
                String protocols = firstLine.substring(firstLine.indexOf(":") + 1).trim();
                String[] protocolArray = protocols.split(",");
                String tmp;
                for(int i = 0;i<protocolArray.length;i++)
                {
                    tmp = protocolArray[i];
                    tmp.trim();
                    protocolArray[i] = tmp;
                    if(protocolArray.equals("UART"))
                    {
                        UART u = new UART();
                        proList.add(u);
                    }
                    else if(protocolArray.equals("I2C"))
                    {
                        I2C i2c = new I2C();
                        proList.add(i2c);
                    }
                    else if(protocolArray.equals("OneWire"))
                    {
                        OneWire oWire = new OneWire();
                        proList.add(oWire);
                    }
                    else if(protocolArray.equals("SPI"))
                    {
                        SPI spi = new SPI();
                        proList.add(spi);
                    }
                }
                system.setPorts(proList);
            }
            int lineNumber = 0;
            while(lineScanner.hasNextLine())
            {
                String lines = lineScanner.nextLine();
                if(lines.contains(":"))
                {
                    String num = lines.substring(lines.indexOf(":") + 1).trim();
                    int numberOfDevice = Integer.parseInt(num);
                    if(lineNumber == 0)
                    {
                        
                    }
                }
            }

            return true;
        }
        catch(File e)
        {
            System.out.println("File could not opened");
            return false;/* */
        }
    }
    public String Menu()
    {
        System.out.println("Do you want to write file name or continue with default one(Y for continue/n):");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        String fileName = "configuration.txt";
        switch(answer){
            case "Y":
            case "y":
                System.out.println("Enter file name:");
                Scanner fName = new Scanner(System.in);
                fileName = fName.nextLine();
                break;
            case "N":
            case "n":
            default:
                break;
        }
        return fileName;
    }
}