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

    /**
     * Closes all protocol ports in the system.
     * This should be called when shutting down the system to ensure proper cleanup.
     */
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
    public void turnOn(int portId)
    {
        if(portId < 0 || portId >= ports.size())
            System.err.println("Port number is out of range!!!(turnON) portID: " + portId);
        else if(!emptyOccupiedPortsState.get(portId))
            System.err.println("Port does not contain any devices!!!(turnON) portID: " + portId);
        else if(onOffPortsState.get(portId))
            System.err.println("Port has already opened!!!(turnON) portID: " + portId);
        else
        {
            onOffPortsState.set(portId,true);
            ports.get(portId).write("turnON");
            if(devices.get(portId) != null ) 
            {
                if(devices.get(portId).getDevType().equals("TempSensor Sensor") || devices.get(portId).getDevType().equals("IMUSensor Sensor"))
                {
                    int deviceIndex = portId - displaysNumber - motorDriversNumber;
                    sensorDevicesList.get(deviceIndex).turnOn();
                    devices.get(portId).setState(DeviceState.ON);
                }
                else if(devices.get(portId).getDevType().equals("MotorDriver"))
                {
                    int deviceIndex = portId - displaysNumber;
                    motorDriverDevicesList.get(deviceIndex).turnOn();
                    devices.get(portId).setState(DeviceState.ON);
                }
                else if(devices.get(portId).getDevType().equals("WirelessIO"))
                {
                    int deviceIndex = portId - displaysNumber -motorDriversNumber - sensorsNumber;
                    wirelessIODevicesList.get(deviceIndex).turnOn();
                    devices.get(portId).setState(DeviceState.ON);
                }
                else if(devices.get(portId).getDevType().equals("Display"))
                {
                    int deviceIndex = portId;
                    displayDevicesList.get(deviceIndex).turnOn();
                    devices.get(portId).setState(DeviceState.ON);
                }
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
            System.err.println("Port number is out of range!!!(turnOFF) portID: " + portId);
        else if(!emptyOccupiedPortsState.get(portId))
            System.err.println("Port does not contain any devices!!!(turnOFF) portID: " + portId);
        else if(!onOffPortsState.get(portId))
            System.err.println("Port has already closed!!!(turnOFF) portID: " + portId);
        else
        {
            onOffPortsState.set(portId,false);
            ports.get(portId).write("turnOFF");
            if(devices.get(portId) != null ) 
            {
                if(devices.get(portId).getDevType().equals("TempSensor Sensor") || devices.get(portId).getDevType().equals("IMUSensor Sensor"))
                {
                    int deviceIndex = portId - displaysNumber - motorDriversNumber;
                    sensorDevicesList.get(deviceIndex).turnOff();
                    devices.get(portId).setState(DeviceState.OFF);
                }
                else if(devices.get(portId).getDevType().equals("MotorDriver"))
                {
                    int deviceIndex = portId - displaysNumber;
                    motorDriverDevicesList.get(deviceIndex).turnOff();
                    devices.get(portId).setState(DeviceState.OFF);
                }
                else if(devices.get(portId).getDevType().equals("WirelessIO"))
                {
                    int deviceIndex = portId - displaysNumber -motorDriversNumber - sensorsNumber;
                    wirelessIODevicesList.get(deviceIndex).turnOff();
                    devices.get(portId).setState(DeviceState.OFF);
                }
                else if(devices.get(portId).getDevType().equals("Display"))
                {
                    int deviceIndex = portId;
                    displayDevicesList.get(deviceIndex).turnOff();
                    devices.get(portId).setState(DeviceState.OFF);
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
        
        Iterator<Protocol> portIterator = ports.iterator();
        Iterator<Boolean> stateIterator = emptyOccupiedPortsState.iterator();
        int portIndex = 0;
        
        while(portIterator.hasNext() && stateIterator.hasNext())
        {
            Protocol port = portIterator.next();
            boolean isOccupied = stateIterator.next();
            
            if(!isOccupied)
                System.out.printf("%d %s empty\n", portIndex, port.getProtocolName());
            else
            {
                System.out.printf("%d %s occupied ", portIndex, port.getProtocolName());
                
                Iterator<Device> deviceIterator = devices.iterator();
                Iterator<Integer> portIdIterator = portIdOfDevices.iterator();
                
                while(deviceIterator.hasNext() && portIdIterator.hasNext())
                {
                    Device device = deviceIterator.next();
                    int devicePortId = portIdIterator.next();
                    
                    if(devicePortId == portIndex && device != null)
                    {
                        int displayedDevId = 0;
                        System.out.printf("%s %s %d %s\n", device.getName(), device.getDevType(), 
                            displayedDevId, device.getState());
                        break;
                    }
                }
            }
            portIndex++;
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
                System.err.println("No matching with any device types(list devType) entered devType: " + devType);
        else
        {
            Boolean flagEmpty = false;
            System.out.printf("List of %ss\n",devType);
            int deviceId = 0;
            if(devType.equals("Display"))
            {
                flagEmpty = true;
                Iterator<Display> deviceIter = displayDevicesList.iterator();
                Iterator<Integer> portIDIterator = displays.iterator();
                
                while(deviceIter.hasNext() && portIDIterator.hasNext())
                {
                    Display display = deviceIter.next();
                    Integer displayInfo = portIDIterator.next();
                    
                    if(display != null)
                        System.out.println(display.getName() + " " + deviceId + " " +
                                displayInfo + " " + display.getProtocol());        
                }
                deviceId++;
            }
            else if(devType.equals("MotorDriver"))
            {
                flagEmpty = true;
                Iterator<MotorDriver> deviceIter = motorDriverDevicesList.iterator();
                Iterator<Integer> portIDIterator = motorDrivers.iterator();
                
                while(deviceIter.hasNext() && portIDIterator.hasNext())
                {
                    MotorDriver motorDriver = deviceIter.next();
                    Integer displayInfo = portIDIterator.next();
                    
                    if(motorDriver != null)
                        System.out.println(motorDriver.getName() + " " + deviceId + " " +
                                displayInfo + " " + motorDriver.getProtocol());        
                }
                deviceId++;
            }
            else if(devType.equals("Sensor"))
            {
                flagEmpty = true;
                Iterator<Sensor> deviceIter = sensorDevicesList.iterator();
                Iterator<Integer> portIDIterator = sensors.iterator();
                
                while(deviceIter.hasNext() && portIDIterator.hasNext())
                {
                    Sensor sensor = deviceIter.next();
                    Integer displayInfo = portIDIterator.next();
                    
                    if(sensor != null)
                        System.out.println(sensor.getName() + " " + deviceId + " " +
                                displayInfo + " " + sensor.getProtocol());        
                }
                deviceId++;
            }
            else if(devType.equals("WireLessIO"))
            {
                flagEmpty = true;
                Iterator<WirelessIO> deviceIter = wirelessIODevicesList.iterator();
                Iterator<Integer> portIDIterator = wirelessIOs.iterator();
                
                while(deviceIter.hasNext() && portIDIterator.hasNext())
                {
                    WirelessIO wirelessIO = deviceIter.next();
                    Integer displayInfo = portIDIterator.next();
                    
                    if(wirelessIO != null)
                        System.out.println(wirelessIO.getName() + " " + deviceId + " " +
                                displayInfo + " " + wirelessIO.getProtocol());        
                }
                deviceId++;
            }
            if(!flagEmpty)
                System.err.println("There is no any device on that type!!!");
        }
    }

    /*it was the first part that I applied changes so for here I got helping from AI for others I convert
     * by myself
     */
    /**
     * Reads data from a sensor device.
     * Displays the sensor name, type, and current data if the device is active.
     * 
     * @param devId The ID of the sensor device to read from
     */
    public void readSensor(int devId)
    {
        if(devId < 0 || devId >= sensorsNumber)
            System.err.println("Device id is out of range(readSensor) devID: " + devId);
        else
        {
            if(sensorDevicesList.get(devId) == null)
                System.err.println("Device is not connected to any port(readSensor part) devID: " + devId);
            else
            {
                if(sensorDevicesList.get(devId).getState() == DeviceState.OFF)
                    System.err.println("Device is not active->Command is failed(on readining sensor) DevID: " + devId);
                else
                {
                    int index = sensors.get(devId);
                    ports.get(index).read();
                    System.out.println(sensorDevicesList.get(devId).getName() + " " + sensorDevicesList.get(devId).getSensType()
                        + ": " + sensorDevicesList.get(devId).data2String());
                }
            }
        }
        
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
            System.err.println("Device id is out of range(printDisplay) devID: " + devId);
        }
        else
        {
            if(displayDevicesList.get(devId) == null)
                System.err.println("Device is not connected to any port(printDisplay part) devID: " + devId);
            else
            {
                if(displayDevicesList.get(devId).getState() == DeviceState.OFF)
                    System.err.println("Device is not active->Command is failed(printDisplay part) DevID: " + devId);
                else
                {
                    int index = displays.get(devId);
                    ports.get(index).write(data);
                    displayDevicesList.get(devId).printData(data);
                }
            }
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
            System.err.println("Device id is out of range(readWireless) devID: " + devId);
        }
        else
        {
            if(wirelessIODevicesList.get(devId) == null)
                System.err.println("Device is not connected to any port(readWireless part) devID: " + devId);
            else
            {
                if(wirelessIODevicesList.get(devId).getState() == DeviceState.OFF)
                    System.err.println("Device is not active->Command is failed(readWireless) DevID: " + devId);
                else
                {
                    int index = wirelessIOs.get(devId);
                    ports.get(index).read();
                    wirelessIODevicesList.get(devId).recvData();
                }
            }    
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
            System.err.println("Device id is out of range(writeWireless) devID " + devId);
        }
        else
        {
            if(wirelessIODevicesList.get(devId) == null)
                System.err.println("Device is not connected to any port(writeWireless part) devID: " + devId);
            else
            {
                if(wirelessIODevicesList.get(devId).getState() == DeviceState.OFF)
                    System.err.println("Device is not active->Command is failed(writeWireless) DevID: " + devId);
                else
                {
                    int index = wirelessIOs.get(devId);
                    ports.get(index).write(data);
                    wirelessIODevicesList.get(devId).sendData(data);
                }
            }
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
            System.err.println("Device id is out of range(setMotorSpeed) devId: " + devId);
        }
        else
        {
            if(motorDriverDevicesList.get(devId) == null)
                System.err.println("Device is not connected to any port(setMotorSpeed) devID: " + devId);
            else
            {
                if(motorDriverDevicesList.get(devId).getState() == DeviceState.OFF)
                    System.err.println("Device is not active->Command is failed(setMotorSpeed) DevID: " + devId);
                else
                {
                    int index = motorDrivers.get(devId);
                    ports.get(index).write("Setting motorspeed to " + Integer.toString(speed));
                    motorDriverDevicesList.get(devId).setMotorSpeed(speed);
                }
            }
            
        }
    }

    /*AI used to converting for loops to iterator usage or usage of other allayList(I created arrayList 
     * AI just give me the possible solving of algorithms problems in there but I converted part by part
    ) */
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
            System.err.println("Port number is out of range!!!(addDev part) devID: " + devId + " portID: " + portId);
        else if(emptyOccupiedPortsState.get(portId))
            System.err.println("Port is occupied by another device!!!(addDev part) devID: " + devId + " portID: " + portId);
        else if(devName.equals("LCD") || devName.equals("OLED"))/*displays */
        {
            Protocol protocolOfPort = ports.get(portId);
            if(devId >= displaysNumber || devId < 0)
                System.err.println("Device id is out of range!!!(addDev part) devID: " + devId + " portID: " + portId);
            else if(displays.get(devId) != -1)
                System.err.println("Device is already connected to another port(addDev part) devID: " + devId + " portID: " + portId);
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
                System.err.println("Device id is out of range(addDev part) devID: " + devId + " portID: " + portId);
            else if(motorDrivers.get(devId) != -1)
                System.err.println("Device is already connected to another port(addDev part) devID: " + devId + " portID: " + portId);
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
                System.err.println("Device id is out of range(addDev part) devID: " + devId + " portID: " + portId);
            else if(sensors.get(devId) != -1)
                System.err.println("Device is already connected to another port(addDev part) devID: " + devId + " portID: " + portId);
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
                System.err.println("Device id is out of range(addDev part) devID: " + devId + " portID: " + portId);
            else if(wirelessIOs.get(devId) != -1)
                System.err.println("Device is already connected to another port(addDev part) devID: " + devId + " portID: " + portId);
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
            System.err.println("Device can not be found!!!(addDev part) devID: " + devId + " portID: " + portId);
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
            System.err.println("Port id is out of range!!!(rmDev part) portID: " + portId);
        else if(onOffPortsState.get(portId))
            System.err.println("Device is active->Command is failed(rmDev part) portID: " + portId);
        else if(!emptyOccupiedPortsState.get(portId))
            System.err.println("No any devices on that port->Command is failed(rmDev part) portID: " + portId);
        else
        {
            emptyOccupiedPortsState.set(portId,false);
            Iterator<Integer> iterator = portIdOfDevices.iterator();
            int j = 0;
            while(iterator.hasNext())
            {
                Integer currentPortId = iterator.next();
                if(currentPortId == portId)
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
                j++;
            }

        }
    }
}