package streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation class of {@link Streams} interface.
 * 
 * @version <code style=
 *          color:green>{@value application.package_info#Version}</code>
 * @author <code style=
 *         color:blue>{@value application.package_info#Author}</code>
 */
public class StreamsImpl implements Streams, Runnable {

    /*
     * arguments passed from command line
     */
    final String[] args;

    /*
     * Random generator.
     */
    final Random rand = new Random();

    /**
     * Public {@code String[] args} constructor.
     * 
     * @param args arguments passed from command line
     */
    public StreamsImpl(String[] args) {
        this.args = args;
    }

    /*
     * Function variable with Lambda expression that accepts one argument and
     * returns a Stream of random Integer numbers between [1, upperBound].
     */
    final Function<Integer, Stream<Integer>> randIntStream = (upperBound) -> Stream
            .generate(() -> rand.nextInt(1, upperBound));

    @Override
    public void run() {
        //
        var res = tenRandomNumbers();
        System.out.println(format("tenRandomNumbers()", null, res));
        //
        res = tenEvenRandomNumbers();
        System.out.println(format("tenEvenRandomNumbers()", null, res));
        //
        res = tenSortedEvenRandomNumbers();
        System.out.println(format("tenSortedEvenRandomNumbers()", null, res));
        //
        res = filteredNumbers(0, 15); // 15 random even numbers
        System.out.println(format("filteredNumbers(0, 15)", "// 15 random even numbers", res));
        //
        res = filteredNumbers(1, 15); // 15 random numbers divisible by 3
        System.out.println(format("filteredNumbers(1, 15)", "// 15 random numbers divisible by 3", res));
        //
        res = filteredNumbers(2, 15); // 15 random prime numbers
        System.out.println(format("filteredNumbers(2, 15)", "// 15 random two-digit prime numbers", res));
        //
        var nams = filteredNames(names, ".*ez$"); // names ending with "ez"
        System.out.println(format("filteredNames(names, \".*ez$\")", "// names ending with \"ez\"", nams));
        //
        nams = sortedNames(names, 8); // first 8 names from sorted name list
        System.out.println(format("sortedNames(names, 8)", "// first 8 names from sorted name list", nams));
        //
        nams = sortedNamesByLength(names); // names sorted by length and alphabetically for names with same length
        System.out.println(format("sortedNamesByLength(names)", "// names sorted by length", nams));
        //
        long value = calculateValue(orders);
        System.out.println(format("calculateValue(orders)", null, value));
        //
        StringBuilder ord = sortOrdersByValue(orders).stream()
                .map(o -> o.toString())
                .reduce(new StringBuilder(""),
                        (acc, str) -> acc.append(str).append("\n"),
                        (sb1, sb2) -> sb1.append(sb2));
        //
        ord.append(" ".repeat(26)).append("--------\n");
        ord.append(" ".repeat(26)).append(String.format("%8d\n", value));
        ord.append(" ".repeat(26)).append("========\n");
        //
        System.out.println(format("sortOrdersByValue(orders)", "// orders sorted by value", ord));
        //
        System.out.println("done.");
    }

    /**
     * Aufgabe 1: Return 10 random integer numbers generated from a Stream<Integer>.
     * 
     * @return 10 random numbers
     */
    @Override
    public List<Integer> tenRandomNumbers() {
        return randIntStream.apply(1000) // Zufallszahlen zwischen 0 und 999
                .limit(10) // Begrenzung des Streams auf 10 Elemente
                /*.collect(Collectors.toList()); Musterlösung */.toList(); // Sammlung in eine Liste
    }

    /**
     * Aufgabe 2: Return 10 even random integer numbers generated from a
     * Stream<Integer>.
     * 
     * @return 10 even random numbers
     */
    @Override
    public List<Integer> tenEvenRandomNumbers() {
        return randIntStream.apply(1000) // Zufallszahlen zwischen 0 und 999
                //Früh Filtern
                .filter(num -> num % 2 == 0) // Nur gerade Zahlen
                .limit(10) // Begrenzung auf 10 Elemente
                .toList(); // Sammlung in eine Liste
    }

    /**
     * Aufgabe 3: Return 10 even sorted random integer numbers generated from a
     * Stream<Integer>.
     * 
     * @return 10 even sorted random numbers
     */
    @Override
    public List<Integer> tenSortedEvenRandomNumbers() {
        return randIntStream.apply(1000) // Zufallszahlen zwischen 0 und 999
                .filter(num -> num % 2 == 0) // Nur gerade Zahlen
                .limit(10) // Begrenzung auf 10 Elemente
                .sorted() // Sortierung der Ergebnisse
                .toList(); // Sammlung in eine Liste
    }

    // Aufgabe 4
    // Initialisierung der Liste der Filterfunktionen
    final static List<Function<Integer, Boolean>> filterFunctions = new ArrayList<>();
    static {
        filterFunctions.add(num -> num % 2 == 0); // gerade Zahlen
        filterFunctions.add(num -> num % 3 == 0); // durch 3 teilbare Zahlen
        filterFunctions.add(num -> {
            if (num < 100 || num > 999)
                return false; // dreistellige Primzahlen
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0)
                    return false; // keine Primzahl
            }
            return true; // ist Primzahl
        });
    }

    /**
     * Aufgabe 4: Method applies function from {@link filterFunctions} to a stream
     * of integer numbers returning only numbers matching the selected filter:
     * 
     * <pre>
     *  - 0: only even numbers
     *  - 1: only numbers divisible by 3
     *  - 2: only 3-digit prime numbers
     * </pre>
     * 
     * @param filterFunctionIndex index of filter function in
     *                            {@link filterFunctions}
     * @param limit               amount of numbers returned
     * @return numbers matching the selected filter
     */
    @Override
    public List<Integer> filteredNumbers(int filterFunctionIndex, int limit) {
        // Überprüfen Sie, ob der Index im gültigen Bereich liegt
        if (filterFunctionIndex < 0 || filterFunctionIndex >= filterFunctions.size()) {
            throw new IndexOutOfBoundsException(
                    String.format("filterFunctionIndex out of range: %d", filterFunctionIndex));
        }

        // Überprüfen Sie, ob das Limit nicht negativ ist
        if (limit < 0) {
            throw new IllegalArgumentException(
                    String.format("negative limit: %d", limit));
        }

        // Erhalten Sie die gewünschte Filterfunktion
        Function<Integer, Boolean> filterFunction = filterFunctions.get(filterFunctionIndex);

        // Generieren Sie einen Strom von Zufallszahlen und wenden Sie den Filter an
        return Stream.generate(() -> rand.nextInt(1, 1001)) // Zufallszahlen zwischen 1 und 1000
                .filter(filterFunction::apply) // Filter anwenden
                .distinct() // Vermeidung von Duplikaten
                .limit(limit) // Begrenzung der Anzahl
                .toList(); // In eine Liste umwandeln
    }

    /**
     * Aufgabe 5: Return sub-list from input names filtered by a regular expression.
     * Order of names remains unchanged, regular expression refers to
     * {@link java.util.regex.Pattern}.
     * 
     * @param names input names
     * @param regex regular expression according to {@link java.util.regex.Pattern}
     * @return sub-list of names
     */
    @Override
    public List<String> filteredNames(List<String> names, String regex) {
        if (names == null || regex == null) { // Prüfen, ob einer der Eingabewerte null ist
            throw new IllegalArgumentException("names or regex argument is null."); // Fehlernachricht angleichen
        }

        Pattern pattern = Pattern.compile(regex); // NullPointerException verhindern

        return names.stream()
                .filter(name -> pattern.matcher(name).matches())
                .collect(Collectors.toList());
    }

    /**
     * Aufgabe 6: Return alphabetically sorted list of names up to limit.
     * 
     * @param names input names
     * @param limit maximum number of names returned
     * @return alphabetically sorted list of names up to limit
     */
    @Override
    public List<String> sortedNames(List<String> names, int limit) {
        // Überprüfung, ob die Namenliste null ist
        if (names == null) {
            throw new IllegalArgumentException("names argument is null.");
        }

        // Überprüfung, ob das Limit negativ ist
        if (limit < 0) {
            throw new IllegalArgumentException("limit argument is negative: " + limit + ".");
        }

        // Wenn das Limit 0 oder weniger ist, geben wir eine leere Liste zurück
        if (limit == 0) {
            return List.of();
        }

        // Alphabetische Sortierung und Begrenzung auf das angegebene Limit
        return names.stream()
                .sorted() // Sortieren der Namen alphabetisch
                .limit(limit) // Begrenzung auf das Limit
                .collect(Collectors.toList()); // Zurückgeben als Liste
    }

    /**
     * Aufgabe 7: Return list of names sorted by name length (first criteria)
     * and alphabetically (second criteria) for names of equal length.
     * 
     * @param names input names
     * @return names sorted by name length
     */
    @Override
    public List<String> sortedNamesByLength(List<String> names) {
        // Überprüfen, ob die Liste von Namen null ist
        if (names == null) {
            throw new IllegalArgumentException("names argument is null.");
        }

        return names.stream()
                // Zuerst nach der Länge der Namen sortieren, dann alphabetisch bei gleichen
                // Längen
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList()); // In eine Liste sammeln und zurückgeben
    }

    /**
     * Aufgabe 8: Calculate value of orders.
     * 
     * @param orders list of orders to process
     * @return value of orders
     */
    @Override
    public long calculateValue(List<Order> orders) {
        if (orders == null) {
            throw new IllegalArgumentException("orders argument is null.");
        }

        return orders.stream()
                // Berechnung des Werts jeder Bestellung: units * unitPrice
                .mapToLong(order -> order.units * order.unitPrice)
                // Alle Werte addieren, um den Gesamtwert zu berechnen
                .reduce(0L, Long::sum);
                // andere Lösung .reduce(OL, (accumulator, p) -> accumulator + p);
    }

    /**
     * Aufgabe 9: Return list of orders sorted by order value (highest-value first).
     * 
     * @param orders list to sort
     * @return orders sorted by order value (highest-value first)
     */
    @Override
    public List<Order> sortOrdersByValue(List<Order> orders) {

        // Validate input
        if (orders == null) {
            throw new IllegalArgumentException("orders cannot be null");
        }

        // Sort orders by value (units * unitPrice) in descending order
        return orders.stream()
                .sorted(Comparator.comparingLong(order -> -(order.units * order.unitPrice))) // descending
                .collect(Collectors.toList());
    }

    /**
     * Return formatted output from parameters.
     * 
     * @param func    function name invoked
     * @param comment provided comment
     * @param result  result to show
     * @return formatted output
     */
    private String format(String func, String comment, Object result) {
        var cmt = comment == null ? "" : "\t" + comment;
        return String.format("- %s:%s\n    -> %s\n", func, cmt, result);
    }
}