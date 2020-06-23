package com.ezgroceries.shoppinglist.web.cocktails;

import com.ezgroceries.shoppinglist.shared.service.CocktailService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gert
 * @since release 202008 / (2020-06-02)
 */
@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;

    @GetMapping
    public List<CocktailResource> SearchCocktails(@RequestParam String search) {
        return new ArrayList<>(cocktailService.getCocktails(search));
        //return cocktailService.getDummyResources();
        //return new ArrayList<>(getDummyResources());
    }

 /*   private List<CocktailResource> getDummyResources() {
        return Arrays.asList(
            new CocktailResource(
                UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), "Margerita",
                "CocktailEntity glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt")),
            new CocktailResource(
                UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita",
                "CocktailEntity glass",
                "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt")));
    }*/
}