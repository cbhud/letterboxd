package letterboxd;


import java.util.*;

public class Person {
    String username;
    Set<String> likedMovies = new HashSet<>();
    List<Friendship> friendships = new LinkedList<>();

    public Person(String username) {
        this.username = username;
    }

    public void likeMovie(String movie) {
        likedMovies.add(movie);
    }

    public void addFriend(Person friend) {
        for (Friendship friendship : friendships) {
        	//kako bismo provjerili prijateljstvo sa obije strane
            if ((friendship.person1 == this && friendship.person2 == friend) ||
                (friendship.person2 == this && friendship.person1 == friend)) {
                return;
            }
        }

        //Kreiramo objekat i dodajemo prijateljstvo
        friendships.add(new Friendship(this, friend));
        //dodajemo prijateljstvo s druge strane
        friend.addFriend(this);
    }

    
    //O(1)
    public boolean removeFriend(Person friend) {

    	if(friendships.contains(friend)) {
    		this.friendships.remove(friend);
    		friend.friendships.remove(this);
    		return true;
    	}
    	
    	return false;
    	
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Username: " + username + ", Liked Movies: " + likedMovies + ", Friends: ");
        
        for (Friendship friendship : friendships) {
            result.append(friendship.toString()).append(", ");
        }
        
        // Da obrisemo zarez na kraju da ne ostaje od ", "
        if (result.length() > 0) {
            result.setLength(result.length() - 2);
        }
        
        return result.toString();
    }
}
