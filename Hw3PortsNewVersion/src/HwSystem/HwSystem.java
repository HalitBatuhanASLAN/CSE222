package HwSystem;

import java.lang.System;
import java.util.ArrayList;
import java.util.Iterator;

import HwSystem.Devices.*;
import HwSystem.Devices.Device.DeviceState;
import HwSystem.Devices.Displays.*;
import HwSystem.Devices.MotorDrivers.*;
import HwSystem.Devices.Sensors.*;
import HwSystem.Devices.WirelessIOs.*;
import HwSystem.Protocols.*;

/**
 * The main hardware system class that manages all devices, protocols, and ports.
 * <p>
 * This class provides functionality to add, remove, and control various hardware devices
 * connected to different communication protocol ports. It maintains the state of all ports
 * and devices in the system and provides methods to interact with them.
 */
public class HwSystem
{
    /** List of available communication protocol ports */
    private ArrayList<Protocol> ports;

    /** State of each port (true if on, false if off) */
    private ArrayList<Boolean> onOffPortsState;

    /** Occupancy state of each port (true if occupied by a device, false if empty) */
    private ArrayList<Boolean> emptyOccupiedPortsState;

    /** List of all devices connected to the system */
    private ArrayList<Device> devices;

    /** Mapping of which port each device is connected to */
    private ArrayList<Integer> portIdOfDevices;

    /** Number of each type of devices in the system */
    private int displaysNumber;
    private int motorDriversNumber;
    private int sensorsNumber;
    private int WirelessIOsNumber;

    /** List of port IDs for each type of devices */
    private ArrayList<Integer> motorDrivers = new ArrayList<>(motorDriversNumber);
    private ArrayList<Integer> wirelessIOs = new ArrayList<>(WirelessIOsNumber);
    private ArrayList<Integer> displays = new ArrayList<>(displaysNumber);
    private ArrayList<Integer> sensors = new ArrayList<>(sensorsNumber);


    /*new addings */
    private ArrayList<Display> displayDevicesList;
    private ArrayList<MotorDriver> motorDriverDevicesList;
    private ArrayList<Sensor> sensorDevicesList;
    private ArrayList<WirelessIO> wirelessIODevicesList;

    /**
     * Constructs a new hardware system with empty device and port lists.
     */
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

        /*new addings */
        this.displayDevicesList = new ArrayList<>(displaysNumber);
        this.motorDriverDevicesList = new ArrayList<>(motorDriversNumber);
        this.sensorDevicesList = new ArrayList<>(sensorsNumber);
        this.wirelessIODevicesList = new ArrayList<>(WirelessIOsNumber);
    }

    public void closeProtocols()
    {
        Iterator<Protocol> iterator = ports.iterator();
        while(iterator.hasNext())
        {
            iterator.next().close();
        }

    }
    /**
     * Initializes the port ID mapping for all devices.
     * Sets all port IDs to -1 (unassigned) initially.
     */
    public void setPortIdOfDevices()
    {
        int totalDevicesCount = displaysNumber + sensorsNumber + motorDriversNumber + WirelessIOsNumber;
        for (int i = 0; i < totalDevicesCount; i++)
            this.portIdOfDevices.add(-1);
    }

    /**
     * Initializes the devices list with null values.
     * Prepares the list to hold device references.
     */
    public void setDevices()
    {
        int totalDevicesCount = displaysNumber + sensorsNumber + motorDriversNumber + WirelessIOsNumber;
        for (int i = 0; i < totalDevicesCount; i++)
            this.devices.add(null);
    }

    /**
     * Adds a new protocol port to the system.
     * 
     * @param pro The Protocol implementation to add
     */
    public void setProtocol(Protocol pro)
    {
        this.ports.add(pro);
        this.onOffPortsState.add(false);
        this.emptyOccupiedPortsState.add(false);
    }

    /**
     * Sets the number of sensor devices in the system and initializes their port ID list.
     * 
     * @param sensorsNumber The number of sensor devices
     */
    public void setSensorsNumber(int sensorsNumber)
    {
        this.sensorsNumber = sensorsNumber;
        this.sensors.clear();
        for (int i = 0; i < sensorsNumber; i++)
            this.sensors.add(-1);
        for(int i = 0;i<sensorsNumber;i++)
            sensorDevicesList.add(null);
    }
    
    /**
     * Gets the number of sensor devices in the system.
     * 
     * @return The number of sensor devices
     */
    public int getSensorsNumber()
        {return sensorsNumber;}
    
    /**
     * Sets the number of display devices in the system and initializes their port ID list.
     * 
     * @param displaysNumber The number of display devices
     */
    public void setDisplaysNumber(int displaysNumber)
    {
        this.displaysNumber = displaysNumber;
        this.displays.clear();
        for (int i = 0; i < displaysNumber; i++)
            this.displays.add(-1);
        for(int i = 0;i<displaysNumber;i++)
            displayDevicesList.add(null);
    }
    
    /**
     * Gets the number of display devices in the system.
     * 
     * @return The number of display devices
     */
    public int getDisplaysNumber()
        {return displaysNumber;}
    
    /**
     * Sets the number of wireless I/O devices in the system and initializes their port ID list.
     * 
     * @param WirelessIOsNumber The number of wireless I/O devices
     */
    public void setWirelessIOsNumber(int WirelessIOsNumber)
    {
        this.WirelessIOsNumber = WirelessIOsNumber;
        this.wirelessIOs.clear();
        for (int i = 0; i < WirelessIOsNumber; i++)
            this.wirelessIOs.add(-1);
        for(int i = 0;i<WirelessIOsNumber;i++)
            wirelessIODevicesList.add(null);
    }
    
    /**
     * Gets the number of wireless I/O devices in the system.
     * 
     * @return The number of wireless I/O devices
     */
    public int getWirelessIOsNumber()
        {return WirelessIOsNumber;}
    
    /**
     * Sets the number of motor driver devices in the system and initializes their port ID list.
     * 
     * @param motorDriversNumber The number of motor driver devices
     */
    public void setMotorDriversNumber(int motorDriversNumber)
    {
        this.motorDriversNumber = motorDriversNumber;
        this.motorDrivers.clear();
        for (int i = 0; i < motorDriversNumber; i++)
            this.motorDrivers.add(-1);
        for(int i = 0;i<motorDriversNumber;i++)
            motorDriverDevicesList.add(null);
    }
    
    /**
     * Gets the number of motor driver devices in the system.
     * 
     * @return The number of motor driver devices
     */
    public int getMotorDriversNumber()
        {return motorDriversNumber;}

    /**
     * Turns on a port and activates the device connected to it.
     * 
     * @param portId The ID of the port to turn on
     */
    /*public void turnOn(int portId)
    {
        if(portId < 0 || portId >= ports.size())
            System.err.println("Port number is out of range!!!");
        else if(!emptyOccupiedPortsState.get(portId))
            System.err.println("Port does not contain any devices!!!");
        else if(onOffPortsState.get(portId))
            System.err.println("Port has already opened!!!");
        else
        {
            onOffPortsState.set(portId,true);
            for(int i = 0;i<portIdOfDevices.size();i++)
            {
                if(portIdOfDevices.get(i) == portId)
                {
                    //devices.get(i).turnOn();
                    ports.get(portId).write("turnON");
                    if(devices.get(i).getDevType().equals("TempSensor Sensor") || devices.get(i).getDevType().equals("IMUSensor Sensor"))
                    {
                        int deviceIndex = i - displaysNumber - motorDriversNumber;
                        sensorDevicesList.get(deviceIndex).turnOn();
                        devices.get(i).setState(DeviceState.ON);
                        /*for(int j = 0;j<sensorDevicesList.size();j++)
                            if(sensorDevicesList.get(j).)
                                sensorDevicesList.get(j).turnOn();

                    }
                    else if(devices.get(i).getDevType().equals("MotorDriver"))
                    {
                        int deviceIndex = i - displaysNumber;
                        motorDriverDevicesList.get(deviceIndex).turnOn();
                        devices.get(i).setState(DeviceState.ON);
                    }
                    else if(devices.get(i).getDevType().equals("WirelessIO"))
                    {
                        int deviceIndex = i - displaysNumber -motorDriversNumber - sensorsNumber;
                        wirelessIODevicesList.get(deviceIndex).turnOn();
                        devices.get(i).setState(DeviceState.ON);
                    }
                    else if(devices.get(i).getDevType().equals("Display"))
                    {
                        int deviceIndex = i;
                        displayDevicesList.get(deviceIndex).turnOn();
                        devices.get(i).setState(DeviceState.ON);
                    }
                    /*if(devices.get(i) instanceof Sensor)
                    {
                        int deviceIndex = i - displaysNumber - motorDriversNumber;
                        sensorDevicesList.get(deviceIndex).turnOn();
                        /*for(int j = 0;j<sensorDevicesList.size();j++)
                            if(sensorDevicesList.get(j).)
                                sensorDevicesList.get(j).turnOn();

                    }
                    else if(devices.get(i) instanceof MotorDriver)
                    {
                        int deviceIndex = i - displaysNumber;
                        motorDriverDevicesList.get(deviceIndex).turnOn();
                    }
                    else if(devices.get(i) instanceof WirelessIO)
                    {
                        int deviceIndex = i - displaysNumber -motorDriversNumber - sensorsNumber;
                        wirelessIODevicesList.get(deviceIndex).turnOn();
                    }
                    else if(devices.get(i) instanceof Display)
                    {
                        int deviceIndex = i;
                        displayDevicesList.get(deviceIndex).turnOn();
                    }
                    break;
                }
            }
        }
    }*/
    public void turnOn(int portId)
    {
        if(portId < 0 || portId >= ports.size())
            System.err.println("Port number is out of range!!!");
        else if(!emptyOccupiedPortsState.get(portId))
            System.err.println("Port does not contain any devices!!!");
        else if(onOffPortsState.get(portId))
            System.err.println("Port has already opened!!!");
        else
        {
            onOffPortsState.set(portId,true);
            
            // For döngüsü yerine iterator kullanımı
            Iterator<Integer> iterator = portIdOfDevices.iterator();
            int i = 0;
            while(iterator.hasNext())
            {
                Integer currentPortId = iterator.next();
                if(currentPortId == portId)
                {
                    //devices.get(i).turnOn();
                    ports.get(portId).write("turnON");
                    if(devices.get(i).getDevType().equals("TempSensor Sensor") || devices.get(i).getDevType().equals("IMUSensor Sensor"))
                    {
                        int deviceIndex = i - displaysNumber - motorDriversNumber;
                        sensorDevicesList.get(deviceIndex).turnOn();
                        devices.get(i).setState(DeviceState.ON);
                        /*for(int j = 0;j<sensorDevicesList.size();j++)
                            if(sensorDevicesList.get(j).)
                                sensorDevicesList.get(j).turnOn();*/

                    }
                    else if(devices.get(i).getDevType().equals("MotorDriver"))
                    {
                        int deviceIndex = i - displaysNumber;
                        motorDriverDevicesList.get(deviceIndex).turnOn();
                        devices.get(i).setState(DeviceState.ON);
                    }
                    else if(devices.get(i).getDevType().equals("WirelessIO"))
                    {
                        int deviceIndex = i - displaysNumber -motorDriversNumber - sensorsNumber;
                        wirelessIODevicesList.get(deviceIndex).turnOn();
                        devices.get(i).setState(DeviceState.ON);
                    }
                    else if(devices.get(i).getDevType().equals("Display"))
                    {
                        int deviceIndex = i;
                        displayDevicesList.get(deviceIndex).turnOn();
                        devices.get(i).setState(DeviceState.ON);
                    }
                    /*if(devices.get(i) instanceof Sensor)
                    {
                        int deviceIndex = i - displaysNumber - motorDriversNumber;
                        sensorDevicesList.get(deviceIndex).turnOn();
                        /*for(int j = 0;j<sensorDevicesList.size();j++)
                            if(sensorDevicesList.get(j).)
                                sensorDevicesList.get(j).turnOn();

                    }
                    else if(devices.get(i) instanceof MotorDriver)
                    {
                        int deviceIndex = i - displaysNumber;
                        motorDriverDevicesList.get(deviceIndex).turnOn();
                    }
                    else if(devices.get(i) instanceof WirelessIO)
                    {
                        int deviceIndex = i - displaysNumber -motorDriversNumber - sensorsNumber;
                        wirelessIODevicesList.get(deviceIndex).turnOn();
                    }
                    else if(devices.get(i) instanceof Display)
                    {
                        int deviceIndex = i;
                        displayDevicesList.get(deviceIndex).turnOn();
                    }*/
                    break;
                }
                i++; // İndeksi artır
            }
        }
    }


    /**
     * Turns off a port and deactivates the device connected to it.
     * 
     * @param portId The ID of the port to turn off
     */
    public void turnOff(int portId)
    {
        if(portId < 0 || portId >= ports.size())
            System.err.println("Port number is out of range!!!");
        else if(!emptyOccupiedPortsState.get(portId))
            System.err.println("Port does not contain any devices!!!");
        else if(!onOffPortsState.get(portId))
            System.err.println("Port has already closed!!!");
        else
        {
            onOffPortsState.set(portId,false);
            for(int i = 0;i<portIdOfDevices.size();i++)
            {
                if(portIdOfDevices.get(i) == portId)
                {
                    ports.get(portId).write("turnOFF");
                    if(devices.get(i).getDevType().equals("TempSensor Sensor") || devices.get(i).getDevType().equals("IMUSensor Sensor"))
                    {
                        int deviceIndex = i - displaysNumber - motorDriversNumber;
                        sensorDevicesList.get(deviceIndex).turnOff();
                        devices.get(i).setState(DeviceState.OFF);
                        /*for(int j = 0;j<sensorDevicesList.size();j++)
                            if(sensorDevicesList.get(j).)
                                sensorDevicesList.get(j).turnOn();*/

                    }
                    else if(devices.get(i).getDevType().equals("MotorDriver"))
                    {
                        int deviceIndex = i - displaysNumber;
                        motorDriverDevicesList.get(deviceIndex).turnOff();
                        devices.get(i).setState(DeviceState.OFF);
                    }
                    else if(devices.get(i).getDevType().equals("WirelessIO"))
                    {
                        int deviceIndex = i - displaysNumber -motorDriversNumber - sensorsNumber;
                        wirelessIODevicesList.get(deviceIndex).turnOff();
                        devices.get(i).setState(DeviceState.OFF);
                    }
                    else if(devices.get(i).getDevType().equals("Display"))
                    {
                        int deviceIndex = i;
                        displayDevicesList.get(deviceIndex).turnOff();
                        devices.get(i).setState(DeviceState.OFF);
                    }
                    /*if(devices.get(i) instanceof Sensor)
                    {
                        int deviceIndex = i - displaysNumber - motorDriversNumber;
                        sensorDevicesList.get(deviceIndex).turnOff();
                        /*for(int j = 0;j<sensorDevicesList.size();j++)
                            if(sensorDevicesList.get(j).)
                                sensorDevicesList.get(j).turnOn();

                    }
                    else if(devices.get(i) instanceof MotorDriver)
                    {
                        int deviceIndex = i - displaysNumber;
                        motorDriverDevicesList.get(deviceIndex).turnOff();
                    }
                    else if(devices.get(i) instanceof WirelessIO)
                    {
                        int deviceIndex = i - displaysNumber -motorDriversNumber - sensorsNumber;
                        wirelessIODevicesList.get(deviceIndex).turnOff();
                    }
                    else if(devices.get(i) instanceof Display)
                    {
                        int deviceIndex = i;
                        displayDevicesList.get(deviceIndex).turnOff();
                    }*/
                    break;
                }
            }
        }
    }

    /**
     * Lists all ports in the system with their status and connected devices.
     * Displays port ID, protocol type, occupancy status, and device details if occupied.
     */
    public void listPorts()
    {
        System.out.println("list of ports:");
        for(int i = 0;i<ports.size();i++)
        {
            if(!emptyOccupiedPortsState.get(i))
                System.out.printf("%d %s empty\n",i,ports.get(i).getProtocolName());
            else
            {
                System.out.printf("%d %s occupied ",i,ports.get(i).getProtocolName());
                for(int j = 0;j<devices.size();j++)
                {
                    if(portIdOfDevices.get(j) == i && devices.get(j) != null)
                    {
                        Device device = devices.get(j);
                        int displayedDevId = 0;
                        //String devType = device.getDevType();
                            
                        /*if(devType.equals("Display"))
                            displayedDevId = j;
                        else if(devType.equals("MotorDriver"))
                            displayedDevId = j - displaysNumber;
                        else if(devType.equals("Sensor"))
                            displayedDevId = j - (displaysNumber + motorDriversNumber);
                        else if(devType.equals("WirelessIO"))
                            displayedDevId = j - (displaysNumber + motorDriversNumber + sensorsNumber);
                         */   
                        System.out.printf("%s %s %d %s\n", device.getName(), device.getDevType(), 
                            displayedDevId, device.getState());
                        break;
                    }
                }
                //System.out.printf("\n");
            }
        }
    }

    /**
     * Lists all devices of a specific type in the system.
     * Displays device name, ID, port ID, and protocol for each matching device.
     * 
     * @param devType The device type to list ("Display", "MotorDriver", "Sensor", or "WirelessIO")
     */
    public void listDevType(String devType)
    {
        if(!devType.equals("Display") && devType.equals("MotorDriver") 
                && devType.equals("Sensor") && devType.equals("WirelessIO"))
                System.err.println("No matching with any device types");
        else
        {
            Boolean flagEmpty = false;
            System.out.printf("List of %ss\n",devType);
            int deviceId = 0;
            if(devType.equals("Display"))
            {
                flagEmpty = true;
                for(int i = 0;i<displayDevicesList.size();i++)
                System.out.println(displayDevicesList.get(i).getName() + " " + deviceId + " " +
                        displays.get(i) + " " + displayDevicesList.get(i).getProtocol());
                deviceId++;
            }
            else if(devType.equals("MotorDriver"))
            {
                flagEmpty = true;
                for(int i = 0;i<motorDriverDevicesList.size();i++)
                System.out.println(motorDriverDevicesList.get(i).getName() + " " + deviceId + " " +
                        motorDrivers.get(i) + " " + motorDriverDevicesList.get(i).getProtocol());
                deviceId++;
            }
            else if(devType.equals("Sensor"))
            {
                flagEmpty = true;
                for(int i = 0;i<sensorDevicesList.size();i++)
                {
                    if(sensorDevicesList.get(i) != null)
                        System.out.println(sensorDevicesList.get(i).getName() + " " + deviceId + " " +
                                sensors.get(i) + " " + sensorDevicesList.get(i).getProtocol());
                }
                deviceId++;
            }
            else if(devType.equals("WireLessIO"))
            {
                flagEmpty = true;
                for(int i = 0;i<wirelessIODevicesList.size();i++)
                System.out.println(wirelessIODevicesList.get(i).getName() + " " + deviceId + " " +
                        wirelessIOs.get(i) + " " + wirelessIODevicesList.get(i).getProtocol());
                deviceId++;
            }
            /*
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
            }*/
            if(!flagEmpty)
                System.err.println("There is no any device on that type!!!");
        }
    }

    /**
     * Reads data from a sensor device.
     * Displays the sensor name, type, and current data if the device is active.
     * 
     * @param devId The ID of the sensor device to read from
     */
    public void readSensor(int devId)
    {
        /*yeni hali */
        if(devId < 0 || devId >= sensorsNumber)
            System.err.println("Device id is out of range(on readining sensor)");
        else
        {
            if(sensorDevicesList.get(devId).getState() == DeviceState.OFF)
                System.err.println("Device is not active\nCommand is failed(on readining sensor)");
            else
            {
                int index = sensors.get(devId);
                ports.get(index).read();
                System.out.println(sensorDevicesList.get(devId).getName() + " " + sensorDevicesList.get(devId).getSensType()
                    + ": " + sensorDevicesList.get(devId).data2String());
            }
        }
        /*if(devId < 0 || devId >= sensorsNumber)
        {
            System.err.println("Device id is out of range");
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
                        System.err.println("Device is not active\nCommand is failed");
                    else
                    {
                        System.out.println(objectSensor.getName() + " " + objectSensor.getSensType()
                            + " " + objectSensor.data2String());
                    }
                }
                else if(devices.get(i) instanceof Sensor tmp && correctDevice < devId)
                    correctDevice++;
            }
        }*/
    }

    /**
     * Prints data to a display device.
     * 
     * @param devId The ID of the display device to print to
     * @param data The data string to display
     */
    public void printDisplay(int devId,String data)
    {
        if(devId < 0 || devId >= displaysNumber)
        {
            System.err.println("Device id is out of range");
        }
        else
        {
            if(displayDevicesList.get(devId).getState() == DeviceState.OFF)
                System.err.println("Device is not active\nCommand is failed");
            else
            {
                int index = displays.get(devId);
                ports.get(index).write(data);
                displayDevicesList.get(devId).printData(data);
                //System.out.println(displayDevicesList.get(devId).getName() + " is printing " + data);
            }

            /*int correctDevice = 0;
            for(int i = 0;i<devices.size() && correctDevice <= devId;i++)
            {
                if(devices.get(i) instanceof Display objectDisplay && correctDevice == devId)
                {
                    correctDevice++;
                    if(devices.get(i).getState() == DeviceState.OFF)
                        System.err.println("Device is not active\nCommand is failed");
                    else
                        objectDisplay.printData(data);
                }
                else if(devices.get(i) instanceof Display tmp && correctDevice < devId)
                    correctDevice++;
            }*/
        }
    }

    /**
     * Reads data from a wireless I/O device.
     * 
     * @param devId The ID of the wireless I/O device to read from
     */
    public void readWireless(int devId)
    {
        if(devId < 0 || devId >= WirelessIOsNumber)
        {
            System.err.println("Device id is out of range");
        }
        else
        {
            if(wirelessIODevicesList.get(devId).getState() == DeviceState.OFF)
                System.err.println("Device is not active\nCommand is failed");
            else
            {
                int index = wirelessIOs.get(devId);
                ports.get(index).read();
                wirelessIODevicesList.get(devId).recvData();
            }
            /*int correctDevice = 0;
            for(int i = 0;i<devices.size() && correctDevice <= devId;i++)
            {
                if(devices.get(i) instanceof WirelessIO objectWirelessIO && correctDevice == devId)
                {
                    correctDevice++;
                    if(devices.get(i).getState() == DeviceState.OFF)
                        System.err.println("Device is not active\nCommand is failed");
                    else
                        objectWirelessIO.recvData();
                }
                else if(devices.get(i) instanceof WirelessIO tmp && correctDevice < devId)
                    correctDevice++;
            }*/
        }
    }

    /**
     * Writes data to a wireless I/O device.
     * 
     * @param devId The ID of the wireless I/O device to write to
     * @param data The data string to send
     */
    public void writeWireless(int devId,String data)
    {
        if(devId < 0 || devId >= WirelessIOsNumber)
        {
            System.err.println("Device id is out of range");
        }
        else
        {
            if(wirelessIODevicesList.get(devId).getState() == DeviceState.OFF)
                System.err.println("Device is not active\nCommand is failed");
            else
            {
                int index = wirelessIOs.get(devId);
                ports.get(index).write(data);
                wirelessIODevicesList.get(devId).sendData(data);
            }
            /*int correctDevice = 0;
            for(int i = 0;i<devices.size() && correctDevice <= devId;i++)
            {
                if(devices.get(i) instanceof WirelessIO objectWirelessIO && correctDevice == devId)
                {
                    correctDevice++;
                    if(devices.get(i).getState() == DeviceState.OFF)
                        System.err.println("Device is not active\nCommand is failed");
                    else
                        objectWirelessIO.sendData(data);
                }
                else if(devices.get(i) instanceof WirelessIO tmp && correctDevice < devId)
                    correctDevice++;
            }*/
        }
    }

    /**
     * Sets the speed of a motor driver device.
     * 
     * @param devId The ID of the motor driver device
     * @param speed The speed value to set
     */
    public void setMotorSpeed(int devId,int speed)
    {
        if(devId < 0 || devId >= motorDriversNumber)
        {
            System.err.println("Device id is out of range");
        }
        else
        {
            if(motorDriverDevicesList.get(devId).getState() == DeviceState.OFF)
                System.err.println("Device is not active\nCommand is failed");
            else
            {
                int index = motorDrivers.get(devId);
                ports.get(index).write("Setting motorspeed to " + Integer.toString(speed));
                motorDriverDevicesList.get(devId).setMotorSpeed(speed);
            }
            /*int correctDevice = 0;
            for(int i = 0;i<devices.size() && correctDevice <= devId;i++)
            {
                if(devices.get(i) instanceof MotorDriver objectMotorDriver && correctDevice == devId)
                {
                    correctDevice++;
                    if(devices.get(i).getState() == DeviceState.OFF)
                        System.err.println("Device is not active\nCommand is failed");
                    else
                        objectMotorDriver.setMotorSpeed(speed);
                }
                else if(devices.get(i) instanceof MotorDriver tmp && correctDevice < devId)
                    correctDevice++;
            }*/
        }
    }

    /**
     * Adds a new device to the system.
     * 
     * @param devName The name/type of device to add
     * @param portId The port ID to connect the device to
     * @param devId The device ID to assign
     */
    public void addDev(String devName,int portId,int devId)
    {
        boolean addController = false;
        Device newDevice = null;
        int deviceIndex = -1;
        if(portId < 0 || portId >= ports.size())
            System.err.println("Port number is out of range!!!(addDev part)");
        else if(emptyOccupiedPortsState.get(portId))
            System.err.println("Port is occupied by another device!!!(addDev part)");
        else if(devName.equals("LCD") || devName.equals("OLED"))/*displays */
        {
            Protocol protocolOfPort = ports.get(portId);
            if(devId >= displaysNumber || devId < 0)
                System.err.println("Device id is out of range!!!(addDev part)");
            else if(displays.get(devId) != -1)
                System.err.println("Device is already connected to another port(addDev part)");
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
                    /*alt satır yeni*/
                    if(devName.equals("LCD"))
                        displayDevicesList.set(devId,new LCD(protocolOfPort.getProtocolName()));
                    else
                        displayDevicesList.set(devId,new OLED(protocolOfPort.getProtocolName()));
                    portIdOfDevices.set(deviceIndex,portId);
                    addController = true;
                }
            }
        }
        else if(devName.equals("PCA9685") || devName.equals("SparkFunMD"))/*MotorDrivers */
        {
            Protocol protocolOfPort = ports.get(portId);
        
            if(devId >= motorDriversNumber || devId < 0)
                System.err.println("Device id is out of range(addDev part)");
            else if(motorDrivers.get(devId) != -1)
                System.err.println("Device is already connected to another port(addDev part)");
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
                    if(devName.equals("PCA9685"))
                        motorDriverDevicesList.set(devId,new PCA9685(protocolOfPort.getProtocolName()));
                    else
                        motorDriverDevicesList.set(devId,new SparkFunMD(protocolOfPort.getProtocolName()));
                    
                    portIdOfDevices.set(deviceIndex,portId);
                    addController = true;
                }
            }
        }
        else if(devName.equals("BME280") || devName.equals("DHT11") || devName.equals("GY951") || devName.equals("MPU6050"))/*Sensor*/
        {
            Protocol protocolOfPort = ports.get(portId);
        
            if(devId >= sensorsNumber || devId < 0)
                System.err.println("Device id is out of range(addDev part)");
            else if(sensors.get(devId) != -1)
                System.err.println("Device is already connected to another port(addDev part)");
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
                    if(devName.equals("BME280"))
                        sensorDevicesList.set(devId,new BME280(protocolOfPort.getProtocolName()));
                    else if(devName.equals("DHT11"))
                        sensorDevicesList.set(devId,new DHT11(protocolOfPort.getProtocolName()));
                    else if(devName.equals("GY951"))
                        sensorDevicesList.set(devId,new GY951(protocolOfPort.getProtocolName()));
                    else
                        sensorDevicesList.set(devId,new MPU6050(protocolOfPort.getProtocolName()));
                    portIdOfDevices.set(deviceIndex,portId);
                    addController = true;
                }
            }
        }
        else if(devName.equals("Bluetooth") || devName.equals("Wifi"))/*Wirelessio*/
        {
            Protocol protocolOfPort = ports.get(portId);
        
            if(devId >= WirelessIOsNumber || devId < 0)
                System.err.println("Device id is out of range(addDev part)");
            else if(wirelessIOs.get(devId) != -1)
                System.err.println("Device is already connected to another port(addDev part)");
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
                    /*alt satır yeni*/
                    if(devName.equals("Bluetooth"))
                        wirelessIODevicesList.set(devId,new Bluetooth(protocolOfPort.getProtocolName()));
                    else
                        wirelessIODevicesList.set(devId,new Wifi(protocolOfPort.getProtocolName()));
                    portIdOfDevices.set(deviceIndex,portId);
                    addController = true;
                }
            }
        }
        else
            System.err.println("Device can not be found!!!(addDev part)");
        if(addController)
            System.out.println("Device added.");
    }

    /**
     * Removes a device from the specified port.
     * 
     * @param portId The ID of the port from which to remove the device
     */
    public void rmDev(int portId)
    {
        if(portId < 0 || portId >= ports.size())
            System.err.println("Port id is out of range!!!(rmDev part)");
        else if(onOffPortsState.get(portId))
            System.err.println("Device is active\nCommand is failed(rmDev part)");
        else if(!emptyOccupiedPortsState.get(portId))
            System.err.println("No any devices on that port\nCommand is failed(rmDev part)");
        else
        {
            emptyOccupiedPortsState.set(portId,false);
            for(int j = 0;j<devices.size();j++)
            {
                if(portIdOfDevices.get(j) == portId)
                {
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
                    System.out.println("Device removed");
                }
            }
        }
    }
}