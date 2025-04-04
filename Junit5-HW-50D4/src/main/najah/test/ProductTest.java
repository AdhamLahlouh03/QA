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
	@Order(1)
	void testValidDiscouont() {  
		p.applyDiscount(10);
		assertEquals(90, p.getFinalPrice());
	}
	
	@Test
	@DisplayName("test invalid input discount (negative price)")
	@Order(2)
	void testInvalidDiscount() {
		assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(-5));
	}
	
	@ParameterizedTest
	@CsvSource({"10,90", "20,80", "0,100"})
	@DisplayName("test parameterized discount price")
	@Order(3)
	void parameterizedDiscount(double discount, double expected) {
		p.applyDiscount(discount);
		assertEquals(expected, p.getFinalPrice());
	}
	
	@Test 
	@Timeout(2)
	@DisplayName("test time out discount")
	@Order(4)
	void timeoutTest() {
		p.applyDiscount(5);
		assertEquals(95, p.getFinalPrice());
	}
	
	@Test
	//@Disabled()
	@DisplayName("test failing: worng final price expectation")
	@Order(5)
	void failingTest() {
		p.applyDiscount(20);
		assertEquals(80, p.getFinalPrice()); // should be 80
	}
	
	@Test
	@DisplayName("test getName method")
	@Order(6)
	void testGetName() {
		assertEquals("Known", p.getName());
	}
	
	@Test
	@DisplayName("test getPrice method")
	@Order(7)
	void testGetPrice() {
		assertEquals(100, p.getPrice());
	}
	
//	@Test
//	@DisplayName("test getDicount method")
//	@Order(8)
//	void testGetDiscount() {
//		assertEquals(15, p.getDiscount());
//	}

}
