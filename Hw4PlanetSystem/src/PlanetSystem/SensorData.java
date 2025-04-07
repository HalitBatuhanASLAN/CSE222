package PlanetSystem;

public class SensorData
{
    /*I will add controller for unvalid inputs */
    public SensorData(double temperature,double pressure,double humidity,double radiation)
    {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.radiation = radiation;
    }
    private double temperature;
    private double pressure;
    private double humidity;
    private double radiation;

    public double getRadiation(){return radiation;}
    public String information()
    {
        String info = " " + this.temperature + " Kelvin " + this.pressure + " Pascals " + 
        this.humidity + " percentage of humudity " + this.radiation + " Sieverts";
        return info; 
    }
}