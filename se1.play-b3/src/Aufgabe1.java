import java.util.Random;
import java.util.stream.Stream;

public class Aufgabe1 {
    public static void main(String[] args) {
        // Zufallsgenerator ohne festen Seed
        Random rand = new Random();

        // Generiere 10 zufällige Zahlen im Bereich von 0 bis 99
        Stream.generate(() -> rand.nextInt(100)) // Lambda-Ausdruck für Zufallszahlen
              .limit(10) // Begrenzung auf 10 Elemente
              .forEach(n -> System.out.print(n + ", ")); // Ausgabe mit Komma
        
        // Zeilenumbruch nach der Ausgabe
        System.out.println(); 
    }
}
