package letterboxd;

import java.util.HashSet;
import java.util.Set;

class Friendship {
    Person person1;
    Person person2;
    int commonMovies;

    public Friendship(Person person1, Person person2) {
        this.person1 = person1;
        this.person2 = person2;
        this.commonMovies = calculateCommonMovies();
    }

    private int calculateCommonMovies() {
        Set<String> common = new HashSet<>(person1.likedMovies);
        common.retainAll(person2.likedMovies);
        return common.size();
    }
}