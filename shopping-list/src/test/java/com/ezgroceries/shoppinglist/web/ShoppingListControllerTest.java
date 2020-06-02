package com.ezgroceries.shoppinglist.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Gert
 * @since release 202008 / (2020-06-02)
 */
@WebMvcTest(ShoppingListController.class)
class ShoppingListControllerTest {

    private static final String[] INGREDIENTS_LIST = {"Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"};

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createShoppingList() throws Exception {
        //arrange
        // nothing to mock at this moment

        // act & assert
        mockMvc.perform(post("/shopping-lists")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"Stephanie's birthday\"}"))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("shoppingListId").value("0a1e619d-b5d7-340a-9462-a38a1d38c0f2"))
            .andExpect(jsonPath("name").value("Stephanie's birthday"));

        // verify
        // nothing to verify at this moment

    }

    @Test
    void addCocktailsToShoppingList() throws Exception {
        //arrange
        // nothing to mock at this moment

        // act & assert
        final String INPUT_BODY = "[{\"cocktailId\":\"3b3d85a-3928-41c0-a533-6538a71e17c4\"},"
            + "{\"cocktailId\":\"3b3d85a-3928-41c0-a533-6538a71e17c4\"}]";

        mockMvc.perform(post("/shopping-lists/shoppingListId/cocktails")
            .contentType(MediaType.APPLICATION_JSON)
            .content(INPUT_BODY))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(INPUT_BODY));
        // verify
        // nothing to verify at this moment
    }


    @Test
    void getShoppingList() throws Exception {

        //arrange
        // nothing to mock at this moment

        // act & assert
        mockMvc.perform(get("/shopping-lists/shoppingListId")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("name").value("Stephanie's birthday"))
            .andExpect(jsonPath("shoppingListId").value("90689338-499a-4c49-af90-f1e73068ad4f"))
            .andExpect(jsonPath("ingredients[0]").value(INGREDIENTS_LIST[0]))
            .andExpect(jsonPath("ingredients[1]").value(INGREDIENTS_LIST[1]))
            .andExpect(jsonPath("ingredients[2]").value(INGREDIENTS_LIST[2]))
            .andExpect(jsonPath("ingredients[3]").value(INGREDIENTS_LIST[3]))
            .andExpect(jsonPath("ingredients[4]").value(INGREDIENTS_LIST[4]));

        // verify
        // nothing to verify at this moment
    }


    @Test
    void getAllShoppingList() throws Exception {
        //arrange
        // nothing to mock at this moment

        // act & assert
        mockMvc.perform(get("/shopping-lists")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("[0].name").value("Stephanie's birthday"))
            .andExpect(jsonPath("[0].shoppingListId").value("90689338-499a-4c49-af90-f1e73068ad4f"))
            .andExpect(jsonPath("[0].ingredients.size())").value(5))
            .andExpect(jsonPath("[0].ingredients[0]").value(INGREDIENTS_LIST[0]))
            .andExpect(jsonPath("[0].ingredients[1]").value(INGREDIENTS_LIST[1]))
            .andExpect(jsonPath("[0].ingredients[2]").value(INGREDIENTS_LIST[2]))
            .andExpect(jsonPath("[0].ingredients[3]").value(INGREDIENTS_LIST[3]))
            .andExpect(jsonPath("[0].ingredients[4]").value(INGREDIENTS_LIST[4]))
            .andExpect(jsonPath("[1].name").value("My Birthday"))
            .andExpect(jsonPath("[1].shoppingListId").value("6c7d09c2-8a25-4d54-a979-25ae779d2465"))
            .andExpect(jsonPath("[1].ingredients.size())").value(5))
            .andExpect(jsonPath("[1].ingredients[0]").value(INGREDIENTS_LIST[0]))
            .andExpect(jsonPath("[1].ingredients[1]").value(INGREDIENTS_LIST[1]))
            .andExpect(jsonPath("[1].ingredients[2]").value(INGREDIENTS_LIST[2]))
            .andExpect(jsonPath("[1].ingredients[3]").value(INGREDIENTS_LIST[3]))
            .andExpect(jsonPath("[1].ingredients[4]").value(INGREDIENTS_LIST[4]));

        // verify
        // nothing to verify at this moment
    }

}