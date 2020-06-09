package com.ezgroceries.shoppinglist.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shoppinglist.web.cocktails.CocktailController;
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
@WebMvcTest(CocktailController.class)
class CocktailControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
    }

    @Test
    void SearchCocktails() throws Exception {
        //arrange
        // nothing to mock at this moment
        // act & assert

        mockMvc.perform(get("/cocktails?search=Russian"))
            .andDo(print())
            .andExpect(status().is(200))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // verify
        // nothing to verify at this moment

    }
}
