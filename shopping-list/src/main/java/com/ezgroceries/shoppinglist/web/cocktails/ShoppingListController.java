package com.ezgroceries.shoppinglist.web.cocktails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gert
 * @since release 202008 / (2020-06-02)
 */
@RestController
public class ShoppingListController {


    @PostMapping(value = "/shopping-lists", produces = "application/json")
    public ResponseEntity<ShoppingListResource> createShoppingList(@RequestBody ShoppingListRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ShoppingListResource(request.getName()));
    }

    @PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails", produces = "application/json")
    public ResponseEntity<List<CocktailId>> addCocktailsToShoppingList(@PathVariable String shoppingListId,
        @RequestBody ArrayList<CocktailId> cocktailsList) {
        /*  ToDo 01/06/2020 validate existence shoppingListId and  process for each cocktail in cocktailsList
                                    validate cocktailId and Add cocktail to shoppingList
        */
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(cocktailsList);

    }

    @GetMapping(value = "/shopping-lists/{shoppingListId}", produces = "application/json")
    public ShoppingIngredientsList getShoppingList(@PathVariable String shoppingListId) {

        return
            new ShoppingIngredientsList(
                UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f"),
                "Stephanie's birthday",
                Arrays.asList("Tequila",
                    "Triple sec",
                    "Lime juice",
                    "Salt",
                    "Blue Curacao"));
    }

    @GetMapping(value = "/shopping-lists", produces = "application/json")
    public List<ShoppingIngredientsList> getAllShoppingList() {

        return Arrays.asList(
            new ShoppingIngredientsList(
                UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f"),
                "Stephanie's birthday",
                Arrays.asList("Tequila",
                    "Triple sec",
                    "Lime juice",
                    "Salt",
                    "Blue Curacao")),
            new ShoppingIngredientsList(
                UUID.fromString("6c7d09c2-8a25-4d54-a979-25ae779d2465"),
                "My Birthday",
                Arrays.asList("Tequila",
                    "Triple sec",
                    "Lime juice",
                    "Salt",
                    "Blue Curacao")));

    }
}
