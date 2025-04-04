package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.parallel.ExecutionMode;

import main.najah.code.UserService;
@Execution(ExecutionMode.CONCURRENT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceSimpleTest {
	
	UserService s ;
	
	@BeforeEach
	void setUp() throws Exception {
		s = new UserService();
	}
	 
	@Test
	@DisplayName("Valid email should pass")
	@Order(1)
	void testValidEmail() {
		assertAll(
				() -> assertTrue(s.isValidEmail("test@example.com")),
				() -> assertTrue(s.isValidEmail("admin@mail.com"))
				);
	}
	
	@Test
	@DisplayName("Invalid email should fail")
	@Order(2)
	void testInvalidEmail() {
		assertAll(
				() -> assertFalse(s.isValidEmail(null)),
				() -> assertFalse(s.isValidEmail("no-at-sympol")),
				() -> assertFalse(s.isValidEmail("missingdot@com"))
				);
	}
	
	@ParameterizedTest
    @CsvSource({
        "admin,1234,true",
        "admin,wrong,false",
        "user,1234,false",
        "admin,,false"
    })
    @DisplayName("Authentication scenarios")
	@Order(3)
    void testAuthentication(String username, String password, boolean expected) {
        assertEquals(expected, s.authenticate(username, password));
    }

    @Test
    @Timeout(value = 300, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Timeout test for email validation")
    @Order(4)
    void testEmailTimeout() {
        assertTrue(s.isValidEmail("fast@test.com"));
    }

    @Test
    @Disabled()
    @DisplayName("Failing test")
    @Order(5)
    void testFailingEmail() {
        assertFalse(s.isValidEmail("admin@mail.com")); // will fail, it's a valid email
    } 

	

}
