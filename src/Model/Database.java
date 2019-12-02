package Model;

import java.io.IOException;
import java.util.*;

import Model.Comparators.ItineraryComparatorFactory;
import Model.Responses.*;

public class Database {

    private TextFileReader textFileReader;
    private TextFileWriter textFileWriter;
    private List<Itinerary> lastFlightInfo;

    private static String CITIES_FILENAME = "data/cities.txt";
    private static String DELAY_TIMES_FILENAME = "data/delay_times.txt";
    private static String MINIMUM_CONNECTION_TIMES_FILENAME = "data/minimum_connection_times.txt";
    private static String TTA_FLIGHTS_FILENAME = "data/TTA_flights.txt";
    private static String WEATHER_TEMPERATURE_FILENAME = "data/weather_temp.txt";
    private static String FILE_DELIMITER = ",";

    /**
     * file for storing reservations. each line contains one reservation
     * with the following format:
     * passenger;n{;itinerary}{0..n}
     *
     * passenger is the passenger name
     * n is the number of reservations for the passenger
     * itinerary in the format described in the SRS document
     */
    private static String RESERVATION_FILENAME = "data/reservations.txt";
    private static String RESERVATION_FILE_DELIMITER = ";";

    /**
     * maps the airport code to the airport
     */
    private Map<String, Airport> airports;

    /**
     * maps airport code to flights that start at that airport
     */
    private Map<String, List<Flight>> flights;

    /**
     * maps a passenger to their reservations
     */
    private Map<String, List<Reservation>> reservations;

    /**
     * @throws Exception if setting up the database has failed
     */
    public Database() throws Exception{
        uploadDatabase();
    }

    public void saveDatabase() throws IOException {
        String reservationsToSave = "";
        for(String passenger: reservations.keySet()){
            List<Reservation> reservationList = reservations.get(passenger);

            int numReservations = reservationList.size();
            reservationsToSave += passenger + ";" + numReservations;
            if(numReservations != 0){
                reservationsToSave += ";";
            }

            for(int i=0; i<numReservations; i++){
                Reservation reservation = reservationList.get(i);
                reservationsToSave += reservation.getPrice() + "," + reservation.getNumFlights();

                List<Flight> flights = reservation.getFlights();
                if(flights.size() > 0){
                    reservationsToSave += ",";
                }

                for(Flight flight: flights){
                    reservationsToSave += flight.getFlightNum() + "," + flight.getOrigin() +
                            "," + flight.getDepartureTime() + "," + flight.getDestination() +
                            "," + flight.getArrivalTime();
                }

                if(i != numReservations-1){
                    reservationsToSave += ";";
                }
            }

            reservationsToSave += "\n";
        }

        textFileWriter = new TextFileWriter(RESERVATION_FILENAME);
        textFileWriter.writeToTextFile(reservationsToSave);
    }


    private void uploadDatabase() throws Exception{
        uploadAirports();
        uploadFlights();
        uploadReservations();
    }

    /**
     * @throws IOException if parsing the cities file failed
     */
    private void uploadAirports() throws Exception{
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
                String message = "Error: Trying to set weather/temperature of unknown airport " +
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

    private void uploadFlights() throws Exception{
        flights = new HashMap<>();

        textFileReader = new TextFileReader(TTA_FLIGHTS_FILENAME);

        ArrayList<String> lines = textFileReader.readTextFile();

        for(String line: lines){
            String[] lineTokens = line.split(FILE_DELIMITER);
            String origin = lineTokens[0];
            String destination = lineTokens[1];
            Time departureTime = new Time(lineTokens[2]);
            Time arrivalTime = new Time(lineTokens[3]);
            int flightNumber = Integer.parseInt(lineTokens[4]);
            float airFare = Float.parseFloat(lineTokens[5]);

            Flight flight = new Flight(origin, destination, departureTime, arrivalTime, flightNumber, airFare);

            if(flights.containsKey(origin)){
                List<Flight> flightList = flights.get(origin);
                flightList.add(flight);
            }
            else{
                List<Flight> newFlightList = new ArrayList<>();
                newFlightList.add(flight);
                flights.put(origin, newFlightList);
            }
        }
    }

    private void uploadReservations() throws Exception{
        reservations = new HashMap<>();

        textFileReader = new TextFileReader(RESERVATION_FILENAME);
        List<String> lines = textFileReader.readTextFile();

        for(String line: lines){
            String[] lineTokens = line.split(RESERVATION_FILE_DELIMITER);
            String passenger = lineTokens[0];
            int numReservations = Integer.parseInt(lineTokens[1]);

            for(int i=2; i<2+numReservations; i++){
                String[] singleItineraryTokens = lineTokens[i].split(FILE_DELIMITER);
                float itineraryPrice = Float.parseFloat(singleItineraryTokens[0]);
                int numFlights = Integer.parseInt(singleItineraryTokens[1]);

                List<Flight> itineraryFlights = new ArrayList<>();
                for(int j=0; j<numFlights; j++){
                    int offset = 2*j;
                    int flightNumber = Integer.parseInt(singleItineraryTokens[offset+2]);
                    String origin = singleItineraryTokens[offset+3];
                    Time departureTime = new Time(singleItineraryTokens[offset+4]);
                    String destination = singleItineraryTokens[offset+5];
                    Time arrivalTime = new Time(singleItineraryTokens[offset+6]);

                    Flight flight = new Flight(origin, destination, departureTime, arrivalTime, flightNumber);
                    itineraryFlights.add(flight);
                }

                Itinerary currentItinerary = new Itinerary(itineraryPrice, itineraryFlights);
                Reservation currentReservation = new Reservation(passenger, currentItinerary);

                addReservation(currentReservation);
            }

        }
    }

    public FlightInfoResponse getFlightInfo(String origin, String destination, int connections, SortOrder sortOrder){
        if(!airports.containsKey(origin)){
            return new FlightInfoResponse("error,unknown origin", null);
        }

        if(!airports.containsKey(destination)){
            return new FlightInfoResponse("error,unknown destination", null);
        }

        List<Itinerary> itineraries = findAllItineraries(origin, destination, connections);

        Comparator<Itinerary> comparator = ItineraryComparatorFactory.makeComparator(sortOrder);

        Collections.sort(itineraries, comparator);

        lastFlightInfo = itineraries;

        return new FlightInfoResponse("successful", itineraries);
    }

    private List<Flight> getFlights(String origin){
        if(flights.containsKey(origin)){
            return flights.get(origin);
        }
        else{
            return null;
        }
    }


    private List<Itinerary> findAllItineraries(String origin, String destination, int connections){
        List<Flight> flightList = getFlights(origin);
        if(flightList == null){
            return new ArrayList<>();
        }

        List<Itinerary> itineraryList = new ArrayList<>();
        for(Flight flight: flightList){
            Set<Flight> visited = new HashSet<>();
            visited.add(flight);
            List<Flight> path = new ArrayList<>();
            path.add(flight);
            List<Itinerary> itinerariesFound = findItineraryForFlight(flight, destination,
                    visited, connections, path);

            if(itinerariesFound != null){
                itineraryList.addAll(itinerariesFound);
            }
        }

        return itineraryList;
    }

    private List<Itinerary> findItineraryForFlight(Flight currentFlight, String destination,
                                                   Set<Flight> visited, int numConnectionsLeft,
                                                   List<Flight> path){
        String currentAirport = currentFlight.getDestination();
        if(currentAirport.equals(destination) && numConnectionsLeft >= 0){
            Itinerary itinerary = new Itinerary(path);
            List<Itinerary> itineraryList = new ArrayList<>();
            itineraryList.add(itinerary);
            return itineraryList;
        }
        else if(numConnectionsLeft == 0){
            return null;
        }

        List<Itinerary> flightItineraries = new ArrayList<>();
        List<Flight> flightList = getFlights(currentAirport);
        if(flightList != null){
            for(Flight flight: flightList){
                if(!visited.contains(flight) &&
                        flightsCanBeScheduled(currentFlight, flight)){
                    visited.add(flight);
                    path.add(flight);
                    List<Itinerary> itineraryList = findItineraryForFlight(flight, destination, visited, numConnectionsLeft-1, path);

                    if(itineraryList != null){
                        flightItineraries.addAll(itineraryList);
                    }
                    path.remove(flight);
                    visited.remove(flight);
                }
            }
        }

        return flightItineraries;
    }

    private boolean flightsCanBeScheduled(Flight flight1, Flight flight2){
        int minuteIntervalBetweenFlights =
                flight2.getDepartureTime().getTotalMinutes() -
                flight1.getArrivalTime().getTotalMinutes();

        if(minuteIntervalBetweenFlights < 0){
            return false;
        }

        String intermediateAirport = flight1.getDestination();
        if(airports.containsKey(intermediateAirport)){
            Airport airport = airports.get(intermediateAirport);
            int layoverTime = airport.getConnectionTime() + airport.getDelay();
            return minuteIntervalBetweenFlights >= layoverTime;
        }
        else{
            return false;
        }
    }

    public ReservationInfoResponse getReservationInfo(String passenger, String origin, String destination){
        List<Reservation> reservationInfo = new ArrayList<>();
        boolean filterByOrigin = !origin.equals("");
        boolean filterByDestination = !destination.equals("");

        // check for errors
        if(filterByOrigin && !airports.containsKey(origin)) {
            return new ReservationInfoResponse("error, unknown origin", null);
        }
        if(filterByDestination && !airports.containsKey(destination)) {
            return new ReservationInfoResponse("error, unknown destination", null);
        }

        // if no reservations exist, return an empty list
        if(!reservations.containsKey(passenger)){
            return new ReservationInfoResponse("successful", reservationInfo);
        }

        // filter out reservations that don't match the requested origin/destination
        // if applicable
        List<Reservation> passengerReservations = reservations.get(passenger);
        for(Reservation reservation: passengerReservations){
            boolean addReservation = true;
            if(filterByOrigin && !reservation.getOrigin().equals(origin)) {
                addReservation = false;
            }
            if(filterByDestination && !reservation.getDestination().equals(destination)) {
                addReservation = false;
            }
            if(addReservation) {
                reservationInfo.add(reservation);
            }
        }

        //sort by origin
        Collections.sort(reservationInfo);

        return new ReservationInfoResponse("successful", reservationInfo);
    }

    public AirportInfoResponse getAirportInfo(String airportCode){
        if(airports.containsKey(airportCode)){
            Airport airport = airports.get(airportCode);
            return new AirportInfoResponse("successful", airport);
        }
        else{
            return new AirportInfoResponse("error, unknown airport", null);
        }
    }


    public Response reserveFlight(int id, String passenger){
        if(lastFlightInfo == null){
            return new Response("error, must get flight information first");
        }
        else if(id < 0 || id > lastFlightInfo.size()){
            return new Response("error, invalid id");
        }
        else{
            Itinerary itinerary = lastFlightInfo.get(id-1);
            Reservation reservation = new Reservation(passenger, itinerary);
            boolean successful = addReservation(reservation);
            if(successful) {
                return new Response("reserve, successful");
            }
            else {
                return new Response("error, duplicate reservation");
            }
        }
    }

    private boolean addReservation(Reservation reservation){
        String passenger = reservation.getPassenger();
        if(reservations.containsKey(passenger)){
            List<Reservation> reservationList = reservations.get(passenger);
            // don't add reservation if a reservation with the same
            // destination and origin exists
            for(Reservation r : reservationList) {
                if(r.equals(reservation)) {
                    return false;
                }
            }
            reservationList.add(reservation);
        }
        else{
            List<Reservation> newReservationList = new ArrayList<>();
            newReservationList.add(reservation);
            reservations.put(passenger, newReservationList);
        }
        return true;
    }

    public Response deleteReservation(String passenger, String origin, String destination){
        if(!reservations.containsKey(passenger)){
            return new Response("error, reservation not found");
        }

        List<Reservation> passengerReservations = reservations.get(passenger);
        if(passengerReservations.size() == 0){
            return new Response("error, reservation not found");
        }

        int reservationIndex = 0;
        for(Reservation reservation: passengerReservations){
            if(reservation.getOrigin().equals(origin) && reservation.getDestination().equals(destination)){
                break;
            }
            reservationIndex++;
        }

        if(reservationIndex == passengerReservations.size()) {
            return new Response("error, reservation not found");
        }

        passengerReservations.remove(reservationIndex);

        return new Response("delete, successful");
    }
}
