package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;


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

import main.najah.code.Product;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {
    Product p;

	@BeforeEach
	void setUp() throws Exception {
		p = new Product("Known", 100);
	}
	
	@Test
	@DisplayName("test valid input discount")
	void testValidDiscouont() {  
		p.applyDiscount(10);
		assertEquals(90, p.getFinalPrice());
	}
	
	@Test
	@DisplayName("test invalid input discount (negative price)")
	void testInvalidDiscount() {
		assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(-5));
	}
	
	@ParameterizedTest
	@CsvSource({"10,90", "20,80", "0,100"})
	@DisplayName("test parameterized discount price")
	void parameterizedDiscount(double discount, double expected) {
		p.applyDiscount(discount);
		assertEquals(expected, p.getFinalPrice());
	}
	
	@Test 
	@Timeout(2)
	@DisplayName("test time out discount")
	void timeoutTest() {
		p.applyDiscount(5);
		assertEquals(95, p.getFinalPrice());
	}
	
	@Test
	//@Disabled()
	@DisplayName("test failing: worng final price expectation")
	void failingTest() {
		p.applyDiscount(20);
		assertEquals(80, p.getFinalPrice()); // should be 80
	}
	
	@Test
	@DisplayName("test getName method")
	void testGetName() {
		assertEquals("Known", p.getName());
	}
	
	@Test
	@DisplayName("test getPrice method")
	void testGetPrice() {
		assertEquals(100, p.getPrice());
	}
	
	@Test
	@DisplayName("test getDicount method")
	void testGetDiscount() {
		assertEquals(0, p.getDiscount());
	}

}
