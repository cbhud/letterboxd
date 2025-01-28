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

    //Pronalazak korisnika kroz listu svih korisnika
    public Person findUser(String username) {
        for (Person user : users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }

    //Dodavanje filma u ArrayListu dostupnih filmova
    public void addMovie(String movie) {
        if (!availableMovies.contains(movie)) {
            availableMovies.add(movie);
        } else {
            System.out.println("Movie already exist in the list.");
        }
    }

    public void displayMovies() {
        System.out.println("Available movies:");
        for (int i = 0; i < availableMovies.size(); i++) {
            System.out.println((i + 1) + ". " + availableMovies.get(i));
        }
    }

    //metoda za dodavanje filma u korisnikov set lajkovanih filmova
    public void likeMovie(String username, int movieIndex) {
        Person person = findUser(username);
        if (person == null) {
            System.out.println("User not found.");
            return;
        }
        if (movieIndex > 0 && movieIndex <= availableMovies.size()) {
        	//movieIndex - 1 iz razloga zato sto java krece od 0 medjutim mi u prikazu liste dostupnih filmova
        	// brojimo od 1 te kako ne bi doslo do greske prilikom odabira
            String movie = availableMovies.get(movieIndex - 1);
            person.likeMovie(movie);
            System.out.println(username + " liked the movie: " + movie);
        } else {
            System.out.println("Invalid movie.");
        }
    }

    
    //Metoda predlaze prijateljstvo na osnovu zajednickih lajkovanih filmova
    public void suggestNewFriendship(String username) {
        Person person = findUser(username);
        if (person == null) {
            System.out.println("User not found.");
            return;
        }

        Person suggestedFriend = null;
        int maxCommonMovies = 0;

        //prolazimo kroz listu korisnika redom
        for (Person other : users) {
        	//potrebno je preskociti ukoliko naidje na isti objekat osobe
        	//ili na osobu sa kojom je vec prijatelj
            if (!other.equals(person) && !person.friendships.contains(other)) {
                int commonMovies = calculateCommonMovies(person, other);
                if (commonMovies > maxCommonMovies) {
                	//setuje onog s kim ima najvise filmova
                	//ovdje smo mogli i da napravimo listu te ukoliko ima vise korisnika
                	//sa istim brojem lajkovanih filmova predlozi sve njih
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
    

    //Metoda koja uporedjuje broj zajednickih filmova izmedju dvije osobe
    // i vraca taj broj
    private int calculateCommonMovies(Person person1, Person person2) {
        Set<String> commonMovies = new HashSet<>(person1.likedMovies);
        //retainAll odbacuje sve koji im nisu zajednicki tj brise ih
        commonMovies.retainAll(person2.likedMovies);
        return commonMovies.size();
    }
    

    //Metoda za provjeru prije dodavanja prijatelja
    // zatim se poziva addFriend iz Person klase koji ima ostatak logike
    public void addFriendship(String username1, String username2) {
    	if(username1.equalsIgnoreCase(username2)) {
    		System.out.println("You cant add yourself as a friend!");
    		return;
    	}
        Person person1 = findUser(username1);
        Person person2 = findUser(username2);

        if (person1 != null && person2 != null) {
            person1.addFriend(person2);
            System.out.println("Friendship added for" + username1 + " and " + username2);
        } else {
            System.out.println("User not found.");
        }
    }
    

    //Metoda za brisanje prijateljstva
    public void removeFriendship(String username1, String username2) {
        Person person1 = findUser(username1);
        Person person2 = findUser(username2);

        if (person1 != null && person2 != null) {
            person1.removeFriend(person2);
            person2.removeFriend(person1);
            System.out.println("Friendship removed " + username1 + " and " + username2);
        } else {
            System.out.println("One or both users not found.");
        }
    }
    
    
    //Metoda koja prikazuje prijateljstva koja imaju broj zajednickih filmova
    //ispod prosjeka
    public void displayFriendshipsBelowAverage() {
        int totalCommonMovies = 0;
        // user1 -[4]- user2
        // user1 -[3]- user6
        // totalCommonMovies = 7
        //Uvodimo totalCommonMovies kako bi kasnije mogli da izracunamo prosjek
        //tj da podijelimo ukupan broj jedinstvenih prijateljstava i podijelimo sa brojem
        //ukupnih zajednickih filmova svih jedinstvenih prijateljstava

        //Uvodimo allFriendships kako bismo mogli da cuvamo sva jedinstvena prijateljstva prilikom obrade
        //HashSet umjesto ArrayListe iz razloga jer je jednostavniji i efikasniji, i ne cuva indexe
        Set<Friendship> allFriendships = new HashSet<>();

        //Cuvamo obradjene parove kako se obrada ne bi ponavljala sa obije strane
        Set<String> processedPairs = new HashSet<>();

        for (Person person : users) {
            for (Friendship friendship : person.friendships) {
            	
            	//pairId 1 i 2 sluze kako bismo upisali prijateljstvo u set obradjenih sa obije strane
            	//tj kako ne bi doslo do ponavljana prilikom obrate
            	String pairId1 = friendship.person1.username + " - " + friendship.person2.username;
                String pairId2 = friendship.person2.username + " - " + friendship.person1.username;


                if (!processedPairs.contains(pairId1) && !processedPairs.contains(pairId2)) {

                    totalCommonMovies += friendship.getCommonMovies();;

                    allFriendships.add(friendship);
                    processedPairs.add(pairId1);
                }
            }
        }

        //Ukoliko je set prazan metoda se tu zaustavlja
        if (allFriendships.isEmpty()) {
            System.out.println("No friendships to analyze.");
            return;
        }

        //Racunamo prosjek
        double averageCommonMovies = (double) totalCommonMovies / allFriendships.size();

        System.out.println("Average number of common liked movies: " + averageCommonMovies);
        System.out.println("Friendships with below-average common liked movies:");

        //Zatim prolazimo kroz sva jedinstvena prijateljstva kako bismo ispisali ona koja su ispod prosjeka
        //uz pomoc if uslova
        for (Friendship friendship : allFriendships) {
            int commonMovies = friendship.getCommonMovies();
            if (commonMovies < averageCommonMovies) {
                String display = friendship.person1.username + " - [" + commonMovies + "] - " + friendship.person2.username;
                System.out.println(display);
            }
        }
    }
        
    
    // kroz modifikovanu toString metodu prikazujemo sva prijateljstva
    // sto ustvari predstavlja nasu listu lista tj Adjacency List
    public void displayAllFriendships() {
        System.out.println("Displaying all friendships:");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        for (Person person : users) {
            System.out.print(person.username + " - ");
            List<String> friendDetails = new LinkedList<>();

            for (Friendship friendship : person.friendships) {
                friendDetails.add(friendship.toString());
            }

            System.out.println(String.join(" - ", friendDetails));
        }
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
    
      
}
