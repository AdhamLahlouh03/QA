package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;




import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Calculator;

@DisplayName("Calculator Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {
	 
    Calculator calc;
    
    @BeforeAll
    static void beforeAll() {
    	System.out.println("Starting Calculator Tests");
    }
    
	@BeforeEach
	void setUp() throws Exception {
		calc = new Calculator();
		System.out.println("Test setup complete");
	}
	
	@AfterEach
	void tearDown() {
		System.out.println("Test completed");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("All Calculator Tests Completed");
	}
	
	@Test
	@DisplayName("Addition with multiple integers")
	@Order(1)
	void testAddition() {
		assertAll("Testing sum",
				() -> assertEquals(6, calc.add(1,2,3)),
				() -> assertEquals(0, calc.add()),
				() -> assertEquals(0, calc.add(-5,5))
				);
	}
	
	@Test
	@DisplayName("Division by non-zero")
	@Order(2)
	void testDivision() {
		assertEquals(2, calc.divide(4, 2));
	}
	
	@Test
	@DisplayName("Division by zero")
	@Order(3)
	void testDivisionByZero() {
		assertThrows(ArithmeticException.class, ()-> calc.divide(5, 0));
	}
	
	@Test
	@DisplayName("Factorial of a number")
	@Order(4)
	void testFactorial() {
		assertEquals(120, calc.factorial(5));
	}
	
	@Test
	@DisplayName("Factorial of a negative number")
	@Order(5)
	void testFactorialNegative() {
		assertThrows(IllegalArgumentException.class, ()-> calc.factorial(-5));
	}
	
	@ParameterizedTest()
	@CsvSource({"1,2,3", "2,3,5", "-1,-1,-2"})
	@DisplayName("Parameterized addition test")
	@Order(6)
	void parameterizedTest(int a, int b, int expected) {
		assertEquals(expected, calc.add(a,b));
	}
	
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	@DisplayName("Timeout test for factorial")
	@Order(7)
	void testTimeout() {
		assertEquals(120, calc.factorial(5));
	}

    @Test
    //@Disabled()
    @DisplayName("Failing test")
    @Order(8)
    void testFailing() {
        assertEquals(10, calc.add(5, 5)); // Should be 10
    }
	

}
