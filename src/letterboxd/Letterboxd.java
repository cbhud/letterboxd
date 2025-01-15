package letterboxd;

import java.util.*;

public class Letterboxd {
    LinkedList<Person> users = new LinkedList<>();
    List<String> availableMovies = new ArrayList<>();

    public void addUser(String username) {
        if (findUser(username) == null) {
            users.add(new Person(username));
        } else {
            System.out.println("User already exists.");
        }
    }

    public Person findUser(String username) {
        for (Person user : users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addMovie(String movie) {
        if (!availableMovies.contains(movie)) {
            availableMovies.add(movie);
        } else {
            System.out.println("Movie already exists in the list.");
        }
    }

    public void displayMovies() {
        System.out.println("Available movies:");
        for (int i = 0; i < availableMovies.size(); i++) {
            System.out.println((i + 1) + ". " + availableMovies.get(i));
        }
    }

    public void likeMovie(String username, int movieIndex) {
        Person person = findUser(username);
        if (person == null) {
            System.out.println("User not found.");
            return;
        }
        if (movieIndex > 0 && movieIndex <= availableMovies.size()) {
            String movie = availableMovies.get(movieIndex - 1);
            person.likeMovie(movie);
            System.out.println(username + " liked the movie: " + movie);
        } else {
            System.out.println("Invalid movie selection.");
        }
    }

    public void suggestNewFriendship(String username) {
        Person person = findUser(username);
        if (person == null) {
            System.out.println("User not found.");
            return;
        }

        Person suggestedFriend = null;
        int maxCommonMovies = 0;

        for (Person other : users) {
            if (!other.equals(person) && !person.friendships.contains(other)) {
                int commonMovies = calculateCommonMovies(person, other);
                if (commonMovies > maxCommonMovies) {
                    maxCommonMovies = commonMovies;
                    suggestedFriend = other;
                }
            }
        }

        if (suggestedFriend != null) {
            System.out.println("Suggested friend for " + username + " is " + suggestedFriend.username 
                               + " with " + maxCommonMovies + " common liked movies.");
        } else {
            System.out.println("No friend suggestions available.");
        }
    }

    private int calculateCommonMovies(Person person1, Person person2) {
        Set<String> commonMovies = new HashSet<>(person1.likedMovies);
        commonMovies.retainAll(person2.likedMovies);
        return commonMovies.size();
    }

    public void addFriendship(String username1, String username2) {
        Person person1 = findUser(username1);
        Person person2 = findUser(username2);

        if (person1 != null && person2 != null) {
            person1.addFriend(person2);
            person2.addFriend(person1);
            System.out.println("Friendship added between " + username1 + " and " + username2);
        } else {
            System.out.println("One or both users not found.");
        }
    }

    public void removeFriendship(String username1, String username2) {
        Person person1 = findUser(username1);
        Person person2 = findUser(username2);

        if (person1 != null && person2 != null) {
            person1.removeFriend(person2);
            person2.removeFriend(person1);
            System.out.println("Friendship removed between " + username1 + " and " + username2);
        } else {
            System.out.println("One or both users not found.");
        }
    }
}
