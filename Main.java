import java.util.Scanner;


public class Main {

    static Scanner sc = new Scanner(System.in);
    static User user;

    static boolean bookingPending = false;
    static Movie pendingMovie = null;
    static String pendingShowTime = null;
    static int pendingSeats = 0;


    static int walletBalance = 0;



    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    static final int WIDTH = 150;

    public static void centerPrint(String text) {
        int len = text.length();
        int pad = (WIDTH - len) / 2;
        System.out.println(" ".repeat(Math.max(0, pad)) + text);
    }

    public static void centerPrintValues(String text) {
        int len = text.length();
        int pad = (WIDTH - len) / 2;
        System.out.print(" ".repeat(Math.max(0, pad)) + text);
    }

    public static void startAnimation() {

        String[] frames = {
                ANSI_CYAN + "Loading Movie Ticket Booking System" + ANSI_RESET,
                ANSI_CYAN + "Loading Movie Ticket Booking System ." + ANSI_RESET,
                ANSI_CYAN + "Loading Movie Ticket Booking System .." + ANSI_RESET,
                ANSI_CYAN + "Loading Movie Ticket Booking System ..." + ANSI_RESET
        };

        for (int i = 0; i < 2; i++) {
            for (String frame : frames) {
                System.out.print("\r");
                System.out.print(" ".repeat(50));
                System.out.print("\r");
                System.out.print(frame);
                try { Thread.sleep(350); } catch (Exception e) {}
            }
        }

        System.out.print("\r");
        System.out.print(" ".repeat(50));
        System.out.print("\r");
    }


    public static void welcomeBannerAnimation() {

        String[] banner = {
                ANSI_YELLOW + "#########################################################################################################" + ANSI_RESET,
                ANSI_YELLOW + "##                                                                                                     ##" + ANSI_RESET,
                ANSI_YELLOW + "##     ███╗   ███╗   ██████╗   ██╗   ██╗  ██╗  ███████╗    ████████╗  ██╗  ███╗   ███╗  ███████╗       ##" + ANSI_RESET,
                ANSI_YELLOW + "##     ████╗ ████║  ██╔═══██╗  ██║   ██║  ██║  ██╔════╝    ╚══██╔══╝  ██║  ████╗ ████║  ██╔════╝       ##" + ANSI_RESET,
                ANSI_YELLOW + "##     ██╔████╔██║  ██║   ██║  ██║   ██║  ██║  █████╗         ██║     ██║  ██╔████╔██║  █████╗         ##" + ANSI_RESET,
                ANSI_YELLOW + "##     ██║╚██╔╝██║  ██║   ██║  ╚██╗ ██╔╝  ██║  ██╔══╝         ██║     ██║  ██║╚██╔╝██║  ██╔══╝         ##" + ANSI_RESET,
                ANSI_YELLOW + "##     ██║ ╚═╝ ██║  ╚██████╔╝   ╚████╔╝   ██║  ███████╗       ██║     ██║  ██║ ╚═╝ ██║  ███████╗       ##" + ANSI_RESET,
                ANSI_YELLOW + "##     ╚═╝     ╚═╝   ╚═════╝     ╚═══╝    ╚═╝  ╚══════╝       ╚═╝     ╚═╝  ╚═╝     ╚═╝  ╚══════╝       ##" + ANSI_RESET,
                ANSI_YELLOW + "##                                                                                                     ##" + ANSI_RESET,
                ANSI_YELLOW + "##                  W E L C O M E   T O   M O V I E   T I C K E T   B O O K I N G                      ##" + ANSI_RESET,
                ANSI_YELLOW + "##                                                                                                     ##" + ANSI_RESET,
                ANSI_YELLOW + "#########################################################################################################" + ANSI_RESET
        };

        centerPrint("");

        for (String line : banner) {
            centerPrint(line);
            try { Thread.sleep(35); } catch (Exception e) {}
        }

        String[] glow = {
                ANSI_RED, ANSI_PURPLE, ANSI_BLUE, ANSI_CYAN, ANSI_GREEN, ANSI_YELLOW
        };

        for (String color : glow) {
            centerPrint(color + ">>>>>>>>>>>>>>>>>>>>>>>  READY TO BOOK YOUR FAVORITE MOVIES  <<<<<<<<<<<<<<<<<<<<<<<" + ANSI_RESET);
            try { Thread.sleep(120); } catch (Exception e) {}
        }

        centerPrint(ANSI_GREEN + "System Loaded Successfully" + ANSI_RESET);
    }


    public static void main (String [] args) {

        boolean valid_option;

        startAnimation();

        welcomeBannerAnimation();

        do {

            centerPrint(ANSI_CYAN+"1.Login"+ANSI_RESET);
            centerPrint(ANSI_CYAN+"2.Sign Up"+ANSI_RESET);
            centerPrintValues(ANSI_GREEN+"Enter the option: "+ANSI_RESET);
            try {
                int a = sc.nextInt();
                valid_option = false;

                if (a == 1) {
                    login("admin","1234");
                }
                else if (a == 2) {
                    Signup();
                } else {
                    centerPrint(ANSI_RED+"Invalid Option"+ANSI_RESET);
                    valid_option = true;
                }
            } catch (Exception e) {
                centerPrint(ANSI_RED+"Invalid Option"+ANSI_RESET);
                sc.nextLine();
                valid_option = true;
            }
        }while(valid_option);

    }
    public static void login(String a, String b){

        centerPrintValues(ANSI_PURPLE+"Enter the USER_NAME:"+ANSI_RESET);
        String LOGIN_USER_NAME = sc.next();

        centerPrintValues(ANSI_PURPLE+"Enter the PASSWORD: "+ANSI_RESET);
        String LOGIN_PASSWORD = sc.next();

        if(LOGIN_USER_NAME.equals(a) && LOGIN_PASSWORD.equals(b)){
            System.out.println();
            centerPrint(ANSI_GREEN+"Login successful!"+ANSI_RESET);
            centerPrint(ANSI_GREEN+"Welcome, "+LOGIN_USER_NAME+"."+ANSI_RESET);
            centerPrint(ANSI_GREEN+"Enjoy booking your favorite movies."+ANSI_RESET);
            Features();
        }
        else if(LOGIN_USER_NAME.equals(user.getUserName()) && LOGIN_PASSWORD.equals(user.getPassword())){
            System.out.println();
            centerPrint(ANSI_GREEN+"Login successful!"+ANSI_RESET);
            centerPrint(ANSI_GREEN+"Welcome, "+LOGIN_USER_NAME+"."+ANSI_RESET);
            centerPrint(ANSI_GREEN+"Enjoy booking your favorite movies."+ANSI_RESET);
            Features();
        }
        else{
            centerPrint(ANSI_RED+"Invalid Login Credentials"+ANSI_RESET);
            login("admin","1234");
        }

    }
    public static void Signup() {
        try {
            System.out.println();
            centerPrintValues(ANSI_BLUE + "Enter your NAME:" + ANSI_RESET);
            String USER_NAME = sc.next();

            centerPrintValues(ANSI_BLUE + "Enter your Gmail: " + ANSI_RESET);
            String GMAIL = sc.next();

            boolean valid_num;

            long MOBILE_NUMBER;

            do {
                centerPrintValues(ANSI_BLUE + "Enter your MOBILE_NUMBER: " + ANSI_RESET);
                MOBILE_NUMBER= sc.nextLong();

                if (MOBILE_NUMBER < 1000000000L || MOBILE_NUMBER > 9999999999L) {
                    centerPrint("Invalid Number, MOBILE_NUMBER must be a 10-digit number");
                    valid_num = true;
                }
                else{
                    valid_num = false;
                }
            }while(valid_num);

            boolean pass;

            do {
                centerPrintValues(ANSI_BLUE + "Enter your PASSWORD: " + ANSI_RESET);
                String PASSWORD = sc.next();

                centerPrintValues(ANSI_BLUE + "Re-Enter your PASSWORD: " + ANSI_RESET);
                String RE_PASSWORD = sc.next();

                if (RE_PASSWORD.equals(PASSWORD)) {
                    pass = false;
                    user = new User(USER_NAME, GMAIL, MOBILE_NUMBER, PASSWORD);
                    centerPrint(ANSI_GREEN + "Your account has been created successfully." + ANSI_RESET);
                    centerPrint(ANSI_GREEN + "Login to access the Movie Ticket Booking System." + ANSI_RESET);
                    login(USER_NAME, PASSWORD);
                } else {
                    pass = true;
                    centerPrint(ANSI_RED + "PASSWORD'S DID NOT MATCH ENTER NEW PASSWORD." + ANSI_RESET);
                }
            } while (pass);
        } catch (Exception e) {
            centerPrint(ANSI_RED + "INVALID_INPUT" + ANSI_RESET);
            sc.nextLine();
            Signup();
        }

    }

    public static class User {

        private final String USER_NAME;
        private final String GMAIL;
        private final long MOBILE_NUMBER;
        private final String PASSWORD;
        public User(String userName, String gmail, long mobileNumber, String password) {
            this.USER_NAME = userName;
            this.GMAIL = gmail;
            this.MOBILE_NUMBER = mobileNumber;
            this.PASSWORD = password;
        }

        public String getUserName() {
            return USER_NAME;
        }

        public String getGmail() {
            return GMAIL;
        }

        public long getMobileNumber() {
            return MOBILE_NUMBER;
        }

        public String getPassword() {
            return PASSWORD;
        }
    }

    public static void Features() {

        boolean log = true;

        while (log) {
            try {
                centerPrint(ANSI_YELLOW + "────────────  FEATURES MENU  ────────────" + ANSI_RESET);

                centerPrint(ANSI_BLUE   + " 1.  Book Movie Tickets" + ANSI_RESET);
                centerPrint(ANSI_PURPLE + " 2.  Movie Reviews & Ratings" + ANSI_RESET);
                centerPrint(ANSI_CYAN   + " 3.  Booking History" + ANSI_RESET);
                centerPrint(ANSI_YELLOW + " 4.  Payment " + ANSI_RESET);
                centerPrint(ANSI_RED    + " 5.  Logout" + ANSI_RESET);

                centerPrint(ANSI_YELLOW + "──────────────────────────────────────────" + ANSI_RESET);
                centerPrintValues(ANSI_GREEN + "Enter your choice: " + ANSI_RESET);

                int select = sc.nextInt();

                switch (select) {

                    case 1:
                        TicketBooking.startBooking();
                        break;

                    case 2:
                        MovieReviewSystem mrs = new MovieReviewSystem();
                        mrs.start();
                        break;

                    case 3:
                        BookingHistory.showHistory();
                        break;

                    case 4:
                        PaymentProcess.pay();
                        break;

                    case 5:
                        centerPrint(ANSI_GREEN + "You have been logged out successfully." + ANSI_RESET);
                        log = false;
                        break;

                    default:
                        centerPrint(ANSI_RED + "Invalid choice! Please select a valid option." + ANSI_RESET);
                }

            } catch (Exception e) {
                centerPrint(ANSI_RED + "INVALID_INPUT" + ANSI_RESET);
                sc.nextLine();
            }
        }
    }

}
