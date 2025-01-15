package letterboxd;


import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Test {
    SocialNetwork socialNetwork = new SocialNetwork();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new Test().start();
    }

    public void start() {
        boolean applicationRunning = true;

        while (applicationRunning) {
            displayMenu();

            try {
                int result = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                switch (result) {
                    case 1 -> addPerson();
                    case 2 -> addMovie();
                    case 3 -> selectAndLikeMovie();
                    case 4 -> addFriendship();
                    case 5 -> removeFriendship();
                    case 6 -> suggestNewFriend();
                    case 7 -> displayFriendshipsBelowAverage();
                    case 8 -> viewPersonDetails();
                    default -> {
                        System.out.println("Application is closed.");
                        applicationRunning = false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
                sc.nextLine(); // Clear the invalid input
            }
        }
    }

    public void displayMenu() {
        System.out.println("""
                Please select one of the following options:
                1. Add person to the social network
                2. Add a movie to the list
                3. Like a movie (select from available movies)
                4. Add friendship between two persons
                5. Remove friendship between two persons
                6. Suggest a new friend
                7. Display friendships with below-average common movies
                8. View person details
                Any other number: Close application
                """);
    }

    private void addPerson() {
        System.out.println("Enter username:");
        String username = sc.nextLine();
        socialNetwork.addUser(username);
        System.out.println("User added successfully.");
    }

    private void addMovie() {
        System.out.println("Enter movie name:");
        String movieName = sc.nextLine();
        socialNetwork.addMovie(movieName);
    }

    private void selectAndLikeMovie() {
        System.out.println("Enter username:");
        String username = sc.nextLine();
        socialNetwork.displayMovies();
        System.out.println("Select a movie by number:");
        int movieIndex = sc.nextInt();
        sc.nextLine(); // Consume the newline character
        socialNetwork.likeMovie(username, movieIndex);
    }

    private void addFriendship() {
        System.out.println("Enter the first username:");
        String username1 = sc.nextLine();
        System.out.println("Enter the second username:");
        String username2 = sc.nextLine();
        socialNetwork.addFriendship(username1, username2);
    }

    private void removeFriendship() {
        System.out.println("Enter the first username:");
        String username1 = sc.nextLine();
        System.out.println("Enter the second username:");
        String username2 = sc.nextLine();
        socialNetwork.removeFriendship(username1, username2);
    }

    private void suggestNewFriend() {
        System.out.println("Enter username:");
        String username = sc.nextLine();
        socialNetwork.suggestNewFriendship(username);
    }

    private void displayFriendshipsBelowAverage() {
        int totalCommonMovies = 0;
        int totalFriendships = 0;

        // Map to store the number of common movies for each friendship
        Map<String, Integer> friendshipCommonMovies = new HashMap<>();

        // Calculate the total number of common movies and the total number of friendships
        for (Person person : socialNetwork.users) {
            for (Person friend : person.friendships) {
                // Avoid counting the same pair twice
                if (person.username.compareTo(friend.username) < 0) {
                    int commonMovies = calculateCommonMovies(person, friend);
                    friendshipCommonMovies.put(person.username + " - " + friend.username, commonMovies);
                    totalCommonMovies += commonMovies;
                    totalFriendships++;
                }
            }
        }

        if (totalFriendships == 0) {
            System.out.println("No friendships to analyze.");
            return;
        }

        double averageCommonMovies = (double) totalCommonMovies / totalFriendships;

        System.out.println("Average number of common liked movies: " + averageCommonMovies);
        System.out.println("Friendships with below-average common liked movies:");

        // Display friendships with below-average common movies
        for (Map.Entry<String, Integer> entry : friendshipCommonMovies.entrySet()) {
            if (entry.getValue() < averageCommonMovies) {
                System.out.println(entry.getKey() + " with " + entry.getValue() + " common movies.");
            }
        }
    }

    private int calculateCommonMovies(Person person1, Person person2) {
        Set<String> commonMovies = new HashSet<>(person1.likedMovies);
        commonMovies.retainAll(person2.likedMovies);
        return commonMovies.size();
    }

    private void viewPersonDetails() {
        System.out.println("Enter username:");
        String username = sc.nextLine();
        Person person = socialNetwork.findUser(username);
        if (person != null) {
            System.out.println(person);
        } else {
            System.out.println("User not found.");
        }
    }
}
