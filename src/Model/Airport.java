package Model;

public class Airport {

    private String code;
    private String weather;
    private String temp;
    private int delay;

    public Airport(String code, String weather, String temp, int delay){
        this.code = code;
        this.weather = weather;
        this.temp = temp;
        this.delay = delay;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

}
