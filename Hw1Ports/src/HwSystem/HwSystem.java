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
    private int displaysNumber;
    private int motorDriversNumber;
    private int sensorsNumber;
    private int WirelessIOsNumber;
    /*private ArrayList<MotorDriver> motorDrivers = new ArrayList<>(motorDriversNumber);
    private ArrayList<WirelessIO> wirelessIOs = new ArrayList<>(WirelessIOsNumber);
    private ArrayList<Display> displays = new ArrayList<>(displaysNumber);
    private ArrayList<Sensor> sensors = new ArrayList<>(sensorsNumber);*/

    private ArrayList<Boolean> motorDrivers = new ArrayList<>(motorDriversNumber);
    private ArrayList<Boolean> wirelessIOs = new ArrayList<>(WirelessIOsNumber);
    private ArrayList<Boolean> displays = new ArrayList<>(displaysNumber);
    private ArrayList<Boolean> sensors = new ArrayList<>(sensorsNumber);

    public HwSystem() {
        this.ports = new ArrayList<>();
        this.sensorsNumber = 0;
        this.displaysNumber = 0;
        this.WirelessIOsNumber = 0;
        this.motorDriversNumber = 0;
    }
    public void setProtocol(Protocol pro)
    {
        this.ports.add(pro);
    }
    public void setSensorsNumber(int sensorsNumber)
        {this.sensorsNumber = sensorsNumber;}
    
    public int getSensorsNumber()
        {return sensorsNumber;}
    
    public void setDisplaysNumber(int displaysNumber)
        {this.displaysNumber = displaysNumber;}
    
    public int getDisplaysNumber()
        {return displaysNumber;}
    
    public void setWirelessIOsNumber(int WirelessIOsNumber)
        {this.WirelessIOsNumber = WirelessIOsNumber;}
    
    public int getWirelessIOsNumber()
        {return WirelessIOsNumber;}
    
    public void setMotorDriversNumber(int motorDriversNumber)
        {this.motorDriversNumber = motorDriversNumber;}
    
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
            /*turnOn device issue look at later */
        }
    }

    public void turnOff(int portId)
    {
        if(portId < 0 || portId >= ports.size())
            System.out.println("Port number is out of range!!!");
        else if(emptyOccupiedPortsState.get(portId))
            System.out.println("Port does not contain any devices!!!");
        else if(!onOffPortsState.get(portId))
            System.out.println("Port has already closed!!!");
        else
        {
            onOffPortsState.set(portId,false);
            /*turnOff device issue look at later */
        }
    }

    public void listPorts()
    {
        for(int i = 0;i<ports.size();i++)
        {
            if(!emptyOccupiedPortsState.get(i))
                System.out.printf("%d %s empty\n",i,ports.get(i).getProtocolName());
            else
            {
                System.out.printf("%d %s occupied ",i,ports.get(i).getProtocolName());
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
            System.out.printf("List of %s\n",devType);
            int deviceId = 0;
            for(int i = 0;i<devices.size();i++)
            {
                if(devType.equals("Display") && devices.get(i) instanceof Display)
                {
                    System.out.println(devices.get(i).getName() + " " + deviceId + " " +
                            "portId" + " " + devices.get(i).getProtocol());
                    deviceId++;
                }
                else if(devType.equals("MotorDriver") && devices.get(i) instanceof MotorDriver)
                {
                    System.out.println(devices.get(i).getName() + " " + deviceId + " " +
                            "portId" + " " + devices.get(i).getProtocol());
                    deviceId++;
                }
                else if(devType.equals("Sensor") && devices.get(i) instanceof Sensor)
                {
                    System.out.println(devices.get(i).getName() + " " + deviceId + " " +
                            "portId" + " " + devices.get(i).getProtocol());
                    deviceId++;
                }
                else if(devType.equals("WirelessIO") && devices.get(i) instanceof WirelessIO)
                {
                    System.out.println(devices.get(i).getName() + " " + deviceId + " " +
                            "portId" + " " + devices.get(i).getProtocol());
                    deviceId++;
                }
            }
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
                        System.out.println("Device is off");
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

    public void printDisplay(int devId)
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
                        System.out.println("Device is off");
                    else
                        objectDisplay.printData("Printing data");
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
                        System.out.println("Device is off");
                    else
                        objectWirelessIO.recvData();
                }
                else if(devices.get(i) instanceof WirelessIO tmp && correctDevice < devId)
                    correctDevice++;
            }
        }
    }

    public void writeWireless(int devId)
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
                        System.out.println("Device is off");
                    else
                        objectWirelessIO.sendData("Data is sending");
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
                        System.out.println("Device is off");
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
        if(emptyOccupiedPortsState.get(portId))
            System.out.println("Port is occupied by another device!!!");
        else if(portId < 0 || portId >= ports.size())
            System.out.println("Port number is out of range!!!");
        else if(devName.equals("LED") || devName.equals("OLED"))/*displays */
        {
            if(devId >= displaysNumber || devId < 0)
                System.out.println("Device id is out of range!!!");
            else if(displays.get(devId))
                System.out.println("Device is already connected to another port");
            else
                displays.set(devId,true);
        }
        else if(devName.equals("PCA9685") || devName.equals("SparkFunMD"))/*MotorDrivers */
        {
            if(devId >= motorDriversNumber || devId < 0)
                System.out.println("Device id is out of range");
            else if(motorDrivers.get(devId))
                System.out.println("Device is already connected to another port");
            else
                motorDrivers.set(devId,true);
        }
        else if(devName.equals("BME280") || devName.equals("DHT11") || devName.equals("GY951") || devName.equals("MPU6050"))/*Sensor*/
        {
            if(devId >= sensorsNumber || devId < 0)
                System.out.println("Device id is out of range");
            else if(sensors.get(devId))
                System.out.println("Device is already connected to another port");
            else
                sensors.set(devId,true);
        }
        else if(devName.equals("Blutooth") || devName.equals("Wifi"))/*Wirelessio*/
        {
            if(devId >= WirelessIOsNumber || devId < 0)
                System.out.println("Device id is out of range");
            else if(wirelessIOs.get(devId))
                System.out.println("Device is already connected to another port");
            else
                wirelessIOs.set(devId,true);
        }
    }

    public void rmDev(int portId)
    {
        if(portId < 0 || portId >= ports.size())
            System.out.println("Port id is out of range!!!");
        else if(onOffPortsState.get(portId))
            System.out.println("Device is active!!!");
        else
        {
            emptyOccupiedPortsState.set(portId,false);
            
        }
    }

}
