import java.util.*;

class Flight {
    private String flightId, airlineName, source, destination, departureTime, arrivalTime, status;
    private int totalSeats, seatsAvailable;
    private List<Integer> allocatedSeats;

    public Flight(String flightId, String airlineName, String source, String destination, 
                  String departureTime, String arrivalTime, String status, int totalSeats) {
        this.flightId = flightId;
        this.airlineName = airlineName;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.totalSeats = totalSeats;
        this.seatsAvailable = totalSeats;
        this.allocatedSeats = new ArrayList<>();
    }

    // Getters
    public String getFlightId() { return flightId; }
    public String getAirlineName() { return airlineName; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public String getStatus() { return status; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public int getTotalSeats() { return totalSeats; }

    // Book a seat and return the allocated seat number
    public Integer bookSeat() {
        if (seatsAvailable > 0) {
            int seatNumber = totalSeats - seatsAvailable + 1;
            allocatedSeats.add(seatNumber);
            seatsAvailable--;
            return seatNumber;
        }
        return null; // No seats available
    }

    @Override
    public String toString() {
        return "Flight ID: " + flightId + ", Airline: " + airlineName + ", Source: " + source + 
               ", Destination: " + destination + ", Departure: " + departureTime + 
               ", Arrival: " + arrivalTime + ", Status: " + status + ", Available Seats: " + seatsAvailable +
               "/" + totalSeats;
    }
}

class Booking {
    private String passengerName, flightId;
    private int seatNumber;

    public Booking(String passengerName, String flightId, int seatNumber) {
        this.passengerName = passengerName;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Passenger: " + passengerName + ", Flight ID: " + flightId + ", Seat Number: " + seatNumber;
    }
}

public class AirlineTrafficManagement {
    private static List<Flight> flights = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();

    public static void addFlight(Flight flight) {
        flights.add(flight);
        System.out.println("Flight added successfully!");
    }

    public static void viewFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    public static void bookTicket(String passengerName, String flightId) {
        for (Flight flight : flights) {
            if (flight.getFlightId().equals(flightId)) {
                Integer seatNumber = flight.bookSeat();
                if (seatNumber != null) {
                    bookings.add(new Booking(passengerName, flightId, seatNumber));
                    System.out.println("Ticket booked successfully for " + passengerName + " on flight " + flightId +
                                       " with Seat Number " + seatNumber + "!");
                } else {
                    System.out.println("No seats available on flight " + flightId + "!");
                }
                return;
            }
        }
        System.out.println("Flight ID not found!");
    }

    public static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }
        System.out.println("Booking Details:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Airline Traffic Management System");
        while (true) {
            System.out.println("\n1. Add Flight\n2. View Flights\n3. Book Ticket\n4. View Bookings\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Flight ID: ");
                    String flightId = scanner.nextLine();
                    System.out.print("Enter Airline Name: ");
                    String airlineName = scanner.nextLine();
                    System.out.print("Enter Source Location: ");
                    String source = scanner.nextLine();
                    System.out.print("Enter Destination: ");
                    String destination = scanner.nextLine();
                    System.out.print("Enter Departure Time: ");
                    String departureTime = scanner.nextLine();
                    System.out.print("Enter Arrival Time: ");
                    String arrivalTime = scanner.nextLine();
                    System.out.print("Enter Status: ");
                    String status = scanner.nextLine();
                    System.out.print("Enter Total Seats: ");
                    int totalSeats = scanner.nextInt();

                    Flight flight = new Flight(flightId, airlineName, source, destination, 
                                               departureTime, arrivalTime, status, totalSeats);
                    addFlight(flight);
                    break;

                case 2:
                    viewFlights();
                    break;

                case 3:
                    System.out.print("Enter Passenger Name: ");
                    String passengerName = scanner.nextLine();
                    System.out.print("Enter Flight ID: ");
                    String flightToBook = scanner.nextLine();
                    bookTicket(passengerName, flightToBook);
                    break;

                case 4:
                    viewBookings();
                    break;

                case 5:
                    System.out.println("Exiting system.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
