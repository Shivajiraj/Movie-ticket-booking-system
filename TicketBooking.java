import java.util.Scanner;
import java.util.InputMismatchException;

class Movie {
    private String name;
    private int price;
    private int totalSeats;
    private int bookedSeats = 0;

    public Movie(String name, int price, int totalSeats) {
        this.name = name;
        this.price = price;
        this.totalSeats = totalSeats;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats;
    }

    public boolean bookSeats(int seats) {
        if (seats > 0 && seats <= getAvailableSeats()) {
            bookedSeats += seats;
            return true;
        }
        return false;
    }
}


class Booking {
    private Movie movie;
    private String showTime;
    private int seatsBooked;

    public Booking(Movie movie, String showTime, int seatsBooked) {
        this.movie = movie;
        this.showTime = showTime;
        this.seatsBooked = seatsBooked;
    }

    public int calculateAmount() {
        return seatsBooked * movie.getPrice();
    }

    public void printSummary() {
        Main.centerPrint("");
        Main.centerPrint(Main.ANSI_BLUE + "========================================" + Main.ANSI_RESET);
        Main.centerPrint(Main.ANSI_YELLOW + "            BOOKING SUMMARY            " + Main.ANSI_RESET);
        Main.centerPrint(Main.ANSI_BLUE + "========================================" + Main.ANSI_RESET);

        Main.centerPrint(Main.ANSI_CYAN + "Movie : " + Main.ANSI_RESET + movie.getName());
        Main.centerPrint(Main.ANSI_CYAN + "Show Time : " + Main.ANSI_RESET + showTime);
        Main.centerPrint(Main.ANSI_CYAN + "Seats Booked : " + Main.ANSI_RESET + seatsBooked);
        Main.centerPrint(Main.ANSI_CYAN + "Ticket Price : " + Main.ANSI_RESET + "₹" + movie.getPrice());
        Main.centerPrint(Main.ANSI_GREEN + "Total Amount : ₹" + calculateAmount() + Main.ANSI_RESET);

        Main.centerPrint(Main.ANSI_BLUE + "========================================" + Main.ANSI_RESET);
    }

    private void centerPrint(String s) {
    }

    public String getMovieName() {
        return movie.getName();
    }

    public String getShowTime() {
        return showTime;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }
}

class TicketBooking {


    public static void resumeBooking() {

        Movie selectedMovie = Main.pendingMovie;
        String showTime = Main.pendingShowTime;
        int seats = Main.pendingSeats;

        Booking booking = new Booking(selectedMovie, showTime, seats);
        booking.printSummary();

        int ticketAmount = booking.calculateAmount();
        int addedAmount = 0;

        if (Main.walletBalance < ticketAmount) {
            Main.centerPrint("");
            Main.centerPrint(Main.ANSI_YELLOW + "Insufficient balance! Please top up." + Main.ANSI_RESET);
            addedAmount = PaymentProcess.TopUp();
        }

        boolean paymentSuccess =
                PaymentProcess.proceedPayment(ticketAmount, addedAmount);

        if (paymentSuccess) {

            selectedMovie.bookSeats(seats);
            BookingHistory.addHistory(booking);

            Main.centerPrint("");
            Main.centerPrint(Main.ANSI_GREEN + "Ticket Booked Successfully!" + Main.ANSI_RESET);
            Main.Features();
        }

        Main.pendingMovie = null;
        Main.pendingShowTime = null;
        Main.pendingSeats = 0;
    }


    private static final Scanner sc = new Scanner(System.in);

    private static final Movie[] movies = {
            new Movie("Salaar 2", 220, 100),
            new Movie("The Paradise", 180, 120),
            new Movie("OG", 250, 90),
            new Movie("Peddi", 160, 110),
            new Movie("The Raja Saab", 200, 100),
            new Movie("Akhanda 2: Thaandavam", 240, 95)
    };

    public static void startBooking() {

        while (true) {

            displayMovies();
            int movieChoice;

            while (true) {
                try {
                    Main.centerPrint("");
                    Main.centerPrintValues(Main.ANSI_YELLOW + "Select Movie: " + Main.ANSI_RESET);
                    movieChoice = sc.nextInt();

                    if (movieChoice < 1 || movieChoice > movies.length) {
                        Main.centerPrint(Main.ANSI_RED + "Invalid movie number!" + Main.ANSI_RESET);
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    Main.centerPrint(Main.ANSI_RED + "Enter numbers only!" + Main.ANSI_RESET);
                    sc.nextLine();
                }
            }

            Movie selectedMovie = movies[movieChoice - 1];
            String showTime = selectShowTime();

            Main.centerPrint("");
            Main.centerPrint(
                    Main.ANSI_CYAN + "Available Seats: " +
                            selectedMovie.getAvailableSeats() +
                            Main.ANSI_RESET
            );

            int seats;
            while (true) {
                try {
                    Main.centerPrintValues(Main.ANSI_YELLOW + "Enter seats to book: " + Main.ANSI_RESET);
                    seats = sc.nextInt();

                    if (seats <= 0 || seats > selectedMovie.getAvailableSeats()) {
                        Main.centerPrint(Main.ANSI_RED + "Invalid seat count!" + Main.ANSI_RESET);
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    Main.centerPrint(Main.ANSI_RED + "Enter numeric value!" + Main.ANSI_RESET);
                    sc.nextLine();
                }
            }

            Booking booking = new Booking(selectedMovie, showTime, seats);
            booking.printSummary();

            int ticketAmount = booking.calculateAmount();
            int addedAmount = 0;

            if (!PaymentProcess.hasAnyPaymentMethod()) {
                Main.centerPrint("");
                Main.centerPrint(Main.ANSI_YELLOW + "No payment method found. Please add one." + Main.ANSI_RESET);

                Main.pendingMovie = selectedMovie;
                Main.pendingShowTime = showTime;
                Main.pendingSeats = seats;

                Main.bookingPending = true;
                PaymentProcess.pay();
                return;

            }

            if (Main.walletBalance < ticketAmount) {
                Main.centerPrint("");
                Main.centerPrint(Main.ANSI_YELLOW + "Insufficient balance! Please top up." + Main.ANSI_RESET);
                addedAmount = PaymentProcess.TopUp();
            }

            boolean paymentSuccess =
                    PaymentProcess.proceedPayment(ticketAmount, addedAmount);

            if (paymentSuccess) {

                selectedMovie.bookSeats(seats);
                BookingHistory.addHistory(booking);

                Main.centerPrint("");
                Main.centerPrint(Main.ANSI_GREEN + "Ticket Booked Successfully!" + Main.ANSI_RESET);
                Main.centerPrint(
                        Main.ANSI_CYAN +
                                "Remaining Seats: " +
                                selectedMovie.getAvailableSeats() +
                                Main.ANSI_RESET
                );

                Main.Features();
                return;

            } else {
                Main.centerPrint(Main.ANSI_RED + "Payment Failed! Try again." + Main.ANSI_RESET);
            }
        }
    }

    private static void displayMovies() {
        Main.centerPrint("");
        Main.centerPrint(Main.ANSI_BLUE + "========================================" + Main.ANSI_RESET);
        Main.centerPrint(Main.ANSI_YELLOW + "          AVAILABLE MOVIES          " + Main.ANSI_RESET);
        Main.centerPrint(Main.ANSI_BLUE + "========================================" + Main.ANSI_RESET);

        for (int i = 0; i < movies.length; i++) {
            Main.centerPrint(
                    Main.ANSI_CYAN + (i + 1) + ". " +
                            movies[i].getName() +
                            " | Price: ₹" +
                            movies[i].getPrice() +
                            Main.ANSI_RESET
            );
        }
    }

    private static String selectShowTime() {
        while (true) {
            try {
                Main.centerPrint(" ");
                Main.centerPrint(Main.ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + Main.ANSI_RESET);
                Main.centerPrint(Main.ANSI_YELLOW + "          SELECT SHOW TIME          " + Main.ANSI_RESET);
                Main.centerPrint(Main.ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + Main.ANSI_RESET);
                Main.centerPrint(" ");

                Main.centerPrint(Main.ANSI_GREEN + "  1.   Morning" + Main.ANSI_RESET);
                Main.centerPrint(Main.ANSI_GREEN + "  2.   Afternoon" + Main.ANSI_RESET);
                Main.centerPrint(Main.ANSI_GREEN + "  3.   Evening" + Main.ANSI_RESET);

                Main.centerPrint(" ");
                Main.centerPrint(Main.ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + Main.ANSI_RESET);
                Main.centerPrintValues(Main.ANSI_YELLOW + "  Enter Your Choice : " + Main.ANSI_RESET);


                int choice = sc.nextInt();
                switch (choice) {
                    case 1: return "Morning";
                    case 2: return "Afternoon";
                    case 3: return "Evening";
                    default:
                        Main.centerPrint(Main.ANSI_RED + "Invalid option!" + Main.ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                Main.centerPrint(Main.ANSI_RED + "Enter numbers only!" + Main.ANSI_RESET);
                sc.nextLine();
            }
        }
    }
}
