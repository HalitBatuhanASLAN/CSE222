package PlanetSystem;
/**
 * Represents environmental sensor measurements for celestial bodies.
 * Stores and manages temperature, pressure, humidity, and radiation data.
 */
public class SensorData
{
    /**
     * Constructs a new SensorData object with specified environmental measurements.
     *
     * @param temperature The temperature value in Kelvin
     * @param pressure The pressure value in Pascals
     * @param humidity The humidity value in percentage
     * @param radiation The radiation value in Sieverts
     */
    public SensorData(double temperature,double pressure,double humidity,double radiation)
    {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.radiation = radiation;
    }
    /** Temperature measurement in Kelvin */
    private double temperature;
    /** Pressure measurement in Pascals */
    private double pressure;
    /** Humidity measurement in percentage */
    private double humidity;
    /** Radiation measurement in Sieverts */
    private double radiation;

    /**
     * Retrieves the radiation measurement.
     *
     * @return The radiation value in Sieverts
     */
    public double getRadiation(){return radiation;}
     /**
     * Creates a formatted string containing all sensor measurements.
     * Includes temperature (Kelvin), pressure (Pascals), humidity (percentage),
     * and radiation (Sieverts) values.
     *
     * @return A string containing all formatted sensor measurements
     */
    public String information()
    {
        String info = " " + this.temperature + " Kelvin " + this.pressure + " Pascals " + 
        this.humidity + " percentage of humudity " + this.radiation + " Sieverts";
        return info; 
    }
}