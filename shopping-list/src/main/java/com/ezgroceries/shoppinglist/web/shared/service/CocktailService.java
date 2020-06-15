package com.ezgroceries.shoppinglist.web.shared.service;

import com.ezgroceries.shoppinglist.web.cocktails.CocktailResource;
import com.ezgroceries.shoppinglist.web.shared.internal.cocktail.CocktailEntity;
import com.ezgroceries.shoppinglist.web.shared.internal.cocktail.CocktailRepository;
import com.ezgroceries.shoppinglist.web.shared.service.CocktailDBResponse.DrinkResource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
public class CocktailService {

    private final CocktailDBClient cocktailDBClient;
    private final CocktailRepository cocktailRepository;

    public List<CocktailResource> getDummyResources() {
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
    }

    public List<CocktailResource> getCocktails(String search) {

        return mergeCocktails(cocktailDBClient.searchCocktails(search).getDrinks());

        //  CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
        //  List<CocktailResource> list = new ArrayList<>();
        //  list = cocktailDBResponse.getDrinks().stream()
        //      .map(this::map)
        //      .collect(Collectors.toList());
        //  return list;
    }

    private CocktailResource map(DrinkResource drinkResource) {
        return CocktailResource.builder()
            .cocktailId(UUID.nameUUIDFromBytes(drinkResource.getIdDrink().getBytes(StandardCharsets.UTF_8)))
            .name(drinkResource.getStrDrink())
            .container(drinkResource.getStrGlass())
            .ingredients(drinkResource.getAllIngredients())
            .picture(drinkResource.getStrDrinkThumb())
            .instructions(drinkResource.getStrInstructions())
            .build();
    }

    public List<CocktailResource> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
        //Get all the idDrink attributes
        List<String> ids = drinks.stream().map(CocktailDBResponse.DrinkResource::getIdDrink).collect(Collectors.toList());

        //Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, CocktailEntity> existingEntityMap = cocktailRepository.findByIdDrinkIn(ids).stream()
            .collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        Map<String, CocktailEntity> allEntityMap = drinks.stream().map(drinkResource -> {
            CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
            if (cocktailEntity == null) {
                CocktailEntity newCocktailEntity = new CocktailEntity();
                newCocktailEntity.setId(UUID.randomUUID());
                newCocktailEntity.setIdDrink(drinkResource.getIdDrink());
                newCocktailEntity.setName(drinkResource.getStrDrink());
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<CocktailResource> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailEntity> allEntityMap) {
        return drinks.stream().map(
            drinkResource -> new CocktailResource(allEntityMap.get(drinkResource.getIdDrink()).getId(), drinkResource.getStrDrink(),
                drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), getIngredients(drinkResource))).collect(Collectors.toList());
    }

    private List<String> getIngredients(DrinkResource drinkResource) {
        return drinkResource.getAllIngredients();
    }
}