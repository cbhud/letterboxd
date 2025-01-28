package letterboxd;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Menus {
    Letterboxd socialNetwork = new Letterboxd();
    Scanner sc = new Scanner(System.in);

    
    
    Menus(){
    	start();
    }

    
    
    public void start() {
        
        //filmove dodajemo kako bismo imali neki osnov
    	//te da ne moramo da dodajemo filmove svaki put kada testiramo
    	//svjesni smo da smo mogli da dodamo jos jednu klasu Test :)
        socialNetwork.addMovie("Munich - The Edge of War");
        socialNetwork.addMovie("Bridge of Spies");
        socialNetwork.addMovie("The Spy Who Came in from the Cold");
        socialNetwork.addMovie("The Imitation Game");
        socialNetwork.addMovie("Argo");
        socialNetwork.addMovie("Fury");
        socialNetwork.addMovie("Tinker Tailor Soldier Spy");

        while (true) {
            displayMenu();

            try {
                int result = sc.nextInt();
                sc.nextLine();

                switch (result) {
                    case 1 -> addPerson();
                    case 2 -> addMovie();
                    case 3 -> selectAndLikeMovie();
                    case 4 -> addFriendship();
                    case 5 -> removeFriendship();
                    case 6 -> suggestNewFriend();
                    case 7 -> displayBelowAverage();
                    case 8 -> viewPersonDetails();
                    case 9 -> ajdacencyListDisplay();
                    default -> {
                        System.out.println("Closed");
                        return;
                    }
                }
                //Kako aplikacija ne bi crashovala u slucaju da korisnik slucajno unese string
            } catch (InputMismatchException e) {
                System.out.println("Invalid input Please enter a valid number");
                sc.nextLine(); 
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
                sc.nextLine();
            }
        }
    }

    
    //Prikaz grafa preko 
    private void ajdacencyListDisplay() {
    	socialNetwork.displayAllFriendships();
	}

	
    
    private void displayBelowAverage() {
    	socialNetwork.displayFriendshipsBelowAverage();
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
    

    
}
