<!-- check-out single branch from repo:
        git clone -b b12 --single-branch https://github.com/sgra64/se1.play.git
 -->

# Assignment B2

Assignment B2 defines methods for calculations over arrays with numbers in
interface [Numbers.java](src/numbers/Numbers.java):

- `sum()`            - calculate sum of numbers[]                   (1Pt)

- `sum_pen()`        - sum of positive even numbers                 (1Pt)

- `sum_rec()`        - sum of numbers[] without using loops         (1Pt)

- `findFirst(int x)`     - find index of first occurrence of x      (1Pt)

- `findLast(int x)`      - find index of last occurrence of x       (1Pt)

- `findAll(int x)`       - find all indices of x in numbers         (1Pt)

- `findSums(int sum)`    - find all pairs {a, b} with a + b = sum   (2Pt, +1EP)

- `findAllSums(int sum)` - find all combinations of numbers that add to sum (3Pt, +2EP)

Follow steps (1-9) below to complete the assignment. Implement a method and
verify with passing the corresponding JUnit-test before proceeding with the
next method.

Points are credited when corresponding JUnit tests pass (tests:
*Numbers_7b_find_sums_duplicates_Tests* and *Numbers_8b_find_all_sums_XL_Tests*
are hard and yield extra points for passing: `7b` (+1EP) and `8b` (+2EP)).


1. [Getting Started](#1-getting-started)

2. [Sourcing the Project](#2-sourcing-the-project)

3. [Building the Application](#3-building-the-application)

4. [Running the Application](#4-running-the-application)

5. [Completing Numbers Methods](#5-completing-numbers-methods)

6. [Running JUnit Tests](#6-running-junit-tests)

7. [Generating Javadoc](#7-generating-javadoc)

8. [Packaging the Application](#8-packaging-the-application)

9. [Examples and Solutions](#9-examples-and-solutions)
    - 9.1 [*sum()*](#91-sum)
    - 9.2 [*sum_positive_even_numbers()*](#92-sum_positive_even_numbers)
    - 9.3 [*sum_recursive()*](#93-sum_recursive)
    - 9.4 [*findFirst()*](#94-findfirst)
    - 9.5 [*findLast()*](#95-findlast)
    - 9.6 [*findAll()*](#96-findall)
    - 9.7 [*findSums()*](#97-findsums)
    - 9.8 [*findAllSums()*](#98-findallsums)


&nbsp;
## 1. Getting started

This assignment adds package `numbers` to `src` and `tests`:

```sh
--<se1.play>:
 |
 +--<src>                       # Java source code
 |   +-- module-info.java           # module defintion file
 |   +--<application>               # Java package 'application'
 |   |   +-- package-info.java      # 'application'-package defintion
 |   |   +-- Application.java       # main application program with main()
 |   |
 |   +--<numbers>               # new 'Numbers' package
 |       +-- Numbers.java           # Numbers interface
 |       +-- NumbersImpl.java       # Numbers implementation class
 |       +-- package-info.java
 |
 +--<tests>                     # Unit-test source code separated from src/main
 |   +--<application>               # mirrored src package structure
 |   |   +-- Application_0_always_pass_Tests.java   # initial JUnit-test
 |   |
 |   +--<numbers>               # new 'Numbers' JUnit tests
 |       +-- Matchers.java      # assertion matcher that ignores order in collections
 |       +-- Numbers_1_sum_Tests.java
 |       +-- Numbers_2_sum_positive_even_Tests.java
 |       +-- Numbers_3_sum_recursion_Tests.java
 |       +-- Numbers_4_find_first_Tests.java
 |       +-- Numbers_5_find_last_Tests.java
 |       +-- Numbers_6_find_all_Tests.java
 |       +-- Numbers_7a_find_sums_Tests.java
 |       +-- Numbers_7b_find_sums_duplicates_Tests.java
 |       +-- Numbers_8a_find_all_sums_Tests.java
 |       +-- Numbers_8b_find_all_sums_XL_Tests.java
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
src/numbers
src/numbers/Numbers.java
src/numbers/NumbersImpl.java
src/numbers/package-info.java
tests
tests/application
tests/application/Application_0_always_pass_Tests.java
tests/numbers
tests/numbers/Matchers.java
tests/numbers/Numbers_1_sum_Tests.java
tests/numbers/Numbers_2_sum_positive_even_Tests.java
tests/numbers/Numbers_3_sum_recursion_Tests.java
tests/numbers/Numbers_4_find_first_Tests.java
tests/numbers/Numbers_5_find_last_Tests.java
tests/numbers/Numbers_6_find_all_Tests.java
tests/numbers/Numbers_7a_find_sums_Tests.java
tests/numbers/Numbers_7b_find_sums_duplicates_Tests.java
tests/numbers/Numbers_8a_find_all_sums_Tests.java
tests/numbers/Numbers_8b_find_all_sums_XL_Tests.java
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
mk compile                        # compile source code
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
bin/classes/numbers
bin/classes/numbers/Numbers$Pair.class
bin/classes/numbers/Numbers.class
bin/classes/numbers/NumbersImpl$1Command.class
bin/classes/numbers/NumbersImpl.class
bin/classes/numbers/package-info.class
bin/resources
bin/resources/application.properties
bin/resources/logging.properties
```


&nbsp;
## 4. Running the Application

After building the application, the application can be called directly
with `java` or through the build command `mk run` from the project
environment.

The `op` parameter names the function to call (`sum` for method *sum()*).
Parameter `n` defines the numbers array in `NumbersImpl.java` used
for the operation (yielding 0 as result if *sum()* is not yet implemented).

```sh
mk run op=sum n=numbers
java application.Application op=sum n=numbers
```

Output:

```
Hello, NumbersImpl
sum('numbers': [-2, 4, 9, 4, -3, 4, 9, 5])
 - result: sum()=0

done.
```

The invocation refers to an operation `sum()` from the
[Numbers.java](src/numbers/Numbers.java)
interface that adds numbers provided as argument `int[] numbers`:

```java
package numbers;

public interface Numbers {

    /**
     * Aufgabe 1.) Calculate sum of numbers[].
     * 
     * @param numbers input
     * @return sum of numbers[]
     */
    int sum(int[] numbers);
    ...
}
```

There are several numbers arrays in the implementation class
[NumbersImpl.java](src/numbers/NumbersImpl.java)

```java
package numbers;

public class NumbersImpl implements Numbers, Runnable {

    /*
     * Numbers with negative numbers and duplicates.
     */
    static final int[] numbers = {-2, 4, 9, 4, -3, 4, 9, 5};

    /*
     * Numbers with no negative numbers and no duplicates.
     */
    static final int[] numb_1 = {8, 10, 7, 2, 14, 5, 4};

    /*
     * Larger set of 24 numbers, no negatives, no duplicates.
     */
    static final int[] numb_2 = {   // 24 numbers
        371,  682,  446,  754,  205,  972,  600,  163,  541,  672,
         27,  170,  226,    7,  190,  639,   87,  773,  651,  370,
        125,  774,  903,  636//,225,  463,  286,  569,  384,    9,
    }; // add more numbers to find more solutions

    /*
     * Even larger set of 63 numbers, no negatives, no duplicates.
     */
    static final int numb_3[] = {
        799, 2377,  936, 3498, 1342,  493, 1635, 4676, 1613, 3851,
       1445, 4506, 3346,    7, 2141, 2064, 1491,  908,   78, 3325,
       1756, 3691,   23, 1995, 1800,   15, 2784, 4305,   36, 2532,
       4292, 4802, 2522, 4183, 3261, 2610,  803, 2656,  498, 1668,
       2038, 2194,  440,  463, 4047, 4235, 3931,  756,  521, 4042,
       3302,  485, 1002,  408, 4691, 3387, 3104, 3658, 2241, 4382,
       1220, 3656,  500,
    };
    ...
}
```

All methods defined in the `Numbers` interface are:

```
1.) sum()            - calculate sum of numbers[].
2.) sum_pen()        - sum of positive even numbers
3.) sum_rec()        - sum of numbers[] without using loops
4.) findFirst(int x)     - find index of first occurrence of x
5.) findLast(int x)      - find index of last occurrence of x
6.) findAll(int x)       - find all indices of x in numbers
7.) findSums(int sum)    - find all pairs {a, b} with a + b = sum
8.) findAllSums(int sum) - find all combinations of numbers that add to sum
```


&nbsp;
## 5. Completing Numbers Methods

[Numbers.java](src/numbers/Numbers.java)
interface methods are implemented in class
[NumbersImpl.java](src/numbers/NumbersImpl.java):

```java
package numbers;

public class NumbersImpl implements Numbers, Runnable {

    /**
     * Aufgabe 1.) Calculate sum of numbers[].
     * 
     * @param numbers input
     * @return sum of numbers[]
     */
    @Override
    public int sum(int[] numbers) {
        int result = 0;

        /*
         * TODO: write code to implement the method
         */
        return result;
    }
    ...
```

Implement the method and verify the result (30):

```sh
mk run op=sum n=numbers
java application.Application op=sum n=numbers
```

The correct result for array `'numbers': [-2, 4, 9, 4, -3, 4, 9, 5]` is `30`:

```
Hello, NumbersImpl
sum('numbers': [-2, 4, 9, 4, -3, 4, 9, 5])
 - result: sum()=30

done.
```

Verify the result with other numbers arrays:

```sh
mk run op=sum n=numbers \
       op=sum n=numb_1 \
       op=sum n=numb_2 \
       op=sum n=numb_3

java application.Application \
    op=sum n=numbers \
    op=sum n=numb_1 \
    op=sum n=numb_2 \
    op=sum n=numb_3
```

Results:

```
Hello, NumbersImpl
sum('numbers': [-2, 4, 9, 4, -3, 4, 9, 5])
 - result: sum() = 30

sum('numb_1': [8, 10, 7, 2, 14, 5, 4])
 - result: sum() = 50

sum('numb_2')
 - result: sum() = 10984

sum('numb_3')
 - result: sum() = 141466
done.
```

Compile and run the JUnit test for the `sum` - method:

```sh
mk compile-tests

java -jar libs/junit-platform-console-standalone-1.9.2.jar $(eval echo $JUNIT_OPTIONS) \
    -c numbers.Numbers_1_sum_Tests
```

The JUnit Test class for the `sum` - method is
[numbers.Numbers_1_sum_Tests.java](tests/numbers/Numbers_1_sum_Tests.java) in
[tests/numbers](tests/numbers).

Output of correct tests:

```
├─ JUnit Jupiter ✔
│  └─ Numbers_1_sum_Tests ✔
│     ├─ test100_sum_regular() ✔
│     ├─ test101_sum_regular() ✔
│     ├─ test102_sum_regular() ✔
│     └─ test103_sum_regular() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 161 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         4 tests found           ]
[         0 tests skipped         ]
[         4 tests started         ]
[         0 tests aborted         ]
[         4 tests successful      ]
[         0 tests failed          ]
```

Also run the JUnit tests for the *sum()* - method in your IDE.


&nbsp;
## 6. Running JUnit Tests

Gradually complete methods one after another and verify with JUnit-Tests:

```
1.) sum()                - Numbers_1_sum_Tests.java
2.) sum_positive_even_numbers() - Numbers_2_sum_positive_even_Tests.java
3.) sum_recursive()      - Numbers_3_recursive_Tests
4.) findFirst(int x)     - Numbers_4_find_first_Tests
5.) findLast(int x)      - Numbers_5_find_last_Tests
6.) findAll(int x)       - Numbers_6_find_all_Tests
7.) findSums(int sum)    - Numbers_7a_find_sums_Tests, Numbers_7b_find_sums_Tests
8.) findAllSums(int sum) - Numbers_8a_find_all_sums_Tests, Numbers_8b_find_all_sums_Tests
```

When tests fail, locate the test-method and verify *expected* values
and *actual* values obtained from from your method implementation.

Run final JUnit-Tests in the IDE for the tests you managed to pass.

Remove tests from the list that do not pass:

```sh
java -jar libs/junit-platform-console-standalone-1.9.2.jar $(eval echo $JUNIT_OPTIONS) \
    -c application.Application_0_always_pass_Tests \
    -c numbers.Numbers_1_sum_Tests \
    -c numbers.Numbers_2_sum_positive_even_Tests \
    -c numbers.Numbers_3_sum_recursion_Tests \
    -c numbers.Numbers_4_find_first_Tests \
    -c numbers.Numbers_5_find_last_Tests \
    -c numbers.Numbers_6_find_all_Tests \
    -c numbers.Numbers_7a_find_sums_Tests \
    -c numbers.Numbers_7b_find_sums_duplicates_Tests \
    -c numbers.Numbers_8a_find_all_sums_Tests \
    -c numbers.Numbers_8b_find_all_sums_XL_Tests
```

Tests `Numbers_7b_find_sums_duplicates_Tests` and `Numbers_8b_find_all_sums_XL_Tests`
are hard and require solid implementations of methods.

Output when all tests pass:

```
├─ JUnit Jupiter ✔
│  ├─ Numbers_3_sum_recursion_Tests ✔
│  │  ├─ test300_sum_recursion_regular() ✔
│  │  ├─ test301_sum_recursion_regular() ✔
│  │  ├─ test302_sum_recursion_regular() ✔
│  │  └─ test303_sum_recursion_regular() ✔
│  ├─ Numbers_7b_find_sums_duplicates_Tests ✔
│  │  ├─ test710_find_sums_duplicates() ✔
│  │  ├─ test711_find_sums_same_duplicates() ✔
│  │  ├─ test712_find_sums_mirror_duplicates() ✔
│  │  └─ test713_find_sums_regular_duplicates() ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  ├─ Numbers_7a_find_sums_Tests ✔
│  │  ├─ test700_find_sums_regular() ✔
│  │  ├─ test701_find_sums_regular() ✔
│  │  ├─ test702_find_sums_regular() ✔
│  │  ├─ test703_find_sums_regular() ✔
│  │  ├─ test704_find_sums_regular() ✔
│  │  ├─ test705_find_sums_regular() ✔
│  │  └─ test706_find_sums_regular() ✔
│  ├─ Numbers_8a_find_all_sums_Tests ✔
│  │  ├─ test800_find_all_sums_regular() ✔
│  │  └─ test821_find_all_sums_regular_nbrs_2_sum999() ✔
│  ├─ Numbers_2_sum_positive_even_Tests ✔
│  │  ├─ test200_sum_positive_even_numbers_regular() ✔
│  │  ├─ test201_sum_positive_even_numbers_regular() ✔
│  │  ├─ test202_sum_positive_even_numbers_regular() ✔
│  │  └─ test203_sum_positive_even_numbers_regular() ✔
│  ├─ Numbers_5_find_last_Tests ✔
│  │  ├─ test500_find_last_regular() ✔
│  │  ├─ test501_find_last_regular() ✔
│  │  ├─ test502_find_last_regular() ✔
│  │  └─ test503_find_last_regular() ✔
│  ├─ Numbers_6_find_all_Tests ✔
│  │  ├─ test600_find_all_regular() ✔
│  │  ├─ test601_find_all_regular() ✔
│  │  ├─ test602_find_all_regular() ✔
│  │  └─ test603_find_all_regular() ✔
│  ├─ Numbers_1_sum_Tests ✔
│  │  ├─ test100_sum_regular() ✔
│  │  ├─ test101_sum_regular() ✔
│  │  ├─ test102_sum_regular() ✔
│  │  └─ test103_sum_regular() ✔
│  ├─ Numbers_8b_find_all_sums_XL_Tests ✔
│  │  ├─ test824_find_all_sums_XL_24_numbers() ✔
│  │  ├─ test825_find_all_sums_XL_25_numbers() ✔
│  │  ├─ test826_find_all_sums_XL_26_numbers() ✔
│  │  ├─ test827_find_all_sums_XL_27_numbers() ✔
│  │  ├─ test828_find_all_sums_XL_28_numbers() ✔
│  │  ├─ test829_find_all_sums_XL_29_numbers() ✔
│  │  └─ test830_find_all_sums_XL_30_numbers() ✔
│  └─ Numbers_4_find_first_Tests ✔
│     ├─ test400_find_first_regular() ✔
│     ├─ test401_find_first_regular() ✔
│     ├─ test402_find_first_regular() ✔
│     └─ test403_find_first_regular() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 3403 ms
[        14 containers found      ]
[         0 containers skipped    ]
[        14 containers started    ]
[         0 containers aborted    ]
[        14 containers successful ]
[         0 containers failed     ]
[        46 tests found           ]
[         0 tests skipped         ]
[        46 tests started         ]
[         0 tests aborted         ]
[        46 tests successful      ]   <-- 46 tests successful
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
-rw-r--r-- 1 svgr2 Kein 15871 Apr 14 21:55 application-1.0.0-SNAPSHOT.jar
```

Run the packaged `.jar` for a final test:

```sh
java -jar bin/application-1.0.0-SNAPSHOT.jar \
    op=sum n=numb_1 \
    op=findSums n=numb_1 sum=12 \
    op=findAllSums n=numb_1 sum=14 \
    op=findAllSums n=numb_1 sum=15
```

Output:

```
Hello, NumbersImpl
sum('numb_1': [8, 10, 7, 2, 14, 5, 4])
 - result: sum() = 50

findSums('numb_1': [8, 10, 7, 2, 14, 5, 4]), sum=12
 - result: [(5,7), (4,8), (2,10)], solutions: 3

findAllSums('numb_1': [8, 10, 7, 2, 14, 5, 4]), sum=14
 - result: [[14], [4, 10], [2, 4, 8], [2, 5, 7]], solutions: 4

findAllSums('numb_1': [8, 10, 7, 2, 14, 5, 4]), sum=15
 - result: [[7, 8], [5, 10], [2, 5, 8]], solutions: 3
```

The packaged `application-1.0.0-SNAPSHOT.jar` can now be distributed.


&nbsp;
## 9. Examples and Solutions

This section shows some examples and solutions for *Numbers* - methods:

- 9.1 [*sum()*](#91-sum)
- 9.2 [*sum_positive_even_numbers()*](#92-sum_positive_even_numbers)
- 9.3 [*sum_recursive()*](#93-sum_recursive)
- 9.4 [*findFirst()*](#94-findfirst)
- 9.5 [*findLast()*](#95-findlast)
- 9.6 [*findAll()*](#96-findall)
- 9.7 [*findSums()*](#97-findsums)
- 9.8 [*findAllSums()*](#98-findallsums)


#### 9.1 *sum()*

Verify *sum()* with all numbers arrays:

```sh
mk run op=sum n=numbers \
       op=sum n=numb_1 \
       op=sum n=numb_2 \
       op=sum n=numb_3
```

Results:

```
Hello, NumbersImpl
sum('numbers': [-2, 4, 9, 4, -3, 4, 9, 5])
 - result: sum() = 30

sum('numb_1': [8, 10, 7, 2, 14, 5, 4])
 - result: sum() = 50

sum('numb_2')
 - result: sum() = 10984

sum('numb_3')
 - result: sum() = 141466
```


#### 9.2 *sum_positive_even_numbers()*

Verify *sum_positive_even_numbers()* ("sum_pen") with all numbers arrays:

```sh
mk run op=sum_pen n=numbers \
       op=sum_pen n=numb_1 \
       op=sum_pen n=numb_2 \
       op=sum_pen n=numb_3
```

Results:

```
Hello, NumbersImpl
sum_pen('numbers': [-2, 4, 9, 4, -3, 4, 9, 5])
 - result: sum_pen() = 12

sum_pen('numb_1': [8, 10, 7, 2, 14, 5, 4])
 - result: sum_pen() = 38

sum_pen('numb_2')
 - result: sum_pen() = 6492

sum_pen('numb_3')
 - result: sum_pen() = 80012
```


#### 9.3 *sum_recursive()*

Results for *sum_recursive()* ("sum_rec") are the same for *sum()*.

```sh
mk run op=sum_rec n=numbers \
       op=sum_rec n=numb_1 \
       op=sum_rec n=numb_2 \
       op=sum_rec n=numb_3
```


#### 9.4 *findFirst()*

```sh
mk run op=findFirst n=numbers x=4 \
       op=findFirst n=numbers x=-3 \
       op=findFirst n=numbers x=1
```

Results:

```
findFirst('numbers': [-2, 4, 9, 4, -3, 4, 9, 5])
 - result: findFirst(x=4)  =  1
 - result: findFirst(x=-3) =  4
 - result: findFirst(x=1)  = -1
```


#### 9.5 *findLast()*

```sh
mk run op=findLast n=numbers x=4 \
       op=findLast n=numbers x=-3 \
       op=findLast n=numbers x=1
```

Results:

```
findLast('numbers': [-2, 4, 9, 4, -3, 4, 9, 5])
 - result: findLast(x=4)  =  5
 - result: findLast(x=-3) =  4
 - result: findLast(x=1)  = -1
```


#### 9.6 *findAll()*

```sh
mk run op=findAll n=numbers x=4 \
       op=findAll n=numbers x=-3 \
       op=findAll n=numbers x=1
```

Results:

```
findAll('numbers': [-2, 4, 9, 4, -3, 4, 9, 5])
 - result: findAll(x=4)  = [1, 3, 5]
 - result: findAll(x=-3) = [4]
 - result: findAll(x=1)  = []
```


#### 9.7 *findSums()*

```sh
mk run op=findSums n=numb_1 sum=10 \
       op=findSums n=numb_1 sum=12 \
       op=findSums n=numb_1 sum=15
```

Results:

```
findSums('numb_1': [8, 10, 7, 2, 14, 5, 4])
 - sum=10, result: [(2,8)]
 - sum=12, result: [(5,7), (4,8), (2,10)]   (in any order)
 - sum=15, result: [(7,8), (5,10)]          (in any order)
```


#### 9.8 *findAllSums()*

```sh
mk run op=findAllSums n=numb_1 sum=10 \
       op=findAllSums n=numb_1 sum=12 \
       op=findAllSums n=numb_1 sum=14 \
       op=findAllSums n=numb_1 sum=15 \
       op=findAllSums n=numb_1 sum=20
```

Results:

```
findAllSums('numb_1': [8, 10, 7, 2, 14, 5, 4])
 - sum=10, result: [[10], [2, 8]]                           (in any order)
 - sum=12, result: [[4, 8], [2, 10], [5, 7]]                (in any order)
 - sum=14, result: [[14], [4, 10], [2, 4, 8], [2, 5, 7]]    (in any order)
 - sum=15, result: [[7, 8], [5, 10], [2, 5, 8]]             (in any order)
 - sum=20, result: [[2, 8, 10], [5, 7, 8], [2, 4, 14]]      (in any order)
```

Weitere Ausführungen:

```sh
mk run op=findAllSums n=numb_2 sum=1000
```

Result:

```
findAllSums('numb_2'), sum=1000
 - result: [
    - [226, 774],
    - [754, 87, 7, 27, 125],
    - [7, 27, 636, 125, 205],
    - [7, 651, 27, 125, 190],
    - [7, 27, 125, 205, 190, 446]
   ], solutions: 5
```

```sh
mk run op=findAllSums n=numb_2 sum=999
```

Result:

```
findAllSums('numb_2'), sum=999
 - result: [
    - [27, 972],
    - [226, 773],
    - [170, 190, 639],
    - [371, 87, 541],
    - [226, 27, 541, 205],
    - [163, 170, 125, 541],
    - [163, 170, 27, 639],
    - [163, 7, 190, 639],
    - [226, 371, 170, 27, 205],
    - [226, 371, 7, 205, 190],
    - [226, 371, 87, 125, 190],
    - [226, 163, 371, 87, 27, 125],
    - [226, 163, 371, 7, 27, 205]
   ], solutions: 13
```


#### 9.9a *findAllSums()* - XL Solution Space 2^30

Extend array `numb_2` in
[NumbersImpl.java](src/numbers/NumbersImpl.java)
by two numbers: +`225` and +`463` (change comment).

```java
/*
    * Larger set of 24 numbers, no negatives, no duplicates.
    */
static final int[] numb_2 = {   // 24 numbers
    371,  682,  446,  754,  205,  972,  600,  163,  541,  672,
        27,  170,  226,    7,  190,  639,   87,  773,  651,  370,
    125,  774,  903,  636//,225,  463,  286,  569,  384,    9,
}; // add more numbers to find more solutions
```

Repeat invocation from 9.1:

```sh
mk run op=findAllSums n=numb_2 sum=999
```

With the two added numbers, 17 solutions are found (13 before).

```
findAllSums('numb_2'), sum=999
 - result: [
    - [225, 774],
    - [27, 972],
    - [226, 773],
    - [371, 87, 541],
    - [170, 190, 639],
    - [163, 7, 190, 639],
    - [226, 27, 541, 205],
    - [163, 170, 125, 541],
    - [225, 226, 7, 541],
    - [163, 170, 27, 639],
    - [226, 371, 170, 27, 205],
    - [226, 371, 7, 205, 190],
    - [225, 226, 371, 7, 170],
    - [226, 371, 87, 125, 190],
    - [226, 163, 371, 87, 27, 125],
    - [226, 163, 371, 7, 27, 205],
    - [225, 87, 7, 27, 190, 463]
   ], solutions: 17
```

Array `numb_2` started with 24 numbers yielding *2^24* combinations.
The solution space expands rapidely by adding numbers to `numb_2`,
doubling with each number added. After adding +`225` and +`463`,
it is *2^26*.

Adding number: +`286` yields 19 solutions from solution space *2^27*.

Adding more numbers one after another yields with +`569` (21 solutions),
+`384` (24 solutions) and +`9` (44 solutions):

```
findAllSums('numb_2'), sum=999
 - result: [
    - [226, 773],                   - [371, 27, 125, 286, 190],
    - [27, 972],                    - [225, 226, 371, 7, 170],
    - [225, 774],                   - [226, 7, 569, 170, 27],
    - [170, 190, 639],              - [225, 370, 9, 205, 190],
    - [371, 87, 541],               - [226, 163, 371, 7, 27, 205],
    - [225, 569, 205],              - [226, 163, 371, 87, 27, 125],
    - [903, 87, 9],                 - [226, 163, 9, 125, 286, 190],
    - [226, 9, 125, 639],           - [225, 7, 9, 170, 125, 463],
    - [163, 9, 541, 286],           - [384, 7, 170, 27, 125, 286],
    - [163, 170, 27, 639],          - [384, 226, 163, 9, 27, 190],
    - [225, 226, 7, 541],           - [225, 7, 9, 27, 541, 190],
    - [163, 170, 125, 541],         - [225, 87, 7, 27, 190, 463],
    - [163, 7, 190, 639],           - [384, 226, 87, 7, 9, 286],
    - [773, 9, 27, 190],            - [7, 9, 125, 205, 190, 463],
    - [226, 27, 541, 205],          - [9, 170, 27, 125, 205, 463],
    - [163, 87, 286, 463],          - [225, 87, 9, 27, 205, 446],
    - [384, 27, 125, 463],          - [384, 226, 87, 7, 170, 125],
    - [226, 371, 87, 125, 190],     - [225, 370, 163, 9, 27, 205],
    - [226, 371, 170, 27, 205],     - [225, 371, 7, 9, 170, 27, 190],
    - [226, 371, 7, 205, 190],      - [163, 7, 9, 27, 125, 205, 463],
    - [163, 371, 9, 170, 286],      - [370, 226, 7, 9, 170, 27, 190],
    - [225, 87, 9, 651, 27],        - [384, 87, 7, 9, 170, 27, 125, 190]
   ], solutions: 44
```

The solution space is now *2^30*.


#### 9.9b *findAllSums()* - XXL Solution Space 2^63

Array `numb_3` with 63 numbers has a solution space of *2^63*, which
cannot be explored *brute force*.

```java
/*
    * Even larger set of 63 numbers, no negatives, no duplicates.
    */
static final int numb_3[] = {
    799, 2377,  936, 3498, 1342,  493, 1635, 4676, 1613, 3851,
    1445, 4506, 3346,    7, 2141, 2064, 1491,  908,   78, 3325,
    1756, 3691,   23, 1995, 1800,   15, 2784, 4305,   36, 2532,
    4292, 4802, 2522, 4183, 3261, 2610,  803, 2656,  498, 1668,
    2038, 2194,  440,  463, 4047, 4235, 3931,  756,  521, 4042,
    3302,  485, 1002,  408, 4691, 3387, 3104, 3658, 2241, 4382,
    1220, 3656,  500,
};
```

Special algorithms such as
[branch & bound](https://en.wikipedia.org/wiki/Branch_and_bound)
can be used to find solutions by "cutting branches" of combinations
that cannot yield a solution, e.g. when sum is exceeded.

```sh
mk run op=findAllSums n=numb_3 sum=999
```

Although only 10 solutions are found from `numb_3` of numbers
yielding sum=999, the space to explore is huge *2^63*.

```
findAllSums('numb_3'), sum=999
 - result: [
    - [521, 463, 15],
    - [500, 36, 463],
    - [36, 7, 493, 463],
    - [498, 408, 78, 15],
    - [498, 23, 463, 15],
    - [23, 440, 521, 15],
    - [500, 36, 23, 440],
    - [36, 485, 463, 15],
    - [36, 23, 7, 440, 493],
    - [36, 485, 23, 440, 15]
   ], solutions: 10
```

Searching for `sum=996`, `sum=500` and `sum=100`

```sh
mk run op=findAllSums n=numb_3 sum=996 \
       op=findAllSums n=numb_3 sum=500 \
       op=findAllSums n=numb_3 sum=100
```

```
findAllSums('numb_3')
 - sum=996, result: [
    - [440, 78, 463, 15],
    - [498, 36, 7, 440, 15]
   ]                                                solutions: 2
 - sum=500, result: [[500], [7, 493], [485, 15]]    solutions: 3
 - sum=100, result: [[7, 78, 15]]                   solutions: 1
```

Searching for larger sum-values increases search time since shorter
branches can be cut.

```sh
mk run op=findAllSums n=numb_3 sum=2000
```

```
sum       solutions found      time
-----------------------------------
2000      38                7,2 sec
2100      18               13,7 sec
2200      37               20,4 sec
2300      64               18,6 sec
2400      73               51,6 sec
2500      52               1:53 min
```
