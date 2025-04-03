## UNIVERSITY "MEDITERAN" PODGORICA

## FACULTY OF INFORMATION TECHNOLOGY, PODGORICA

# DATA STRUCTURES AND ALGORITHMS

# Social Network - Movies

Students: Professor:

Ajša Dacić Dr. Amar Kapić

Index No: 01 - 23

Amer Hot

Index No: 17 - 23

```
Podgorica, January 2025.
```

## Contents:

- 1. Introduction
- 2. Problem Description
- 3. Application Description
   - 3.1 Graph Implementation
   - 3.2 Description of Methods for Adding, Modifying, and Deleting Data from the Graph
   - 3.3 Description of Additional Methods
- 4. Conclusion

## 1. Introduction

This document describes the implementation of a social network system that allows users to receive movie recommendations based on their liked movies and connections with other users. The system is designed as a graph where nodes represent users, and edges represent their relationships and shared movies.

For implementation purposes, classes have been created to cover different aspects of the system, including users, friendships, and the network structure itself. Each class is described in detail, along with methods that enable adding, modifying, and displaying data. Additionally, advanced functionalities such as suggesting new friendships based on shared movies have been implemented.

The documentation is structured to explain key parts of the system step by step and the logic behind their implementation.

## 2. Problem Description

The given task to solve is as follows:

“A graph representing 'friends' on a social network for movie recommendations.

For each person, the username and liked movies (only movie titles) are known.
For each connection, it is known whether two people are friends and how many movies they have liked in common.

a) Adding, modifying, and deleting data (analyze and implement all possibilities).

b) A method that takes a username as an input argument and suggests a new friend (the person with whom they have the most shared movies but are not yet friends).

c) A method that displays all friend pairs who have fewer shared movies than the average number.”

We decided to solve this step by step, as described in detail below.

## 3. Application Description

### 3.1 Graph Implementation

Below is an illustration of the implemented graph:

```
Figure 1 - Initial Graph Idea
```

The graph is represented by the `LetterBoxd` class, which contains a list of users.

Each user is a graph node (class `Person`) and is connected via a `Friendship` object to other nodes.

The `Friendship` object represents the graph edges, which are bidirectional and have a weight.

The number of movies both persons liked represents the edge weight.

According to the task:

1. Each person has a username and a list of liked movies (only movie titles).
   - A `Person` class was created with the attributes: `username` (string), a list of liked movies (HashSet of strings - movie titles), and a list of friends (LinkedList of `Person` objects).
2. Each connection records whether two people are friends and how many movies they have liked in common.
   - A `Friendship` class was created with attributes: `person1` and `person2` (`Person` objects) and the number of shared liked movies (int).

3. A graph representing 'friends' on a social network for movie recommendations.
   - The `LetterBoxd` class was created based on the existing website with a similar idea to our task.
   - Attributes of this class include: a list of available movies in the application (ArrayList) and a list of all users (LinkedList). This class represents the graph's foundation. Nodes of our graph are application users, while edges are `Friendship` objects connecting users.

### 3.2 Description of Methods for Adding, Modifying, and Deleting Data from the Graph

#### Implemented methods by class:

#### Person

- `likeMovie(String movieName)`: Adds a movie to the liked movies list.
- `addFriend(Person person)`: Adds a friend if they are not already friends.
- `removeFriend(Person person)`: Removes a friend from the friend list.

#### LetterBoxd

- `findUser(String username)`: Finds and returns a `Person` object by username.
- `addUser(String username)`: Adds a new user if they do not already exist.
- `addMovie(String movieName)`: Adds a movie to the available movies list.
- `displayMovies()`: Displays available movies.
- `likeMovie(String username, int movieIndex)`: Allows a user to like a movie.
- `calculateCommonMovies(String user1, String user2)`: Returns the number of shared liked movies.
- `addFriendship(String user1, String user2)`: Establishes a friendship between two users.
- `removeFriendship(String user1, String user2)`: Removes a friendship.

#### Friendship

- `getCommonMovies()`: Returns the number of shared liked movies between two friends.

### 3.3 Description of Additional Methods

#### Friendship Suggestion

- `suggestNewFriendship(String username)`: Suggests a new friend based on the highest number of shared movies.

#### Display Friendships Below Average

- `displayFriendshipsBelowAverage()`: Displays friend pairs who have fewer shared movies than the average.

## 4. Conclusion

This project presents a solution for creating a social network for movie recommendations, allowing users to like movies, connect with other users through friendships, and receive recommendations based on shared interests. Through the implementation of the `Person`, `Friendship`, and `LetterBoxd` classes, all key requirements of the task have been covered.
