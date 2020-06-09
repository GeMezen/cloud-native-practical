package com.ezgroceries.shoppinglist.web.shared.service;

import com.ezgroceries.shoppinglist.web.cocktails.CocktailId;
import com.ezgroceries.shoppinglist.web.cocktails.ShoppingIngredientsList;
import com.ezgroceries.shoppinglist.web.cocktails.ShoppingListRequest;
import com.ezgroceries.shoppinglist.web.cocktails.ShoppingListResource;
import com.ezgroceries.shoppinglist.web.shared.internal.shopping_list.ShoppingListRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Gert
 * @since release 202008 / (2020-06-09)
 */
@Service
@RequiredArgsConstructor
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListResource create(ShoppingListRequest request) {
        return new ShoppingListResource(request.getName());
    }

    public ArrayList<CocktailId> addCocktails(String shoppingListId, ArrayList<CocktailId> cocktailsList) {
        /*  ToDo 01/06/2020 validate existence shoppingListId and  process for each cocktail in cocktailsList
                                    validate cocktailId and Add cocktail to shoppingList
        */
        return cocktailsList;  // temporary Solution to return some dummy data
    }

    public ShoppingIngredientsList getShoppingList(String shoppingListId) {

        // temporary Solution to return some dummy data
        return new ShoppingIngredientsList(
            UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f"),
            "Stephanie's birthday",
            Arrays.asList("Tequila",
                "Triple sec",
                "Lime juice",
                "Salt",
                "Blue Curacao"));

    }

    public List<ShoppingIngredientsList> getAllShoppingList() {

        // temporary Solution to return some dummy data
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
