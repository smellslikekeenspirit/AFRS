package Model;

import java.io.IOException;
import java.util.*;

import Model.Comparators.ItineraryComparatorFactory;
import Model.Comparators.SortOrder;
import Model.Responses.*;

/**
 * Database responsible for loading and saving data, and executing user requests
 */
public class Database {

    /**
     * the text file reader for this database
     */
    private TextFileReader textFileReader;

    /**
     * the text file writer for this database
     */
    private TextFileWriter textFileWriter;

    /**
     * the last flight information that was requested by the user
     */
    private List<Itinerary> lastFlightInfo;

    /**
     * each line stores:
     * three-letter-airport-code,city-name
     */
    private String CITIES_FILENAME = "cities.txt";

    /**
     * each line stores:
     * three-letter-airport-code, current-average-delay-time-in-minutes
     */
    private String DELAY_TIMES_FILENAME = "delay_times.txt";

    /**
     * each line stores:
     * three-letter-airport-code,minimum-connection-time-in-minutes
     */
    private String MINIMUM_CONNECTION_TIMES_FILENAME = "minimum_connection_times.txt";

    /**
     * each line stores:
     * origin-airport-code,destination-airport-code,departure-time, arrival-time,flight-number,airfare
     */
    private String TTA_FLIGHTS_FILENAME = "TTA_flights.txt";

    /**
     * each line stores:
     * three-letter-airport-code{,weather,temperature}{1..n}
     */
    private String WEATHER_TEMPERATURE_FILENAME = "weather_temp.txt";

    /**
     * default delimiter for separating tokens in the text files
     */
    private String FILE_DELIMITER = ",";

    /**
     * file for storing reservations. each line contains one reservation
     * with the following format:
     * passenger;n{;itinerary}{0..n}
     *
     * passenger is the passenger name
     * n is the number of reservations for the passenger
     * itinerary in the format described in the SRS document
     */
    private String RESERVATION_FILENAME = "reservations.txt";

    /**
     * the delimiter for separating tokens in RESERVATION_FILENAME
     */
    private String RESERVATION_FILE_DELIMITER = ";";

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
     * @param path the path the data files are stored in
     * @throws Exception if setting up the database has failed
     */
    public Database(String path) throws Exception{
        CITIES_FILENAME = path + CITIES_FILENAME;
        DELAY_TIMES_FILENAME = path + DELAY_TIMES_FILENAME;
        MINIMUM_CONNECTION_TIMES_FILENAME = path + MINIMUM_CONNECTION_TIMES_FILENAME;
        RESERVATION_FILENAME = path + RESERVATION_FILENAME;
        TTA_FLIGHTS_FILENAME = path + TTA_FLIGHTS_FILENAME;
        WEATHER_TEMPERATURE_FILENAME = path + WEATHER_TEMPERATURE_FILENAME;
        uploadDatabase();
    }

    /**
     * saves the reservations to the database
     * @throws IOException if the saving has failed
     */
    public void saveDatabase() throws IOException {
        String reservationsToSave = "";

        //save reservations for each passenger
        for(String passenger: reservations.keySet()){
            List<Reservation> reservationList = reservations.get(passenger);

            int numReservations = reservationList.size();
            reservationsToSave += passenger + RESERVATION_FILE_DELIMITER + numReservations;
            if(numReservations != 0){
                reservationsToSave += RESERVATION_FILE_DELIMITER;
            }

            for(int i=0; i<numReservations; i++){
                Reservation reservation = reservationList.get(i);
                reservationsToSave += reservation.getPrice() + FILE_DELIMITER + reservation.getNumFlights();

                List<Flight> flights = reservation.getFlights();
                if(flights.size() > 0){
                    reservationsToSave += FILE_DELIMITER;
                }

                int numFlights = flights.size();
                for(int j=0; j<numFlights; j++){
                    Flight flight = flights.get(j);
                    reservationsToSave += flight.getFlightNum() + FILE_DELIMITER + flight.getOrigin() +
                            FILE_DELIMITER + flight.getDepartureTime() + FILE_DELIMITER + flight.getDestination() +
                            FILE_DELIMITER + flight.getArrivalTime();

                    if(j != numFlights-1){
                        reservationsToSave += FILE_DELIMITER;
                    }
                }

                if(i != numReservations-1){
                    reservationsToSave += RESERVATION_FILE_DELIMITER;
                }
            }

            reservationsToSave += "\n";
        }

        textFileWriter = new TextFileWriter(RESERVATION_FILENAME);
        textFileWriter.writeToTextFile(reservationsToSave);
    }


    /**
     * uploads data from the text files to this database
     * @throws Exception if the uploading failed
     */
    private void uploadDatabase() throws Exception{
        uploadAirports();
        uploadFlights();
        uploadReservations();
    }

    /**
     * uploads airport information from CITIES_FILENAME, DELAY_TIMES_FILENAME,
     * MINIMUM_CONNECTION_TIMES_FILENAME, and WEATHER_TEMPERATURE_FILENAME
     *
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


    /**
     * uploads flight information from TTA_FLIGHTS_FILENAME
     * @throws Exception
     */
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


    /**
     * uploads reservations from RESERVATION_FILENAME
     * @throws Exception if unable to upload reservations
     */
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
                    int offset = 5*j;
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


    /**
     * gets a list of flight itineraries from the given origin to given destination
     *
     * @param origin the origin airport
     * @param destination the destination airport
     * @param connections max number of additional flights to take from origin to
     *                    destination. e.g., a connection of zero can have a flight
     *                    path of A-B, while a connection of one can have a flight
     *                    path of A-B-C
     * @param sortOrder the order to sort all the possible itineraries by
     * @return a message on success/failure of getting flight information and a list
     *      of itineraries, if successful (null if failed)
     */
    public FlightInfoResponse getFlightInfo(String origin, String destination, int connections, SortOrder sortOrder){
        if(!airports.containsKey(origin)){
            return new FlightInfoResponse("error,unknown origin", null);
        }

        if(!airports.containsKey(destination)){
            return new FlightInfoResponse("error,unknown destination", null);
        }

        List<Itinerary> itineraries = findAllItineraries(origin, destination, connections);

        ItineraryComparatorFactory itineraryComparatorFactory = new ItineraryComparatorFactory();
        Comparator<Itinerary> comparator = itineraryComparatorFactory.makeComparator(sortOrder);

        Collections.sort(itineraries, comparator);

        lastFlightInfo = itineraries;

        return new FlightInfoResponse("successful", itineraries);
    }


    /**
     * @param origin the given airport code
     * @return the flights that start at the given airport code
     */
    private List<Flight> getFlights(String origin){
        if(flights.containsKey(origin)){
            return flights.get(origin);
        }
        else{
            return null;
        }
    }


    /**
     * finds all itineraries that start at the origin and end at the destination
     * @param origin origin airport code
     * @param destination destination airport code
     * @param connections make connections that can be made
     * @return all itineraries that start at the origin and end at the destination
     */
    private List<Itinerary> findAllItineraries(String origin, String destination, int connections){
        List<Flight> flightList = getFlights(origin);
        if(flightList == null){
            return new ArrayList<>();
        }

        List<Itinerary> itineraryList = new ArrayList<>();
        for(Flight flight: flightList){
            Set<String> visited = new HashSet<>();
            visited.add(flight.getOrigin());
            visited.add(flight.getDestination());
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


    /**
     * finds an itinerary from a flight to the destination airport
     * @param currentFlight the flight to start from
     * @param destination the destination airport
     * @param visited set of airport codes that have already been traversed
     * @param numConnectionsLeft the number of connections left that can be made
     * @param path the path of flights that have been taken so far
     * @return the list of itineraries that can be taken to the destination, given
     *      the currentFlight is taken
     */
    private List<Itinerary> findItineraryForFlight(Flight currentFlight, String destination,
                                                   Set<String> visited, int numConnectionsLeft,
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
                String newDestination = flight.getDestination();
                if(!visited.contains(newDestination) &&
                        flightsCanBeScheduled(currentFlight, flight)){
                    visited.add(newDestination);
                    path.add(flight);
                    List<Itinerary> itineraryList = findItineraryForFlight(flight, destination,
                            visited, numConnectionsLeft-1, path);

                    if(itineraryList != null){
                        flightItineraries.addAll(itineraryList);
                    }
                    path.remove(flight);
                    visited.remove(newDestination);
                }
            }
        }

        return flightItineraries;
    }


    /**
     * Two flights can be scheduled together if...
     * -the first flight's arrival time is before the second flight's
     *  departure time
     * -the time between the first flight's arrival time and the second
     *  flight's departure time is greater than or equal to the delay+connection
     *  time of the airport shared between the two flights
     * @param flight1 the first flight
     * @param flight2 the second flight
     * @return if flight1 and flight2 can be scheduled together
     */
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


    /**
     * gets reservation information of the passenger, filtering the reservations
     * by origin and/or destination (if given)
     * @param passenger the passenger whose reservations are to be retrieved
     * @param origin the origin to filter by
     * @param destination the destination to filter by
     * @return a message about success/failure and a list of reservations/null, respectively
     */
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


    /**
     * @param airportCode airport code of the airport of interest
     * @return error/success message with airport of interest/null, respectively
     */
    public AirportInfoResponse getAirportInfo(String airportCode){
        if(airports.containsKey(airportCode)){
            Airport airport = airports.get(airportCode);
            return new AirportInfoResponse("successful", airport);
        }
        else{
            return new AirportInfoResponse("error, unknown airport", null);
        }
    }


    /**
     * reserves a flight for the given passenger
     * @param id 1-index position of the itinerary shown during the
     *           last get flight information request
     * @param passenger the passenger to reserve the flight for
     * @return message of whether or not the response was successful
     */
    public Response reserveFlight(int id, String passenger){
        if(lastFlightInfo == null){
            return new Response("error, must get flight information first");
        }
        else if(id <= 0 || id > lastFlightInfo.size()){
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


    /**
     * tries to add a reservation for a passenger. a reservation can
     * only be added if the origin/destination of the reservation is not
     * already the origin/destination of another reservation
     *
     * @param reservation the reservation to add
     *
     * @return whether or not the reservation was added or not
     */
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


    /**
     * deletes a reservation for a passenger. the reservation
     * must start at the origin and end at the destination
     *
     * @param passenger the passenger to delete the reservation for
     * @param origin the origin airport of the reservation's itinerary
     * @param destination the destination airport of the reservation's itinerary
     *
     * @return message of success/failure to delete the reservation
     */
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
