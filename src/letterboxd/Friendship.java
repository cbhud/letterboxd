package letterboxd;
import java.util.*;


public class Friendship {
    Person person1;
    Person person2;
    int commonMovies;

    public Friendship(Person person1, Person person2) {
        this.person1 = person1;
        this.person2 = person2;
        this.commonMovies = calculateCommonMovies();
    }

    private int calculateCommonMovies() {
        Set<String> commonMoviesSet = new HashSet<>(person1.likedMovies);
        commonMoviesSet.retainAll(person2.likedMovies);
        return commonMoviesSet.size(); 
    }

    public int getCommonMovies() {
        return commonMovies;
    }

    @Override
    public String toString() {
        return person2.username + "[" + commonMovies + "] ";
    }
}
