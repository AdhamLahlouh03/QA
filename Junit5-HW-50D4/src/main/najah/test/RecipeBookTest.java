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
    void setup() throws Exception {
        book = new RecipeBook();
        r1 = new Recipe();
        r1.setName("Tea");
    }

    // ========== Tests for addRecipe() ==========
    
    @Test
    @DisplayName("Add new recipe")
    void testAddRecipe() {
        assertTrue(book.addRecipe(r1));
    }

    @Test
    @DisplayName("Duplicate recipe should not be added")
    void testAddDuplicateRecipe() {
        book.addRecipe(r1);
        assertFalse(book.addRecipe(r1));
    }

    @ParameterizedTest
    @CsvSource({"Tea", "Cappuccino", "Espresso"})
    @DisplayName("Parameterized add recipe")
    void parameterizedAddRecipe(String name) throws Exception {
        Recipe r = new Recipe();
        r.setName(name);
        assertTrue(book.addRecipe(r));
    }

    @Test
    @Timeout(value = 300, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Timeout test for add recipe")
    void testAddRecipeTimeout() {
        Recipe r = new Recipe();
        r.setName("Quick");
        assertTrue(book.addRecipe(r));
    }

    // ========== Tests for deleteRecipe() ==========

    @Test
    @DisplayName("Delete existing recipe")
    void testDeleteRecipe() {
        book.addRecipe(r1);
        assertEquals("Tea", book.deleteRecipe(0));
    }

    @Test
    @DisplayName("Return delete empty when the index is empty")
    void testDeleteNullRecipe() {
        assertNull(book.deleteRecipe(0), "Expected null when deleting from an empty slot");
    }

    @Test
    @Disabled()
    @DisplayName("Failing test for deleting empty recipe")
    void testDeleteEmpty() {
        assertEquals("Tea", book.deleteRecipe(0)); // Will fail, nothing added
    }

    // ========== Tests for editRecipe() ==========

    @Test
    @DisplayName("Edit existing recipe")
    void testEditRecipe() {
        book.addRecipe(r1);
        Recipe r2 = new Recipe();
        r2.setName("Latte");
        assertEquals("Tea", book.editRecipe(0, r2));
    }

    @Test
    @DisplayName("Return the edit empty when the index is empty")
    void testEditNullRecipe() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Mocha");
        assertNull(book.editRecipe(0, newRecipe), "Expected null when editing an empty slot");
    }

    // ========== Test for getRecipes() ==========

    @Test
    @DisplayName("Test getRecipes returns the recipe array (not null)")
    void testGetRecipesReturnsArray() {
        Recipe[] recipes = book.getRecipes();
        assertNotNull(recipes, "Expected recipe array not to be null");
        assertEquals(4, recipes.length, "Expected recipe array to have length 4");
    }
}
