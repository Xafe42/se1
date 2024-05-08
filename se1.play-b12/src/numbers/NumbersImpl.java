package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation class of {@link Numbers} interface.
 * 
 * @version <code style=
 *          color:green>{@value application.package_info#Version}</code>
 * @author <code style=
 *         color:blue>{@value application.package_info#Author}</code>
 */
public class NumbersImpl implements Numbers, Runnable {

    /*
     * arguments passed from command line
     */
    private final String[] args;

    /*
     * Numbers with negative numbers and duplicates.
     */
    static final int[] numbers = { -2, 4, 9, 4, -3, 4, 9, 5 };

    /*
     * Numbers with no negative numbers and no duplicates.
     */
    static final int[] numb_1 = { 8, 10, 7, 2, 14, 5, 4 };

    /*
     * Larger set of 24 numbers, no negatives, no duplicates.
     */
    static final int[] numb_2 = { // 24 numbers
            371, 682, 446, 754, 205, 972, 600, 163, 541, 672,
            27, 170, 226, 7, 190, 639, 87, 773, 651, 370,
            125, 774, 903, 636// ,225, 463, 286, 569, 384, 9,
    }; // add more numbers to find more solutions

    /*
     * Even larger set of 63 numbers, no negatives, no duplicates.
     */
    static final int numb_3[] = {
            799, 2377, 936, 3498, 1342, 493, 1635, 4676, 1613, 3851,
            1445, 4506, 3346, 7, 2141, 2064, 1491, 908, 78, 3325,
            1756, 3691, 23, 1995, 1800, 15, 2784, 4305, 36, 2532,
            4292, 4802, 2522, 4183, 3261, 2610, 803, 2656, 498, 1668,
            2038, 2194, 440, 463, 4047, 4235, 3931, 756, 521, 4042,
            3302, 485, 1002, 408, 4691, 3387, 3104, 3658, 2241, 4382,
            1220, 3656, 500,
    };

    /**
     * Public {@code String[] args} constructor.
     * 
     * @param args arguments passed from command line
     */
    public NumbersImpl(String[] args) {
        this.args = args != null ? args : new String[] {};
    }

    /**
     * Aufgabe 1.) Calculate sum of numbers[].
     * 
     * @param numbers input
     * @return sum of numbers[]
     */
    @Override
    public int sum(int[] numbers) {

        int result = 0;

        for (int number : numbers) {
            result += number;
        }

        return result;
    }

    /**
     * Aufgabe 2.) Calculate sum of positive even numbers[].
     * 
     * @param numbers input
     * @return sum of positive even numbers[]
     */
    @Override
    public int sum_positive_even_numbers(int[] numbers) {
        int result = 0;
        for (int number : numbers) {
            if (number > 0 && number % 2 == 0) {
                result += number;
            }
        }
        return result;
    }

    /**
     * Aufgabe 3.) Calculate sum of numbers[] recursively without using loops
     * (for, while, do/while).
     * 
     * @param numbers input numbers
     * @param i       start index, calculate sum from index i in numbers[]
     * @return sum of numbers[]
     */
    @Override
    public int sum_recursive(int[] numbers, int i) {
        if (i >= numbers.length) {
            return 0;
        }
        return numbers[i] + sum_recursive(numbers, i + 1);
    }

    /**
     * Aufgabe 4.) Return index of first occurrence of x in numbers[]
     * or return -1 if x was not found.
     * 
     * @param numbers input
     * @param x       number to find
     * @return index of first occurrence of x in numbers[] or -1 if not found
     */
    @Override
    public int findFirst(int[] numbers, int x) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == x) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Aufgabe 5.) Return index of last occurrence of x in numbers[]
     * or return -1 if x was not found.
     * 
     * @param numbers input
     * @param x       number to find
     * @return index of last occurrence of x in numbers[] or -1 if not found
     */
    @Override
    public int findLast(int[] numbers, int x) {
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] == x) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Aufgabe 6.) Return list of all indices of number x in numbers[].
     * Return empty list, if x was not found.
     * 
     * @param numbers input
     * @param x       number to find
     * @return list with all indices of x in numbers[]
     */
    @Override
    public List<Integer> findAll(int[] numbers, int x) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == x) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Aufgabe 7.) Return all pairs (a, b) in numbers[] with a + b = sum with
     * consolidating mirror copies such as (a, b) and (b, a) by returning
     * either (a, b) or (b, a), not both.
     * 
     * @param numbers input
     * @param sum     to find
     * @return Set of all Pairs (a, b) that add to sum
     */
    @Override
    public Set<Pair> findSums(int[] numbers, int sum) {
        Set<Pair> result = new HashSet<>();

        for (int i = 0; i < numbers.length; i++) {
            int a = numbers[i];
            for (int j = i + 1; j < numbers.length; j++) {
                int b = numbers[j];
                if (a + b == sum) {
                    Pair pair = new Pair(Math.min(a, b), Math.max(a, b));
                    result.add(pair);
                }
            }
        }

        return result;
    }

    /**
     * Aufgabe 8.) Find all combinations of numbers in numbers[] that add to sum.
     * 
     * @param numbers input
     * @param sum     to find
     * @return set of all combinations of numbers that add to sum or empty list
     */
    @Override
    public Set<Set<Integer>> findAllSums(int[] numbers, int sum) {
        Set<Set<Integer>> result = new HashSet<>();
        findAllSumsHelper(numbers, sum, new HashSet<>(), 0, result);
        return result;
    }

    /**
     * Recursive helper method to find all combinations of numbers that add to the
     * given sum.
     * 
     * @param numbers    input array
     * @param target     the remaining sum to reach
     * @param currentSet current combination of numbers
     * @param index      the current index in the input array
     * @param result     the set of all valid combinations
     */
    private void findAllSumsHelper(int[] numbers, int target, Set<Integer> currentSet, int index,
            Set<Set<Integer>> result) {
        if (target == 0) { // If we found a combination that sums to the target
            result.add(new HashSet<>(currentSet)); // Add a copy of the current set to the result
            return;
        }

        if (target < 0 || index >= numbers.length) { // If the sum is exceeded or we're out of bounds
            return;
        }

        // Include the current number in the set and recursively find further
        // combinations
        currentSet.add(numbers[index]);
        findAllSumsHelper(numbers, target - numbers[index], currentSet, index + 1, result);

        // Exclude the current number from the set and try the next index
        currentSet.remove(numbers[index]);
        findAllSumsHelper(numbers, target, currentSet, index + 1, result);
    }

    /**
     * Parse and execute command line arguments:
     * 
     * <pre>
     * java application.Application op=sum_pen n=numbers
     * </pre>
     */
    @Override
    public void run() {
        System.out.println(String.format("Hello, %s", this.getClass().getSimpleName()));

        /**
         * Local class to represent command comprised from command line parameters.
         */
        class Command {
            String op = "";
            String n = "";
            String x = "";
            String y = "";
            String sum = "";

            //
            boolean isEmpty() {
                return (op + n + x + y + sum).length() == 0;
            }

            int[] getNumbers() {
                switch (n) {
                    case "numbers":
                        return numbers;
                    case "numb_1":
                        return numb_1;
                    case "numb_2":
                        return numb_2;
                    case "numb_3":
                        return numb_3;
                    default:
                        System.out.println(String.format("did not find numbers: '%s'", n));
                        return new int[] {};
                }
            }

            int toInt(String name, String value) {
                try {
                    return Integer.parseInt(value);
                } catch (NumberFormatException nex) {
                    System.out.println(
                            String.format("NumberFormatException: '%s' %s", name, nex.getMessage().toLowerCase()));
                    return 0;
                }
            }

            String print(String n, String v) {
                return v.length() == 0 ? "" : String.format(", %s=%s", n, v);
            }

            public String toString() {
                var num = Arrays.toString(getNumbers());
                num = num.length() < 36 ? String.format("'%s': %s", n, num) : String.format("'%s'", n);
                return String.format("%s(%s)%s%s%s", op, num, print("x", x), print("y", y), print("sum", sum));
            }
        }
        ;

        List<Command> cmds = new ArrayList<>();
        Command cmd = new Command();
        //
        // parse args, combine and collect commands
        for (var arg : args) {
            String[] spl = arg.split("=");
            if (spl.length > 1) {
                switch (spl[0]) {
                    case "op":
                        if (cmd.op.length() > 0) {
                            cmds.add(cmd);
                            cmd = new Command();
                        }
                        cmd.op = spl[1];
                        break;
                    case "n":
                        cmd.n = spl[1];
                        break;
                    case "x":
                        cmd.x = spl[1];
                        break;
                    case "y":
                        cmd.y = spl[1];
                        break;
                    case "sum":
                        cmd.sum = spl[1];
                        break;
                }
            }
        }
        if (!cmd.isEmpty()) {
            cmds.add(cmd);
        }
        // execute commands calling Numbers functions and report results
        cmds.stream()
                .peek(System.out::println) // print command
                .map(cmd2 -> {
                    String result = null;
                    int[] numbers = cmd2.getNumbers();
                    switch (cmd2.op) {
                        //
                        case "sum": // calculate result
                            var res = sum(numbers);
                            result = String.format("sum() = %d", res);
                            break;
                        //
                        case "sum_pen":
                            res = sum_positive_even_numbers(numbers);
                            result = String.format("sum_pen() = %d", res);
                            break;
                        //
                        case "sum_rec":
                            res = sum_recursive(numbers, 0);
                            result = String.format("sum_rec() = %d", res);
                            break;
                        //
                        case "findFirst":
                            int x = cmd2.toInt("x", cmd2.x);
                            res = findFirst(numbers, x);
                            result = String.format("findFirst(x=%d) = %d", x, res);
                            break;
                        //
                        case "findLast":
                            x = cmd2.toInt("x", cmd2.x);
                            res = findLast(numbers, x);
                            result = String.format("findLast(x=%d) = %d", x, res);
                            break;
                        //
                        case "findAll":
                            x = cmd2.toInt("x", cmd2.x);
                            var resList = findAll(numbers, x);
                            result = String.format("findAll(x=%d) = %s", x, resList);
                            break;
                        //
                        case "findSums":
                            var sum = cmd2.toInt("sum", cmd2.sum);
                            var resPairs = findSums(numbers, sum);
                            // result = String.format("%s, solutions: %d", resPairs, resPairs.size());
                            result = prettyPrintPairs(resPairs);
                            break;
                        //
                        case "findAllSums":
                            sum = cmd2.toInt("sum", cmd2.sum);
                            var resAny = findAllSums(numbers, sum);
                            // result = String.format("findAllSums() = %s", prettyPrint(resAny),
                            // resAny.size());
                            result = prettyPrint(resAny);
                            break;
                    }
                    return Optional.ofNullable(result);
                })
                .map(opt -> String.format(" - result: %s\n", opt.isPresent() ? opt.get() : "none"))
                .forEach(System.out::println); // print result
    }

    /**
     * Pretty-print variable length {@code Set<Pair>} numbers.
     * 
     * @param pairs to print
     * @return pretty-printed pairs
     */
    private String prettyPrintPairs(Set<Pair> pairs) {
        StringBuffer sb = new StringBuffer("[");
        String numStr = pairs != null ? pairs.toString() : "";
        boolean large = numStr.length() > 40;
        int j = 0;
        for (Pair p : pairs) {
            sb.append(sb.length() > 1 ? ", " : "");
            if (large && j % 5 == 0) {
                sb.append("\n    - ");
            }
            sb.append(p.toString());
            j++;
        }
        sb.append(large ? "\n   ], " : "], ");
        sb.append(String.format("solutions: %d", j));
        return sb.toString();
    }

    /**
     * Pretty-print variable length, nested {@code Set<Set<Integer>>} numbers.
     * 
     * @param nested set to print
     * @return pretty-printed nested sets
     */
    private String prettyPrint(Set<Set<Integer>> numbers) {
        StringBuffer sb = new StringBuffer("[");
        String numStr = numbers != null ? numbers.toString() : "";
        boolean large = numStr.length() > 40;
        var solutions = numbers.stream()
                .sorted((a, b) -> Integer.compare(a.size(), b.size()))
                .map(sol -> {
                    sb.append(sb.length() > 1 ? ", " : "");
                    sb.append(large ? "\n    - " : "").append(sol.toString());
                    return sol;
                }).collect(Collectors.toList());
        sb.append(large ? "\n   ], " : "], ");
        sb.append(String.format("solutions: %d", solutions.size()));
        return sb.toString();
    }
}