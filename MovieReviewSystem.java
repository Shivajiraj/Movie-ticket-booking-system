import java.util.*;

class Reviews {

    private String name;
    private String[] reviews;
    private int[] ratings;
    private int reviewCount;

    private static final int MAX_REVIEWS = 50;

    public Reviews(String name) {
        this.name = name;
        reviews = new String[MAX_REVIEWS];
        ratings = new int[MAX_REVIEWS];
        reviewCount = 0;

        if (name.equalsIgnoreCase("Salaar 2")) {
            addDefaultReview("High voltage action scenes", 5);
            addDefaultReview("Prabhas mass performance", 4);
        } else if (name.equalsIgnoreCase("The Paradise")) {
            addDefaultReview("Unique concept and visuals", 4);
            addDefaultReview("Engaging storytelling", 4);
        } else if (name.equalsIgnoreCase("OG")) {
            addDefaultReview("Stylish presentation", 5);
            addDefaultReview("Powerful BGM", 4);
        } else if (name.equalsIgnoreCase("Peddi")) {
            addDefaultReview("Family entertainer", 4);
            addDefaultReview("Good emotions", 3);
        } else if (name.equalsIgnoreCase("The Raja Saab")) {
            addDefaultReview("Horror comedy done well", 4);
            addDefaultReview("Entertaining second half", 4);
        } else if (name.equalsIgnoreCase("Akhanda 2: Thaandavam")) {
            addDefaultReview("Balayya mass", 5);
            addDefaultReview("Power packed dialogues", 5);
        }
    }

    private void addDefaultReview(String review, int rating) {
        reviews[reviewCount] = review;
        ratings[reviewCount] = rating;
        reviewCount++;
    }

    public void addReview(String review, int rating) {
        if (review.isBlank()) {
            Main.centerPrint(Main.ANSI_RED + "Review cannot be empty!" + Main.ANSI_RESET);
            return;
        }

        if (rating < 1 || rating > 5) {
            Main.centerPrint(Main.ANSI_RED + "Rating must be between 1 and 5!" + Main.ANSI_RESET);
            return;
        }

        if (reviewCount < MAX_REVIEWS) {
            reviews[reviewCount] = review;
            ratings[reviewCount] = rating;
            reviewCount++;
            Main.centerPrint(Main.ANSI_GREEN + "Review added successfully!" + Main.ANSI_RESET);
        } else {
            Main.centerPrint(Main.ANSI_RED + "Maximum reviews reached." + Main.ANSI_RESET);
        }
    }

    public void viewReviews() {
        if (reviewCount == 0) {
            Main.centerPrint(Main.ANSI_YELLOW + "No reviews available yet." + Main.ANSI_RESET);
            return;
        }

        Main.centerPrint("");
        Main.centerPrint(Main.ANSI_BLUE + "----------------------------------------" + Main.ANSI_RESET);
        Main.centerPrint(Main.ANSI_YELLOW + "Reviews for " + name + Main.ANSI_RESET);
        Main.centerPrint(Main.ANSI_BLUE + "----------------------------------------" + Main.ANSI_RESET);

        int totalRating = 0;

        for (int i = 0; i < reviewCount; i++) {
            Main.centerPrint(
                    Main.ANSI_CYAN + (i + 1) + ". " +
                            "\"" + reviews[i] + "\" | Rating: " +
                            ratings[i] + "/5" +
                            Main.ANSI_RESET
            );
            totalRating += ratings[i];
        }

        double avgRating = (double) totalRating / reviewCount;

        Main.centerPrint("");
        Main.centerPrint(
                Main.ANSI_GREEN + "Average Rating: " +
                        String.format("%.1f", avgRating) + "/5" +
                        Main.ANSI_RESET
        );
    }

    public String getName() {
        return name;
    }
}

public class MovieReviewSystem {

    public static void main(String[] args) {
        new MovieReviewSystem().start();
    }

    public void start() {

        Scanner sc = new Scanner(System.in);

        Reviews[] movies = {
                new Reviews("Salaar 2"),
                new Reviews("The Paradise"),
                new Reviews("OG"),
                new Reviews("Peddi"),
                new Reviews("The Raja Saab"),
                new Reviews("Akhanda 2: Thaandavam")
        };

        boolean running = true;

        while (running) {
            try {
                Main.centerPrint("");
                Main.centerPrint(Main.ANSI_BLUE + "========================================" + Main.ANSI_RESET);
                Main.centerPrint(Main.ANSI_YELLOW + "         MOVIE REVIEW SYSTEM         " + Main.ANSI_RESET);
                Main.centerPrint(Main.ANSI_BLUE + "========================================" + Main.ANSI_RESET);
                Main.centerPrint("");

                for (int i = 0; i < movies.length; i++) {
                    Main.centerPrint(Main.ANSI_CYAN + (i + 1) + ". " + movies[i].getName() + Main.ANSI_RESET);
                }

                Main.centerPrint(Main.ANSI_CYAN + (movies.length + 1) + ". Back" + Main.ANSI_RESET);
                Main.centerPrint("");
                Main.centerPrintValues(Main.ANSI_YELLOW + "Enter your choice: " + Main.ANSI_RESET);

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == movies.length + 1) {
                    running = false;
                    Main.Features();
                    break;
                }

                if (choice < 1 || choice > movies.length) {
                    Main.centerPrint(Main.ANSI_RED + "Invalid choice!" + Main.ANSI_RESET);
                    continue;
                }

                Reviews selectedMovie = movies[choice - 1];
                boolean movieMenu = true;

                while (movieMenu) {
                    Main.centerPrint("");
                    Main.centerPrint(Main.ANSI_BLUE + "----------------------------------------" + Main.ANSI_RESET);
                    Main.centerPrint(Main.ANSI_YELLOW + selectedMovie.getName() + Main.ANSI_RESET);
                    Main.centerPrint(Main.ANSI_BLUE + "----------------------------------------" + Main.ANSI_RESET);

                    Main.centerPrint(Main.ANSI_PURPLE+"1. Add Review"+Main.ANSI_RESET);
                    Main.centerPrint(Main.ANSI_BLUE+"2. View Reviews"+Main.ANSI_RESET);
                    Main.centerPrint(Main.ANSI_GREEN+"3. Back"+Main.ANSI_RESET);
                    Main.centerPrint("");
                    Main.centerPrintValues(Main.ANSI_YELLOW + "Choose option: " + Main.ANSI_RESET);

                    int option = sc.nextInt();
                    sc.nextLine();

                    switch (option) {
                        case 1:
                            Main.centerPrintValues(Main.ANSI_CYAN + "Enter your review: " + Main.ANSI_RESET);
                            String review = sc.nextLine();

                            Main.centerPrintValues(Main.ANSI_CYAN + "Enter rating (1-5): " + Main.ANSI_RESET);
                            int rating = sc.nextInt();
                            sc.nextLine();

                            selectedMovie.addReview(review, rating);
                            break;

                        case 2:
                            selectedMovie.viewReviews();
                            break;

                        case 3:
                            movieMenu = false;
                            break;

                        default:
                            Main.centerPrint(Main.ANSI_RED + "Invalid option!" + Main.ANSI_RESET);
                    }
                }

            } catch (InputMismatchException e) {
                Main.centerPrint(Main.ANSI_RED + "Invalid input! Enter numbers only." + Main.ANSI_RESET);
                sc.nextLine();
            }
        }

        sc.close();
    }
}
