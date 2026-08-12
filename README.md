# Wielowątkowy Serwer TCP z Drzewem AVL

Projekt klienta i serwera w Javie, który pozwala wielu użytkownikom jednocześnie zarządzać współdzielonym, samo-balansującym się drzewem binarnym (AVL) przez sieć. Aplikacja posiada interfejs graficzny w JavaFX, obsługuje wiele typów danych (m.in. Integer, String, Person) i jest pokryta testami jednostkowymi.

## Technologie i architektura

* **Język:** Java
* **Komunikacja sieciowa:** Gniazda (Sockets TCP), strumienie wejścia/wyjścia.
* **Wielowątkowość:** Serwer tworzy dedykowany wątek (`ClientHandler`) dla każdego klienta. Słowo kluczowe `synchronized` chroni współdzielone drzewo przed kolizjami w pamięci.
* **Struktury danych:** Generyczna implementacja drzewa AVL (`Tree<T>`) z automatycznym bilansowaniem węzłów (rotacje).
* **Testy:** JUnit 5 (automatyczne testowanie logiki drzewa).
* **GUI:** JavaFX.

## Struktura projektu

* **`Server.java`** – Startuje serwer, przypisuje drzewa i nasłuchuje na porcie TCP.
* **`ClientHandler.java`** – Wątek obsługujący komunikację, parsowanie poleceń klienta i synchronizację.
* **`Tree.java`** – Logika drzewa AVL, algorytmy bilansowania i operacje na węzłach.
* **`Person.java`** – Model danych obiektowych z zaimplementowanym interfejsem `Comparable`.
* **`Client.java`** – Aplikacja klienta z interfejsem graficznym.
* **`TreeTest.java` / `PersonTest.java`** – Testy jednostkowe potwierdzające poprawność operacji na drzewie i obiektach.

## Jak uruchomić

Wystartuj serwer w terminalu:
```bash
javac Server.java
java Server
```
Następnie w oddzielnym terminalu (lub kilku, dla wielu klientów):
```bash
javac Client.java
java Client
```
Aby uruchomić automatyczne testy jednostkowe (wymaga Mavena):
```bash
mvn test
```
