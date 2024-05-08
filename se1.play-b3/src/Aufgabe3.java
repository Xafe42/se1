import java.util.stream.IntStream;

public class Aufgabe3 {
    public static void main(String[] args) {
        int sum = IntStream.range(0, 10)
                           .boxed() // Umwandeln in Stream<Integer>
                           .filter(n -> n % 3 == 0) // nur durch 3 teilbare Zahlen
                           .sorted((a, b) -> Integer.compare(b, a)) // absteigend sortieren
                           .peek(n -> System.out.print(n + ", ")) // Zahlen ausgeben
                           .mapToInt(Integer::intValue) // um die Summe zu berechnen
                           .reduce(0, Integer::sum);

        System.out.println("sum: " + sum); // Ausgabe der Summe
    }
}
