package letterboxd;
import java.util.*;


public class Friendship {
    Person person1;
    Person person2;
    int commonMovies;

    public Friendship(Person person1, Person person2) {
        this.person1 = person1;
        this.person2 = person2;
        this.commonMovies = getCommonMovies();
    }

    public int getCommonMovies() {
        Set<String> common = new HashSet<>(person1.likedMovies);
        common.retainAll(person2.likedMovies);
        //svaki put updatuje broj kako bi uvijek imali azuran i tacan broj 
        //zajednickih filmova u slucaju promjene
        // nakon dodavanja prijatelja
        this.commonMovies = common.size();
        return common.size();
    }


    @Override
    public String toString() {
        return person2.username + "[" + getCommonMovies() + "] ";
    }
}
