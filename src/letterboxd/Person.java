package letterboxd;
import java.util.HashSet;
import java.util.Set;

public class Person {
    String username;
    Set<String> likedMovies = new HashSet<>();
    Set<Person> friendships = new HashSet<>();

    public Person(String username) {
        this.username = username;
    }

    public void likeMovie(String movie) {
        likedMovies.add(movie);
    }

    public void addFriend(Person friend) {
        friendships.add(friend);
    }

    public void removeFriend(Person friend) {
        friendships.remove(friend);
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Liked Movies: " + likedMovies;
    }
}
