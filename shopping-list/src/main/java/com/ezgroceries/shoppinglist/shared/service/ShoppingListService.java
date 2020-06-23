package com.ezgroceries.shoppinglist.shared.service;

import com.ezgroceries.shoppinglist.shared.persistence.cocktail.CocktailEntity;
import com.ezgroceries.shoppinglist.shared.persistence.cocktail.CocktailRepository;
import com.ezgroceries.shoppinglist.shared.persistence.cocktail_shopping_list.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.shared.persistence.cocktail_shopping_list.CocktailShoppingListRepository;
import com.ezgroceries.shoppinglist.shared.persistence.shopping_list.ShoppingListEntity;
import com.ezgroceries.shoppinglist.shared.persistence.shopping_list.ShoppingListRepository;
import com.ezgroceries.shoppinglist.web.shoppingList.CocktailId;
import com.ezgroceries.shoppinglist.web.shoppingList.ShoppingIngredientsList;
import com.ezgroceries.shoppinglist.web.shoppingList.ShoppingListRequest;
import com.ezgroceries.shoppinglist.web.shoppingList.ShoppingListResource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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
    private final CocktailShoppingListRepository cocktailShoppingListRepository;
    private final CocktailRepository cocktailRepository;

    /*
        Create a new Shopping List
         Refactor the ShoppingListController to have an autowired reference to the ShoppingListService
         and invoke the appropriate service layer method.
         Replace the dummy response resources with the actual result of the creation.
    */
    public ShoppingListResource create(ShoppingListRequest request) {
        ShoppingListEntity entity = shoppingListRepository.save(ShoppingListEntity.builder().name(request.getName()).build());
        return ShoppingListResource.builder().name(entity.getName()).shoppingListId(entity.getId().toString()).build();
    }

    /*
        Add Cocktails to Shopping List
        Replace the dummy resources and provide a real persisted implementation.
        This will include a service layer that will take care of linking cocktails with a specific shopping list.
    */
    public ArrayList<CocktailId> addCocktails(String shoppingListId, ArrayList<CocktailId> cocktailsList) {

        //  retrieve shoppingList
        UUID shoppingListUuid = getUuidfromString(shoppingListId);
        ShoppingListEntity entity = shoppingListRepository.findShoppingListEntityById(shoppingListUuid);

        // add cocktails to this list
        cocktailsList.stream().map(cocktailId -> getUuidfromString(cocktailId.getCocktailId()))
            .map(cocktailUuid -> CocktailShoppingListEntity.builder().shoppingListId(shoppingListUuid).cocktailId(cocktailUuid).build())
            .forEach(cocktailShoppingListRepository::save);

        // return the list of cocktails
        return cocktailsList;
    }

    private UUID getUuidfromString(String cocktailId2) {
        return UUID.nameUUIDFromBytes(cocktailId2.getBytes(StandardCharsets.UTF_8));
    }

    /*
        Get a Shopping List
        Replace the dummy resources and provide a real persisted implementation.
        This is also the API where most of our business value is going to happen!
        Implement the logic to retrieve all the Cocktails of the specific Shopping List
         and extract the distinct ingredients to include them in the response body.
    */
    public ShoppingIngredientsList getShoppingList(String shoppingListId) {

        //  retrieve shopping ListName
        ShoppingListEntity shoppingListEntity = shoppingListRepository.findShoppingListEntityById(getUuidfromString(shoppingListId));

        //  retrieve all cocktails from this  shoppingList
        List<CocktailShoppingListEntity> cocktailList = cocktailShoppingListRepository.findAllByShoppingListId(getUuidfromString(shoppingListId));

        //  retrieve all ingredients form those cocktails
        List<CocktailEntity> cocktailEntityList = cocktailRepository
            .findAllByIdIn(cocktailList.stream().map(i -> i.getCocktailId().toString()).collect(
                Collectors.toList()));

        // build and return
        List<String> allIngredients = new ArrayList<>();
        cocktailEntityList.stream().map(CocktailEntity::getIngredients).forEach(ingredients -> ingredients.forEach(allIngredients::add));

        return ShoppingIngredientsList.builder()
            .shoppingListId(getUuidfromString(shoppingListId))
            .name(shoppingListEntity.getName())
            .ingredients(allIngredients)
            .build();

        // temporary Solution to return some dummy data
 /*           (
            UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f"),
            "Stephanie's birthday",
            Arrays.asList("Tequila",
                "Triple sec",
                "Lime juice",
                "Salt",
                "Blue Curacao"));
*/
    }

    public List<ShoppingIngredientsList> getAllShoppingList() {

        /*
        Get all Shopping Lists
        Replace the dummy resources and provide a real persisted implementation.
        */
        List<ShoppingIngredientsList> allShoppingLists = shoppingListRepository.findAll().stream()
            .map(list -> getShoppingList(list.getId().toString())).collect(Collectors.toList());

        return allShoppingLists;

        // temporary Solution to return some dummy data
  /*      return Arrays.asList(
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

    */
    }
}

