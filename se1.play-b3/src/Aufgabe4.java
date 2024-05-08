import java.util.Arrays;

public class Aufgabe4 {
    public static void main(String[] args) {
        // Demonstration der map-Funktion
        Arrays.asList(2, 7, 4, 2, 5, 2, 6).stream()
              .map(i -> i * 10) // jedes Element mit 10 multiplizieren
              .forEach(n -> System.out.print(n + ", ")); // Ausgabe: 20, 70, 40, ...

        System.out.println(); // für Zeilenumbruch

        // Demonstration der reduce-Funktion
        int sum = Arrays.asList(2, 7, 4, 2, 5, 2, 6).stream()
                         .reduce(0, (accumulator, next) -> accumulator + next);

        System.out.println("sum: " + sum); // Ausgabe der Summe
    }
}
