package com.ezgroceries.shoppinglist.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shoppinglist.shared.service.CocktailService;
import com.ezgroceries.shoppinglist.web.cocktails.CocktailController;
import com.ezgroceries.shoppinglist.web.cocktails.CocktailResource;
import java.util.List;
import javax.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Gert
 * @since release 202008 / (2020-06-02)
 */

@WebMvcTest(CocktailController.class)
@ContextConfiguration(classes = CocktailControllerTestConfiguration.class)
class CocktailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailService cocktailService;

    @Resource
    List<CocktailResource> resultList;


    @BeforeEach
    void setUp() {
    }

    @Test
    void SearchCocktails() throws Exception {
        //arrange

        given(cocktailService.getCocktails("Russian"))
            .willReturn(resultList);

        // act & assert

        mockMvc.perform(get("/cocktails?search=Russian"))
            .andDo(print())
            .andExpect(status().is(200))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // verify
        // nothing to verify at this moment

    }
}

