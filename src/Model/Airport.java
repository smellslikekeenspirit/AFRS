package Model;

import java.util.LinkedList;

/**
 * represents a single Airport
 */
public class Airport {

    /**
     * three letter code for the airport
     */
    private String code;

    /**
     * the city the airport is in
     */
    private String cityName;

    /**
     * the weather at the airport
     */
    private LinkedList<String> weather;

    /**
     * the temperature at te airport
     */
    private LinkedList<Integer> temp;

    /**
     * the delay for the airport
     */
    private int delay;

    /**
     * the connection time for the airport
     */
    private int connectionTime;

    /**
     * constructor
     * @param code this airport's code
     * @param cityName this airport's city
     */
    public Airport(String code, String cityName){
        this.code = code;
        this.cityName = cityName;
        weather = new LinkedList<>();
        temp = new LinkedList<>();
    }

    /**
     * adds a weather condition to this airport's weather
     * @param weatherCondition the weather condition to add
     */
    public void addWeatherCondition(String weatherCondition){
        weather.add(weatherCondition);
    }

    /**
     * gets the current weather condition of the airport
     * and updates it
     * @return the current weather condition of the airport
     */
    public String getCurrentWeather(){
        String currentWeather = weather.pop();
        addWeatherCondition(currentWeather);
        return currentWeather;
    }

    /**
     * adds a temperature condition to this airport's temperature
     * @param temperatureCondition the temperature condition to add
     */
    public void addTemperatureCondition(int temperatureCondition){
        temp.add(temperatureCondition);
    }

    /**
     * gets and then updates the current temperature of this airport
     * @return the current temperature of this airport
     */
    public int getCurrentTemperature(){
        int currentTemperature = temp.pop();
        addTemperatureCondition(currentTemperature);
        return currentTemperature;
    }


    public String getName() { return cityName; }


    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }


    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

}
