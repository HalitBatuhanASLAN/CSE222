package HwSystem;

import java.lang.System;
import java.util.ArrayList;
import HwSystem.Devices.*;
import HwSystem.Devices.Device.DeviceState;
import HwSystem.Devices.Displays.*;
import HwSystem.Devices.MotorDrivers.*;
import HwSystem.Devices.Sensors.*;
import HwSystem.Devices.WirelessIOs.*;
import HwSystem.Protocols.*;

public class HwSystem
{
    private ArrayList<Protocol> ports;
    private ArrayList<Boolean> onOffPortsState;/*true if on */
    private ArrayList<Boolean> emptyOccupiedPortsState;/*true if occupied */
    private ArrayList<Device> devices;
    private ArrayList<Integer> portIdOfDevices;
    private int displaysNumber;
    private int motorDriversNumber;
    private int sensorsNumber;
    private int WirelessIOsNumber;
    
    /*private ArrayList<MotorDriver> motorDrivers = new ArrayList<>(motorDriversNumber);
    private ArrayList<WirelessIO> wirelessIOs = new ArrayList<>(WirelessIOsNumber);
    private ArrayList<Display> displays = new ArrayList<>(displaysNumber);
    private ArrayList<Sensor> sensors = new ArrayList<>(sensorsNumber);*/

    private ArrayList<Integer> motorDrivers = new ArrayList<>(motorDriversNumber);
    private ArrayList<Integer> wirelessIOs = new ArrayList<>(WirelessIOsNumber);
    private ArrayList<Integer> displays = new ArrayList<>(displaysNumber);
    private ArrayList<Integer> sensors = new ArrayList<>(sensorsNumber);

    public HwSystem() {
        this.ports = new ArrayList<>();
        this.onOffPortsState = new ArrayList<>();
        this.emptyOccupiedPortsState = new ArrayList<>();
        this.devices = new ArrayList<>();
        this.portIdOfDevices = new ArrayList<>();

        this.sensorsNumber = 0;
        this.displaysNumber = 0;
        this.WirelessIOsNumber = 0;
        this.motorDriversNumber = 0;
        
        this.sensors = new ArrayList<>(sensorsNumber);
        this.displays = new ArrayList<>(displaysNumber);
        this.wirelessIOs = new ArrayList<>(WirelessIOsNumber);
        this.motorDrivers = new ArrayList<>(motorDriversNumber);
    }
    public void setPortIdOfDevices()
    {
        int totalDevicesCount = displaysNumber + sensorsNumber + motorDriversNumber + WirelessIOsNumber;
        for (int i = 0; i < totalDevicesCount; i++)
            this.portIdOfDevices.add(-1);
    }
    public void setDevices()
    {
        int totalDevicesCount = displaysNumber + sensorsNumber + motorDriversNumber + WirelessIOsNumber;
        for (int i = 0; i < totalDevicesCount; i++)
            this.devices.add(null);
    }
    public void setProtocol(Protocol pro)
    {
        this.ports.add(pro);
        this.onOffPortsState.add(false);
        this.emptyOccupiedPortsState.add(false);
    }
    public void setSensorsNumber(int sensorsNumber)
    {
        this.sensorsNumber = sensorsNumber;
        this.sensors.clear();
        for (int i = 0; i < sensorsNumber; i++)
            this.sensors.add(-1);
    }
    
    public int getSensorsNumber()
        {return sensorsNumber;}
    
    public void setDisplaysNumber(int displaysNumber)
    {
        this.displaysNumber = displaysNumber;
        this.displays.clear();
        for (int i = 0; i < displaysNumber; i++)
            this.displays.add(-1);
    }
    
    public int getDisplaysNumber()
        {return displaysNumber;}
    
    public void setWirelessIOsNumber(int WirelessIOsNumber)
    {
        this.WirelessIOsNumber = WirelessIOsNumber;
        this.wirelessIOs.clear();
        for (int i = 0; i < WirelessIOsNumber; i++)
            this.wirelessIOs.add(-1);
    }
    
    public int getWirelessIOsNumber()
        {return WirelessIOsNumber;}
    
    public void setMotorDriversNumber(int motorDriversNumber)
    {
        this.motorDriversNumber = motorDriversNumber;
        this.motorDrivers.clear();
        for (int i = 0; i < motorDriversNumber; i++)
            this.motorDrivers.add(-1);
    }
    
    public int getMotorDriversNumber()
        {return motorDriversNumber;}

    public void turnOn(int portId)
    {
        if(portId < 0 || portId >= ports.size())
            System.out.println("Port number is out of range!!!");
        else if(!emptyOccupiedPortsState.get(portId))
            System.out.println("Port does not contain any devices!!!");
        else if(onOffPortsState.get(portId))
            System.out.println("Port has already opened!!!");
        else
        {
            onOffPortsState.set(portId,true);
            for(int i = 0;i<portIdOfDevices.size();i++)
            {
                if(portIdOfDevices.get(i) == portId)
                {
                    devices.get(i).turnOn();
                    break;
                }
            }
            //System.out.printf("%s:Writing Turn ON",ports.get(portId).getProtocolName());
        }
    }

    public void turnOff(int portId)
    {
        if(portId < 0 || portId >= ports.size())
            System.out.println("Port number is out of range!!!");
        else if(!emptyOccupiedPortsState.get(portId))
            System.out.println("Port does not contain any devices!!!");
        else if(!onOffPortsState.get(portId))
            System.out.println("Port has already closed!!!");
        else
        {
            onOffPortsState.set(portId,false);
            for(int i = 0;i<portIdOfDevices.size();i++)
            {
                if(portIdOfDevices.get(i) == portId)
                {
                    devices.get(i).turnOff();
                    break;
                }
            }
            /*turnOff device issue look at later */
        }
    }

    public void listPorts()
    {
        System.out.println("List of ports");
        for(int i = 0;i<ports.size();i++)
        {
            if(!emptyOccupiedPortsState.get(i))
                System.out.printf("%d %s empty\n",i,ports.get(i).getProtocolName());
            else
            {
                System.out.printf("%d %s occupied ",i,ports.get(i).getProtocolName());
                for(int j = 0;j<devices.size();j++)
                {
                    /*if(devices.get(j) != null)
                        System.out.printf("%s %s %d %s",devices.get(j).getName(),devices.get(j).getDevType(),0,devices.get(j).getState());*/
                    if(portIdOfDevices.get(j) == i && devices.get(j) != null)
                    {
                        Device device = devices.get(j);
                        int displayedDevId = 0;
                        String devType = device.getDevType();
                            
                        if(devType.equals("Display"))
                            displayedDevId = j;
                        else if(devType.equals("MotorDriver"))
                            displayedDevId = j - displaysNumber;
                        else if(devType.equals("Sensor"))
                            displayedDevId = j - (displaysNumber + motorDriversNumber);
                        else if(devType.equals("WirelessIO"))
                            displayedDevId = j - (displaysNumber + motorDriversNumber + sensorsNumber);
                            
                        System.out.printf("%s %s %d %s", device.getName(), device.getDevType(), 
                            displayedDevId, device.getState());
                        break; // Bir port sadece bir cihaza bağlı olabilir
                    }
                }
                /*dev name devType devId state look later*/
                System.out.printf("\n");
            }
        }
    }

    public void listDevType(String devType)
    {
        if(!devType.equals("Display") && devType.equals("MotorDriver") 
                && devType.equals("Sensor") && devType.equals("WirelessIO"))
                System.out.println("No matching with any device types");
        else
        {
            Boolean flagEmpty = false;
            System.out.printf("List of %s\n",devType);
            int deviceId = 0;
            for(int i = 0;i<devices.size();i++)
            {
                if(devType.equals("Display") && devices.get(i) instanceof Display)
                {
                    flagEmpty = true;
                    System.out.println(devices.get(i).getName() + " " + deviceId + " " +
                        portIdOfDevices.get(i) + " " + devices.get(i).getProtocol());
                    deviceId++;
                }
                else if(devType.equals("MotorDriver") && devices.get(i) instanceof MotorDriver)
                {
                    flagEmpty = true;
                    System.out.println(devices.get(i).getName() + " " + deviceId + " " +
                        portIdOfDevices.get(i) + " " + devices.get(i).getProtocol());
                    deviceId++;
                }
                else if(devType.equals("Sensor") && devices.get(i) instanceof Sensor)
                {
                    flagEmpty = true;
                    System.out.println(devices.get(i).getName() + " " + deviceId + " " +
                        portIdOfDevices.get(i) + " " + devices.get(i).getProtocol());
                    deviceId++;
                }
                else if(devType.equals("WirelessIO") && devices.get(i) instanceof WirelessIO)
                {
                    flagEmpty = true;
                    System.out.println(devices.get(i).getName() + " " + deviceId + " " +
                        portIdOfDevices.get(i) + " " + devices.get(i).getProtocol());
                    deviceId++;
                }
            }
            if(!flagEmpty)
                System.out.println("There is no any device on that type!!!");
        }
    }

    public void readSensor(int devId)
    {
        if(devId < 0 || devId >= sensorsNumber)
        {
            System.out.println("Device id is out of range");
        }
        else
        {
            int correctDevice = 0;
            for(int i = 0;i<devices.size() && correctDevice <= devId;i++)
            {
                if(devices.get(i) instanceof Sensor objectSensor && correctDevice == devId)
                {
                    correctDevice++;
                    if(devices.get(i).getState() == DeviceState.Off)
                        System.out.println("Device is not active\nCommand is failed");
                    else
                    {
                        System.out.println(objectSensor.getName() + " " + objectSensor.getSensType()
                            + " " + objectSensor.data2String());
                    }
                }
                else if(devices.get(i) instanceof Sensor tmp && correctDevice < devId)
                    correctDevice++;
            }
        }
    }

    public void printDisplay(int devId,String data)
    {
        if(devId < 0 || devId >= displaysNumber)
        {
            System.out.println("Device id is out of range");
        }
        else
        {
            int correctDevice = 0;
            for(int i = 0;i<devices.size() && correctDevice <= devId;i++)
            {
                if(devices.get(i) instanceof Display objectDisplay && correctDevice == devId)
                {
                    correctDevice++;
                    if(devices.get(i).getState() == DeviceState.Off)
                        System.out.println("Device is not active\nCommand is failed");
                    else
                        objectDisplay.printData(data);
                }
                else if(devices.get(i) instanceof Display tmp && correctDevice < devId)
                    correctDevice++;
            }
        }
    }

    public void readWireless(int devId)
    {
        if(devId < 0 || devId >= WirelessIOsNumber)
        {
            System.out.println("Device id is out of range");
        }
        else
        {
            int correctDevice = 0;
            for(int i = 0;i<devices.size() && correctDevice <= devId;i++)
            {
                if(devices.get(i) instanceof WirelessIO objectWirelessIO && correctDevice == devId)
                {
                    correctDevice++;
                    if(devices.get(i).getState() == DeviceState.Off)
                        System.out.println("Device is not active\nCommand is failed");
                    else
                        objectWirelessIO.recvData();
                }
                else if(devices.get(i) instanceof WirelessIO tmp && correctDevice < devId)
                    correctDevice++;
            }
        }
    }

    public void writeWireless(int devId,String data)
    {
        if(devId < 0 || devId >= WirelessIOsNumber)
        {
            System.out.println("Device id is out of range");
        }
        else
        {
            int correctDevice = 0;
            for(int i = 0;i<devices.size() && correctDevice <= devId;i++)
            {
                if(devices.get(i) instanceof WirelessIO objectWirelessIO && correctDevice == devId)
                {
                    correctDevice++;
                    if(devices.get(i).getState() == DeviceState.Off)
                        System.out.println("Device is not active\nCommand is failed");
                    else
                        objectWirelessIO.sendData(data);
                }
                else if(devices.get(i) instanceof WirelessIO tmp && correctDevice < devId)
                    correctDevice++;
            }
        }
    }

    public void setMotorSpeed(int devId,int speed)
    {
        if(devId < 0 || devId >= motorDriversNumber)
        {
            System.out.println("Device id is out of range");
        }
        else
        {
            int correctDevice = 0;
            for(int i = 0;i<devices.size() && correctDevice <= devId;i++)
            {
                if(devices.get(i) instanceof MotorDriver objectMotorDriver && correctDevice == devId)
                {
                    correctDevice++;
                    if(devices.get(i).getState() == DeviceState.Off)
                        System.out.println("Device is not active\nCommand is failed");
                    else
                        objectMotorDriver.setMotorSpeed(speed);
                }
                else if(devices.get(i) instanceof MotorDriver tmp && correctDevice < devId)
                    correctDevice++;
            }
        }
    }

    public void addDev(String devName,int portId,int devId)
    {
        Device newDevice = null;
        int deviceIndex = -1;
        if(portId < 0 || portId >= ports.size())
            System.out.println("Port number is out of range!!!");
        else if(emptyOccupiedPortsState.get(portId))
            System.out.println("Port is occupied by another device!!!");
        else if(devName.equals("LCD") || devName.equals("OLED"))/*displays */
        {
            Protocol protocolOfPort = ports.get(portId);
            if(devId >= displaysNumber || devId < 0)
                System.out.println("Device id is out of range!!!");
            else if(displays.get(devId) != -1)
                System.out.println("Device is already connected to another port");
            else
            {   
                if(devName.equals("LCD"))
                    newDevice = new LCD(protocolOfPort.getProtocolName());
                else
                    newDevice = new OLED(protocolOfPort.getProtocolName());
                if(newDevice.getProtocol() != null)
                {
                    deviceIndex = devId;
                    displays.set(devId,portId);
                    emptyOccupiedPortsState.set(portId,true);
                    devices.set(deviceIndex, newDevice);
                    portIdOfDevices.set(deviceIndex,portId);
                }
            }
        }
        else if(devName.equals("PCA9685") || devName.equals("SparkFunMD"))/*MotorDrivers */
        {
            Protocol protocolOfPort = ports.get(portId);
        
            if(devId >= motorDriversNumber || devId < 0)
                System.out.println("Device id is out of range");
            else if(motorDrivers.get(devId) != -1)
                System.out.println("Device is already connected to another port");
            else
            {   
                if(devName.equals("PCA9685"))
                    newDevice = new PCA9685(protocolOfPort.getProtocolName());
                else
                    newDevice = new SparkFunMD(protocolOfPort.getProtocolName());
                if(newDevice.getProtocol() != null)
                {
                    deviceIndex = devId + displaysNumber;
                    motorDrivers.set(devId,portId);
                    emptyOccupiedPortsState.set(portId,true);
                    devices.set(deviceIndex, newDevice);
                    portIdOfDevices.set(deviceIndex,portId);
                }
            }
        }
        else if(devName.equals("BME280") || devName.equals("DHT11") || devName.equals("GY951") || devName.equals("MPU6050"))/*Sensor*/
        {
            Protocol protocolOfPort = ports.get(portId);
        
            if(devId >= sensorsNumber || devId < 0)
                System.out.println("Device id is out of range");
            else if(sensors.get(devId) != -1)
                System.out.println("Device is already connected to another port");
            else
            {   
                if(devName.equals("BME280"))
                    newDevice = new BME280(protocolOfPort.getProtocolName());
                else if(devName.equals("DHT11"))
                    newDevice = new DHT11(protocolOfPort.getProtocolName());
                else if(devName.equals("GY951"))
                    newDevice = new GY951(protocolOfPort.getProtocolName());
                else
                    newDevice = new MPU6050(protocolOfPort.getProtocolName());
                if(newDevice.getProtocol() != null)
                {
                    deviceIndex = devId + displaysNumber + motorDriversNumber;
                    sensors.set(devId,portId);
                    emptyOccupiedPortsState.set(portId,true);
                    devices.set(deviceIndex, newDevice);
                    portIdOfDevices.set(deviceIndex,portId);
                }
            }
        }
        else if(devName.equals("Bluetooth") || devName.equals("Wifi"))/*Wirelessio*/
        {
            Protocol protocolOfPort = ports.get(portId);
        
            if(devId >= WirelessIOsNumber || devId < 0)
                System.out.println("Device id is out of range");
            else if(wirelessIOs.get(devId) != -1)
                System.out.println("Device is already connected to another port");
            else
            {   
                if(devName.equals("Bluetooth"))
                    newDevice = new Bluetooth(protocolOfPort.getProtocolName());
                else
                    newDevice = new Wifi(protocolOfPort.getProtocolName());
                if(newDevice.getProtocol() != null)
                {
                    deviceIndex = displaysNumber + motorDriversNumber + sensorsNumber + devId;
                    wirelessIOs.set(devId,portId);
                    emptyOccupiedPortsState.set(portId,true);
                    devices.set(deviceIndex, newDevice);
                    portIdOfDevices.set(deviceIndex,portId);
                }
            }
        }
        else
            System.out.println("Device can not be found!!!");
    }

    public void rmDev(int portId)
    {
        if(portId < 0 || portId >= ports.size())
            System.out.println("Port id is out of range!!!");
        else if(onOffPortsState.get(portId))
            System.out.println("Device is active\nCommand is failed");
        else if(!emptyOccupiedPortsState.get(portId))
            System.out.println("No any devices on that port\nCommand is failed");
        else
        {
            emptyOccupiedPortsState.set(portId,false);
            for(int j = 0;j<devices.size();j++)
            {
                if(portIdOfDevices.get(j) == portId)
                {
                    /*portIdOfDevices.set(j,-1);
                    //sensors.set(0,false);
                    if(devices.get(0).getDevType().equals("Sensor"))
                        sensors.set(0,-1);
                    else if(devices.get(j).getDevType().equals("Display"))
                        displays.set(j,-1);
                    else if(devices.get(j).getDevType().equals("MotorDriver"))
                        motorDrivers.set(j,-1);
                    else if(devices.get(j).getDevType().equals("WirelessIO"))
                        wirelessIOs.set(j,-1);*/
                    String devType = devices.get(j).getDevType();
                    portIdOfDevices.set(j, -1);
                        
                    if(devType.equals("Sensor"))
                    {
                        int sensorIndex = j - (displaysNumber + motorDriversNumber);
                        if(sensorIndex >= 0 && sensorIndex < sensorsNumber)
                            sensors.set(sensorIndex, -1);
                    }
                    else if(devType.equals("Display"))
                    {
                        if(j < displaysNumber)
                            displays.set(j, -1);
                    }
                    else if(devType.equals("MotorDriver"))
                    {
                        int motorIndex = j - displaysNumber;
                        if(motorIndex >= 0 && motorIndex < motorDriversNumber)
                            motorDrivers.set(motorIndex, -1);
                    }
                    else if(devType.equals("WirelessIO"))
                    {
                        int wirelessIndex = j - (displaysNumber + motorDriversNumber + sensorsNumber);
                        if(wirelessIndex >= 0 && wirelessIndex < WirelessIOsNumber)
                            wirelessIOs.set(wirelessIndex, -1);
                    }                
                    devices.set(j, null);
                }
            }
        }
    }

}
