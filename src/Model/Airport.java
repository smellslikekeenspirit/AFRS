package Model;

import java.util.LinkedList;
import java.util.List;

public class Airport {

    private String code;
    private String cityName;
    private LinkedList<String> weather;
    private LinkedList<Integer> temp;
    private int delay;
    private int connectionTime;

    public Airport(String code, String cityName){
        this.code = code;
        this.cityName = cityName;
        weather = new LinkedList<>();
        temp = new LinkedList<>();
    }

    public void addWeatherCondition(String weatherCondition){
        weather.add(weatherCondition);
    }

    public String getCurrentWeather(){
        String currentWeather = weather.pop();
        addWeatherCondition(currentWeather);
        return currentWeather;
    }

    public void addTemperatureCondition(int temperatureCondition){
        temp.add(temperatureCondition);
    }

    public int getCurrentTemperature(){
        int currentTemperature = temp.pop();
        addTemperatureCondition(currentTemperature);
        return currentTemperature;
    }

    public String getCode() {
        return code;
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
