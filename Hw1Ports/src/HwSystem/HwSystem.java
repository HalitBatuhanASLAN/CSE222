package HwSystem;

import java.lang.System;
import java.util.ArrayList;
import HwSystem.Devices.*;
import HwSystem.Devices.Device.DeviceState;
import HwSystem.Devices.Displays.Display;
import HwSystem.Devices.MotorDrivers.*;
import HwSystem.Devices.Sensors.Sensor;
import HwSystem.Devices.WirelessIOs.WirelessIO;
import HwSystem.Protocols.*;

public class HwSystem
{
    private ArrayList<Protocol> ports = new ArrayList<>();
    private ArrayList<Boolean> portsState = new ArrayList<>();
    private ArrayList<Device> devices = new ArrayList<>();
    private ArrayList<MotorDriver> motorDrivers = new ArrayList<>();
    private ArrayList<WirelessIO> wirelessIOs = new ArrayList<>();
    private ArrayList<Display> displays = new ArrayList<>();
    private ArrayList<Sensor> sensors = new ArrayList<>();

    public void setPorts(ArrayList<Protocol> configurationFile)
    {
        ports = configurationFile;
    }
    public Boolean setMotorSpeed(int devId, int speed)
    {
        if(motorDrivers.get(devId) == null) return false;
        motorDrivers.get(devId).setMotorSpeed(speed);
        return true;
    }

    public void turnOn(int portId)
    {
        if(portsState.size() <= portId || portId<0)
            System.out.printf("Error: Port id is out of range!!!");
        else if(portsState.get(portId))
            System.out.println("Error: Port is already opened!!!");
        else
        {
            portsState.set(portId,true);
            System.out.printf("%d Port is opened now",portId);
        }
    }
    public void turnOff(int portId)
    {
        if(portsState.size() <= portId || portId<0)
            System.out.printf("Error: Port id is out of range!!!");
        else if(portsState.get(portId))
            System.out.println("Error: Port is already closed!!!");
        else
        {
            portsState.set(portId,false);
            System.out.printf("%d Port is closed now",portId);
        }
    }

    public void addDev(String devType,int portNumber,int devNumber)
    {
        if(portNumber <0 || portNumber >= portsState.size())
            System.out.println("Error: Port number is out of range!!!");
        else if(devType.equals("MotorDriver"))
        {
            if(motorDrivers.size() <= devNumber || devNumber <0)
                System.out.println("Error: Device number is out of range!!!");
            else if(motorDrivers.get(devNumber).getState() == DeviceState.On)
                System.out.println("Error: Device is conected to another port!!!");
            else
            {
                if(ports.get(portNumber).equals(motorDrivers.get(devNumber).getProtocol()))
                {                
                    motorDrivers.get(devNumber).setState(DeviceState.On);
                    portsState.set(portNumber,true);
                }
                else
                    System.out.println("Error: Device is not configured with ports protocol!!!");
            }
        }
        else if(devType.equals("Display"))
        {
            if(displays.size() <= devNumber || devNumber <0)
                System.out.println("Error: Device number is out of range!!!");
            else if(displays.get(devNumber).getState() == DeviceState.On)
                System.out.println("Error: Device is conected to another port!!!");
            else
            {
                if(ports.get(portNumber).equals(displays.get(devNumber).getProtocol()))
                {                
                    displays.get(devNumber).setState(DeviceState.On);
                    portsState.set(portNumber,true);
                }
                else
                    System.out.println("Error: Device is not configured with ports protocol!!!");
            }
        }
        else if(devType.equals("Sensor"))
        {
            if(sensors.size() <= devNumber || devNumber <0)
                System.out.println("Error: Device number is out of range!!!");
            else if(displays.get(devNumber).getState() == DeviceState.On)
                System.out.println("Error: Device is conected to another port!!!");
            else
            {
                if(ports.get(portNumber).equals(sensors.get(devNumber).getProtocol()))
                {                
                    sensors.get(devNumber).setState(DeviceState.On);
                    portsState.set(portNumber,true);
                }
                else
                    System.out.println("Error: Device is not configured with ports protocol!!!");
            }
        }
        else if(devType.equals("wirelessIOs"))
        {
            if(wirelessIOs.size() <= devNumber || devNumber <0)
                System.out.println("Error: Device number is out of range!!!");
            else if(wirelessIOs.get(devNumber).getState() == DeviceState.On)
                System.out.println("Error: Device is conected to another port!!!");
            else
            {
                if(ports.get(portNumber).equals(wirelessIOs.get(devNumber).getProtocol()))
                {                
                    wirelessIOs.get(devNumber).setState(DeviceState.On);
                    portsState.set(portNumber,true);
                }
                else
                    System.out.println("Error: Device is not configured with ports protocol!!!");
            }
        }
    }

    public void rmDev(int portNumber)
    {
        if(portNumber<0 || portNumber >= portsState.size())
            System.out.println("Error: Port number is out of range!!!");
        else if(portsState.get(portNumber).equals(false))
            System.out.println("Error: Port is closed(No devices are on port)!!!");
        else
        {
            
        }    
    }

    public void listPorts()
    {
        for(int i = 0;i<ports.size();i++)
        {
            System.out.print(i + " ");
            System.out.print(ports.get(i) + " ");
            if(portsState.get(i))
                System.out.print("Occupied ");
            else
                System.out.print("Empty ");
            //System.out.print();
        }
    }

    public Boolean writeWireless(int devId,String data)
    {
        if(wirelessIOs.get(devId) == null) return false;
        wirelessIOs.get(devId).sendData(data);
        return true;
    }

    public Boolean readWireless(int devId)
    {
        if(wirelessIOs.get(devId) == null) return false;
        System.out.println(wirelessIOs.get(devId).recvData());
        return true;
    }

    public Boolean printDisplay(int devId,String data)
    {
        if(displays.get(devId) == null) return false;
        displays.get(devId).printData(data);
        return true;
    }

    public void readSensor(int devId)
    {
        System.out.print(sensors.get(devId).getName() + " " + sensors.get(devId).getSensType() + " " + sensors.get(devId).data2String());
    }

    public void listDeviceInfo(String devType)
    {
        
    }
}