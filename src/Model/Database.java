package Model;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.*;

public class Database {

    private TextFileReader textFileReader;
    private ArrayList<Itinerary> lastFlightInfo;

    private static String CITIES_FILENAME = "cities.txt";
    private static String DELAY_TIMES_FILENAME = "delay_times.txt";
    private static String MINIMUM_CONNECTION_TIMES_FILENAME = "minimum_connection_times.txt";
    private static String TTA_FLIGHTS_FILENAME = "TTA_flights.txt";
    private static String WEATHER_TEMPERATURE_FILENAME = "weather_temp.txt";
    private static String FILE_DELIMITER = ",";

    /**
     * maps the airport code to the airport
     */
    private Map<String, Airport> airports;

    /**
     * @throws Exception if setting up the database has failed
     */
    public Database() throws Exception{
        registerAirports();
    }

    /**
     * @throws IOException if parsing the cities file failed
     */
    private void registerAirports() throws Exception{
        airports = new HashMap<>();

        //first get the airport codes and their city names
        textFileReader = new TextFileReader(CITIES_FILENAME);
        for (String line: textFileReader.readTextFile()) {
            String[] lineTokens = line.split(FILE_DELIMITER);
            String airportCode = lineTokens[0];
            String airportCityName = lineTokens[1];

            Airport airport = new Airport(airportCode, airportCityName);
            if (!airports.containsKey(airportCode)) {
                airports.put(airportCode, airport);
            } else {
                String message = "Error: trying to register two airports with the same" +
                        "airport code \"" + airportCode + "\".";
                throw new Exception(message);
            }
        }

        //set airport delay times
        textFileReader = new TextFileReader(DELAY_TIMES_FILENAME);
        for(String line: textFileReader.readTextFile()){
            String[] lineTokens = line.split(FILE_DELIMITER);
            String airportCode = lineTokens[0];
            int delayTime = Integer.parseInt(lineTokens[1]);

            if(airports.containsKey(airportCode)){
                Airport airport = airports.get(airportCode);
                airport.setDelay(delayTime);
            }
            else{
                String message = "Error: Trying to set delay time of unknown airport " +
                        "\"" + airportCode + "\".";
                throw new Exception(message);
            }
        }

        //set airport connection times
        textFileReader = new TextFileReader(MINIMUM_CONNECTION_TIMES_FILENAME);
        for(String line: textFileReader.readTextFile()){
            String[] lineTokens = line.split(FILE_DELIMITER);
            String airportCode = lineTokens[0];
            int connectionTime = Integer.parseInt(lineTokens[1]);

            if(airports.containsKey(airportCode)){
                Airport airport = airports.get(airportCode);
                airport.setConnectionTime(connectionTime);
            }
            else{
                String message = "Error: Trying to set connection time of unknown airport " +
                        "\"" + airportCode + "\".";
                throw new Exception(message);
            }
        }

        //set airport weather and temperature conditions
        textFileReader = new TextFileReader(WEATHER_TEMPERATURE_FILENAME);
        for(String line: textFileReader.readTextFile()){
            String[] lineTokens = line.split(FILE_DELIMITER);
            String airportCode = lineTokens[0];
            Airport airport;
            if(airports.containsKey(airportCode)){
                airport = airports.get(airportCode);
            }
            else{
                String message = "Error: Trying to set connection time of unknown airport " +
                        "\"" + airportCode + "\".";
                throw new Exception(message);
            }

            for(int i=1; i<lineTokens.length; i+=2){
                String weather = lineTokens[i];
                int temperature = Integer.parseInt(lineTokens[i+1]);

                airport.addWeatherCondition(weather);
                airport.addTemperatureCondition(temperature);
            }
        }
    }

    public ArrayList<Itinerary> getLastFlightInfo() {
        return lastFlightInfo;
    }

    public ArrayList<Reservation> getReservationInfo(){
        return null;
    }

    public ArrayList<Airport> getAirportInfo(){
        return null;
    }

    public boolean reserveFlight(){
        return true;
    }

    public boolean deleteReservation(){
        return true;
    }
}
