package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * JUnit 5 test class of Numbers class, sum() method.
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_1_sum_Tests {

    /*
     * tested object is an instance of the Numbers class
     */
    private static Numbers testedObj;


    /**
     * Static setup method executed once for all tests. Creates
     * the test object.
     * 
     * @throws Exception is creation of test object fails
     */
    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        testedObj = Numbers.createNumbersInstance();
    }

    @Test
    @Order(100)
    void test100_sum_regular() {
        assertEquals(30, testedObj.sum(NumbersImpl.numbers));
    }

    @Test
    @Order(101)
    void test101_sum_regular() {
        assertEquals(50, testedObj.sum(NumbersImpl.numb_1));
    }

    @Test
    @Order(102)
    void test102_sum_regular() {
        assertEquals(10984, testedObj.sum(NumbersImpl.numb_2));
    }

    @Test
    @Order(103)
    void test103_sum_regular() {
        assertEquals(141466, testedObj.sum(NumbersImpl.numb_3));
    }
}