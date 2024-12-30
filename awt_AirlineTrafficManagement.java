import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Flight {
    private String flightId, airlineName, source, destination, departureTime, arrivalTime, status;
    private int totalSeats, seatsAvailable;

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
    }

    public String getFlightId() { return flightId; }
    public String getDetails() {
        return flightId + " | " + airlineName + " | " + source + " -> " + destination + " | Seats: " + seatsAvailable + "/" + totalSeats;
    }

    public boolean bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
            return true;
        }
        return false;
    }
}

public class EnhancedAirlineTrafficManagement extends JFrame {
    private List<Flight> flights = new ArrayList<>();
    private List<String> bookings = new ArrayList<>(); // To store booked tickets
    private JTextArea displayArea;

    public EnhancedAirlineTrafficManagement() {
        setTitle("Airline Traffic Management System");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panels for Tabs
        tabbedPane.add("Home", createHomePanel());
        tabbedPane.add("Add Flight", createAddFlightPanel());
        tabbedPane.add("Book Ticket", createBookTicketPanel());
        tabbedPane.add("View Flights", createViewFlightsPanel());
        tabbedPane.add("View Tickets", createViewTicketsPanel()); // New Tab for Viewing Tickets

        add(tabbedPane, BorderLayout.CENTER);

        // Footer Area for Display
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(Color.LIGHT_GRAY);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Airline Traffic Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.BLUE);

        panel.add(welcomeLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createAddFlightPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Input Fields
        JTextField tfFlightId = new JTextField(15);
        JTextField tfAirlineName = new JTextField(15);
        JTextField tfSource = new JTextField(15);
        JTextField tfDestination = new JTextField(15);
        JTextField tfDeparture = new JTextField(15);
        JTextField tfArrival = new JTextField(15);
        JTextField tfStatus = new JTextField(15);
        JTextField tfTotalSeats = new JTextField(15);

        JButton btnAddFlight = new JButton("Add Flight");
        btnAddFlight.setBackground(Color.GREEN);
        btnAddFlight.setForeground(Color.WHITE);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Flight ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(tfFlightId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Airline Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(tfAirlineName, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Source:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(tfSource, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Destination:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(tfDestination, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Departure Time:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4; panel.add(tfDeparture, gbc);

        gbc.gridx = 0; gbc.gridy = 5; panel.add(new JLabel("Arrival Time:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5; panel.add(tfArrival, gbc);

        gbc.gridx = 0; gbc.gridy = 6; panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1; gbc.gridy = 6; panel.add(tfStatus, gbc);

        gbc.gridx = 0; gbc.gridy = 7; panel.add(new JLabel("Total Seats:"), gbc);
        gbc.gridx = 1; gbc.gridy = 7; panel.add(tfTotalSeats, gbc);

        gbc.gridx = 1; gbc.gridy = 8;
        panel.add(btnAddFlight, gbc);

        // Add Flight Action
        btnAddFlight.addActionListener(e -> {
            try {
                String flightId = tfFlightId.getText();
                String airlineName = tfAirlineName.getText();
                String source = tfSource.getText();
                String destination = tfDestination.getText();
                String departure = tfDeparture.getText();
                String arrival = tfArrival.getText();
                String status = tfStatus.getText();
                int totalSeats = Integer.parseInt(tfTotalSeats.getText());

                flights.add(new Flight(flightId, airlineName, source, destination, departure, arrival, status, totalSeats));
                JOptionPane.showMessageDialog(this, "Flight Added Successfully!");
                tfFlightId.setText(""); tfAirlineName.setText(""); tfSource.setText("");
                tfDestination.setText(""); tfDeparture.setText(""); tfArrival.setText("");
                tfStatus.setText(""); tfTotalSeats.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input! Please check the fields.");
            }
        });

        return panel;
    }

    private JPanel createBookTicketPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField tfPassengerName = new JTextField(15);
        JTextField tfFlightId = new JTextField(15);

        JButton btnBook = new JButton("Book Ticket");
        btnBook.setBackground(Color.BLUE);
        btnBook.setForeground(Color.WHITE);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Passenger Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(tfPassengerName, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Flight ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(tfFlightId, gbc);

        gbc.gridx = 1; gbc.gridy = 2; panel.add(btnBook, gbc);

        btnBook.addActionListener(e -> {
            String passengerName = tfPassengerName.getText();
            String flightId = tfFlightId.getText();

            Flight selectedFlight = flights.stream()
                    .filter(f -> f.getFlightId().equals(flightId))
                    .findFirst().orElse(null);

            if (selectedFlight != null && selectedFlight.bookSeat()) {
                String bookingDetail = "Passenger: " + passengerName + ", Flight ID: " + flightId;
                bookings.add(bookingDetail); // Save booking details
                JOptionPane.showMessageDialog(this, "Ticket Booked Successfully for " + passengerName + "!");
            } else {
                JOptionPane.showMessageDialog(this, "Booking Failed! Either flight not found or no seats available.");
            }
        });

        return panel;
    }

    private JPanel createViewFlightsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea flightsArea = new JTextArea();
        flightsArea.setEditable(false);
        flightsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> {
            flightsArea.setText("Available Flights:\n");
            for (Flight flight : flights) {
                flightsArea.append(flight.getDetails() + "\n");
            }
        });

        panel.add(new JScrollPane(flightsArea), BorderLayout.CENTER);
        panel.add(btnRefresh, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createViewTicketsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea ticketsArea = new JTextArea();
        ticketsArea.setEditable(false);
        ticketsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> {
            ticketsArea.setText("Booked Tickets:\n");
            for (String ticket : bookings) {
                ticketsArea.append(ticket + "\n");
            }
        });

        panel.add(new JScrollPane(ticketsArea), BorderLayout.CENTER);
        panel.add(btnRefresh, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        new EnhancedAirlineTrafficManagement();
    }
}
