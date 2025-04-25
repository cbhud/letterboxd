# 🎬 Social Network - Movies

_Data Structures and Algorithms Project_

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)  
[![University](https://img.shields.io/badge/university-Mediteran%20Podgorica-blue)](https://unimediteran.net)  
[![Faculty](https://img.shields.io/badge/faculty-Information%20Technology-lightgrey)]()

---

## 📍 University "Mediteran" Podgorica

**Faculty of Information Technology**

📚 **Course:** Data Structures and Algorithms  
👩‍🏫 **Professor:** Dr. Amar Kapić  
👩‍🎓 **Students:**

- Ajša Dacić (Index No: 01-23)
- Amer Hot (Index No: 17-23)

🗓️ **Date:** January 2025  
📍 **Location:** Podgorica

---

## 📖 Table of Contents

1. [Introduction](#1-introduction)
2. [Problem Description](#2-problem-description)
3. [Application Description](#3-application-description)
   - [3.1 Graph Implementation](#31-graph-implementation)
   - [3.2 Methods for Data Manipulation](#32-methods-for-data-manipulation)
   - [3.3 Additional Functionalities](#33-additional-functionalities)
4. [Conclusion](#4-conclusion)

---

## 1. Introduction

This project simulates a **social network for movie recommendations** using a graph-based structure. Users can:

- Like movies.
- Connect with friends.
- Get suggestions for potential friends based on shared interests.

Each user is a node in the graph, and friendships (edges) are weighted by the number of common liked movies. The system includes functionalities to manipulate the graph (add/remove users and friendships) and analyze relationships using algorithms and data structures.

---

## 2. Problem Description

**Assignment Overview:**

A graph models a social network for movie recommendations. Each user has:

- A username.
- A list of liked movies.

Each edge (friendship) contains:

- A friendship status.
- The number of shared liked movies.

### Tasks Implemented:

a) Add, modify, and delete graph data.  
b) Suggest new friends based on common liked movies.  
c) Display friend pairs with fewer common movies than average.

---

## 3. Application Description

### 3.1 Graph Implementation

The core system is built using three main classes:

- **`Person`** - Represents a user (graph node).
- **`Friendship`** - Represents a friendship (graph edge) with weight (common movies).
- **`LetterBoxd`** - Acts as the graph manager, holding all users and their relations.

> 📌 _Each connection is bidirectional and dynamically updated based on movie preferences._

```
Figure 1 - Graph Structure Concept
```

#### Data Structures Used:

- `HashSet<String>` for liked movies (ensures uniqueness and fast lookup).
- `LinkedList<Person>` for friend lists (flexible additions/removals).
- `ArrayList<String>` for available movies.
- `LinkedList<Person>` for storing all users.

---

### 3.2 Methods for Data Manipulation

#### Person

| Method                        | Description         |
| ----------------------------- | ------------------- |
| `likeMovie(String movieName)` | Adds a liked movie. |
| `addFriend(Person person)`    | Adds a friend.      |
| `removeFriend(Person person)` | Removes a friend.   |

#### LetterBoxd

| Method                                              | Description                            |
| --------------------------------------------------- | -------------------------------------- |
| `findUser(String username)`                         | Finds a user by name.                  |
| `addUser(String username)`                          | Adds a new user.                       |
| `addMovie(String movieName)`                        | Adds a movie to the system.            |
| `displayMovies()`                                   | Displays all available movies.         |
| `likeMovie(String username, int movieIndex)`        | User likes a movie.                    |
| `calculateCommonMovies(String user1, String user2)` | Returns number of shared liked movies. |
| `addFriendship(String user1, String user2)`         | Creates a friendship.                  |
| `removeFriendship(String user1, String user2)`      | Removes a friendship.                  |

#### Friendship

| Method              | Description                      |
| ------------------- | -------------------------------- |
| `getCommonMovies()` | Returns number of shared movies. |

---

### 3.3 Additional Functionalities

#### ✅ Friend Suggestion

- `suggestNewFriendship(String username)`  
  Suggests a user with the highest number of common liked movies that is not already a friend.

#### 📉 Below-Average Friendships

- `displayFriendshipsBelowAverage()`  
  Lists friend pairs with fewer shared movies than the average across all friendships.

---

## 4. Conclusion

This project provides a robust and extensible graph-based simulation of a **social movie network**. Using object-oriented design and efficient data structures, it enables personalized recommendations and advanced relationship insights.

The project fulfills the assignment requirements and offers potential for further development such as:

- UI integration.
- Database storage.
- Enhanced recommendation algorithms.

---

## 📁 Project Structure (Example)

```
📦 social-network-movies/
├── src/
│   ├── Person.java
│   ├── Friendship.java
│   └── LetterBoxd.java
├── README.md
└── LICENSE
```

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).

---

## 💬 Feedback & Contributions

Feel free to fork, open issues, or contribute!  
We appreciate feedback and ideas for improving the project further.
For any information contact me on discord: cbhud
