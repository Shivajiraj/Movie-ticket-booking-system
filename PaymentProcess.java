interface PaymentMethod {
    boolean pay(int amount);
}

public class PaymentProcess extends Main {

    private static boolean bankLinked = false;
    private static boolean upiLinked = false;
    private static boolean cardLinked = false;

    private static int mpin;
    private static int wrongPinCount = 0;
    private static final int DAILY_TOPUP_LIMIT = 50000;


    private static void returnAfterPayment() {
        if (Main.bookingPending) {
            Main.bookingPending = false;
            TicketBooking.resumeBooking();

        } else {
            Features();
        }
    }

    abstract static class AbstractPayment implements PaymentMethod {

        protected boolean verifyMPIN() {

            Main.centerPrint("");
            Main.centerPrint(Main.ANSI_BLUE + "------------------------------------" + Main.ANSI_RESET);
            Main.centerPrint(Main.ANSI_CYAN + "Secure Verification Required" + Main.ANSI_RESET);
            Main.centerPrint(Main.ANSI_BLUE + "------------------------------------" + Main.ANSI_RESET);
            Main.centerPrintValues(Main.ANSI_YELLOW + "Enter MPIN: " + Main.ANSI_RESET);

            int entered = Main.sc.nextInt();

            if (entered != mpin) {
                wrongPinCount++;
                Main.centerPrint(Main.ANSI_RED + "Incorrect MPIN!" + Main.ANSI_RESET);

                if (wrongPinCount >= 3) {
                    Main.centerPrint(
                            Main.ANSI_RED + "Too many attempts! Payment blocked." + Main.ANSI_RESET
                    );
                }
                return false;
            }

            wrongPinCount = 0;
            Main.centerPrint(Main.ANSI_GREEN + "MPIN Verified Successfully" + Main.ANSI_RESET);
            return true;
        }
    }

    static class BankPayment extends AbstractPayment {
        @Override
        public boolean pay(int amount) {
            if (!verifyMPIN()) return false;

            if (walletBalance >= amount) {
                walletBalance -= amount;
                Main.centerPrint(Main.ANSI_GREEN + "Bank Payment Successful!" + Main.ANSI_RESET);
                return true;
            }
            Main.centerPrint(Main.ANSI_RED + "Insufficient Balance!" + Main.ANSI_RESET);
            return false;
        }
    }

    static class UPIPayment extends AbstractPayment {
        @Override
        public boolean pay(int amount) {
            if (!verifyMPIN()) return false;

            if (walletBalance >= amount) {
                walletBalance -= amount;
                Main.centerPrint(Main.ANSI_GREEN + "UPI Payment Successful!" + Main.ANSI_RESET);
                return true;
            }
            Main.centerPrint(Main.ANSI_RED + "Insufficient Balance!" + Main.ANSI_RESET);
            return false;
        }
    }

    static class CardPayment extends AbstractPayment {
        @Override
        public boolean pay(int amount) {
            if (!verifyMPIN()) return false;

            if (walletBalance >= amount) {
                walletBalance -= amount;
                Main.centerPrint(Main.ANSI_GREEN + "Card Payment Successful!" + Main.ANSI_RESET);
                return true;
            }
            Main.centerPrint(Main.ANSI_RED + "Insufficient Balance!" + Main.ANSI_RESET);
            return false;
        }
    }

    public static boolean hasAnyPaymentMethod() {
        return bankLinked || upiLinked || cardLinked;
    }

    public static void pay() {

        while (true) {
            centerPrint(" ");
            centerPrint(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
            centerPrint(ANSI_YELLOW + "            PAYMENT OPTIONS           " + ANSI_RESET);
            centerPrint(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
            centerPrint(" ");

            centerPrint(ANSI_GREEN + "  1.   Bank Account" + ANSI_RESET);
            centerPrint(ANSI_GREEN + "  2.   UPI" + ANSI_RESET);
            centerPrint(ANSI_GREEN + "  3.   Credit Card" + ANSI_RESET);
            centerPrint(ANSI_RED   + "  4.   Back" + ANSI_RESET);

            centerPrint(" ");
            centerPrint(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
            centerPrint(ANSI_CYAN + "   Please select a payment method   " + ANSI_RESET);
            centerPrint(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
            centerPrint(" ");

            centerPrintValues(ANSI_YELLOW + "  Enter Your Option : " + ANSI_RESET);

            int n;
            try {
                n = sc.nextInt();
            } catch (Exception e) {
                centerPrint(ANSI_RED + "Enter numbers only!" + ANSI_RESET);
                sc.next();
                continue;
            }

            switch (n) {
                case 1 -> {
                    if (linkBank()) {
                        returnAfterPayment();
                        return;
                    }
                }
                case 2 -> {
                    if (linkUPI()) {
                        returnAfterPayment();
                        return;
                    }
                }
                case 3 -> {
                    if (linkCard()) {
                        returnAfterPayment();
                        return;
                    }
                }
                case 4 -> {
                    returnAfterPayment();
                    return;
                }
                default -> centerPrint(ANSI_RED + "Invalid Option!" + ANSI_RESET);
            }
        }
    }

    private static boolean linkBank() {

        centerPrint("");
        centerPrint(ANSI_BLUE + "----------- Bank Account Linking -----------" + ANSI_RESET);
        centerPrintValues(ANSI_YELLOW + "Enter Account Holder Name: " + ANSI_RESET);
        sc.nextLine();
        sc.nextLine();

        centerPrintValues(ANSI_YELLOW + "Enter Bank Account Number: " + ANSI_RESET);
        long accNo = sc.nextLong();

        if (String.valueOf(accNo).length() < 10) {
            centerPrint(ANSI_RED + "Invalid Account Number!" + ANSI_RESET);
            return false;
        }

        centerPrintValues(ANSI_YELLOW + "Enter IFSC Code: " + ANSI_RESET);
        String ifsc = sc.next().toUpperCase();

        if (!ifsc.matches("[A-Z]{3,6}[0-9]{3,7}")) {
            centerPrint(ANSI_RED + "Invalid IFSC Code!" + ANSI_RESET);
            return false;
        }

        setMPIN();
        bankLinked = true;

        centerPrint(ANSI_GREEN + "Bank Account Linked Successfully!" + ANSI_RESET);
        return true;
    }

    private static boolean linkUPI() {

        centerPrint("");
        centerPrint(ANSI_BLUE + "----------- UPI Linking -----------" + ANSI_RESET);
        centerPrintValues(ANSI_YELLOW + "Enter UPI ID: " + ANSI_RESET);
        String upi = sc.next();

        if (!upi.contains("@")) {
            centerPrint(ANSI_RED + "Invalid UPI ID!" + ANSI_RESET);
            return false;
        }

        setMPIN();
        upiLinked = true;
        centerPrint(ANSI_GREEN + "UPI Linked Successfully!" + ANSI_RESET);
        return true;
    }

    private static boolean linkCard() {

        centerPrint("");
        centerPrint(ANSI_BLUE + "----------- Credit Card Linking -----------" + ANSI_RESET);
        centerPrintValues(ANSI_YELLOW + "Enter Card Number (16 Digit's): " + ANSI_RESET);
        long card = sc.nextLong();

        if (String.valueOf(card).length() != 16) {
            centerPrint(ANSI_RED + "Invalid Card Number!" + ANSI_RESET);
            return false;
        }

        centerPrintValues(ANSI_YELLOW + "Enter CVV: " + ANSI_RESET);
        int cvv = sc.nextInt();

        if (cvv < 100 || cvv > 999) {
            centerPrint(ANSI_RED + "Invalid CVV!" + ANSI_RESET);
            return false;
        }
        setMPIN();

        cardLinked = true;
        centerPrint(ANSI_GREEN + "Credit Card Linked Successfully!" + ANSI_RESET);
        return true;


    }

    private static void setMPIN() {
        centerPrintValues(ANSI_YELLOW + "Set 4-digit MPIN: " + ANSI_RESET);
        mpin = sc.nextInt();
        centerPrint(ANSI_GREEN + "MPIN Set Successfully!" + ANSI_RESET);
    }

    public static int TopUp() {

        AbstractPayment temp = new BankPayment();
        if (!temp.verifyMPIN()) return 0;

        centerPrint(" ");
        centerPrint(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        centerPrint(ANSI_YELLOW + "             WALLET TOP-UP             " + ANSI_RESET);
        centerPrint(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        centerPrint(" ");

        centerPrint(ANSI_CYAN + "  Current Balance  : " + ANSI_RESET + "₹" + walletBalance);

        centerPrint(" ");
        centerPrint(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        centerPrintValues(ANSI_YELLOW + "  Enter Amount to Add : ₹" + ANSI_RESET);


        int amount = sc.nextInt();
        if (amount <= 0 || amount > DAILY_TOPUP_LIMIT) {
            centerPrint(ANSI_RED + "Invalid Amount!" + ANSI_RESET);
            return 0;
        }

        walletBalance += amount;
        centerPrint(ANSI_GREEN + "Top-Up Successful!" + ANSI_RESET);
        return amount;
    }

    public static boolean proceedPayment(int ticketAmount, int addedAmount) {

        centerPrint(" ");
        centerPrint(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        centerPrint(ANSI_YELLOW + "             PAYMENT SUMMARY             " + ANSI_RESET);
        centerPrint(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        centerPrint(" ");

        centerPrint(ANSI_CYAN + "  Ticket Cost      : " + ANSI_RESET + "₹" + ticketAmount);
        centerPrint(ANSI_CYAN + "  Wallet Balance   : " + ANSI_RESET + "₹" + walletBalance);

        centerPrint(" ");
        centerPrint(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        centerPrintValues(ANSI_YELLOW + "  Proceed with payment? (yes / no) : " + ANSI_RESET);


        sc.nextLine();
        String choice = sc.nextLine();
        if (!choice.equalsIgnoreCase("yes")) return false;

        PaymentMethod paymentMethod;

        if (bankLinked) paymentMethod = new BankPayment();
        else if (upiLinked) paymentMethod = new UPIPayment();
        else if (cardLinked) paymentMethod = new CardPayment();
        else {
            centerPrint(ANSI_RED + "No payment method linked!" + ANSI_RESET);
            return false;
        }

        return paymentMethod.pay(ticketAmount);
    }
}
