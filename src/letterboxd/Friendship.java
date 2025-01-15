package letterboxd;
import java.util.*;


public class Friendship {
    Person person1;
    Person person2;
    int commonMovies;

    public Friendship(Person person1, Person person2) {
        this.person1 = person1;
        this.person2 = person2;
        this.commonMovies = calculateCommonMovies(person1, person2);
    }

    private int calculateCommonMovies(Person person1, Person person2) {
        System.out.println(person1.username + " liked movies: " + person1.likedMovies);
        System.out.println(person2.username + " liked movies: " + person2.likedMovies);

        Set<String> commonMovies = new HashSet<>(person1.likedMovies);
        commonMovies.retainAll(person2.likedMovies);
        
        System.out.println("Common movies: " + commonMovies);  // Debugging line

        return commonMovies.size();
    }

    public int getCommonMovies() {
        Set<String> common = new HashSet<>(person1.likedMovies);
        common.retainAll(person2.likedMovies); // Retains only the elements in common
        return common.size(); // Returns the count of common movies
    }


    @Override
    public String toString() {
        return person2.username + "[" + commonMovies + "] ";
    }
}
