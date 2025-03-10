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
        }
    }

    /**
     * Lists all ports in the system with their status and connected devices.
     * Displays port ID, protocol type, occupancy status, and device details if occupied.
     */
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
                        break;
                    }
                }
                System.out.printf("\n");
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

    /**
     * Reads data from a sensor device.
     * Displays the sensor name, type, and current data if the device is active.
     * 
     * @param devId The ID of the sensor device to read from
     */
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

    /**
     * Reads data from a wireless I/O device.
     * 
     * @param devId The ID of the wireless I/O device to read from
     */
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

    /**
     * Adds a new device to the system.
     * 
     * @param devName The name/type of device to add
     * @param portId The port ID to connect the device to
     * @param devId The device ID to assign
     */
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

    /**
     * Removes a device from the specified port.
     * 
     * @param portId The ID of the port from which to remove the device
     */
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