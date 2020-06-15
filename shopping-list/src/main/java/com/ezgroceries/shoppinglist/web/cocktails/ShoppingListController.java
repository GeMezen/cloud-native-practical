package com.ezgroceries.shoppinglist.web.cocktails;

import com.ezgroceries.shoppinglist.web.shared.service.ShoppingListService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gert
 * @since release 202008 / (2020-06-02)
 */
@RestController
@RequestMapping(produces = "application/json")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService service;

    @PostMapping(value = "/shopping-lists")
    public ResponseEntity<ShoppingListResource> createShoppingList(@RequestBody ShoppingListRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.create(request));
    }

    @PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails")
    public ResponseEntity<List<CocktailId>> addCocktailsToShoppingList(@PathVariable String shoppingListId,
        @RequestBody ArrayList<CocktailId> cocktailsList) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.addCocktails(shoppingListId, cocktailsList));
    }

    @GetMapping(value = "/shopping-lists/{shoppingListId}")
    public ShoppingIngredientsList getShoppingList(@PathVariable String shoppingListId) {
        return service.getShoppingList(shoppingListId);
    }

    @GetMapping(value = "/shopping-lists", produces = "application/json")
    public List<ShoppingIngredientsList> getAllShoppingList() {
        return service.getAllShoppingList();
    }
}
