package letterboxd;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Menus {
    Letterboxd socialNetwork = new Letterboxd();
    Scanner sc = new Scanner(System.in);

    Menus(){
    	start();
    }

    public void start() {
        boolean applicationRunning = true;
        
        socialNetwork.addMovie("Munich - The Edge of War");
        socialNetwork.addMovie("Bridge of Spies");
        socialNetwork.addMovie("The Spy Who Came in from the Cold");
        socialNetwork.addMovie("The Imitation Game");
        socialNetwork.addMovie("Argo");
        socialNetwork.addMovie("Fury");
        socialNetwork.addMovie("Tinker Tailor Soldier Spy");

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
                    case 9 -> displayAllFriendships();
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
                2. Add movie to the list
                3. Like movie
                4. Add friendship between two persons
                5. Remove friendship between two persons
                6. Suggest a new friend
                7. Display friendships with below-average common movies
                8. View person details
                9. Adjacency List Display
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

        // List to store the friendships and their common movies
        List<Friendship> friendshipsBelowAverage = new ArrayList<>();

        // Set to store processed friendships to avoid duplicates
        Set<String> processedPairs = new HashSet<>();

        // Calculate the total number of common movies and the total number of friendships
        for (Person person : socialNetwork.users) {
            for (Friendship friendship : person.friendships) {
                // Create a unique pair identifier for the friendship
                String pairIdentifier1 = friendship.person1.username + " - " + friendship.person2.username;
                String pairIdentifier2 = friendship.person2.username + " - " + friendship.person1.username;

                // Check if the pair has already been processed
                if (!processedPairs.contains(pairIdentifier1) && !processedPairs.contains(pairIdentifier2)) {
                    int commonMovies = friendship.getCommonMovies();  // Get common movies from Friendship object
                    totalCommonMovies += commonMovies;
                    totalFriendships++;

                    friendshipsBelowAverage.add(friendship);
                    processedPairs.add(pairIdentifier1);  // Mark the pair as processed
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
        for (Friendship friendship : friendshipsBelowAverage) {
            int commonMovies = friendship.getCommonMovies();
            if (commonMovies < averageCommonMovies) {
                // Format the output as friend1 - [noOfCommonMovies] - friend2
                String display = friendship.person1.username + " - [" + commonMovies + "] - " + friendship.person2.username;
                System.out.println(display);
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
    
    private void displayAllFriendships() {
        System.out.println("Displaying all friendships:");

        for (Person person : socialNetwork.users) {
            System.out.print(person.username + " - ");
            List<String> friendDetails = new LinkedList<>();

            for (Friendship friendship : person.friendships) {
                friendDetails.add(friendship.toString());
            }

            System.out.println(String.join(" - ", friendDetails));
        }
    }


    
    
}
