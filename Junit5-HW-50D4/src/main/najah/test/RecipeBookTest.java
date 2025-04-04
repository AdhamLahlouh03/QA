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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecipeBookTest {
	RecipeBook book;
	Recipe r1;
	
	@BeforeEach
	void setup() throws Exception{
		book = new RecipeBook();
		r1 = new Recipe();
		r1.setName("Tea");  
	}

	@Test
	@DisplayName("Add new recipe")
	@Order(1)
	void testAddRecipe() {
		assertTrue(book.addRecipe(r1));
	}
	
	@Test
	@DisplayName("Duplicate recipe should not be added")
	@Order(2)
	void testAddDuplicateRecipe() {
		book.addRecipe(r1);
		assertFalse(book.addRecipe(r1));
	}
	
	@Test
	@DisplayName("Delete existing recipe")
	@Order(3)
	void testDeleteRecipe() {
		book.addRecipe(r1);
		assertEquals("Tea", book.deleteRecipe(0));
	}
	
	@Test
	@DisplayName("Edit existing recipe")
	@Order(4)
	void testEditRecipe() {
		book.addRecipe(r1);
		Recipe r2 = new Recipe();
		r2.setName("Latte");
		assertEquals("Tea", book.editRecipe(0, r2));
	}
	
	@ParameterizedTest
    @CsvSource({"Tea", "Cappuccino", "Espresso"})
    @DisplayName("Parameterized add recipe")
	@Order(5)
    void parameterizedAddRecipe(String name) throws Exception {
        Recipe r = new Recipe();
        r.setName(name);
        assertTrue(book.addRecipe(r));
    }

    @Test
    @Timeout(value = 300, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Timeout test for add recipe")
    @Order(6)
    void testAddRecipeTimeout() {
        Recipe r = new Recipe();
        r.setName("Quick");
        assertTrue(book.addRecipe(r));
    }

    @Test
    @Disabled()
    @DisplayName("Failing test for deleting empty recipe")
    @Order(7)
    void testDeleteEmpty() {
        assertEquals("Tea", book.deleteRecipe(0)); // Will fail, nothing added
    }


}
