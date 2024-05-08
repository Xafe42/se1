package numbers;

import java.util.List;
import java.util.Set;

import application.Application;


/**
 * Public interface for <i>"numbers"</i> assignment.
 * 
 */
public interface Numbers {

    /**
     * Aufgabe 1.) Calculate sum of numbers[].
     * 
     * @param numbers input
     * @return sum of numbers[]
     */
    int sum(int[] numbers);


    /**
     * Aufgabe 2.) Calculate sum of positive even numbers[].
     * 
     * @param numbers input
     * @return sum of positive even numbers[]
     */
    int sum_positive_even_numbers(int[] numbers);


    /**
     * Aufgabe 3.) Calculate sum of numbers[] recursively without using loops
     * (for, while, do/while).
     * 
     * @param numbers input numbers
     * @param i start index, calculate sum from index i in numbers[]
     * @return sum of numbers[]
     */
    int sum_recursive(int[] numbers, int i);


    /**
     * Aufgabe 4.) Return index of first occurrence of x in numbers[]
     * or return -1 if x was not found.
     * 
     * @param numbers input
     * @param x number to find
     * @return index of first occurrence of x in numbers[] or -1 if not found
     */
    int findFirst(int[] numbers, int x);


    /**
     * Aufgabe 5.) Return index of last occurrence of x in numbers[]
     * or return -1 if x was not found.
     * 
     * @param numbers input
     * @param x number to find
     * @return index of last occurrence of x in numbers[] or -1 if not found
     */
    int findLast(int[] numbers, int x);


    /**
     * Aufgabe 6.) Return list of all indices of number x in numbers[].
     * Return empty list, if x was not found.
     * 
     * @param numbers input
     * @param x number to find
     * @return list with all indices of x in numbers[]
     */
    List<Integer> findAll(int[] numbers, int x);


    /**
     * Represent a immutable pair of integer values a and b.
     * 
     * @param a first element of pair
     * @param b second element of pair
     */
    record Pair(int a, int b) {
        public String toString() { return String.format("(%d,%d)", a, b); }
    };


    /**
     * Aufgabe 7.) Return all pairs (a, b) in numbers[] with a + b = sum with
     * consolidating mirror copies such as (a, b) and (b, a) by returning
     * either (a, b) or (b, a), not both.
     * 
     * @param numbers input
     * @param sum to find
     * @return Set of all Pairs (a, b) that add to sum
     */
    Set<Pair> findSums(int[] numbers, int sum);


    /**
     * Aufgabe 8.) Find all combinations of numbers in numbers[] that add to sum.
     * 
     * @param numbers input
     * @param sum to find
     * @return set of all combinations of numbers that add to sum or empty list
     */
    Set<Set<Integer>> findAllSums(int[] numbers, int sum);


    /**
     * Static factory method that creates a {@link Numbers} instance from a list
     * of class names returning the instance of first class name found in the list
     * that can be instantiated or returns instance of {@link Numbers} class.
     * 
     * @return instance that is assignable from the {@link Numbers} interface.
     */
    public static Numbers createNumbersInstance() {
        String[] args = null;
        return Application.create(new String[] { "numbers.NumbersImpl_2", "numbers.NumbersImpl" },
            cls -> Numbers.class.isAssignableFrom(cls), args, () -> new NumbersImpl(args));
    }
}