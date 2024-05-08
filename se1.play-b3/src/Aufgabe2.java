import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Aufgabe2 {
    public static void main(String[] args) {
        Random rand = new Random(); // Zufallszahlengenerator
        
        Supplier<Integer> source = () -> rand.nextInt(100); // Quelle der Zufallszahlen
        Consumer<Integer> lambdaPrint = (n) -> System.out.print(n + ", "); // Ausgabeformat
        
        List<Integer> numbers = Stream.generate(source) // Erzeuge Zufallszahlen
            .limit(10) // Limitiere auf 10 Zahlen
            .collect(Collectors.toList()); // Sammle die Zahlen in einer Liste
        
        numbers.forEach(lambdaPrint); // Ausgabe der Liste
    }
}
