package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import application.Application;


/**
 * Public interface for <i>"Streams"</i> assignment.
 * 
 */
public interface Streams {

    /**
     * Aufgabe 1: Return 10 random integer numbers generated from a Stream<Integer>.
     * @return 10 random numbers
     */
    List<Integer> tenRandomNumbers();


    /**
     * Aufgabe 2: Return 10 even random integer numbers generated from a Stream<Integer>.
     * @return 10 even random numbers
     */
    List<Integer> tenEvenRandomNumbers();


    /**
     * Aufgabe 3: Return 10 even sorted random integer numbers generated from a Stream<Integer>.
     * @return 10 even sorted random numbers
     */
    List<Integer> tenSortedEvenRandomNumbers();


    /*
     * Aufgabe 4: indexed list of lambda expressions that yield a Boolean value:
     * <pre>
     *  - i=0: lambda yields true when a number is even
     *  - i=1: lambda yields true when a number divides by 3
     *  - i=2: lambda yields true when a number is three-digit prime number
     * </pre>
     */
    final static List<Function<Integer, Boolean>> filterFunctions = new ArrayList<>();

    /**
     * Aufgabe 4: Method applies function from {@link filterFunctions} to a stream
     * of integer numbers returning only numbers matching the selected filter:
     * <pre>
     *  - 0: only even numbers
     *  - 1: only numbers divisible by 3
     *  - 2: only 3-digit prime numbers
     * </pre>
     * @param filterFunctionIndex index of filter function in {@link filterFunctions}
     * @param limit amount of numbers returned
     * @return numbers matching the selected filter
     */
    List<Integer> filteredNumbers(int filterFunctionIndex, int limit);


    final static List<String> names = List.of(
        "Hendricks", "Raymond", "Pena", "Gonzalez", "Nielsen", "Hamilton",
        "Graham", "Gill", "Vance", "Howe", "Ray", "Talley", "Brock", "Hall",
        "Gomez", "Bernard", "Witt", "Joyner", "Rutledge", "Petty", "Strong",
        "Soto", "Duncan", "Lott", "Case", "Richardson", "Crane", "Cleveland",
        "Casey", "Buckner", "Hardin", "Marquez", "Navarro"
    );


    /**
     * Aufgabe 5: Return sub-list from input names filtered by a regular expression.
     * Order of names remains unchanged, regular expression refers to {@link java.util.regex.Pattern}.
     * @param names input names
     * @param regex regular expression according to {@link java.util.regex.Pattern}
     * @return sub-list of names
     */
    List<String> filteredNames(List<String> names, String regex);


    /**
     * Aufgabe 6: Return alphabetically sorted list of names up to limit.
     * @param names input names
     * @param limit maximum number of names returned
     * @return alphabetically sorted list of names up to limit
     */
    List<String> sortedNames(List<String> names, int limit);


    /**
     * Aufgabe 7: Return list of names sorted by name length (first criteria)
     * and alphabetically (second criteria) for names of equal length.
     * @param names input names
     * @return names sorted by name length
     */
    List<String> sortedNamesByLength(List<String> names);


    /*
     * Aufgabe 8: class 'Order' defines an order (Bestellung) of an
     * article of n units at a price per unit (in Cent). 
     */
    class Order {
        final String article;
        final long units;
        final long unitPrice;
        //
        public Order(String description, long units, long unitPrice) {
            this.article = description;
            this.units = units;
            this.unitPrice = unitPrice;
        }
        public String toString() {
            return String.format("\t- %-7s %dx %4d = %6d", article + ",", units, unitPrice, units * unitPrice);
        }
    }

    final static List<Order> orders = List.of(
        new Order("Becher", 2,  199),   // 2x  199 =  398
        new Order("Tasse",  7,  249),   // 7x  249 = 1743
        new Order("Stift",  4,   49),   // 4x   49 =  196
        new Order("Vase",   2,  999),   // 2x  999 = 1998
        new Order("Kanne",  5, 1499),   // 5x 1499 = 7495
        new Order("Lampe",  2, 1999),   // 2x 1999 = 3998
        new Order("Messer", 6,  789)    // 6x  789 = 4734
    );                                  // Summe:   20562 = 205,62€

    /**
     * Aufgabe 8: Calculate value of orders.
     * @param orders list of orders to process
     * @return value of orders
     */
    long calculateValue(List<Order> orders);


    /**
     * Aufgabe 9: Return list of orders sorted by order value (highest-value first).
     * @param orders list to sort
     * @return orders sorted by order value (highest-value first)
     */
    List<Order> sortOrdersByValue(List<Order> orders);


    /**
     * Static factory method that creates a {@link Numbers} instance from a list
     * of class names returning the instance of first class name found in the list
     * that can be instantiated or returns instance of {@link Numbers} class.
     * 
     * @return instance that is assignable from the {@link Numbers} interface.
     */
    public static Streams createStreamsInstance() {
        String[] args = null;
        return Application.create(new String[] { "streams.StreamsImpl_2", "streams.StreamsImpl" },
            cls -> Streams.class.isAssignableFrom(cls), args, () -> new StreamsImpl(args));
    }
}