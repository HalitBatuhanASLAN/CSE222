package System;
import java.util.ArrayList;
import System.Devices.*;
import System.Protocols.*;

public class System
{
    private ArrayList<Protocol> ports;
    private ArrayList<Device> devices;
    private ArrayList<MotorDriver> motorDrivers;

    public Boolean setMotorSpeed(int devId, int speed)
    {
        if(motorDrivers.get(devId) == null) return false;
        motorDrivers.get(devId).setMotorSpeed(speed);
        return true;
    } 
    
}