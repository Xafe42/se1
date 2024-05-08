<!-- check-out single branch from repo:
        git clone -b b34 --single-branch https://github.com/sgra64/se1.play.git
 -->

# Assignment B4

Assignment B4 demonstrates the use of the
[Java Stream API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/stream/Stream.html).

Interface [Streams.java](src/streams/Streams.java) defines a number of methods
that will be implemented using the Java Stream API:

- `List<Integer> tenRandomNumbers();`                               (1Pt)

- `List<Integer> tenEvenRandomNumbers();`                           (1Pt)

- `List<Integer> tenSortedEvenRandomNumbers();`                     (1Pt)

- `List<Integer> filteredNumbers(int filterFunctionIndex, int limit);` (1Pt)

- `List<String> filteredNames(List<String> names, String regex);`   (1Pt)

- `List<String> sortedNames(List<String> names, int limit);`        (1Pt)

- `List<String> sortedNamesByLength(List<String> names);`           (1Pt)

- `long calculateValue(List<Order> orders);`                        (1Pt)

- `List<Order> sortOrdersByValue(List<Order> orders);`              (1Pt)

- alle JUnit Tests passieren.                                       (1pt)


Follow steps (1-8) below to complete the assignment. Implement a method and
verify with passing the corresponding JUnit-test before proceeding with the
next method.

Points are credited when corresponding JUnit tests pass.


1. [Getting Started](#1-getting-started)

2. [Sourcing the Project](#2-sourcing-the-project)

3. [Building the Application](#3-building-the-application)

4. [Running the Application](#4-running-the-application)

5. [Completing Numbers Methods](#5-completing-numbers-methods)

6. [Running JUnit Tests](#6-running-junit-tests)

7. [Generating Javadoc](#7-generating-javadoc)

8. [Packaging the Application](#8-packaging-the-application)


&nbsp;
## 1. Getting started

This assignment adds package `streams` to `src` and `tests`:

```sh
--<se1.play>:
 |
 +--<src>                       # Java source code
 |   +-- module-info.java           # module defintion file
 |   +--<application>               # Java package 'application'
 |   |   +-- package-info.java      # 'application'-package defintion
 |   |   +-- Application.java       # main application program with main()
 |   |
 |   +--<streams>               # new 'Streams' package
 |       +-- Streams.java           # Streams interface
 |       +-- StreamsImpl.java       # Streams implementation class
 |       +-- package-info.java
 |
 +--<tests>                     # Unit-test source code separated from src/main
 |   +--<application>               # mirrored src package structure
 |   |   +-- Application_0_always_pass_Tests.java   # initial JUnit-test
 |   |
 |   +--<numbers>               # new 'Numbers' JUnit tests
 |       +-- Matchers.java      # assertion matcher that ignores order in collections
 |       +-- Streams_1_tenRandomNumbers_Tests.java
 |       +-- Streams_2_tenEvenRandomNumbers_Tests.java
 |       +-- Streams_3_tenSortedEvenRandomNumbers_Tests.java
 |       +-- Streams_4_filteredNumbers_Tests.java
 |       +-- Streams_5_filteredNames_Tests.java
 |       +-- Streams_6_sortedNames_Tests.java
 |       +-- Streams_7_sortedNamesByLength_Tests.java
 |       +-- Streams_8_calculateOrderValue_Tests.java
 |       +-- Streams_9_sortByOrderValue_Tests.java
 |
```


&nbsp;
## 2. Sourcing the Project

Sourcing the project means to set up the project environment:

```sh
source .env/project.sh
```

Make sure files are in the proper place. Open a terminal and navigate
to the project directory:

```sh
find src tests resources
```

Output:

```
src
src/application
src/application/Application.java
src/application/package-info.java
src/module-info.java
src/streams
src/streams/Streams.java
src/streams/StreamsImpl.java
tests
tests/application
tests/application/Application_0_always_pass_Tests.java
tests/streams
tests/streams/Streams_1_tenRandomNumbers_Tests.java
tests/streams/Streams_2_tenEvenRandomNumbers_Tests.java
tests/streams/Streams_3_tenSortedEvenRandomNumbers_Tests.java
tests/streams/Streams_4_filteredNumbers_Tests.java
tests/streams/Streams_5_filteredNames_Tests.java
tests/streams/Streams_6_sortedNames_Tests.java
tests/streams/Streams_7_sortedNamesByLength_Tests.java
tests/streams/Streams_8_calculateOrderValue_Tests.java
tests/streams/Streams_9_sortByOrderValue_Tests.java
resources
resources/application.properties
resources/logging.properties
resources/META-INF
resources/META-INF/MANIFEST.MF
```


&nbsp;
## 3. Building the Application

Command `show` shows operations for the *Build-Process*:

```sh
show
show --all
```

```
source | project:
  source .env/project.sh

classpath:
  echo $CLASSPATH | tr "[;:]" "\n"

compile:
  javac $(find src/main -name '*.java') -d bin/classes; \
  copy src/resources bin/resources

compile-tests:
  javac $(find src/tests -name '*.java') -d bin/test-classes; \
  copy src/resources bin/resources

resources:
  copy src/resources bin/resources

run:
  java application.Application

run-tests:
  java -jar libs/junit-platform-console-standalone-1.9.2.jar \
    $(eval echo $JUNIT_OPTIONS) --scan-class-path

javadoc:
  javadoc -d docs $(eval echo $JDK_JAVADOC_OPTIONS) \
    $(cd src/main; find . -type f | xargs dirname | uniq | cut -c 3-)

clean:
  rm -rf bin logs docs

wipe:
```

Execute build steps with the `build` or `mk` (make) command:

```sh
mk compile compile-tests                    # compile source and test code
```

The last command is called a *clean build*. It clears the `bin` directory,
removes all content and re-compiles all source code.

The result is in the `bin` directory:

```sh
find bin
```

Output

```
bin
bin/classes
bin/classes/application
bin/classes/application/Application.class
bin/classes/application/package-info.class
bin/classes/application/package_info.class
bin/classes/module-info.class
bin/classes/streams
bin/classes/streams/Streams$Order.class
bin/classes/streams/Streams.class
bin/classes/streams/StreamsImpl.class
bin/resources
bin/resources/application.properties
bin/resources/logging.properties
bin/test-classes
bin/test-classes/application
bin/test-classes/application/Application_0_always_pass_Tests.class
bin/test-classes/streams
bin/test-classes/streams/Streams_1_tenRandomNumbers_Tests.class
bin/test-classes/streams/Streams_2_tenEvenRandomNumbers_Tests.class
bin/test-classes/streams/Streams_3_tenSortedEvenRandomNumbers_Tests.class
bin/test-classes/streams/Streams_4_filteredNumbers_Tests.class
bin/test-classes/streams/Streams_5_filteredNames_Tests.class
bin/test-classes/streams/Streams_6_sortedNames_Tests.class
bin/test-classes/streams/Streams_7_sortedNamesByLength_Tests.class
bin/test-classes/streams/Streams_8_calculateOrderValue_Tests.class
bin/test-classes/streams/Streams_9_sortByOrderValue_Tests.class
```


&nbsp;
## 4. Running the Application

After building the application, the application can be called directly
with `java` or through the build command `mk run` from the project
environment.

```sh
mk run
java application.Application
```

Output:

```
- tenRandomNumbers():
    -> []

- tenEvenRandomNumbers():
    -> []

- tenSortedEvenRandomNumbers():
    -> []

- filteredNumbers(0, 15):       // 15 random even numbers
    -> []

- filteredNumbers(1, 15):       // 15 random numbers divisible by 3
    -> []

- filteredNumbers(2, 15):       // 15 random two-digit prime numbers
    -> []

- filteredNames(names, ".*ez$"):        // names ending with "ez"
    -> []

- sortedNames(names, 8):        // first 8 names from sorted name list
    -> []

- sortedNamesByLength(names):   // names sorted by length
    -> []

- calculateValue(orders):
    -> 0

- sortOrdersByValue(orders):    // orders sorted by value
    ->                           --------
                                 0
                          ========
done.
```


&nbsp;
## 5. Completing Numbers Methods

When the applicaiton runs, the `run()` method is invoked from
[StreamsImpl.java](src/streams/StreamsImpl.java),
which implements the
[Streams.java](src/streams/Streams.java)
interface.

```java
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

}
```

Complete methods step-by-step in the implementation class
[StreamsImpl.java](src/streams/StreamsImpl.java)
and re-run the application.

After completing the first three methods with output:

```
- tenRandomNumbers():
    -> [714, 878, 770, 667, 610, 926, 359, 213, 423, 156]

- tenEvenRandomNumbers():
    -> [288, 600, 350, 912, 972, 778, 394, 160, 64, 584]

- tenSortedEvenRandomNumbers():
    -> [10, 184, 230, 468, 682, 686, 790, 812, 882, 980]
```


&nbsp;
## 6. Running JUnit Tests

When tests fail, locate the test-method and verify *expected* values
and *actual* values obtained from from your method implementation.

Run final JUnit-Tests in the IDE for the tests you managed to pass.

Run the first test `streams.Streams_1_tenRandomNumbers_Tests`:

```sh
java -jar libs/junit-platform-console-standalone-1.9.2.jar $(eval echo $JUNIT_OPTIONS) \
    -c application.Application_0_always_pass_Tests \
    -c streams.Streams_1_tenRandomNumbers_Tests
```

Test results:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  └─ Streams_1_tenRandomNumbers_Tests ✔
│     └─ test100_tenRandomNumbers_regular() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 193 ms
[         5 containers found      ]
[         0 containers skipped    ]
[         5 containers started    ]
[         0 containers aborted    ]
[         5 containers successful ]
[         0 containers failed     ]
[         3 tests found           ]
[         0 tests skipped         ]
[         3 tests started         ]
[         0 tests aborted         ]
[         3 tests successful      ]
[         0 tests failed          ]
```

Add more tests as you implement methods. Include only tests that
pass:

```sh
java -jar libs/junit-platform-console-standalone-1.9.2.jar $(eval echo $JUNIT_OPTIONS) \
    -c application.Application_0_always_pass_Tests \
    \
    -c streams.Streams_1_tenRandomNumbers_Tests \
    -c streams.Streams_2_tenEvenRandomNumbers_Tests \
    -c streams.Streams_3_tenSortedEvenRandomNumbers_Tests \
    -c streams.Streams_4_filteredNumbers_Tests \
    -c streams.Streams_5_filteredNames_Tests \
    -c streams.Streams_6_sortedNames_Tests \
    -c streams.Streams_7_sortedNamesByLength_Tests \
    -c streams.Streams_8_calculateOrderValue_Tests \
    -c streams.Streams_9_sortByOrderValue_Tests
```

Output when all tests pass:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Streams_5_filteredNames_Tests ✔
│  │  ├─ test500_filteredNames_regular() ✔
│  │  ├─ test590_filteredNames_irregularNamesNull() ✔
│  │  ├─ test591_filteredNames_irregularRegexNull() ✔
│  │  └─ test592_filteredNames_irregularNamesAndRegexNull() ✔
│  ├─ Streams_6_sortedNames_Tests ✔
│  │  ├─ test600_sortedNames_regular() ✔
│  │  ├─ test601_sortedNames_regular() ✔
│  │  ├─ test610_sortedNames_emptyNames() ✔
│  │  ├─ test690_sortedNames_irregularNamesNull() ✔
│  │  ├─ test691_sortedNames_irregularLimitNegativ() ✔
│  │  └─ test692_sortedNames_irregularNamesNullAndLimitNegativ() ✔
│  ├─ Streams_7_sortedNamesByLength_Tests ✔
│  │  ├─ test700_sortedNamesByLength_regular() ✔
│  │  ├─ test710_sortedNamesByLength_emptyNames() ✔
│  │  └─ test790_sortedNamesByLength_irregular_names_Null() ✔
│  ├─ Streams_2_tenEvenRandomNumbers_Tests ✔
│  │  └─ test200_tenEvenRandomNumbers_regular() ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  ├─ Streams_9_sortByOrderValue_Tests ✔
│  │  ├─ test900_sortByOrderValue_regular() ✔
│  │  ├─ test901_sortByOrderValue_regular() ✔
│  │  ├─ test910_sortByOrderValue_emptyOrders() ✔
│  │  └─ test990_sortByOrderValue_irregular_orders_Null() ✔
│  ├─ Streams_4_filteredNumbers_Tests ✔
│  │  ├─ test400_filteredNumbers_50evenNumbers_regular() ✔
│  │  ├─ test410_filteredNumbers_50divisibleBy3Numbers_regular() ✔
│  │  ├─ test420_filteredNumbers_50primeNumbers_regular() ✔
│  │  ├─ test430_filteredNumbers_different_even_numbers_returned() ✔
│  │  ├─ test431_filteredNumbers_different_div_by_three_numbers_returned() ✔
│  │  ├─ test432_filteredNumbers_different_prime_numbers_returned() ✔
│  │  ├─ test490_filteredNumbers_50evenNumbers_irregularNegativeIndex() ✔
│  │  └─ test491_filteredNumbers_50evenNumbers_irregularIndex() ✔
│  ├─ Streams_1_tenRandomNumbers_Tests ✔
│  │  └─ test100_tenRandomNumbers_regular() ✔
│  ├─ Streams_3_tenSortedEvenRandomNumbers_Tests ✔
│  │  └─ test300_tenSortedEvenRandomNumbers_regular() ✔
│  └─ Streams_8_calculateOrderValue_Tests ✔
│     ├─ test800_calculateValue_regular() ✔
│     ├─ test801_calculateValue_regular() ✔
│     ├─ test810_calculateValue_emptyOrders() ✔
│     └─ test890_calculateValue_irregular_orders_Null() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 332 ms
[        13 containers found      ]
[         0 containers skipped    ]
[        13 containers started    ]
[         0 containers aborted    ]
[        13 containers successful ]
[         0 containers failed     ]
[        34 tests found           ]
[         0 tests skipped         ]
[        34 tests started         ]
[         0 tests aborted         ]
[        34 tests successful      ]   <-- 34 tests successful
[         0 tests failed          ]   <-- 0 tests failed
done.
```

Run JUnit-Tests also in your IDE.


&nbsp;
## 7. Generating Javadoc

Build the javadoc for the project:

```sh
mk javadoc
```

Open `docs/index.html` in a browser.


&nbsp;
## 8. Packaging the Application

After testing, the application is ready for packaging:

```sh
mk package
```

Resulting `application-1.0.0-SNAPSHOT.jar` is in the `bin` directory.

```sh
ls -la bin
```

Output:

```
total 16
drwxr-xr-x 1 svgr2 Kein     0 Apr 14 21:55 .
drwxr-xr-x 1 svgr2 Kein     0 Apr 14 21:54 ..
-rw-r--r-- 1 svgr2 Kein 17174 Apr 14 21:55 application-1.0.0-SNAPSHOT.jar
```

Run the packaged `.jar` for a final test:

```sh
java -jar bin/application-1.0.0-SNAPSHOT.jar
```

Output when all methods have been implemented:

```
- tenRandomNumbers():
    -> [419, 796, 965, 102, 384, 316, 560, 381, 612, 241]

- tenEvenRandomNumbers():
    -> [100, 376, 16, 560, 222, 412, 146, 302, 526, 578]

- tenSortedEvenRandomNumbers():
    -> [166, 174, 266, 278, 284, 354, 374, 532, 936, 976]

- filteredNumbers(0, 15):       // 15 random even numbers
    -> [294, 316, 576, 942, 766, 6, 370, 202, 652, 666, 674, 474, 176, 402, 624]

- filteredNumbers(1, 15):       // 15 random numbers divisible by 3
    -> [732, 663, 729, 918, 420, 48, 843, 999, 768, 465, 696, 804, 789, 45, 606]

- filteredNumbers(2, 15):       // 15 random two-digit prime numbers
    -> [239, 661, 557, 661, 367, 677, 389, 859, 223, 263, 787, 599, 367, 211, 61
3]

- filteredNames(names, ".*ez$"):        // names ending with "ez"
    -> [Gonzalez, Gomez, Marquez]

- sortedNames(names, 8):        // first 8 names from sorted name list
    -> [Bernard, Brock, Buckner, Case, Casey, Cleveland, Crane, Duncan]

- sortedNamesByLength(names):   // names sorted by length
    -> [Ray, Case, Gill, Hall, Howe, Lott, Pena, Soto, Witt, Brock, Casey, Crane
, Gomez, Petty, Vance, Duncan, Graham, Hardin, Joyner, Strong, Talley, Bernard,
Buckner, Marquez, Navarro, Nielsen, Raymond, Gonzalez, Hamilton, Rutledge, Cleve
land, Hendricks, Richardson]

- calculateValue(orders):
    -> 20562

- sortOrdersByValue(orders):    // orders sorted by value
    ->  - Kanne,  5x 1499 =   7495
        - Messer, 6x  789 =   4734
        - Lampe,  2x 1999 =   3998
        - Vase,   2x  999 =   1998
        - Tasse,  7x  249 =   1743
        - Becher, 2x  199 =    398
        - Stift,  4x   49 =    196
                          --------
                             20562
                          ========
done.
```
