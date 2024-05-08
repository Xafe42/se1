package numbers;

import java.util.*;
import java.util.stream.*;


/**
 * Implementation class of {@link Numbers} interface with actual
 * implementations of methods.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public class NumbersImpl_2 extends NumbersImpl {

    /**
     * Public {@code String[] args} constructor.
     * @param args arguments passed from command line
     */
    public NumbersImpl_2(String[] args) {
        super(args);
    }

    /**
     * Aufgabe 1.) Calculate sum of numbers[].
     * 
     * @param numbers input
     * @return sum of numbers[]
     */
    @Override
    public int sum(int[] numbers) {
        // int sum = 0;
        // for(int i=0; i < numbers.length; i++) {
        //     sum = sum + numbers[i];
        // }
        // return sum;
        //
        // return Arrays.stream(numbers).reduce(0, (a, b) -> a + b);
        // return Arrays.stream(numbers).reduce(0, Integer::sum);
        return Arrays.stream(numbers).sum();
    }


    /**
     * Aufgabe 2.) Calculate sum of positive even numbers[].
     * 
     * @param numbers input
     * @return sum of positive even numbers[]
     */
    @Override
    public int sum_positive_even_numbers(int[] numbers) {
        return Arrays.stream(numbers)
            .filter(n -> n > 0 && n % 2==0)
            .sum();
    }


    /**
     * Aufgabe 3.) Calculate sum of numbers[] recursively without using loops
     * (for, while, do/while).
     * 
     * @param numbers input numbers
     * @param i start index, calculate sum from index i in numbers[]
     * @return sum of numbers[]
     */
    @Override
    public int sum_recursive(int[] numbers, int i) {
        return i < 0 || i >= numbers.length? 0 : numbers[i] + sum_recursive(numbers, i + 1);
    }


    /**
     * Aufgabe 4.) Return index of first occurrence of x in numbers[]
     * or return -1 if x was not found.
     * 
     * @param numbers input
     * @param x number to find
     * @return index of first occurrence of x in numbers[] or -1 if not found
     */
    @Override
    public int findFirst(int[] numbers, int x) {
        // for(int i=0; i < numbers.length; i++) {
        //     if(numbers[i]==x)
        //         return i;   // return index of first match
        // }
        // return -1;
        //
        return IntStream.range(0, numbers.length)
            .filter(i -> numbers[i]==x)
            .findFirst().orElse(-1);
    }


    /**
     * Aufgabe 5.) Return index of last occurrence of x in numbers[]
     * or return -1 if x was not found.
     * 
     * @param numbers input
     * @param x number to find
     * @return index of last occurrence of x in numbers[] or -1 if not found
     */
    @Override
    public int findLast(int[] numbers, int x) {
        // int result = -1;
        //
        // forward loop needs to run to the end and remember finds
        // for(int i=0; i < numbers.length; i++) {
        //     if(numbers[i]==x)
        //         result = i;
        // }
        //
        // backward loop can return immediately
        // for(int i=numbers.length-1; i >= 0; i--) {
        //     if(numbers[i]==x)
        //         return i;
        // }
        // return result;
        //
        return IntStream.range(0, numbers.length)
            .boxed()
            .sorted(Collections.reverseOrder())
            .filter(i -> numbers[i]==x)
            .findFirst().orElse(-1);
    }


    /**
     * Aufgabe 6.) Return list of all indices of number x in numbers[].
     * Return empty list, if x was not found.
     * 
     * @param numbers input
     * @param x number to find
     * @return list with all indices of x in numbers[]
     */
    @Override
    public List<Integer> findAll(int[] numbers, int x) {
        // List<Integer> result = new ArrayList<Integer>();
        // for(int i=0; i < numbers.length; i++) {
        //     if(numbers[i]==x)
        //         result.add(i);  // collect results
        // }
        // return result;
        //
        return IntStream.range(0, numbers.length)
            .filter(i -> numbers[i]==x)
            .boxed()
            .collect(Collectors.toList());
    }


    /**
     * Aufgabe 7.) Return all pairs (a, b) in numbers[] with a + b = sum with
     * consolidating mirror copies such as (a, b) and (b, a) by returning
     * either (a, b) or (b, a), not both.
     * 
     * @param numbers input
     * @param sum to find
     * @return Set of all Pairs (a, b) that add to sum
     */
    @Override
    public Set<Pair> findSums(int[] numbers, int sum) {
    	Set<Pair> result = new HashSet<>();
        int algorithm = 2;
        //
        switch(algorithm) {
        case 1:
            // find matching pairs with two nested loops requiring n*(n/2) passes, complexity: n^2
            for(int i=0; i < numbers.length; i++) {
                for(int j=i+1; j < numbers.length; j++) {
                    if((numbers[i] + numbers[j]) == sum) {
                        var pair = new Pair(numbers[i], numbers[j]);
                        // avoid mirror duplicates: [x, y], [y, x] --> [x, y]
                        if( ! result.contains(new Pair(numbers[j], numbers[i]))) {
                            result.add(pair);
                        }
                    }
                }
            }
            break;
        //
        case 2:
            // optimized algorithm using one pass and a complements cache with complexity: n.
            int first = numbers.length > 0? numbers[0] : 0;
            // remember complements of prior numbers
            Set<Integer> complements = new HashSet<Integer>();
            complements.add(sum - first);
            for(int i=1; i < numbers.length; i++) {
                int n = numbers[i];
                int complement = sum - n;
                if(complements.contains(n)) {
                    var pair = new Pair(n, complement);
                    // avoid mirror duplicates: [x, y], [y, x] --> [x, y]
                    if( ! result.contains(new Pair(complement, n))) {
                        result.add(pair);
                    }
                }
                complements.add(complement);
            }
            break;
        }
        return result;
    }


    /**
     * Aufgabe 8.) Find all combinations of numbers in numbers[] that add to sum.
     * 
     * @param numbers input
     * @param sum to find
     * @return set of all combinations of numbers that add to sum or empty list
     */
    @Override
    public Set<Set<Integer>> findAllSums(int[] numbers, int sum) {
        Set<Set<Integer>> result;
        int algorithm = 3;
        //
        switch(algorithm) {
        case 1:
            result = findAllSumsBruteForcePSet(numbers, sum);
            break;
        //
        case 2:
            result = findAllSumsBruteForce64(numbers, sum);
            break;
        //
        case 3:
            var numbersList = IntStream.of(numbers).boxed().collect(Collectors.toList());
            result = findAllSumsBranchAndBound(numbersList, sum);
            break;
        //
        default:
            result = new HashSet<>();
        }
        return result;
    }


    /**
     * Brute-force algorithm that uses powerSet() function to enumerate the full
     * 2^n power set.
     * 
     * @param sum to find.
     * @return list of all combinations of numbers that add to sum or empty list.
     */
    Set<Set<Integer>> findAllSumsBruteForcePSet(int[] numbers, int sum) {
        var result = new HashSet<Set<Integer>>();
        var input = Arrays.stream(numbers).boxed().collect(Collectors.toSet());
        var pset = powerSet(input);
        //
        pset.forEach(set -> result.add(new HashSet<>(set)));
        return result;
    }


    /**
     * Brute-force algorithm using a 64-bit number to enumerate the full
     * 2^n power set (n < 64).
     * 
     * @param numbers input
     * @param sum to find.
     * @return set of all combinations of numbers that add to sum or empty list.
     */
    Set<Set<Integer>> findAllSumsBruteForce64(int[] numbers, int sum) {
        var result = new HashSet<Set<Integer>>();
        int len = numbers.length;
        long mask = ~(0xffffffffffffffffL << len);  // 64-bit mask
        //
        Set<Integer> matchingSubset = new HashSet<Integer>();
        for(long m=0; m <= mask; m++) {
            matchingSubset.clear();
            int sum2 = 0;
            for(int i=0; i < len; i++) {
                if(((1L << i) & m) != 0L) {
                    matchingSubset.add(numbers[i]);
                    sum2 += numbers[i];
                }
            }
            if(sum2==sum) {
                // make copy for result list
                result.add(new HashSet<>(matchingSubset));
            }
        }
        return result;
    }


    /**
     * The actual branch-and-bound algorithm uses Sets (not Lists) to avoid duplicates.
     * The algorithm cuts infeasible solutions from the 2^n power set exploration
     * space. A solution is infeasible when an incomplete sub set already exceeds
     * the target sum.
     * 
     * A solution candidate becomes infeasible, when its numbers exceed the target
     * sum stopping further exploration.
     * 
     * @param sum sum to find.
     * @param numbers converted to List<Integer>.
     * @return subset of power set with numbers that add to sum.
     */
    Set<Set<Integer>> findAllSumsBranchAndBound(List<Integer> numbers, int sum) {
        var result = new HashSet<Set<Integer>>();
        //
        for(int i=0; i < numbers.size(); i++) {
            int n = numbers.get(i);
            if(n==sum) {
                Set<Integer> matchingSubset = new HashSet<Integer>();
                matchingSubset.add(n);
                result.add(matchingSubset);
            } else {
                // continue exploration only when target sum is not exceeded
                if(n < sum) {
                    var subList = new ArrayList<>(numbers); // copy
                    subList.remove(i);  // remove i-th element
                    //
                    // recursively explore sub-solutions with reduced sub-list and target sum
                    findAllSumsBranchAndBound(subList, sum-n).forEach(subSolution -> {
                        int subSum = subSolution.stream().reduce(0, Integer::sum);
                        if(subSum + n == sum) {
                            subSolution.add(n);
                            result.add(subSolution);
                        }
                    });
                }
            }
        }
        return result;
    }


    /**
     * Powerset is set of all subsets, including the empty set {}.
     * 
     * @param inputSet set for which powerset is produced
     * @return powerset of elements in input set
     */
    private Set<Set<Integer>> powerSet(Set<Integer> inputSet) {
        var pSet = new HashSet<Set<Integer>>(); // result set
        //
        pSet.add(Set.of()); // add empty set (always part of powerSet)
        //
        // iterate with e through inputSet, separate remainder: inputSet without e
        inputSet.forEach(e -> {
            pSet.add(Set.of(e));        // add single element e to pSet
            //
            if(inputSet.size() > 2) {   // separate remainder for more than 2 elements
                var remainder = new HashSet<Integer>(inputSet);
                remainder.remove(e);    // remove e from copied inputSet
                //
                powerSet(remainder).forEach(    // recurse with remainder
                    rpSet -> pSet.add(rpSet)    // add all remainder subsets to pSet
                );
            }
            pSet.add(inputSet);         // add inputSet as last element
        });
        return pSet;
    }
}