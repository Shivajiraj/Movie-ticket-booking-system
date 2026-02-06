public class BookingHistory extends Main {

    private static String history = "";
    private static final int CONSOLE_WIDTH = 150;

    public static void addHistory(Booking booking) {

        history += ANSI_PURPLE+"-------------------------------\n"+ANSI_RESET;
        history += ANSI_GREEN+"Movie : " + booking.getMovieName() + "\n"+ANSI_RESET;
        history += ANSI_GREEN+"Time  : " + booking.getShowTime() + "\n"+ANSI_RESET;
        history += ANSI_GREEN+"Seats : " + booking.getSeatsBooked() + "\n"+ANSI_RESET;
        history += ANSI_GREEN+"Total : ₹" + booking.calculateAmount() + "\n"+ANSI_RESET;
        history += ANSI_PURPLE+"-------------------------------\n\n"+ANSI_RESET;
    }

    public static void showHistory() {

        if (history.isEmpty()) {
            centerPrint(ANSI_RED+"No booking history found."+Main.ANSI_RESET);
        } else {
            centerPrint(ANSI_CYAN+"========== BOOKING HISTORY =========="+ANSI_RESET);
            System.out.println();
          
            String[] lines = history.split("\n");
            for (String line : lines) {
                centerPrint(line);
            }
        }
    }

    public  static void centerPrint(String text) {

        if (text == null || text.isEmpty()) {
            System.out.println();
            return;
        }

        int padding = (CONSOLE_WIDTH - text.length()) / 2;
        if (padding < 0) padding = 0;

        System.out.println(" ".repeat(padding) + text);
    }
}
