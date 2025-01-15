package letterboxd;


import java.util.*;

public class Person {
    String username;
    Set<String> likedMovies = new HashSet<>();
    List<Friendship> friendships = new LinkedList<>();  // Change to list of Friendship objects

    public Person(String username) {
        this.username = username;
    }

    public void likeMovie(String movie) {
        likedMovies.add(movie);
    }

    public void addFriend(Person friend) {
        // Check if friendship already exists
        for (Friendship friendship : friendships) {
            if ((friendship.person1 == this && friendship.person2 == friend) ||
                (friendship.person2 == this && friendship.person1 == friend)) {
                return;  // Avoid adding duplicate friendships
            }
        }

        friendships.add(new Friendship(this, friend));  // Create new Friendship object
        friend.addFriend(this);  // Add this user as a friend to the other person as well
    }

    public void removeFriend(Person friend) {
        friendships.removeIf(friendship -> 
            (friendship.person1 == this && friendship.person2 == friend) || 
            (friendship.person2 == this && friendship.person1 == friend)
        );
        friend.removeFriend(this);  // Remove this user from friend's friend list as well
    }

    private int calculateCommonMovies(Person person1, Person person2) {
        Set<String> commonMovies = new HashSet<>(person1.likedMovies);
        commonMovies.retainAll(person2.likedMovies);
        return commonMovies.size();
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Username: " + username + ", Liked Movies: " + likedMovies + ", Friends: ");
        
        for (Friendship friendship : friendships) {
            result.append(friendship.toString()).append(", ");
        }
        
        // Remove the last comma and space
        if (result.length() > 0) {
            result.setLength(result.length() - 2);
        }
        
        return result.toString();
    }
}
