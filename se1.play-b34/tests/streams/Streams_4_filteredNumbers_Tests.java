package streams;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Streams_4_filteredNumbers_Tests {

    /*
     * tested object is an instance of the Numbers class
     */
    private static Streams testedObj;

    // https://prime-numbers.info/list/first-1000-primes
    private final Set<Integer> threeDigitPrimes = Arrays.asList(
        /* 2,  3,   5,   7,  11,  13,  17,  19,  23,  29,
         31,  37,  41,  43,  47,  53,  59,  61,  67,  71,
         73,  79,  83, 89, 97,*/ 101, 103, 107, 109, 113,
        127, 131, 137, 139, 149, 151, 157, 163, 167, 173,
        179, 181, 191, 193, 197, 199, 211, 223, 227, 229,
        233, 239, 241, 251, 257, 263, 269, 271, 277, 281,
        283, 293, 307, 311, 313, 317, 331, 337, 347, 349,
        353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
        419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
        467, 479, 487, 491, 499, 503, 509, 521, 523, 541,
        547, 557, 563, 569, 571, 577, 587, 593, 599, 601,
        607, 613, 617, 619, 631, 641, 643, 647, 653, 659,
        661, 673, 677, 683, 691, 701, 709, 719, 727, 733,
        739, 743, 751, 757, 761, 769, 773, 787, 797, 809,
        811, 821, 823, 827, 829, 839, 853, 857, 859, 863,
        877, 881, 883, 887, 907, 911, 919, 929, 937, 941,
        947, 953, 967, 971, 977, 983, 991, 997)
        //
        .stream().collect(Collectors.toSet());

    /**
     * Static setup method executed once for all tests. Creates
     * the test object.
     * 
     * @throws Exception is creation of test object fails
     */
    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        testedObj = Streams.createStreamsInstance();
    }

    @Test
    @Order(400)
    void test400_filteredNumbers_50evenNumbers_regular() {
        //
        // 50 even numbers
        List<Integer> actual = testedObj.filteredNumbers(0, 50);
        assertEquals(50, actual.size());
        //
        // verify all numbers are even
        boolean testAllNumbers = actual.stream()
            .map(n -> n > 0 && n % 2 == 0)
            .reduce(true, (accumulator, n) -> accumulator && n);
        //
        assertTrue(testAllNumbers);
    }

    @Test
    @Order(410)
    void test410_filteredNumbers_50divisibleBy3Numbers_regular() {
        //
        // 50 numbers divisible by 3
        List<Integer> actual = testedObj.filteredNumbers(1, 50);
        assertEquals(50, actual.size());
        //
        // verify all numbers are divisible by 3
        boolean testAllNumbers = actual.stream()
            .map(n -> n > 0 && n % 3 == 0)
            .reduce(true, (accumulator, n) -> accumulator && n);
        //
        assertTrue(testAllNumbers);
    }

    @Test
    @Order(420)
    void test420_filteredNumbers_50primeNumbers_regular() {
        //
        List<Integer> actual = testedObj.filteredNumbers(2, 50);
        assertEquals(50, actual.size());
        //
        // verify all numbers are three-digit prime numbers
        boolean testAllNumbers = actual.stream()
            .map(n -> threeDigitPrimes.contains(n))
            .reduce(true, (accumulator, n) -> accumulator && n);
        //
        assertTrue(testAllNumbers);
    }

    @Test
    @Order(430)
    void test430_filteredNumbers_different_even_numbers_returned() {
        int limit = 5;
        List<Integer> l1 = testedObj.filteredNumbers(0, limit);
        //
        // other three-digit prime numbers
        List<Integer> l2 = testedObj.filteredNumbers(0, limit);
        //
        assertEquals(limit, l1.size());
        assertEquals(limit, l2.size());
        //
        // verify l1 and l2 contain different numbers
        boolean different = true;
        for(int i=0; different && i < limit; i++) {
            different = l1.get(i) != l2.get(i);
        }
        assertTrue(different);
    }

    @Test
    @Order(431)
    void test431_filteredNumbers_different_div_by_three_numbers_returned() {
        int limit = 5;
        List<Integer> l1 = testedObj.filteredNumbers(1, limit);
        //
        // other three-digit prime numbers
        List<Integer> l2 = testedObj.filteredNumbers(1, limit);
        //
        assertEquals(limit, l1.size());
        assertEquals(limit, l2.size());
        //
        // verify l1 and l2 contain different numbers
        boolean different = true;
        for(int i=0; different && i < limit; i++) {
            different = l1.get(i) != l2.get(i);
        }
        assertTrue(different);
    }

    @Test
    @Order(432)
    void test432_filteredNumbers_different_prime_numbers_returned() {
        int limit = 5;
        // three-digit prime numbers
        List<Integer> l1 = testedObj.filteredNumbers(2, limit);
        //
        // other three-digit prime numbers
        List<Integer> l2 = testedObj.filteredNumbers(2, limit);
        //
        assertEquals(limit, l1.size());
        assertEquals(limit, l2.size());
        //
        // verify l1 and l2 contain different numbers
        boolean different = true;
        for(int i=0; different && i < limit; i++) {
            different = l1.get(i) != l2.get(i);
        }
        assertTrue(different);
    }

    @Test
    @Order(490)
    void test490_filteredNumbers_50evenNumbers_irregularNegativeIndex() {
        //
        IndexOutOfBoundsException thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            // Code under test is supposed to throw exception for illegal index
            testedObj.filteredNumbers(-1, 50);    // -1 is an illegal index
        });
        assertEquals("filterFunctionIndex out of range: -1", thrown.getMessage());

        thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            // Code under test is supposed to throw exception for illegal index
            testedObj.filteredNumbers(-36256, 50);    // -1 is an illegal index
        });
        assertEquals("filterFunctionIndex out of range: -36256", thrown.getMessage());

        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class, () -> {
            // Code under test is supposed to throw exception for illegal limit
            testedObj.filteredNumbers(0, -2);    // -2 is an illegal limit
        });
        assertEquals("negative limit: -2", thrown2.getMessage());
    }

    @Test
    @Order(491)
    void test491_filteredNumbers_50evenNumbers_irregularIndex() {
        //
        IndexOutOfBoundsException thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            // Code under test that is supposed to throws exception for illegal index
            testedObj.filteredNumbers(36256, 50);    // -1 is an illegal index
        });
        assertEquals("filterFunctionIndex out of range: 36256", thrown.getMessage());
    }
}